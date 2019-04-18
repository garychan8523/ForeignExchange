package com.fdmgroup.ForeignExchange.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(value = "/")
	public String renderHome(HttpSession session, 
			@RequestParam(value = "currencyBuyCode", required = false) String currencyBuyCode,
			@RequestParam(value = "currencySellCode", required = false) String currencySellCode,
			@ModelAttribute(value = "login_user") User loginFormBlankUser,
			@ModelAttribute(value = "signup_user") User signupFormBlankUser, Model model) {

		// initialise currency pair for first time user
		if (session.getAttribute("currencyBuyCode") == null) {
			session.setAttribute("currencyBuyCode", "USD");
		}
		if (session.getAttribute("currencySellCode") == null) {
			session.setAttribute("currencySellCode", "HKD");
		}
		
		if (currencyBuyCode != null) {
			session.setAttribute("currencyBuyCode", currencyBuyCode);
		}
		if (currencySellCode != null) {
			session.setAttribute("currencySellCode", currencySellCode);
		}
		if (session.getAttribute("currencyBuyCode").equals(session.getAttribute("currencySellCode"))) {
			model.addAttribute("msg", "please choose different currency pair");
		}
		
		session.setAttribute("currencyList", currencyDaoImpl.getCurrencyList());
		
		model.addAttribute("tradeList", tradeDaoImpl.getTradeList(10L));
		model.addAttribute("activeBuyOrder",
				orderDaoImpl.getActiveOrderListByTypeCurrency(Order.Type.BUY,
						currencyDaoImpl.getCurrency(session.getAttribute("currencyBuyCode").toString()),
						currencyDaoImpl.getCurrency(session.getAttribute("currencySellCode").toString()), 10L));
		model.addAttribute("activeSellOrder",
				orderDaoImpl.getActiveOrderListByTypeCurrency(Order.Type.SELL,
						currencyDaoImpl.getCurrency(session.getAttribute("currencyBuyCode").toString()),
						currencyDaoImpl.getCurrency(session.getAttribute("currencySellCode").toString()), 10L));
		
		model.addAttribute("currencyBuyCode", session.getAttribute("currencyBuyCode"));
		model.addAttribute("currencySellCode", session.getAttribute("currencySellCode"));
		
		model.addAttribute("fromurl", "/");
		return "index";
	}

}
