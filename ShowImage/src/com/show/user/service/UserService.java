package com.show.user.service;

import java.sql.SQLException;
import java.util.Date;

import com.show.user.dao.UserDao;
import com.show.user.pojo.User;

public class UserService {
	private UserDao userDao = new UserDao();

	public User login(User formUser) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user = userDao.findByLoginnameAndLoginpass(formUser.getUsername(), formUser.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public void save(User formUser) {
		// TODO Auto-generated method stub
		formUser.setUid(null);
		formUser.setDatejoined(new Date());
		formUser.setState(1);
		try {
			userDao.save(formUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean valiteUseName(String username) {

		try {
			return userDao.findUserByUserName(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
