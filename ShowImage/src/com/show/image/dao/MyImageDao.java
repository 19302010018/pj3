package com.show.image.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.show.city.pojo.GeoCountries;
import com.show.image.pojo.MyImage;
import com.show.image.pojo.TravelImageFavor;
import com.show.image.vo.ImageDetails;
import com.show.user.pojo.User;
import com.show.util.pager.Expression;
import com.show.util.pager.PageBean;
import com.show.util.pager.PageConstants;

import cn.itcast.jdbc.TxQueryRunner;

public class MyImageDao {
	private QueryRunner qr = new TxQueryRunner();

	public List<MyImage> getIndexImage() throws SQLException {
		// TODO Auto-generated method stub
		String sql1 = "select * from travelimage where Topic =  'Animal'  limit 0,6";
		List<MyImage> list1 = qr.query(sql1, new BeanListHandler<MyImage>(MyImage.class));
		String sql2 = "select * from travelimage where Topic =  'Scenery' limit 0,6";
		List<MyImage> list2 = qr.query(sql2, new BeanListHandler<MyImage>(MyImage.class));
		String sql3 = "select * from travelimage where Topic =  'Comic' limit 0,6";
		List<MyImage> list3 = qr.query(sql3, new BeanListHandler<MyImage>(MyImage.class));
		String sql4 = "select * from travelimage where Topic =  'Character' limit 0,6";
		List<MyImage> list4 = qr.query(sql4, new BeanListHandler<MyImage>(MyImage.class));
		String sql5 = "select * from travelimage where Topic =  'Natural' limit 0,6";
		List<MyImage> list5 = qr.query(sql5, new BeanListHandler<MyImage>(MyImage.class));
		List<MyImage> dataList = new ArrayList<MyImage>();
		dataList.addAll(list1);
		dataList.addAll(list2);
		dataList.addAll(list3);
		dataList.addAll(list4);
		dataList.addAll(list5);
		return dataList;
	}

	public MyImage findImageByImageID(Integer imageID) throws SQLException {
		String sqlString = "select * from travelimage where ImageID = ?";
		MyImage myImage = qr.query(sqlString, new BeanHandler<MyImage>(MyImage.class), imageID);
		return myImage;
	}

	public void updateImage(MyImage myImage) throws SQLException {
		// TODO Auto-generated method stub
		String sqString = "update travelimage set Title=?,Description=?,"
				+ "CityCode=?,CountryCodeISO=?,Topic=? where ImageId = ?";
		Object[] params = new Object[] { myImage.getTitle(), myImage.getDescription(), myImage.getCityCode(),
				myImage.getCountryCodeISO(), myImage.getTopic(), myImage.getImageId() };
		qr.update(sqString, params);
	}

	public void deleteImage(Integer uid, Integer imageId) throws SQLException {
		// TODO Auto-generated method stub
		String sqlString = "delete from travelimage where ImageID = ? and uid = ?";
		qr.update(sqlString, imageId, uid);
	}

	public ImageDetails getImageDetils(String imageId, String uid) throws SQLException {
		String sqlString = "select * from travelimage where ImageID = ?";
		MyImage myImage = qr.query(sqlString, new BeanHandler<MyImage>(MyImage.class), imageId);
		ImageDetails details = new ImageDetails();
		details.setImageId(myImage.getImageId());
		details.setUid(myImage.getUid());
		details.setDescribe(myImage.getDescription());
		details.setTitle(myImage.getTitle());
		details.setTheme(myImage.getTopic());
		details.setPath(myImage.getPATH());
		details.setUname(getUserNameByUid(myImage.getUid()));
		details.setCountry(getCountryByIso(myImage.getCountryCodeISO()));
		details.setIsCollection(getUserCollection(uid, imageId));
		return details;
	}

	private Integer getUserCollection(String uid, String imageId) throws SQLException {
		String sqlString = "select count(1) from travelimagefavor where ImageID = ? and uid = ?";
		Number number = (Number) qr.query(sqlString, new ScalarHandler(), imageId, uid);
		return number.intValue();
	}

	private String getCountryByIso(String countryCodeISO) throws SQLException {
		String sqlString = "select CountryName from geocountries where ISO = ?";
		GeoCountries countries = qr.query(sqlString, new BeanHandler<GeoCountries>(GeoCountries.class), countryCodeISO);
		return countries.getCountryName();
	}

	private String getUserNameByUid(Integer uid) throws SQLException {
		String sqlString = "select username from traveluser where uid = ?";
		User user = qr.query(sqlString, new BeanHandler<User>(User.class), uid);
		return user.getUsername();
	}

	public void addCollection(String uid, String imageId) throws SQLException {
		// TODO Auto-generated method stub
		String sqlString = "insert into travelimagefavor(fid,uid,ImageID) values(?,?,?)";
		qr.update(sqlString, null, uid, imageId);
	}

	public void deleteCollection(String uid, String imageId) throws SQLException {
		// TODO Auto-generated method stub
		String sqlString = "delete from travelimagefavor where uid=? and ImageID = ?";
		qr.update(sqlString, uid, imageId);
	}

	public PageBean<MyImage> findMyImageByUid(Integer uid, Integer pc) throws SQLException {
		int ps = PageConstants.IMAGE_PAGE_SIZE;
		String sql = "select count(*) from travelimage where uid = ?";
		Number number = (Number) qr.query(sql, new ScalarHandler(), uid);
		int tr = number.intValue();// 得到了总记录数
		String sqlsString = "select * from travelimage where uid = ? limit ?,?";
		List<MyImage> myImages = qr.query(sqlsString, new BeanListHandler<MyImage>(MyImage.class), uid, (pc - 1) * ps,
				ps);
		PageBean<MyImage> pb = new PageBean<MyImage>();
		pb.setBeanList(myImages);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		return pb;
	}

