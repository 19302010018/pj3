package com.show.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.show.user.pojo.User;

import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	public User findByLoginnameAndLoginpass(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from traveluser where username = ? and password = ?";
		return qr.query(sql, new BeanHandler<User>(User.class), username, password);
	}

	public void save(User formUser) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into traveluser values(?,?,?,?,?,?)";
		Object[] params = { formUser.getUid(), formUser.getUsername(), formUser.getPassword(), formUser.getState(),
				formUser.getDatejoined(), formUser.getDatelastmodified() };
		qr.update(sql, params);
	}

	public boolean findUserByUserName(String username) throws SQLException {
		// TODO Auto-generated method stub
		String sqlString = "select count(*) from traveluser where username = ?";
		Number number = (Number) qr.query(sqlString, new ScalarHandler(), username);
		return number.intValue() != 0;
	}

}
