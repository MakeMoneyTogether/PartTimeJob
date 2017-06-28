package com.partjob.dao;

import com.partjob.entity.TblMchntInfo;
import com.partjob.utils.HibernateBaseDao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MPJ on 17/4/8.
 */
@Repository
public class MchntInfoDao extends HibernateBaseDao<TblMchntInfo,Serializable>{


	public TblMchntInfo getMchntInfo(String password,String phone){
		String hql="from TblMchntInfo mchnt where mchnt.password=? and phone=?";
		return findUnique(hql,password,phone);
		
	}
	
	/**
	 * 根据商户倒排查询所有商户信息
	 * @return
	 */
	public List<TblMchntInfo> getAllMchntInf(){
		String hql="from TblMchntInfo mchnt order by mchnt.mchntCd desc";
		return find(hql, null);
	}
	
	public List<TblMchntInfo> getMchntByName(String name){
		String hql="from TblMchntInfo mchnt where mchnt.mchntName like ? order by mchnt.mchntCd desc";
		return find(hql, "%"+name+"%");
	}
}
