package com.fdmgroup.ForeignExchange.runner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.fdmgroup.ForeignExchange.dal.CurrencyDaoImpl;
import com.fdmgroup.ForeignExchange.dal.TradeDaoImpl;
import com.fdmgroup.ForeignExchange.dal.UserDaoImpl;
import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.User;

public class Runner {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("foreign_exchange_config");

		// TradeDaoImpl tDao = new TradeDaoImpl(emf);
		// UserDaoImpl uDao = new UserDaoImpl(emf);
		// User user = uDao.getUser("1");
		// System.out.println(user);
		CurrencyDaoImpl cdao = new CurrencyDaoImpl(emf);
		// Currency c = cd.getCurrency("1");
		// System.out.println(tDao.getTradeListByUserCurrency(user, c));
		// User user = new User();
		// user.setUserName("4");
		// user.setPassword("4");
		// user.setEmail("4");
		// System.out.println(uDao.addUser(user));
		Currency c1 = new Currency();
		c1.setCurrencyCode("HKD");
		c1.setCurrencyName("Hong Kong Dollar");
		c1.setSymbol("$");
		cdao.addCurrency(c1);
		Currency c2 = new Currency();
		c2.setCurrencyCode("USD");
		c2.setCurrencyName("US Dollar");
		c2.setSymbol("$");
		cdao.addCurrency(c2);
		Currency c3 = new Currency();
		c3.setCurrencyCode("EUR");
		c3.setCurrencyName("Euro");
		c3.setSymbol("€");
		cdao.addCurrency(c3);
		Currency c4 = new Currency();
		c4.setCurrencyCode("GBP");
		c4.setCurrencyName("British Pound");
		c4.setSymbol("£");
		cdao.addCurrency(c4);
		Currency c5 = new Currency();
		c5.setCurrencyCode("CNY");
		c5.setCurrencyName("Chinese Yuan Renminbi");
		c5.setSymbol("¥");
		cdao.addCurrency(c5);
		Currency c6 = new Currency();
		c6.setCurrencyCode("JPY");
		c6.setCurrencyName("Japanese Yen");
		c6.setSymbol("¥");
		cdao.addCurrency(c6);
		Currency c7 = new Currency();
		c7.setCurrencyCode("AUD");
		c7.setCurrencyName("Australian Dollar");
		c7.setSymbol("$");
		cdao.addCurrency(c7);
		Currency c8 = new Currency();
		c8.setCurrencyCode("CHF");
		c8.setCurrencyName("Swiss Franc");
		c8.setSymbol("CHF");
		cdao.addCurrency(c8);
		Currency c9 = new Currency();
		c9.setCurrencyCode("CAD");
		c9.setCurrencyName("Canadian Dollar");
		c9.setSymbol("$");
		cdao.addCurrency(c9);
		Currency c10 = new Currency();
		c10.setCurrencyCode("SGD");
		c10.setCurrencyName("Singapore Dollar");
		c10.setSymbol("$");
		cdao.addCurrency(c10);
		emf.close();
	}
}
