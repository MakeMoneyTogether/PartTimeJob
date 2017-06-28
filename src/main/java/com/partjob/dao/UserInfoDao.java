package com.partjob.dao;

import com.partjob.entity.TblUserInfo;
import com.partjob.utils.HibernateBaseDao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sloriac on 2017/4/16.
 * 用户信息的DAO类
 */
@Repository
public class UserInfoDao extends HibernateBaseDao<TblUserInfo, Serializable> {

    public void update(TblUserInfo tblUserInfo) {
        super.saveOrUpdate(tblUserInfo);
    }

    public TblUserInfo getByPhone(String phone) {
        String hql = "from TblUserInfo u where u.phone=?";
        return findUnique(hql, phone);
    }

    public TblUserInfo getUserInfo(String phone, String passwordMD5) {
        String hql = "from TblUserInfo u where u.pwd=? and u.phone=?";
        return findUnique(hql, passwordMD5, phone);
    }
    
    public List<TblUserInfo> getAllUser(){
    	String hql="from TblUserInfo u  order by u.uid desc";
    	return find(hql, null);
    }
    
    public List<TblUserInfo> getUserByName(String name){
    	String hql="from TblUserInfo u  where u.name like ? order by u.uid desc";
    	return find(hql, "%"+name+"%");
    }
}
