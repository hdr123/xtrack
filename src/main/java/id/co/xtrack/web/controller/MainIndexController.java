package id.co.xtrack.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainIndexController {

	@GetMapping(path = "/index.html")
	public String index(){
		return "index";
	}
}
