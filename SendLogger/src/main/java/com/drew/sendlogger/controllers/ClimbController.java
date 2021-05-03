package com.drew.sendlogger.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drew.sendlogger.models.Climb;
import com.drew.sendlogger.models.User;
import com.drew.sendlogger.services.ClimbService;
import com.drew.sendlogger.services.UserService;

@Controller
@RequestMapping("/climbs")
public class ClimbController {
	private UserService userServ;
	private ClimbService climbServ;
	
	public ClimbController(UserService userServ, ClimbService climbServ) {
		this.userServ = userServ;
		this.climbServ = climbServ;
	}
	
	@GetMapping("")
	public String climbs(HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		session.setAttribute("userObj", userServ.findUserById((long) session.getAttribute("user")));
		model.addAttribute("climbs", climbServ.findAllClimbs());
		return "dashboard.jsp";
	}
	
	@GetMapping("/new")
	public String newClimbForm(@ModelAttribute("creClimb") Climb climb) {
		return "newClimb.jsp";
	}
	
	@PostMapping("/new")
	public String newClimbSub(@Valid @ModelAttribute("creClimb") Climb climb, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "newClimb.jsp";
		}
		long user_id = (long) session.getAttribute("user");
		User creator = userServ.findUserById(user_id);
		climb.setUser(creator);
		climbServ.createClimb(climb);
		return "redirect:/climbs";
	}
	
	@PostMapping("/like/{id}")
	public String like(@PathVariable("id") long id, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		Climb voted = climbServ.findClimbById(id);
		long user_id = (long) session.getAttribute("user");
		User fullUser = userServ.findUserById(user_id);
		
		List<User> listUser = voted.getUsers();
		
		if(!listUser.contains(fullUser)){
			listUser.add(fullUser);
			voted.setUsers(listUser);
			climbServ.updateClimb(voted);
		}
		
		return "redirect:/climbs";
	}
	
	@PostMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") long id, HttpSession session) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		
		Climb voted = climbServ.findClimbById(id);
		long user_id = (long) session.getAttribute("user");
		User fullUser = userServ.findUserById(user_id);
		
		List<User> listUser = voted.getUsers();
		
		if(listUser.contains(fullUser)){
			listUser.remove(fullUser);
			voted.setUsers(listUser);
			climbServ.updateClimb(voted);
		}
		return "redirect:/climbs";
	}
	
	@GetMapping("/{id}")
	public String info(@PathVariable("id") long id, HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		
		Climb climbObj = climbServ.findClimbById(id);
		User creator = userServ.findUserById((long) session.getAttribute("user"));
		if(creator == climbObj.getUser()) {
			boolean validUser = true;
			model.addAttribute("isUser", validUser);
		} else {
			boolean validUser = false;
			model.addAttribute("isUser", validUser);
		}
		model.addAttribute("climb", climbObj);
		
		return "climbInfo.jsp";
	}
	
	@GetMapping("/edit/{id}")
	public String showEdit(@Valid @ModelAttribute("editClimb") Climb climb, BindingResult result, @PathVariable("id") long id, HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		Climb climbObj = climbServ.findClimbById(id);
		User creator = climbObj.getUser();
		User sessUser = userServ.findUserById((long) session.getAttribute("user"));
		if(creator != sessUser) {
			return "redirect:/climbs/" + id;
		}
		
		model.addAttribute("climb", climbServ.findClimbById(id));
		return "editClimb.jsp";
	}
	
	@PostMapping("/edit/{id}")
	public String edit(@Valid @ModelAttribute("editClimb") Climb climb, BindingResult result, @PathVariable("id") long id, HttpSession session, Model model) {
		if(session.getAttribute("user") == null) {
			return "redirect:/users";
		}
		
		if(result.hasErrors()) {
			return "editClimb.jsp";
		}
		
		Climb editClimb = climbServ.findClimbById(id);
		
		if(userServ.findUserById((long) session.getAttribute("user")).equals(editClimb.getUser())) {
			editClimb.setClimb(climb.getClimb());
			climbServ.updateClimb(editClimb);
		}
		return "redirect:/climbs/" + id;
		
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		climbServ.deleteClimbById(id);
		return "redirect:/climbs";
	}

}