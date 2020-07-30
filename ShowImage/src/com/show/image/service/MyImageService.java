package com.show.image.service;

import java.sql.SQLException;
import java.util.List;

import com.show.image.dao.MyImageDao;
import com.show.image.pojo.MyImage;
import com.show.image.vo.ImageDetails;
import com.show.util.pager.PageBean;

public class MyImageService {
	private MyImageDao mImageDao = new MyImageDao();

	public List<MyImage> getIndexImage() {
		// TODO Auto-generated method stub
		try {
			return mImageDao.getIndexImage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<MyImage> findCollectionByUid(Integer uid) {
		// TODO Auto-generated method stub
		try {
			return mImageDao.findCollectionByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageBean<MyImage> findCollectionByUid(Integer uid, int pc) {
		// TODO Auto-generated method stub
		try {
			return mImageDao.findCollectionByUid(uid, pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageBean<MyImage> findMyImageByUid(Integer uid, int pc) {
		try {
			return mImageDao.findMyImageByUid(uid, pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<MyImage> findMyImageByUid(String uid) {
		try {
			return mImageDao.findMyImageByUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public MyImage findImageByImageID(Integer imageID) {
		try {
			return mImageDao.findImageByImageID(imageID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void updateImage(MyImage myImage) {
		// TODO Auto-generated method stub
		try {
			mImageDao.updateImage(myImage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteImage(Integer uid, Integer imageId) {
		// TODO Auto-generated method stub
		try {
			mImageDao.deleteImage(uid, imageId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImageDetails getImageDetils(String imageId, String uid) {
		try {
			return mImageDao.getImageDetils(imageId, uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void addCollection(String uid, String imageId) {
		// TODO Auto-generated method stub
		try {
			mImageDao.addCollection(uid, imageId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteCollection(String uid, String imageId) {
		// TODO Auto-generated method stub
		try {
			mImageDao.deleteCollection(uid, imageId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PageBean<MyImage> findByTitle(String title, String uidString, int pc) {
		// TODO Auto-generated method stub
		try {
			return mImageDao.findByTitle(title, uidString, pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public PageBean<MyImage> findByCombination(MyImage myImage, int pc) {
		// TODO Auto-generated method stub
		try {
			return mImageDao.findByCombination(myImage, pc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<MyImage> getHotImage() {

		try {
			return mImageDao.getHotImage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
