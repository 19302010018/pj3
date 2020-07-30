package com.show.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.show.image.pojo.MyImage;
import com.show.user.pojo.Friend;
import com.show.user.pojo.FriendFavor;
import com.show.util.pager.PageBean;
import com.show.util.pager.PageConstants;

import cn.itcast.jdbc.TxQueryRunner;

public class FriendDao {
	private QueryRunner qRunner = new TxQueryRunner();

	public List<Friend> findFriendByUid(Integer uid) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from friends where uid = ?";
		List<Friend> list = qRunner.query(sql, new BeanListHandler<Friend>(Friend.class), uid);
		return list;
	}

	public PageBean<MyImage> findImageByFid(Integer fid, int pc) throws SQLException {
		// TODO Auto-generated method stub
		List<MyImage> dataList = new ArrayList<MyImage>();
		String sq = "select count(*) from friendfavor where fid = ?";
		Number number = (Number) qRunner.query(sq, new ScalarHandler(), fid);
		int tr = number.intValue();
		int ps = PageConstants.IMAGE_PAGE_SIZE;
		String sql = "select * from friendfavor where fid = ? limit ?,?";
		List<FriendFavor> friendFavors = qRunner.query(sql, new BeanListHandler<FriendFavor>(FriendFavor.class), fid,
				(pc - 1) * ps, ps);
		String sqlString = "select * from travelimage where ImageID = ?";
		for (FriendFavor friendFavor : friendFavors) {
			MyImage myImage = qRunner.query(sqlString, new BeanHandler<MyImage>(MyImage.class),
					friendFavor.getImageID());
			dataList.add(myImage);
		}
		PageBean<MyImage> pb = new PageBean<MyImage>();
		pb.setTr(tr);
		pb.setPs(ps);
		pb.setPc(pc);
		pb.setBeanList(dataList);
		return pb;
	}

}
