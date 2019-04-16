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

import com.fdmgroup.ForeignExchange.entities.Account;
import com.fdmgroup.ForeignExchange.entities.Currency;
import com.fdmgroup.ForeignExchange.entities.User;

public class AccountDaoImplTest {

	@Mock
	private EntityManagerFactory mockEmf;

	@InjectMocks
	private AccountDaoImpl accountDaoImpl = new AccountDaoImpl();

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void given_account_when_addAccount_then_persist_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Account mockAccount = mock(Account.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		accountDaoImpl.addAccount(mockAccount);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).persist(mockAccount);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_user_when_getAccountListByUser_then_query_should_be_created() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);

		User mockUser = mock(User.class);

		@SuppressWarnings("unchecked")
		TypedQuery<Account> mockQuery = Mockito.mock(TypedQuery.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT a from Account a WHERE a.USERID = :userID", Account.class))
				.thenReturn(mockQuery);
		when(mockQuery.setParameter("userId", mockUser.getUserId())).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);

		// Act
		accountDaoImpl.getAccountListByUser(mockUser);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT a from Account a WHERE a.USERID = :userID", Account.class);
		inOrder.verify(mockQuery).setParameter("userId", mockUser.getUserId());
		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_user_currency_when_getUserCurrencyAccount_then_query_should_be_created() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);

		User mockUser = mock(User.class);
		Currency mockCurrency = mock(Currency.class);

		@SuppressWarnings("unchecked")
		TypedQuery<Account> mockQuery = Mockito.mock(TypedQuery.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT a from Account a WHERE a.USERID = :userId AND a.CURRENCYID = :currencyId",
				Account.class)).thenReturn(mockQuery);
		when(mockQuery.setParameter("userId", mockUser.getUserId())).thenReturn(mockQuery);
		when(mockQuery.setParameter("currencyId", mockCurrency.getCurrencyId())).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);

		// Act
		accountDaoImpl.getUserCurrencyAccount(mockUser, mockCurrency);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery(
				"SELECT a from Account a WHERE a.USERID = :userId AND a.CURRENCYID = :currencyId", Account.class);
		inOrder.verify(mockQuery).setParameter("userId", mockUser.getUserId());
		inOrder.verify(mockQuery).setParameter("currencyId", mockCurrency.getCurrencyId());
		inOrder.verify(mockQuery).getSingleResult();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_user_when_removeAccountsByUser_then_remove_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);

		@SuppressWarnings("unchecked")
		TypedQuery<Account> mockQuery = Mockito.mock(TypedQuery.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("DELETE a from Account a WHERE a.USERID = :userId", Account.class))
				.thenReturn(mockQuery);
		when(mockQuery.setParameter("userId", mockUser.getUserId())).thenReturn(mockQuery);
		when(mockQuery.executeUpdate()).thenReturn(1);

		// Act
		accountDaoImpl.removeAccountsByUser(mockUser);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockQuery, mockUser);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).createQuery("DELETE a from Account a WHERE a.USERID = :userId", Account.class);
		inOrder.verify(mockQuery).setParameter("userId", mockUser.getUserId());
		inOrder.verify(mockQuery).executeUpdate();
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_account_when_removeAccount_then_remove_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Account mockAccount = mock(Account.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		accountDaoImpl.removeAccount(mockAccount);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockAccount);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).remove(mockAccount);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_account_when_updateAccount_then_update_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Account mockAccount = mock(Account.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(Account.class, mockAccount.getAccountKey())).thenReturn(mockAccount);

		// Act
		accountDaoImpl.updateAccount(mockAccount);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockAccount);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEm).find(Account.class, mockAccount.getAccountKey());
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockAccount).setBalance(mockAccount.getBalance());
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

}
