package com.fdmgroup.ForeignExchange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.ForeignExchange.dal.UserDaoImpl;
import com.fdmgroup.ForeignExchange.entities.User;
import com.fdmgroup.ForeignExchange.utilities.PasswordUtility;

@Component
public class UserService {

	@Autowired
	private UserDaoImpl userDao;

	public User getUserByUsername(String username) {
		return userDao.getUser(username);
	}

	public User authenticate(String username, String password) throws Exception {
		User user = userDao.getUser(username);
		if (user != null) {
			if (PasswordUtility.check(password, user.getPassword())) {
				return user;
			}
		}
		return null;
	}

}
