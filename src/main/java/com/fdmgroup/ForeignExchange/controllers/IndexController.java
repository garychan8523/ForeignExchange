package com.fdmgroup.ForeignExchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.ForeignExchange.entities.User;
import com.fdmgroup.ForeignExchange.services.IndexService;

@Controller
public class IndexController {
	@Autowired
	private IndexService indexService;

	@RequestMapping(value = "/")
	public String goToIndex(@ModelAttribute(value = "login_user") User login_form_blank_user, @ModelAttribute(value = "sign_user") User signupUser) {
		return "index";
	}

}
