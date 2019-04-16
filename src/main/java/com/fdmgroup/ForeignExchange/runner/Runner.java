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

//		TradeDaoImpl tDao = new TradeDaoImpl(emf);
		UserDaoImpl uDao = new UserDaoImpl(emf);
//		User user = uDao.getUser("1");
//		System.out.println(user);
//		CurrencyDaoImpl cd = new CurrencyDaoImpl(emf);
//		Currency c = cd.getCurrency("1");
//		System.out.println(tDao.getTradeListByUserCurrency(user, c));
		User user = new User();
		user.setUserName("4");
		user.setPassword("4");
		user.setEmail("4");
		System.out.println(uDao.addUser(user));
		emf.close();
	}
}
