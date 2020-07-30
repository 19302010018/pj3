package com.show.user.service;

import java.sql.SQLException;
import java.util.List;

import com.show.image.pojo.MyImage;
import com.show.user.dao.FriendDao;
import com.show.user.pojo.Friend;
import com.show.util.pager.PageBean;

public class FriendService {
	private FriendDao friendDao = new FriendDao();

	public List<Friend> findFriendByUid(Integer uid) {
		try {
			return friendDao.findFriendByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public PageBean<MyImage> findImageByFid(Integer fid, int pc) {

		try {
			return friendDao.findImageByFid(fid, pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
