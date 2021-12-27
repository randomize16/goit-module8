package ua.goit.controller;

import java.util.Map;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String mainPage(Authentication authentication,
						   Map<String, Object> model) {
		model.put("userName", authentication.getName());
		return "main";
	}

}
