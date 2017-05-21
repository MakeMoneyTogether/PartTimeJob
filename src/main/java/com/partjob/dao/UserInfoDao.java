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

    public TblUserInfo getByPhone(String phone) {
        String hql = "from TblUserInfo user where user.phone=:phone";
        return findUnique(hql, phone);
    }

    public TblUserInfo getUserInfo(String phone, String passwordMD5) {
        String hql = "from TblUserInfo user where user.password=:password and phone=:phone";
        return findUnique(hql, passwordMD5, phone);
    }
}
