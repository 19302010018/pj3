package com.show.upload.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.show.image.pojo.MyImage;

import cn.itcast.jdbc.TxQueryRunner;

public class UploadDao {
	private QueryRunner qr = new TxQueryRunner();

	public void add(MyImage myImage) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(myImage);
		String sqlString = "insert into travelimage(ImageID,Title,Description,CityCode,CountryCodeISO,uid,PATH,Topic) values(?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] { myImage.getImageId(), myImage.getTitle(), myImage.getDescription(),
				myImage.getCityCode(), myImage.getCountryCodeISO(), myImage.getUid(), myImage.getPATH(),
				myImage.getTopic() };
		qr.update(sqlString, params);
	}

}
