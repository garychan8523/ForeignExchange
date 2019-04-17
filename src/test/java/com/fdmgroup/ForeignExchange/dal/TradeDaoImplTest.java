package com.fdmgroup.ForeignExchange.dal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.ForeignExchange.entities.Trade;

public class TradeDaoImplTest {

	@Mock
	private EntityManagerFactory mockEmf;

	@InjectMocks
	private TradeDaoImpl tradeDaoImpl = new TradeDaoImpl();

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void given_trade_when_addTrade_then_persist_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Trade mockTrade = mock(Trade.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		tradeDaoImpl.addTrade(mockTrade);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).persist(mockTrade);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_limit_when_getTradeList_then_query_should_be_created() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);

		@SuppressWarnings("unchecked")
		TypedQuery<Trade> mockQuery = Mockito.mock(TypedQuery.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT t from Trade t where rownum <= :limit", Trade.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);

		// Act
		tradeDaoImpl.getTradeList(10L);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT t from Trade t where rownum <= :limit", Trade.class);
		inOrder.verify(mockQuery).setParameter("limit", 10);
		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}

//	@Test
//	public void given_user_currency_when_getTradeListByUserCurrency_then_criteriaQuery_should_be_created() {
//		// Arrange
//		EntityManager mockEm = mock(EntityManager.class);
//		CriteriaBuilder mockCriteriaBuilder = mock(CriteriaBuilder.class);
//
//		User mockUser = mock(User.class);
//		Currency mockCurrency = mock(Currency.class);
//		CriteriaQuery<Trade> mockCriteriaQuery = mock(CriteriaQuery.class);
//		Root<Trade> mockRoot = mock(Root.class);
//		Selection<? extends Trade> mockJoin = mock(Join.class);
//
//		@SuppressWarnings("unchecked")
//		TypedQuery<User> mockQuery = Mockito.mock(TypedQuery.class);
//
//		// Stub
//		when(mockEmf.createEntityManager()).thenReturn(mockEm);
//		when(mockEm.getCriteriaBuilder()).thenReturn(mockCriteriaBuilder);
//		when(mockCriteriaBuilder.createQuery(Trade.class)).thenReturn(mockCriteriaQuery);
//		when(mockCriteriaQuery.from(Trade.class)).thenReturn(mockRoot);
//		when(mockRoot.join("order", JoinType.LEFT)).thenReturn(mockJoin);
//
//		when(mockQuery.getResultList()).thenReturn(null);
//
//		// Act
//		tradeDaoImpl.getTradeListByUserCurrency(mockUser, mockCurrency);
//
//		// Assert or Verify
//		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);
//
//		inOrder.verify(mockEmf).createEntityManager();
//		inOrder.verify(mockEm).getCriteriaBuilder();
//		inOrder.verify(mockCriteriaBuilder).createQuery(Trade.class);
//		inOrder.verify(mockCriteriaQuery).from(Trade.class);
//		inOrder.verify(mockRoot).join("order", JoinType.LEFT);
//
//		inOrder.verify(mockCriteriaQuery)
//				.where(mockCriteriaBuilder.and(
//						mockCriteriaBuilder.or(mockCriteriaBuilder.equal(mockJoin.getParent().get("user"), mockUser), // Trade
//								// table
//								mockCriteriaBuilder.equal(mockJoin.get("user"), mockUser) // Order table
//						), mockCriteriaBuilder.or(mockCriteriaBuilder.equal(mockJoin.get("currencyBuy"), mockCurrency),
//								mockCriteriaBuilder.equal(mockJoin.get("currencySell"), mockCurrency))));
//
//		inOrder.verify(mockCriteriaQuery).select(mockJoin).distinct(true);
//
//	}

}
