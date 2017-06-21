package com.partjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partjob.dao.SwiperDao;
import com.partjob.entity.TblSwiperInfo;

@Service
@Transactional
public class SwiperService {
	@Autowired
	SwiperDao swiperDao;
	
	public void insert(String file,String url){
		TblSwiperInfo tblSwiperInfo= swiperDao.getByFile(file);
		if(tblSwiperInfo == null){
			tblSwiperInfo = new TblSwiperInfo();
		}
		tblSwiperInfo.setFile(file);
		tblSwiperInfo.setUrl(url);
		swiperDao.saveOrUpdate(tblSwiperInfo);
	}
	
	public List<TblSwiperInfo> getAll(){
		return swiperDao.getAll();
	}
}
