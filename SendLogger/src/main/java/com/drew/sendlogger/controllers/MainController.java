package com.drew.sendlogger.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("")
	public String dash(HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users/login";
		}
		return "redirect:/climbs";
	}
}
