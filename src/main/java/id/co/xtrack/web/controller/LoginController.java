package id.co.xtrack.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

	Logger logger  = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(path = "/login.html", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(path = "/login-error.html", method = RequestMethod.GET)
	public String loginRedirectFailure(HttpServletRequest request, Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
	@RequestMapping(path = "/login-error.html", method = RequestMethod.POST)
	public String loginForwardFailure(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(path = "/403.html", method = RequestMethod.GET)
	public String forbidden() {
		return "/error/403";
	}
}
