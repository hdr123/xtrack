package id.co.xtrack.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="co_user")
public class User extends BaseDomain{

	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "co_user_id")
	private String id;
	private String username;
	private String password;
//	private Date lastResetPasswordDate;
	private String email;
	private Boolean locked = false;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public Date getLastResetPasswordDate() {
//		return lastResetPasswordDate;
//	}
//	public void setLastResetPasswordDate(Date lastResetPasswordDate) {
//		this.lastResetPasswordDate = lastResetPasswordDate;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public Boolean isLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	
	

}
