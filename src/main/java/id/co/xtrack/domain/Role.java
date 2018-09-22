package id.co.xtrack.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="co_role")
public class Role extends BaseDomain {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name ="co_role_id")
	private String id;
	private String namaRole;
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<UserRole> roleUsers = new ArrayList<UserRole>();
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public String getNamaRole() {
		return namaRole;
	}

	public void setNamaRole(String namaRole) {
		this.namaRole = namaRole;
	}

	public List<UserRole> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(List<UserRole> roleUsers) {
		this.roleUsers = roleUsers;
	}


	
}
