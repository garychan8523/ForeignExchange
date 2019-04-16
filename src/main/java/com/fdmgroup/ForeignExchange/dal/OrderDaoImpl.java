package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.ForeignExchange.entities.Order;
import com.fdmgroup.ForeignExchange.entities.Order.Status;
import com.fdmgroup.ForeignExchange.entities.Order.Type;
import com.fdmgroup.ForeignExchange.entities.User;

public class OrderDaoImpl implements OrderDao {
	@Autowired
	private EntityManagerFactory emf;

	public OrderDaoImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public int addOrder(Order order) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(order);
			et.commit();
		} catch (IllegalStateException | EntityExistsException | RollbackException | IllegalArgumentException
				| TransactionRequiredException e) {
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
		List<Order> orderList = null;
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT o from Order o WHERE o.USERID = :userID AND status = :status",
				Order.class);
		query.setParameter("userId", user.getUserId());
		query.setParameter("status", status);
		try {
			orderList = (List<Order>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return orderList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getActiveOrderList(Type type, int limit) {
		List<Order> orderList = null;
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT o from Order o WHERE o.type = :type AND rownum <= :limit", Order.class);
		query.setParameter("type", type);
		query.setParameter("limit", limit);
		try {
			orderList = (List<Order>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return orderList;
	}

	@Override
	public int updateOrder(Order order) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		Order existingOrder = em.find(Order.class, order.getOrderId());
		try {
			et.begin();
			existingOrder.setCurrentAmount(order.getCurrentAmount());
			existingOrder.setStatus(order.getStatus());
			et.commit();
		} catch (IllegalStateException | RollbackException e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
		return 1;
	}

}
