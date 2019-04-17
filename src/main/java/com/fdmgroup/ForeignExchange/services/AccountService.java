package com.fdmgroup.ForeignExchange.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.ForeignExchange.dal.AccountDaoImpl;
import com.fdmgroup.ForeignExchange.dal.CurrencyDaoImpl;
import com.fdmgroup.ForeignExchange.entities.Account;
import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.User;

public class AccountService {
	
	@Autowired
	private AccountDaoImpl accountDaoImpl;
	@Autowired
	private CurrencyDaoImpl currencyDaoImpl;

	public int addAccountByUser(User user) {
		Account accountadd = new Account();
		int count = 0;
		
		for (Currency c : currencyDaoImpl.getCurrencyList()) {
			accountadd.setUser(user);
			accountadd.setCurrency(c);
			accountadd.setBalance(0.0d); // initial account balance equals to 0
			count += accountDaoImpl.addAccount(accountadd);
		}
		return count;
	}
	
}
