package com.webencyclop.demo.controller;
import com.webencyclop.demo.repository.UserRepository;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.webencyclop.demo.model.User;
import com.webencyclop.demo.service.UserService;


@Controller
public class AuthenticationController {

	private static final String ERROR_MESSAGE = "error1";
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = { "/test/id:{id}" }, method = RequestMethod.GET)
	public <T> T test(@PathVariable("id") int id) {
		System.out.println(id);
		//System.out.println(userRepository.getEmail(id).get(0).getContactEmail());
		try {
			List<String> li = userRepository.findContactEmail(id);
			return (T) li;
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return (T) "no";
		}
		
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("Views/login"); // resources/template/login.html
		return "Views/login";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
//		ModelAndView modelAndView = new ModelAndView();
//		User user = new User();
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("/Views/register"); // resources/template/register.html
		return "/Views/register";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("/Views/home"); // resources/template/home.html
		return "/Views/home";
	}
	@RequestMapping(value = "/rae", method = RequestMethod.GET)
	public String rae() {
		return "/Views/rae";
	}

	
}
