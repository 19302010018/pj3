package com.show.city.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.show.city.pojo.GeoCities;
import com.show.city.pojo.GeoCountries;
import com.show.city.vo.Geo;

import cn.itcast.jdbc.TxQueryRunner;

public class GeoDao {
	private QueryRunner queryRunner = new TxQueryRunner();

	public List<Geo> findAllGeoCountry() throws SQLException {
		List<Geo> dataList = new ArrayList<Geo>();
		String sqlString = "select * from geocountries";
		String sql = "select * from geocities where CountryCodeISO=?";
		List<GeoCountries> countries = queryRunner.query(sqlString,
				new BeanListHandler<GeoCountries>(GeoCountries.class));
		for (GeoCountries geoCountries : countries) {
			Geo geo = new Geo();
			geo.setCountryName(geoCountries.getCountryName());
			geo.setIso(geoCountries.getIso());
			List<GeoCities> cities = queryRunner.query(sql, new BeanListHandler<GeoCities>(GeoCities.class),
					geoCountries.getIso());
			geo.setCities(cities);
			dataList.add(geo);
		}
		return dataList;
	}

	public List<GeoCities> findCityByCountry(String iso) throws SQLException {
		String sql = "select * from geocities where CountryCodeISO=?";
		List<GeoCities> cities = queryRunner.query(sql, new BeanListHandler<GeoCities>(GeoCities.class), iso);
		return cities;
	}

	public List<GeoCities> findAllCity() throws SQLException {
		String sql = "select * from geocities";
		List<GeoCities> cities = queryRunner.query(sql, new BeanListHandler<GeoCities>(GeoCities.class));
		return cities;
	}
}
