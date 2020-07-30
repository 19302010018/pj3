package com.show.city.service;

import java.sql.SQLException;
import java.util.List;

import com.show.city.dao.GeoDao;
import com.show.city.pojo.GeoCities;
import com.show.city.vo.Geo;

public class GeoService {
	private GeoDao geoDao = new GeoDao();

	public List<Geo> findAllGeoCountry() {
		try {
			return geoDao.findAllGeoCountry();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public List<GeoCities> findAllCity(String iso) {
		try {
			return geoDao.findCityByCountry(iso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<GeoCities> findAllCity() {
		// TODO Auto-generated method stub
		try {
			return geoDao.findAllCity();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
