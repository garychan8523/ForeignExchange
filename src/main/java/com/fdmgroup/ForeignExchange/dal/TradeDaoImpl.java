package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.Trade;
import com.fdmgroup.ForeignExchange.entities.Order;
import com.fdmgroup.ForeignExchange.entities.User;

public class TradeDaoImpl implements TradeDao {

	public TradeDaoImpl(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	@Autowired
	private EntityManagerFactory emf;


	public TradeDaoImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public int addTrade(Trade trade) {
		EntityManager em = getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.persist(trade);
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
	public List<Trade> getTradeList(int limit) {
		List<Trade> tradeList = null;
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT t from Trade t where rownum <= :limit", Trade.class);
		query.setParameter("limit", limit);
		try {
			tradeList = (List<Trade>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
		return tradeList;
	}

	@Override
	public List<Trade> getTradeListByUserCurrency(User user, Currency currency) {
		List<Trade> list = null;

		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Trade> query = criteriaBuilder.createQuery(Trade.class);

		Root<Trade> sqlFromClauseTable = query.from(Trade.class);
		Join<Trade, Order> join = sqlFromClauseTable.join("order", JoinType.LEFT);
		query.where(criteriaBuilder.and(criteriaBuilder.or(criteriaBuilder.equal(join.getParent().get("user"), user), // Trade
																														// table
				criteriaBuilder.equal(join.get("user"), user) // Order table
		), criteriaBuilder.or(criteriaBuilder.equal(join.get("currencyBuy"), currency),
				criteriaBuilder.equal(join.get("currencySell"), currency))));
		query.select(sqlFromClauseTable).distinct(true);
		TypedQuery<Trade> typedQuery = em.createQuery(query);
		list = typedQuery.getResultList();
		return list;
	}

	@Override
	public List<Trade> getTradeListByCurrencyPair(Currency currencyBuy, Currency currencySell, int limit) {
		List<Trade> list = null;

		EntityManager em = getEntityManager();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Trade> query = criteriaBuilder.createQuery(Trade.class);

		Root<Trade> sqlFromClauseTable = query.from(Trade.class);
		Join<Trade, Order> join = sqlFromClauseTable.join("order", JoinType.LEFT);
		query.where(criteriaBuilder.and(criteriaBuilder.equal(join.get("currencyBuy"), currencyBuy),
				criteriaBuilder.equal(join.get("currencySell"), currencySell)));
		query.select(sqlFromClauseTable).distinct(true);
		TypedQuery<Trade> typedQuery = em.createQuery(query);
		list = typedQuery.setMaxResults(limit).getResultList();
		return list;
	}

}
