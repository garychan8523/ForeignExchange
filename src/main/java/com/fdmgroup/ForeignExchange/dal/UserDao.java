package com.fdmgroup.ForeignExchange.dal;

import java.util.List;

import com.fdmgroup.ForeignExchange.entities.User;

public interface UserDao {

	public void addUser(User user);

	public User getUser(String username);

	public void removeUser(String username);

	public void updateUser(User user);

	public List<User> getUserList();

}
