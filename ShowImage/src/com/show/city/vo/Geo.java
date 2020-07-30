package com.show.city.vo;

import java.util.List;

import com.show.city.pojo.GeoCities;

public class Geo {
	private String countryName;
	private String iso;
	private List<GeoCities> cities;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getIso() {
		return iso;
	}

	public void setIso(String iso) {
		this.iso = iso;
	}

	public List<GeoCities> getCities() {
		return cities;
	}

	public void setCities(List<GeoCities> cities) {
		this.cities = cities;
	}
}
