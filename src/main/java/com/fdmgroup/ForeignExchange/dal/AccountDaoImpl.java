package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.ForeignExchange.entities.Account;
import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.User;

public class AccountDaoImpl implements AccountDao {

	@Autowired
	private EntityManagerFactory emf;

	public AccountDaoImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public int addAccount(Account account) {
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
			em.persist(account);
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
	public List<Account> getAccountListByUser(User user) {
		EntityManager em;
		Query query;
		List<Account> accountList = null;

		try {
			em = getEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			return accountList;
		}

		try {
			query = em.createQuery("SELECT a from Account a WHERE a.USERID = :userID", Account.class);
			query.setParameter("userId", user.getUserId());
			accountList = (List<Account>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return accountList;
		} finally {
			em.close();
		}
		return accountList;
	}

	@Override
	public Account getUserCurrencyAccount(User user, Currency currency) {
		EntityManager em;
		Query query;
		Account userCurrecyAccountList = null;

		try {
			em = getEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
			return userCurrecyAccountList;
		}

		try {
			query = em.createQuery("SELECT a from Account a WHERE a.USERID = :userId AND a.CURRENCYID = :currencyId",
					Account.class);
			query.setParameter("userId", user.getUserId());
			query.setParameter("currencyId", currency.getCurrencyId());
			userCurrecyAccountList = (Account) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return userCurrecyAccountList;
		} finally {
			em.close();
		}
		return userCurrecyAccountList;
	}

	@Override
	public int removeAccountsByUser(User user) {
		EntityManager em;
		EntityTransaction et;
		int rows = 0;
		Query query;

		try {
			em = getEntityManager();
			et = em.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			return rows;
		}

		try {
			et.begin();
			query = em.createQuery("DELETE a from Account a WHERE a.USERID = :userId", Account.class);
			query.setParameter("userId", user.getUserId());
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
	public int removeAccount(Account account) {
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
			em.remove(account);
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
	public int updateAccount(Account account) {
		EntityManager em;
		EntityTransaction et;
		Account existingAccount;

		try {
			em = getEntityManager();
			et = em.getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		try {
			existingAccount = em.find(Account.class, account.getAccountKey());
			et.begin();
			existingAccount.setBalance(account.getBalance());
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
