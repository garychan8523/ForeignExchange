package com.fdmgroup.ForeignExchange.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.ForeignExchange.dal.UserDaoImpl;
import com.fdmgroup.ForeignExchange.entities.User;
import com.fdmgroup.ForeignExchange.utilities.PasswordUtility;

@Component
public class SignupService {

	@Autowired
	private UserDaoImpl userDao;

	public boolean isUserNameTaken(String username) {
		User targetUser = userDao.getUser(username);
		if (targetUser != null) {
			return true;
		} else {
			return false;
		}
	}

	public void registerUser(User user) {
		try {
			user.setPassword(PasswordUtility.getSaltedHash(user.getPassword()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			userDao.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
