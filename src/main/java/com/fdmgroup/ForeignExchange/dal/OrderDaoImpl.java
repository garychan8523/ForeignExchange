package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.Order;
import com.fdmgroup.ForeignExchange.entities.Order.Status;
import com.fdmgroup.ForeignExchange.entities.Order.Type;
import com.fdmgroup.ForeignExchange.entities.User;

@Component
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private EntityManagerFactory emf;

	public OrderDaoImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public OrderDaoImpl(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	@Override
	public int addOrder(Order order) {
		EntityManager em;
		EntityTransaction et;

		try {
			em = getEntityManager();
			et = em.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		try {
			et.begin();
			em.persist(order);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrderListByUserStatus(User user, Status status) {
		EntityManager em;
		Query query;
		List<Order> orderList = null;

		try {
			em = getEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			return orderList;
		}

		try {
			query = em.createQuery("SELECT o from Order o WHERE o.user = :user AND status = :status", Order.class);
			query.setParameter("user", user);
			query.setParameter("status", status);
			orderList = (List<Order>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return orderList;
		} finally {
			em.close();
		}
		return orderList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getActiveOrderListByTypeCurrency(Type type, Currency currencyBuy, Currency currencySell,
			Long limit) {
		EntityManager em;
		List<Order> orderList = null;

		try {
			em = getEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			return orderList;
		}

		try {
			Query query = em.createQuery(
					"SELECT o from Order o WHERE o.type = :type and o.currencyBuy = :currencyBuy and o.currencySell = :currencySell and rownum <= :limit",
					Order.class);
			query.setParameter("type", type);
			query.setParameter("currencyBuy", currencyBuy);
			query.setParameter("currencySell", currencySell);
			query.setParameter("limit", limit);
			orderList = (List<Order>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return orderList;
		} finally {
			em.close();
		}
		return orderList;
	}

	@Override
	public int updateOrder(Order order) {
		EntityManager em;
		EntityTransaction et;
		Order existingOrder;

		try {
			em = getEntityManager();
			et = em.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		try {
			existingOrder = em.find(Order.class, order.getOrderId());
			et.begin();
			existingOrder.setCurrentAmount(order.getCurrentAmount());
			existingOrder.setStatus(order.getStatus());
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
		return 1;
	}

}
