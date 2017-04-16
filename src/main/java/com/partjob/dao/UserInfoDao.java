package com.partjob.dao;

import com.partjob.entity.TblUserInfo;
import com.partjob.utils.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Sloriac on 2017/4/16.
 * 用户信息的DAO类
 */
@Repository
public class UserInfoDao extends HibernateBaseDao<TblUserInfo, Serializable> {

    public void save(TblUserInfo tblUserInfo) {
        save(tblUserInfo);
    }

    public void update(TblUserInfo tblUserInfo) {
        update(tblUserInfo);
    }

    public TblUserInfo getUserInfo(String passwordMD5, String phone) {
        String hql = "from TblUserInfo user where user.password=:password amd phone=:phone";
        return findUnique(hql, passwordMD5, phone);
    }
}
