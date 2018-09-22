//package id.co.xtrack.dao.listener;
//
//import java.io.Serializable;
//import java.util.Calendar;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import id.co.xtrack.domain.AuditableProvider;
//import id.co.xtrack.domain.User;
//
//
//@Component
//public class CustomAuditableListener implements Serializable{
//	
//	private static final long serialVersionUID = 1L;
//
//	@PrePersist
//	public void touchForCreate(AuditableProvider am){
//		Calendar c = Calendar.getInstance();
//		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
//		User user = null;
//		if (currentUser != null && currentUser.getPrincipal() != null
//				&& currentUser.getPrincipal() instanceof UserDetailsWrapper) {
//			user = ((UserDetailsWrapper) currentUser.getPrincipal()).getUserAccount();
//		}
//		// creating
//		if (am.getCreated() == null) {
//			am.setCreated(c.getTime());
//		}
//
//		if (am.getCreatedBy() == null) {
//			if (user != null) {
//				am.setCreatedBy(user);
//			}
//		}
//	}
//	
//	
//	@PreUpdate
//	public void touchForUpdate(AuditableProvider am) {
//		Calendar c = Calendar.getInstance();
//		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
//		User user = null;
//		if (currentUser != null && currentUser.getPrincipal() != null
//				&& currentUser.getPrincipal() instanceof UserDetailsWrapper) {
//			user = ((UserDetailsWrapper) currentUser.getPrincipal()).getUserAccount();
//		}
//
//		//updating
//		am.setModified(c.getTime());
//		if (user != null) {
//			am.setModifiedBy(user);
//		}
//
//	}
//}
