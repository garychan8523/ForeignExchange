package com.fdmgroup.ForeignExchange.dal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.ForeignExchange.entities.User;

@Component
public class UserDaoImpl implements UserDao {

	public UserDaoImpl(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	@Autowired
	private EntityManagerFactory emf;

	public UserDaoImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public int addUser(User user) {
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
			em.persist(user);
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
		return 1;
	}

	@Override
	public User getUser(String username) {
		EntityManager em;
		Query query;
		User user = null;

		try {
			em = getEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		}

		try {
			query = em.createQuery("SELECT u FROM User u where u.userName = :username", User.class);
			query.setParameter("username", username);
			user = (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return user;
		} finally {
			em.close();
		}
		return user;
	}

	@Override
	public int removeUser(String username) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		Query query;
		int rows = 0;

		try {
			et.begin();
			query = em.createQuery("delete from User u where u.username = :username");
			query.setParameter("username", username);
			rows = query.executeUpdate();
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return rows;
		} finally {
			em.close();
		}
		return rows;
	}

	@Override
	public int updateUser(User user) {
		EntityManager em;
		EntityTransaction et;
		User oldUser;

		try {
			em = getEntityManager();
			et = em.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		try {
			oldUser = em.find(User.class, user.getUserId());
			et.begin();
			oldUser.setEmail(user.getEmail());
			oldUser.setPassword(user.getPassword());
			oldUser.setUserName(user.getUserName());
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
		return 1;
	}

	@Override
	public List<User> getUserList() {
		EntityManager em;
		TypedQuery<User> query;
		ArrayList<User> resultList = null;

		try {
			em = getEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		}

		try {
			query = em.createQuery("SELECT u from User u", User.class);
			resultList = (ArrayList<User>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return resultList;
		} finally {
			em.close();
		}
		return resultList;
	}

}
