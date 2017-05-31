package com.partjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partjob.dao.CityInfoDao;
import com.partjob.entity.TblCityInfo;

@Service
@Transactional
public class CityService {

	@Autowired
	CityInfoDao cityInfoDao;
	
	public String getCityName(int cityCode){
		return cityInfoDao.get(cityCode).getCityName();
	}
	
	public List<TblCityInfo> getCitys(int cityCode){
		return cityInfoDao.findByProperty("superCode", cityCode);
	}
}
