package id.co.xtrack.config.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import id.co.xtrack.domain.User;
import id.co.xtrack.domain.UserRole;
import id.co.xtrack.repository.RolePrivilegeRepo;
import id.co.xtrack.repository.UserRepo;
import id.co.xtrack.repository.UserRoleRepo;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{

	@Qualifier("messageSource")
	@Autowired
	private MessageSource ms;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(exception instanceof LockedException){
			
		}
		 Map<String, Object> errorLogin = new HashMap<String, Object>();
		 errorLogin.put("message", exception.getMessage());
//		 String username = request.getParameter("username");
//		 User userExist = userRepo.findByUsername(username);
//		 if(userExist != null){
//			
//			 
//			 String password = request.getParameter("password");
//				if(password != null && !password.isEmpty()){
//					errorLogin.put("user_val", username);
//					errorLogin.put("password_empty", ms.getMessage("error.login.password.empty", null, null));
//				    
//				}else{
//					String pass = passEncoder.encode(password);
//					if (pass.equals(userExist.getPassword())) {
//						if(rolePrivilegeRepo == null){
//							System.out.println("OK");
//						}else{
//
//							System.out.println("NO");
//						}
//						UserRole ur = userRoleRepo.findByUser_Id(userExist.getId());
//						Long countOtoritas = rolePrivilegeRepo.countByRole_Id(ur.getRole().getId());
//						if(countOtoritas == 0){
//							errorLogin.put("message", ms.getMessage("error.login.authorization.notfound", null, null));
//							log.debug("Login gagal.  " + username + " tidak mempunyai otoritas.");
//						}else{
//							errorLogin.put("message", exception.getMessage());
//							log.debug("Login gagal. Password dari akun " + username + " tidak valid.");
//						}
//						
//					}else{
//						errorLogin.put("message", ms.getMessage("error.login.password.invalid", null, null));
//						log.debug("Login gagal. Password dari akun " + username + " tidak valid.");
//					}
//				}
//				
//				errorLogin.put("user_val", username);
//			 
//			
//			
//		 }else{
//			String password = request.getParameter("password");
//			if ((username != null && !username.isEmpty()) && (password != null && !password.isEmpty())) {
//				 errorLogin.put("message", ms.getMessage("error.login.user.notfound", null, null));
//				 errorLogin.put("user_val", username);
//			}
//			if ((username == null || username.isEmpty()) && (password == null || password.isEmpty())) {
//				 errorLogin.put("username_empty", ms.getMessage("error.login.username.empty", null, null));
//				 errorLogin.put("password_empty", ms.getMessage("error.login.password.empty", null, null));		 
//			}
//			if ((username != null && !username.isEmpty()) && (password == null || password.isEmpty())) {
//				 errorLogin.put("user_val", username);
//				 errorLogin.put("password_empty", ms.getMessage("error.login.password.empty", null, null));
//			}
//			if ((username == null || username.isEmpty()) && (password != null && !password.isEmpty())) {
//				 errorLogin.put("username_empty",  ms.getMessage("error.login.username.empty", null, null));
//			}
//		 }
		 request.setAttribute("error_msg",errorLogin);
		
		 
		 
		 //passed request to login-error.html page by RequestDispatcher for processing request
		 //using forward method 
		 RequestDispatcher rd = request.getRequestDispatcher("./login-error.html");
		 rd.forward(request, response);
	}

}
