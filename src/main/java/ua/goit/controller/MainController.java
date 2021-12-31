package ua.goit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Value("${myconfig.welcome.message}")
	private String welcomeMsg;
	@Value("${myconfig.welcome.img}")
	private String welcomeImg;

	@GetMapping("/")
	public String mainPage(Authentication authentication,
						   HttpServletResponse response,
						   HttpServletRequest request,
						   Map<String, Object> model) {
		model.put("userName", authentication.getName());
		model.put("welcomeMsg", welcomeMsg);
		model.put("welcomeImg", welcomeImg);
		return "main";
	}

}
