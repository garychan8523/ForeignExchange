package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.ForeignExchange.entities.Currency;

@Component
public class CurrencyDaoImpl implements CurrencyDao {
	@Autowired
	private EntityManagerFactory emf;

	public CurrencyDaoImpl(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	public CurrencyDaoImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public int addCurrency(Currency currency) {
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
			em.persist(currency);
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
	public Currency getCurrency(String currencyCode) {
		EntityManager em;
		Query query;
		Currency currency = null;

		try {
			em = getEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			return currency;
		}

		try {
			query = em.createQuery("SELECT c from Currency c WHERE c.currencyCode = :currencyCode", Currency.class);
			query.setParameter("currencyCode", currencyCode);
			currency = (Currency) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return currency;
		} finally {
			em.close();
		}
		return currency;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Currency> getCurrencyList() {
		EntityManager em;
		Query query;
		List<Currency> currencyList = null;

		try {
			em = getEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			return currencyList;
		}

		try {
			query = em.createQuery("SELECT c from Currency c", Currency.class);
			currencyList = (List<Currency>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return currencyList;
		} finally {
			em.close();
		}
		return currencyList;
	}

}
