package com.fdmgroup.ForeignExchange.controllers;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ForeignExchange.entities.User;
import com.fdmgroup.ForeignExchange.services.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String authenticate(HttpSession session, @ModelAttribute(value = "signup_user") User signupFormUser, @ModelAttribute(value = "login_user") User loginFormUser,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "frompage", required = false) String frompage, Model model) throws IOException {
		
		if (logout != null) {
			session.removeAttribute("username");

			if (frompage != null) {
				return "redirect:" + frompage;
			} else {
				model.addAttribute("msg", "Logged out");
				model.addAttribute("fromuri", frompage);
				return "index";
			}
		} else {
			User authUser = null;
			String username = loginFormUser.getUserName();
			String password = loginFormUser.getPassword();

			try {
				authUser = userService.authenticate(username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (authUser == null) {
				model.addAttribute("msg", "User not found");
				model.addAttribute("fromuri", frompage);
				if (frompage != null) {
					return "redirect:" + frompage;
				} else {
					return "index";
				}
			} else {

				session.setAttribute("username", authUser.getUserName());

				if (frompage != null) {
					return "redirect:" + frompage;
				} else {
					model.addAttribute("msg", "Welcome " + authUser.getUserName());
					model.addAttribute("fromuri", frompage);
					return "index";
				}
			}
		}
	}
	
}

