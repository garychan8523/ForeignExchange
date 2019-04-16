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

import com.fdmgroup.ForeignExchange.entities.Account;
import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.User;

public class AccountDaoImpl implements AccountDao {
	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	private CurrencyDaoImpl currencyDaoImpl;

	public AccountDaoImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public int addAccountByUser(User user) {
		Account accountadd = new Account();
		int count = 0;
		
		for (Currency c : currencyDaoImpl.getCurrencyList()) {
			accountadd.setUser(user);
			accountadd.setCurrency(c);
			accountadd.setBalance(0.0d); // initial account balance equals to 0
			count += addAccount(accountadd);
		}
		return count;
	}

	@Override
	public int addAccount(Account account) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			em.persist(account);
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
	public List<Account> getAccountListByUser(User user) {
		List<Account> accountList = null;
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT a from Account a WHERE a.USERID = :userID", Account.class);
		query.setParameter("userId", user.getUserId());
		try {
			accountList = (List<Account>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return accountList;
	}

	@Override
	public Account getUserCurrencyAccount(User user, Currency currency) {
		Account userCurrecyAccountList = null;
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT a from Account a WHERE a.USERID = :userId AND a.CURRENCYID = :currencyId",
				Account.class);
		query.setParameter("userId", user.getUserId());
		query.setParameter("currencyId", currency.getCurrencyId());
		try {
			userCurrecyAccountList = (Account) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return userCurrecyAccountList;
	}

	@Override
	public int removeAccountsByUser(User user) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		int rows;
		Query query;

		try {
			et.begin();
			query = em.createQuery("DELETE a from Account a WHERE a.USERID = :userId", Account.class);
			query.setParameter("userId", user.getUserId());
			rows = query.executeUpdate();
			et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
		return rows;
	}

	@Override
	public int removeAccount(Account account) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		
		try {
			et.begin();
			em.remove(account);
			et.commit();
		} catch (IllegalArgumentException | TransactionRequiredException | IllegalStateException
				| RollbackException e) {
			e.printStackTrace();
			return 0;
		} finally {
			em.close();
		}
		return 1;
	}

	@Override
	public int updateAccount(Account account) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		Account existingAccount = em.find(Account.class, account.getAccountKey());
		try {
			et.begin();
			existingAccount.setBalance(account.getBalance());
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
