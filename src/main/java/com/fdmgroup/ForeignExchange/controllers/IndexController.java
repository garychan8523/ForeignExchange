package com.fdmgroup.ForeignExchange.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.ForeignExchange.entities.User;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String renderHome(@ModelAttribute(value = "login_user") User loginFormBlankUser, @ModelAttribute(value = "signup_user") User signupFormBlankUser, Model model) {
		model.addAttribute("fromurl", "/");
		return "index";
	}

}
