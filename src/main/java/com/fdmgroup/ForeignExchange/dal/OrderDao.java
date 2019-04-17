package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.Order;
import com.fdmgroup.ForeignExchange.entities.Order.Status;
import com.fdmgroup.ForeignExchange.entities.Order.Type;
import com.fdmgroup.ForeignExchange.entities.User;

public interface OrderDao {

	public int addOrder(Order order);

	public List<Order> getOrderListByUserStatus(User user, Status status);

	public List<Order> getActiveOrderListByTypeCurrency(Type type, Currency currencyBuy,Currency currencySell, Long limit);

	public int updateOrder(Order order);

}
