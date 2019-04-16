package com.fdmgroup.ForeignExchange.dal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fdmgroup.ForeignExchange.entities.User;

public class UserDaoImplTest {

	@Mock
	private EntityManagerFactory mockEmf;

	@InjectMocks
	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void given_user_when_updateUser_then_persist_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);

		// Act
		userDaoImpl.addUser(mockUser);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).persist(mockUser);
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_username_when_getUser_then_find_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);

		@SuppressWarnings("unchecked")
		TypedQuery<User> mockQuery = Mockito.mock(TypedQuery.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("SELECT u FROM User u where u.username = :username", User.class)).thenReturn(mockQuery);
		when(mockQuery.setParameter("username", "dummy")).thenReturn(mockQuery);
		when(mockQuery.getSingleResult()).thenReturn(mockUser);

		// Act
		userDaoImpl.getUser("dummy");

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockQuery);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT u FROM User u where u.username = :username", User.class);
		inOrder.verify(mockQuery).setParameter("username", "dummy");
		inOrder.verify(mockQuery).getSingleResult();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_username_when_removeUser_then_remove_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		Query mockQuery = Mockito.mock(Query.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.createQuery("delete from User u where u.username = :username")).thenReturn(mockQuery);
		when(mockQuery.setParameter("username", "dummy")).thenReturn(mockQuery);
		when(mockQuery.executeUpdate()).thenReturn(1);

		// Act
		userDaoImpl.removeUser("dummy");

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockQuery);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockEm).createQuery("delete from User u where u.username = :username");
		inOrder.verify(mockQuery).setParameter("username", "dummy");
		inOrder.verify(mockQuery).executeUpdate();
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void given_user_when_updateUser_then_update_related_procedures_should_be_called() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);
		EntityTransaction mockEt = mock(EntityTransaction.class);

		User mockUser = mock(User.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.getTransaction()).thenReturn(mockEt);
		when(mockEm.find(User.class, mockUser.getUserId())).thenReturn(mockUser);

		// Act
		userDaoImpl.updateUser(mockUser);

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockEt, mockUser);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).getTransaction();
		inOrder.verify(mockEm).find(User.class, mockUser.getUserId());
		inOrder.verify(mockEt).begin();
		inOrder.verify(mockUser).setEmail(mockUser.getEmail());
		inOrder.verify(mockUser).setPassword(mockUser.getPassword());
		inOrder.verify(mockUser).setUserName(mockUser.getUserName());
		inOrder.verify(mockEt).commit();
		inOrder.verify(mockEm).close();
	}

	@Test
	public void when_getUserList_then_query_should_be_created() {
		// Arrange
		EntityManager mockEm = mock(EntityManager.class);

		@SuppressWarnings("unchecked")
		TypedQuery<User> mockQuery = Mockito.mock(TypedQuery.class);

		// Stub
		when(mockEmf.createEntityManager()).thenReturn(mockEm);
		when(mockEm.createQuery("SELECT u from User u", User.class)).thenReturn(mockQuery);
		when(mockQuery.getResultList()).thenReturn(null);

		// Act
		userDaoImpl.getUserList();

		// Assert or Verify
		InOrder inOrder = Mockito.inOrder(mockEmf, mockEm, mockQuery);

		inOrder.verify(mockEmf).createEntityManager();
		inOrder.verify(mockEm).createQuery("SELECT u from User u", User.class);
		inOrder.verify(mockQuery).getResultList();
		inOrder.verify(mockEm).close();
	}

}
