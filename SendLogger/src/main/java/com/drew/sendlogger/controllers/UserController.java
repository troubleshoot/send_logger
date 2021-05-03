package com.drew.sendlogger.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.drew.sendlogger.models.User;
import com.drew.sendlogger.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private UserService userServ;

	public UserController(UserService userServ) {
		this.userServ = userServ;
	}
	
	@GetMapping("")
	public String dash(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users/login";
		}
		return "redirect:/climbs";
	}
	
	@GetMapping("/registration")
	public String register(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
			return "redirect:/users";
		}
		model.addAttribute("user", user);
		return "registration.jsp";
	}
	
	@PostMapping("/registration")
	public String registerCreate(@Valid @ModelAttribute("user") User user, BindingResult response, Model model) {
		if(response.hasErrors()) {
			return "registration.jsp";
		} else {
			if(!user.getPassword().equals(user.getConfirm())) {
				model.addAttribute("passwordMatchError","Password and Confirmed Password must match.");
				return "registration.jsp";
			} else {
				User exists = userServ.findByEmail(user.getEmail());
				if(exists != null) {
					model.addAttribute("emailMatchError","That Email is already in use.");
					return "registration.jsp";
				} else {
					String pw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
					user.setPassword(pw);
					userServ.createUser(user);
					return "redirect:/users/login";
				}
			}
		}
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
			return "redirect:/events";
		}
		model.addAttribute("user", user);
		return "login.jsp";
	}
	
	@PostMapping("/login")
	public String loginCheck(@RequestParam("email") String email,@RequestParam("password") String password, Model model, HttpSession session) {
//		System.out.println(email.length());
//		System.out.println(password.length());
		if(email.length() < 3) {
			model.addAttribute("loginError","Invalid Credentials.");
			model.addAttribute("user",new User());
			return "login.jsp";
		}
		if(password.length() < 8) {
			model.addAttribute("loginError","Invalid Credentials.");
			return "login.jsp";
		}
		
		User us = userServ.findByEmail(email);
		
		if(us == null) {
			model.addAttribute("loginError","Invalid Credentials!");
			model.addAttribute("user",new User());
			return "login.jsp";	
		} else {
			boolean matches = BCrypt.checkpw(password,us.getPassword());
			
			if(matches) {
				session.setAttribute("user", us.getId());
				return "redirect:/climbs";
			} else {
				model.addAttribute("loginError","Invalid Credentials!");
				model.addAttribute("user",new User());
				return "login.jsp";
			}
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/users/login";
	}
}