	public List<MyImage> findMyImageByUid(String uid) throws SQLException {
		String sqlsString = "select * from travelimage where uid = ?";
		List<MyImage> myImages = qr.query(sqlsString, new BeanListHandler<MyImage>(MyImage.class), uid);
		return myImages;
	}

	public PageBean<MyImage> findCollectionByUid(Integer uid, int pc) throws SQLException {
		int ps = PageConstants.IMAGE_PAGE_SIZE;
		String sqlsString = "select count(*) from travelimagefavor where uid = ?";
		Number number = (Number) qr.query(sqlsString, new ScalarHandler(), uid);
		int tr = number.intValue();// 得到了总记录数
		List<MyImage> dataList = new ArrayList<MyImage>();
		String sql = "select * from travelimagefavor where uid = ? limit ?,?";
		List<TravelImageFavor> list = qr.query(sql, new BeanListHandler<TravelImageFavor>(TravelImageFavor.class), uid,
				(pc - 1) * ps, ps);

		for (TravelImageFavor travelImageFavor : list) {
			String sq = "select * from travelimage where ImageID = ?";
			MyImage myImage = qr.query(sq, new BeanHandler<MyImage>(MyImage.class), travelImageFavor.getImageID());
			dataList.add(myImage);
		}
		PageBean<MyImage> pb = new PageBean<MyImage>();
		pb.setBeanList(dataList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		return pb;
	}

	public List<MyImage> findCollectionByUid(Integer uid) throws SQLException {
		List<MyImage> dataList = new ArrayList<MyImage>();
		String sql = "select * from travelimagefavor where uid = ?";
		List<TravelImageFavor> list = qr.query(sql, new BeanListHandler<TravelImageFavor>(TravelImageFavor.class), uid);
		for (TravelImageFavor travelImageFavor : list) {
			String sq = "select * from travelimage where ImageID = ?";
			MyImage myImage = qr.query(sq, new BeanHandler<MyImage>(MyImage.class), travelImageFavor.getImageID());
			dataList.add(myImage);
		}
		return dataList;
	}

	public PageBean<MyImage> findByTitle(String title, String uid, int pc) throws SQLException {
		// TODO Auto-generated method stub
		int ps = PageConstants.IMAGE_PAGE_SIZE;
		String sqlString2 = "select count(*) from travelimage where title like '%" + title + "%'";
		Number number = (Number) qr.query(sqlString2, new ScalarHandler());
		int tr = number.intValue();// 得到了总记录数
		String sqlString = "select * from travelimage where title like '%" + title + "%' limit ?,?";
		List<MyImage> myImages = qr.query(sqlString, new BeanListHandler<MyImage>(MyImage.class), (pc - 1) * ps, ps);
		for (MyImage myImage : myImages) {
			myImage.setIsCollection(getUserCollection(uid, String.valueOf(myImage.getImageId())));
		}
		PageBean<MyImage> pb = new PageBean<MyImage>();
		pb.setBeanList(myImages);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		return pb;
	}

	public PageBean<MyImage> findByCombination(MyImage myImage, int pc) throws SQLException {
		int ps = PageConstants.IMAGE_PAGE_SIZE;
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("Title", "like", "%" + myImage.getTitle() + "%"));
		exprList.add(new Expression("Description", "like", "%" + myImage.getDescription() + "%"));
		exprList.add(new Expression("CountryCodeISO", "like", "%" + myImage.getCountryCodeISO() + "%"));
		exprList.add(new Expression("CityCode", "=", myImage.getCityCode()));
		exprList.add(new Expression("Topic", "like", "%" + myImage.getTopic() + "%"));
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();// SQL中有问号，它是对应问号的值
		for (Expression expr : exprList) {
			/*
			 * 添加一个条件上， 1) 以and开头 2) 条件的名称 3) 条件的运算符，可以是=、!=、>、< ... is null，is null没有值 4)
			 * 如果条件不是is null，再追加问号，然后再向params中添加一与问号对应的值
			 */
			whereSql.append(" and ").append(expr.getName()).append(" ").append(expr.getOperator()).append(" ");
			// where 1=1 and bid = ?
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}

		/*
		 * 3. 总记录数
		 */
		String sql = "select count(*) from travelimage" + whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();// 得到了总记录数
		/*
		 * 4. 得到beanList，即当前页记录
		 */
		System.out.println(whereSql);
		sql = "select * from travelimage" + whereSql + " limit ?,?";
		params.add((pc - 1) * ps);// 当前页首行记录的下标
		params.add(ps);// 一共查询几行，就是每页记录数

		List<MyImage> myImages = qr.query(sql, new BeanListHandler<MyImage>(MyImage.class), params.toArray());
		PageBean<MyImage> pb = new PageBean<MyImage>();
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setBeanList(myImages);
		pb.setTr(tr);

		return pb;
	}

	public List<MyImage> getHotImage() throws SQLException {
		String sql6 = "SELECT  *  from travelimagefavor";
		String sql7 = "SELECT count(*)  from travelimagefavor where ImageID = ?";
		List<TravelImageFavor> list6 = qr.query(sql6, new BeanListHandler<TravelImageFavor>(TravelImageFavor.class));
		List<TravelImageFavor> list7 = new ArrayList<TravelImageFavor>();

		for (TravelImageFavor myImage : list6) {
			Number number = (Number) qr.query(sql7, new ScalarHandler(), myImage.getImageID());
			if (number.intValue() >= 3) {
				list7.add(myImage);
			}
		}
		List<MyImage> list8 = new ArrayList<MyImage>();

		for (TravelImageFavor travelImageFavor : list7) {
			MyImage image = findImageByImageID(travelImageFavor.getImageID());
			list8.add(image);
		}
		return list8;
	}

}
