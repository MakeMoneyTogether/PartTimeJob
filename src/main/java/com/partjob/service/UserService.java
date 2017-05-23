package com.partjob.service;

import java.util.ArrayList;
import java.util.List;

import com.partjob.constant.ResponseCode;
import com.partjob.dao.UserInfoDao;
import com.partjob.dao.UserJobDao;
import com.partjob.entity.TblRelUserJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.model.UserInfo;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by MPJ on 17/4/8.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserJobDao userJobDao;

    public int register(String pwd, String phone, String code, String invitation) {
        // 先检查手机有没有注册过
        TblUserInfo tblUserInfo = userInfoDao.getByPhone(phone);
        if (tblUserInfo != null) return ResponseCode.FAIL;
        //验证码检查, 先忽略
        tblUserInfo = new TblUserInfo();
        tblUserInfo.setPhone(phone);
        tblUserInfo.setPwd(CommonUtil.toMD5(pwd));
        userInfoDao.save(tblUserInfo);
        return ResponseCode.SUCCESS;
    }

    public UserInfo login(String phone, String pwd) {
        pwd = CommonUtil.toMD5(pwd);
        TblUserInfo tblUserInfo = userInfoDao.getUserInfo(phone, pwd);
        return transModel(tblUserInfo);
    }
    
    public UserInfo getUser(int id){
    	TblUserInfo tblUserInfo = userInfoDao.get(id);
    	return transModel(tblUserInfo);
    }

    public int rpwd(String phone, String pwd, String npwd){
        // 先检查登录
        pwd = CommonUtil.toMD5(pwd);
        TblUserInfo tblUserInfo = userInfoDao.getUserInfo(phone, pwd);
        if (tblUserInfo == null) return ResponseCode.FAIL;
        else {
            tblUserInfo.setPwd(CommonUtil.toMD5(npwd));
            userInfoDao.update(tblUserInfo);
            return ResponseCode.SUCCESS;
        }
    }

    public UserInfo getByPhone(String phone){
        TblUserInfo tblUserInfo = userInfoDao.getByPhone(phone);
        return transModel(tblUserInfo);
    }


    public void saveUser(UserInfo userInfo, String passwordMD5) {
        if (checkPhone(userInfo.getPhone(), userInfo)) {
            TblUserInfo tblUserInfo = new TblUserInfo();
            ApplicationUtil.copyProperties(userInfo, tblUserInfo);
            userInfoDao.save(tblUserInfo);
        }
    }

    public boolean checkPhone(String phone, UserInfo userInfo) {
        TblUserInfo tblUserInfo = userInfoDao.findUniqueByProperty("phone", phone);
        if ((tblUserInfo == null && userInfo == null)
                || (tblUserInfo != null && userInfo.getPhone().equals(tblUserInfo.getPhone()))) {
            return false;
        } else {
            return true;
        }
    }

    private UserInfo transModel(TblUserInfo tblUserInfo) {
        UserInfo userInfo = new UserInfo();
        if (tblUserInfo == null)
            return null;
        ApplicationUtil.copyProperties(tblUserInfo, userInfo);
        return userInfo;
    }

}
