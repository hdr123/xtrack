package id.co.xtrack.config.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import id.co.xtrack.domain.Role;
import id.co.xtrack.domain.RolePrivilege;
import id.co.xtrack.domain.User;
import id.co.xtrack.domain.UserRole;
import id.co.xtrack.repository.RolePrivilegeRepo;
import id.co.xtrack.repository.RoleRepo;
import id.co.xtrack.repository.UserRepo;
import id.co.xtrack.repository.UserRoleRepo;

public class CustomJdbcUserDetailsManager extends JdbcUserDetailsManager {

	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private String userRolesQuery;
	private String authoritiesByUsernameAndRoleQuery;

	public static final String SELECT_USERS_BY_USERNAME_QUERY = "SELECT co_user_id as username, password as password, 'true' as enabled "
			+ "FROM co_user WHERE username = ?";

	// public static final String SELECT_AUTHORITIES_BY_USERNAME_QUERY =
	// "SELECT ur.co_user_id as username, p.kode as authority "
	// + "FROM co_role_privilege rp "
	// + "INNER JOIN co_privilege p on p.co_privilege_id = rp.co_privilege_id "
	// + "INNER JOIN co_user_role ur on ur.co_role_id = rp.co_role_id "
	// + "WHERE ur.co_user_role_id = "
	// + "(SELECT ur.co_user_role_id FROM co_user_role ur WHERE "
	// + "ur.co_user_id=? "
	// + ")";
	//

	public static final String SELECT_AUTHORITIES_BY_USERNAME_AND_ROLE_QUERY = "SELECT co_user_id as username, p.kode as authority "
			+ "FROM co_user_role ur " + "INNER JOIN co_role_privilege rp ON rp.co_role_id = ur.co_role_id "
			+ "INNER JOIN co_privilege p on p.co_privilege_id = rp.co_privilege_id "
			+ "WHERE co_user_id=? AND rp.co_role_id=? ";

	@Override
	public UserDetailsWrapper loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Role> roles = roleRepo.findByRoleUsersUserUsername(username);
		Role defaultRole = !roles.isEmpty() ? roles.get(0) : null;
		logger.debug(String.format("Found defaultRole %s for user %s", defaultRole, username));
		UserDetailsWrapper ud = loadUserByUsername(username, defaultRole);
		return ud;
	}

	public UserDetailsWrapper loadUserByUsername(String username, Role role) throws UsernameNotFoundException {
		List<UserDetails> users = loadUsersByUsername(username);

		if (users.size() == 0) {
			this.logger.debug("Query returned no results for user '" + username + "'");

			throw new UsernameNotFoundException(this.messages.getMessage("JdbcDaoImpl.notFound",
					new Object[] { username }, "Username {0} not found"));
		}

		UserDetails user = users.get(0); // contains no GrantedAuthority[]

		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

		Assert.notNull(role, this.messages.getMessage("error.rolenull", "Peran tidak ditemukan"));
		if (super.getEnableAuthorities()) {
			logger.debug("add otoritas user " + username);
			dbAuthsSet.addAll(loadUserAuthorities(user.getUsername(), role.getId()));
		}

		if (this.getEnableGroups()) {
			logger.debug("add otoritas group for user " + username);
			dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
		}

		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);

		addCustomAuthorities(user.getUsername(), dbAuths);

		if (dbAuths.size() == 0) {
			this.logger.debug("User '" + username + "' with role '" + role.getId()
					+ "' has no authorities and will be treated as 'not found'");

			throw new UsernameNotFoundException(this.messages.getMessage("JdbcDaoImpl.noAuthority",
					new Object[] { username }, "User {0} has no GrantedAuthority"));
		}
		return createUserDetails(username, user, dbAuths, role);
	}

	private List<GrantedAuthority> loadUserAuthorities(String username, String roleId) {
		return getJdbcTemplate().query(this.authoritiesByUsernameAndRoleQuery, new String[] { username, roleId },
				new RowMapper<GrantedAuthority>() {
					@Override
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName = CustomJdbcUserDetailsManager.super.getRolePrefix() + rs.getString(2);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}

	protected UserDetailsWrapper createUserDetails(String username, UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities, Role currentRole) {
		String id = userFromUserQuery.getUsername();
		User user = userRepo.findOne(id);
		String returnUsername = userFromUserQuery.getUsername();

		if (!super.isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}

		logger.debug("create details user " + username);
		UserDetailsWrapper ur = new UserDetailsWrapper(user, returnUsername, userFromUserQuery.getPassword(),
				userFromUserQuery.isEnabled(), true, true, !user.isLocked(), combinedAuthorities);
		ur.putInfo(UserDetailsWrapper.CURRENT_ROLE, currentRole);

		List<Role> roles = roleRepo.findByRoleUsersUserUsername(username);
		ur.putInfo(UserDetailsWrapper.ROLES, roles);

		return ur;
	}

	public void setUserRepo(UserRepo userRepository) {
		this.userRepo = userRepository;
	}

	public void setAuthoritiesByUsernameAndRoleQuery(String authoritiesByUsernameAndRoleQuery) {
		this.authoritiesByUsernameAndRoleQuery = authoritiesByUsernameAndRoleQuery;
	}

	public String getUserRolesQuery() {
		return userRolesQuery;
	}

	public void setUserRolesQuery(String userRolesQuery) {
		this.userRolesQuery = userRolesQuery;
	}

	public void setRoleRepository(RoleRepo roleRepository) {
		this.roleRepo = roleRepository;
	}

	public RoleRepo getRoleRepo() {
		return roleRepo;
	}

}
