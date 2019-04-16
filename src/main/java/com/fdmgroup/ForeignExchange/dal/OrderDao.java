package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import com.fdmgroup.ForeignExchange.entities.Order;
import com.fdmgroup.ForeignExchange.entities.Order.Status;
import com.fdmgroup.ForeignExchange.entities.Order.Type;
import com.fdmgroup.ForeignExchange.entities.User;

public interface OrderDao {

	public void addOrder(Order order);

	public Order getOrder(Long orderId);

	public List<Order> getOrderListByUserStatus(User user, Status status);

	public List<Order> getActiveOrderList(Type type, int limit);

	public void updateOrder(Order order);

}
