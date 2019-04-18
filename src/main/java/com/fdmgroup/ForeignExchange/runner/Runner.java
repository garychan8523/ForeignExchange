package com.fdmgroup.ForeignExchange.runner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.ForeignExchange.dal.CurrencyDaoImpl;
import com.fdmgroup.ForeignExchange.dal.OrderDaoImpl;
import com.fdmgroup.ForeignExchange.dal.TradeDaoImpl;
import com.fdmgroup.ForeignExchange.dal.UserDaoImpl;
import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.Order;
import com.fdmgroup.ForeignExchange.entities.Trade;
import com.fdmgroup.ForeignExchange.entities.User;
import com.fdmgroup.ForeignExchange.utilities.PasswordUtility;

public class Runner {

	public static void main(String[] args) throws Exception {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("foreign_exchange_config");

		User user;
		Currency currency;
		Order order;
		Trade trade;

		CurrencyDaoImpl currencyDaoImpl;
		UserDaoImpl userDaoImpl;
		TradeDaoImpl tradeDaoImpl;
		OrderDaoImpl orderDaoImpl;

		currencyDaoImpl = new CurrencyDaoImpl(emf);
		userDaoImpl = new UserDaoImpl(emf);
		tradeDaoImpl = new TradeDaoImpl(emf);
		orderDaoImpl = new OrderDaoImpl(emf);

		// initialise currency data
		initializeCurrencyData(emf);

		// add dummy user
		List<String> userList = new ArrayList<String>();
		userList.add("dummy1");
		userList.add("dummy2");
		addDummyUser(emf, userList);

		// add 2 order
		order = new Order();
		order.setUser(userDaoImpl.getUser("dummy1"));
		order.setType(Order.Type.BUY);
		order.setOriginalAmount(150.0D);
		order.setCurrentAmount(100.0D);
		order.setCurrencyBuy(currencyDaoImpl.getCurrency("HKD"));
		order.setCurrencySell(currencyDaoImpl.getCurrency("USD"));
		order.setRate(7.8D);
		order.setStatus(Order.Status.ACTIVE);
		orderDaoImpl.addOrder(order);

		order = new Order();
		order.setUser(userDaoImpl.getUser("dummy2"));
		order.setType(Order.Type.SELL);
		order.setOriginalAmount(200.0D);
		order.setCurrentAmount(100.0D);
		order.setCurrencyBuy(currencyDaoImpl.getCurrency("CAD"));
		order.setCurrencySell(currencyDaoImpl.getCurrency("AUD"));
		order.setRate(12D);
		order.setStatus(Order.Status.ACTIVE);
		orderDaoImpl.addOrder(order);

		// add 1 trade
		trade = new Trade();
		List<Order> orderList = orderDaoImpl.getOrderListByUserStatus(userDaoImpl.getUser("dummy1"),
				Order.Status.ACTIVE);
		trade.setUser(userDaoImpl.getUser("dummy2"));
		trade.setOrder(orderList.get(0));
		trade.setAmount(50.0D);
		tradeDaoImpl.addTrade(trade);

		emf.close();
	}

	public static void initializeCurrencyData(EntityManagerFactory emf) {
		Currency currency;
		CurrencyDaoImpl currencyDaoImpl;
		currencyDaoImpl = new CurrencyDaoImpl(emf);

		currency = new Currency();
		currency.setCurrencyCode("HKD");
		currency.setCurrencyName("Hong Kong Dollar");
		currency.setSymbol("$");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("USD");
		currency.setCurrencyName("US Dollar");
		currency.setSymbol("$");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("EUR");
		currency.setCurrencyName("Euro");
		currency.setSymbol("€");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("GBP");
		currency.setCurrencyName("British Pound");
		currency.setSymbol("£");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("CNY");
		currency.setCurrencyName("Chinese Yuan Renminbi");
		currency.setSymbol("¥");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("JPY");
		currency.setCurrencyName("Japanese Yen");
		currency.setSymbol("¥");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("AUD");
		currency.setCurrencyName("Australian Dollar");
		currency.setSymbol("$");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("CHF");
		currency.setCurrencyName("Swiss Franc");
		currency.setSymbol("CHF");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("CAD");
		currency.setCurrencyName("Canadian Dollar");
		currency.setSymbol("$");
		currencyDaoImpl.addCurrency(currency);

		currency = new Currency();
		currency.setCurrencyCode("SGD");
		currency.setCurrencyName("Singapore Dollar");
		currency.setSymbol("$");
		currencyDaoImpl.addCurrency(currency);
	}

	public static void addDummyUser(EntityManagerFactory emf, List<String> userList) throws Exception {
		User user;
		UserDaoImpl userDaoImpl;

		userDaoImpl = new UserDaoImpl(emf);

		for (String username : userList) {
			user = new User();
			user.setUserName(username);
			user.setPassword(PasswordUtility.getSaltedHash("password"));
			user.setEmail("dummy@dummy.com");
			userDaoImpl.addUser(user);
		}
	}

}
