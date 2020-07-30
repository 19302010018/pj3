package com.show.user.pojo;

import java.util.Date;

public class User {
	private String username;
	private String password;
	private Integer uid;
	private Integer state;
	private Date datejoined;
	private Date datelastmodified;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getDatejoined() {
		return datejoined;
	}
	public void setDatejoined(Date datejoined) {
		this.datejoined = datejoined;
	}
	public Date getDatelastmodified() {
		return datelastmodified;
	}
	public void setDatelastmodified(Date datelastmodified) {
		this.datelastmodified = datelastmodified;
	}
	
}
