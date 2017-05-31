package com.partjob.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_city_info", schema = "parttimejob", catalog = "")
public class TblCityInfo {

	private int cityCode;
	private String  cityName;
	private int superCode;
	 @Basic
	 @Column(name = "city_code")
	public int getCityCode() {
		return cityCode;
	}
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	 @Basic
	 @Column(name = "city_name")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	 @Basic
	 @Column(name = "super_code")
	public int getSuperCode() {
		return superCode;
	}
	public void setSuperCode(int superCode) {
		this.superCode = superCode;
	}
	
	
}
