package com.show.upload.service;

import java.sql.SQLException;

import com.show.image.pojo.MyImage;
import com.show.upload.dao.UploadDao;

public class UploadService {
	private UploadDao uploadDao = new UploadDao();

	public void add(MyImage myImage) {
		// TODO Auto-generated method stub
		try {
			uploadDao.add(myImage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
