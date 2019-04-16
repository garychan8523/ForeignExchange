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

import com.fdmgroup.ForeignExchange.entities.Currency;

public class CurrencyDaoImplTest {

	@Mock
	private EntityManagerFactory mockEmf;

	@InjectMocks
	private CurrencyDaoImpl currencyDaoImpl = new CurrencyDaoImpl();

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void given_currency_when_addCurrency_then_persist_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Currency mockCurrency = mock(Currency.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		currencyDaoImpl.addCurrency(mockCurrency);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).persist(mockCurrency);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_currencyName_when_getCurrency_then_find_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		// EntityTransaction mockEt = mock(EntityTransaction.class);

		Currency mockCurrency = mock(Currency.class);

		@SuppressWarnings("unchecked")
		TypedQuery<Currency> mockQuery = Mockito.mock(TypedQuery.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		// when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT c from Currency c WHERE c.currencyName = :currencyName", Currency.class))
				.thenReturn(mockQuery);
		when(mockQuery.setParameter("currencyName", "dummy")).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockCurrency);

		// Act
		currencyDaoImpl.getCurrency("dummy");

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT c from Currency c WHERE c.currencyName = :currencyName",
				Currency.class);
		inOrder.verify(mockQuery).setParameter("currencyName", "dummy");
		inOrder.verify(mockQuery).getSingleResult();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void when_getCurrencyList_then_query_should_be_created() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);

		@SuppressWarnings("unchecked")
		TypedQuery<Currency> mockQuery = Mockito.mock(TypedQuery.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT c from Currency c", Currency.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);

		// Act
		currencyDaoImpl.getCurrencyList();

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT c from Currency c", Currency.class);
		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}

}
