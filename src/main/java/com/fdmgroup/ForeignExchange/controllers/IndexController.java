package com.fdmgroup.ForeignExchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fdmgroup.ForeignExchange.dal.CurrencyDaoImpl;
import com.fdmgroup.ForeignExchange.dal.OrderDaoImpl;
import com.fdmgroup.ForeignExchange.dal.TradeDaoImpl;
import com.fdmgroup.ForeignExchange.entities.Order;
import com.fdmgroup.ForeignExchange.entities.User;

@Controller
public class IndexController {
	
	@Autowired
	private TradeDaoImpl tradeDaoImpl;
	@Autowired
	private OrderDaoImpl orderDaoImpl;
	@Autowired
	private CurrencyDaoImpl currencyDaoImpl;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String renderHome(@ModelAttribute(value = "login_user") User loginFormBlankUser,
			@ModelAttribute(value = "signup_user") User signupFormBlankUser, Model model) {
		
		model.addAttribute("tradeList", tradeDaoImpl.getTradeList(10L));
		model.addAttribute("activeBuyOrder",orderDaoImpl.getActiveOrderListByTypeCurrency(Order.Type.BUY, currencyDaoImpl.getCurrency("HKD"), currencyDaoImpl.getCurrency("USD"), 10L));
		model.addAttribute("activeSellOrder",orderDaoImpl.getActiveOrderListByTypeCurrency(Order.Type.SELL, currencyDaoImpl.getCurrency("EUR"), currencyDaoImpl.getCurrency("GBP"), 10L));
		model.addAttribute("fromurl", "/");
		return "index";
	}

}
