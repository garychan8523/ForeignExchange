package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import com.fdmgroup.ForeignExchange.entities.Account;
import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.User;

public interface AccountDao {

	public int addAccountByUser(User user);

	public int addAccount(Account account);

	public List<Account> getAccountListByUser(User user);

	public Account getUserCurrencyAccount(User user, Currency currency);

	public int removeAccountsByUser(User user);

	public int removeAccount(Account account);

	public int updateAccount(Account account);
}
