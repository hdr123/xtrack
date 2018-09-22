package id.co.xtrack.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseDomain  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract String getId();
	public abstract void setId(String id);
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[id=").append(getId()).append("]");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null && this.getClass().isAssignableFrom(obj.getClass())) {
			result = getId() != null && Objects.equals(getId(), ((BaseDomain) obj).getId());
		}
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClass().getName());
	}
	
}
