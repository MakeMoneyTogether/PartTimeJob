package com.partjob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.partjob.dao.AboutMeDao;
import com.partjob.dao.SwiperDao;
import com.partjob.entity.TblAboutMe;
import com.partjob.entity.TblSwiperInfo;

@Service
@Transactional
public class SwiperService {
	@Autowired
	SwiperDao swiperDao;
	@Autowired
	AboutMeDao aboutMeDao;
	
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
	
	public TblAboutMe getAboutMe(){
		TblAboutMe tblAboutMe = aboutMeDao.get(1);
		return tblAboutMe;
	}
	public void updateAboutMe(String content){
		TblAboutMe tblAboutMe = aboutMeDao.get(1);
		tblAboutMe.setContent(content);
		aboutMeDao.modify(tblAboutMe);
	}
}
