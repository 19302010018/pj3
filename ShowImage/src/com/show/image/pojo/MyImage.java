package com.show.image.pojo;

/**
 * @author 徐东忠
 *
 */
public class MyImage {
	private Integer imageId;
	private String title;
	private String description;
	private Double cityCode;
	private String countryCodeISO;
	private Integer uid;
	private String PATH;
	private String topic;
	private Integer isCollection;

	@Override
	public String toString() {
		return "MyImage [imageId=" + imageId + ", title=" + title + ", description=" + description + ", cityCode="
				+ cityCode + ", countryCodeISO=" + countryCodeISO + ", uid=" + uid + ", PATH=" + PATH + ", topic="
				+ topic + ", isCollection=" + isCollection + "]";
	}

	public Integer getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getPATH() {
		return PATH;
	}

	public void setPATH(String pATH) {
		PATH = pATH;
	}

	public Double getCityCode() {
		return cityCode;
	}

	public void setCityCode(Double cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountryCodeISO() {
		return countryCodeISO;
	}

	public void setCountryCodeISO(String countryCodeISO) {
		this.countryCodeISO = countryCodeISO;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
