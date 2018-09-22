package id.co.xtrack.config.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserDetailsWrapper extends User{

	private static final long serialVersionUID = 1L;
	public static final String ROLES = "roles";
	public static final String CURRENT_ROLE = "currentRole";
	public static final String CURRENT_CLIENT = "currentClient";
	private Map<String, Object> info = new HashMap<String, Object>();
	/*
	 * Karena adanya kebutuhan aplikasi yang harus menampilkan informasi nama atau lainnya
	 * dari user yang telah login, maka harus menyertakan Object UserAccount yang menampung
	 * informasi tsb.
	 * 
	 * Infromasi user tsb tidak boleh diakses public, informasi tsb hanya digunakan
	 * sebagai sarana pendukung bagi deveoper. Oleh karena itu, informasi detail user tsb
	 * tidak perlu ditampilkan ketika di convert ke dalam bentuk JSON 
	 */
	@JsonIgnore
	private id.co.xtrack.domain.User user;
	
	
	
	
	public UserDetailsWrapper(id.co.xtrack.domain.User userAccount, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.user = userAccount;
	}


	public Map<String, Object> getInfo() {
		return info;
	}


	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}
	
	public Object getInfo(Object key){
		return info.get(key);
	}

	public void putInfo(String key, Object value){
		info.put(key, value);
	}


	public id.co.xtrack.domain.User getUserAccount() {
		return user;
	}


	public void setUserAccount(id.co.xtrack.domain.User userAccount) {
		this.user = userAccount;
	}
	
	
	
}
