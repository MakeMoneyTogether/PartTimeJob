package com.partjob.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.partjob.entity.TblSwiperInfo;
import com.partjob.utils.HibernateBaseDao;
@Repository
public class SwiperDao extends HibernateBaseDao<TblSwiperInfo, Serializable>{

	public TblSwiperInfo getByFile(String file){
		String HQL = "from TblSwiperInfo where file = ?";
		return findUnique(HQL,file);
	}
}
