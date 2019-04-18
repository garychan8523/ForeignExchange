package com.fdmgroup.ForeignExchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ForeignExchange.entities.User;
import com.fdmgroup.ForeignExchange.services.SignupService;

@Controller
public class SignupController {

	@Autowired
	private SignupService signupService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute(value = "signup_user") User signupFormUser,@ModelAttribute(value = "login_user") User loginFormUser, @RequestParam(value = "frompage", required = false) String frompage, Model model) {

		if (signupService.isUserNameTaken(signupFormUser.getUserName())) {
			model.addAttribute("msg", "Name taken");
		} else {
			signupService.registerUser(signupFormUser);
			model.addAttribute("msg", "Registered successfully " + signupFormUser.getUserName());
		}
		
		return "index";
	}
}
