package id.co.xtrack.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.co.xtrack.config.security.CustomJdbcUserDetailsManager;
import id.co.xtrack.repository.RoleRepo;
import id.co.xtrack.repository.UserRepo;


/**
 * If you experience instantiation issues (e.g. using JDBC or JPA for the user detail store) it might 
 * be worth extracting the AuthenticationManagerBuilder callback into a GlobalAuthenticationConfigurerAdapter 
 * (in the init() method so it happens before the authentication manager is needed elsewhere).
 * @author Hendra
 *
 */


@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class ConfigurationGlobalAuthenticationManager extends GlobalAuthenticationConfigurerAdapter  {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigurationWebSecurity.class);
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * Recomended encoder password spring security
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
//	/**
//	 * Encoder password SHA-1
//	 * Pada kasus ini developer melakukan enkripsi dengan SHA-1
//	 */
//	@Bean
//	public ShaPasswordEncoder passwordEncoderSHA1() {
//		return new ShaPasswordEncoder(1);
//	}
//	
	
	//Otentikasi user dengan menggunakan teknik JDBC
	@Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("ConfigurationGlobalAuthenticationManager.init: {}", auth);
		
		//developer mengasumsikan bahwa kelas CustomJdbcUserDetailsManager tidak perlu 
		//dipublish ke dalam komponen spring
		CustomJdbcUserDetailsManager customUserDetail = new CustomJdbcUserDetailsManager();
		customUserDetail.setDataSource(dataSource);
		customUserDetail.setUserRepo(userRepo);
		customUserDetail.setRoleRepository(roleRepo);
		customUserDetail.setUsersByUsernameQuery(CustomJdbcUserDetailsManager.SELECT_USERS_BY_USERNAME_QUERY);
//		customUserDetail.setAuthoritiesByUsernameQuery(CustomJdbcUserDetailsManager.SELECT_AUTHORITIES_BY_USERNAME_QUERY);
		customUserDetail.setAuthoritiesByUsernameAndRoleQuery(CustomJdbcUserDetailsManager.SELECT_AUTHORITIES_BY_USERNAME_AND_ROLE_QUERY);
		customUserDetail.setRolePrefix("PRIV_");
		auth.userDetailsService(customUserDetail).passwordEncoder(passwordEncoder);
    }
}
