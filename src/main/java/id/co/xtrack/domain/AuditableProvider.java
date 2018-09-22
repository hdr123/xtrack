//package id.co.xtrack.domain;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.MappedSuperclass;
//
//import id.co.xtrack.dao.listener.CustomAuditableListener;
//
//@MappedSuperclass
//@EntityListeners(value = CustomAuditableListener.class)
//public abstract class AuditableProvider implements Serializable{
//
//	private static final long serialVersionUID = 1L;
//
//	@ManyToOne
//	@JoinColumn(name = "createdby", referencedColumnName = "username", nullable = true)
//	private User createdBy;
//
//	@Column(name = "createddate", nullable = false)
//	private Date created;
//
//	@ManyToOne
//	@JoinColumn(name = "modifiedby", referencedColumnName = "username", nullable = true)
//	private User modifiedBy;
//
//	@Column(name = "modifieddate", nullable = false)
//	private Date modified;
//
//	public User getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(User created) {
//		this.createdBy = created;
//	}
//
//	public Date getCreated() {
//		return created;
//	}
//
//	public void setCreated(Date created) {
//		this.created = created;
//	}
//
//	public User getModifiedBy() {
//		return modifiedBy;
//	}
//
//	public void setModifiedBy(User modifiedBy) {
//		this.modifiedBy = modifiedBy;
//	}
//
//	public Date getModified() {
//		return modified;
//	}
//
//	public void setModified(Date modified) {
//		this.modified = modified;
//	}
//}
