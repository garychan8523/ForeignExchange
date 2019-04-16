package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import com.fdmgroup.ForeignExchange.entities.User;

public interface UserDao {

	public int addUser(User user);

	public User getUser(String username);

	public int removeUser(String username);

	public int updateUser(User user);

	public List<User> getUserList();

}
