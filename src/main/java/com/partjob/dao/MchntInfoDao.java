package com.partjob.dao;

import com.partjob.entity.TblMchntInfo;
import com.partjob.utils.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by MPJ on 17/4/8.
 */
@Repository
public class MchntInfoDao extends HibernateBaseDao<TblMchntInfo,Serializable>{


	public TblMchntInfo getMchntInfo(String password,String phone){
		String hql="from TblMchntInfo mchnt where mchnt.password=:password amd phone=:phone";
		return findUnique(hql,password,phone);
		
	}
}
