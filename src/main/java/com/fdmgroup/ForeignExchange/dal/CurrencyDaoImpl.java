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

import com.fdmgroup.ForeignExchange.entities.Currency;

public class CurrencyDaoImpl implements CurrencyDao {
	@Autowired
	private EntityManagerFactory emf;

	public CurrencyDaoImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public int addCurrency(Currency currency) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			em.persist(currency);
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

	@Override
	public Currency getCurrency(String currencyName) {
		Currency currency = null;
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT c from Currency c WHERE c.currencyName = :currencyName", Currency.class);
		query.setParameter("currencyName", currencyName);

		try {
			currency = (Currency) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return currency;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Currency> getCurrencyList() {
		List<Currency> currencyList = null;
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT c from Currency c", Currency.class);
		try {
			currencyList = (List<Currency>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return currencyList;
	}

}
