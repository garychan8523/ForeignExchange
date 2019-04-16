package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import com.fdmgroup.ForeignExchange.entities.Currency;

public interface CurrencyDao {

	public int addCurrency(Currency currency);

	public Currency getCurrency(String currencyName);

	public List<Currency> getCurrencyList();

}
