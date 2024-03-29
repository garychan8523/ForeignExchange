package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.Trade;
import com.fdmgroup.ForeignExchange.entities.User;

public interface TradeDao {

	public int addTrade(Trade trade);

	public List<Trade> getTradeList(Long limit);

	public List<Trade> getTradeListByUserCurrency(User user, Currency currency);
	
	public List<Trade> getTradeListByCurrencyPair(Currency currencyBuy, Currency currencySell, int limit);
	
}
