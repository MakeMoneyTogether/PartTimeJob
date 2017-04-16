package com.partjob.service;

import com.partjob.dao.UserInfoDao;
import com.partjob.entity.TblUserInfo;
import com.partjob.model.UserInfo;
import com.partjob.utils.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by MPJ on 17/4/8.
 */
@Service
@Transactional
public class UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    public void saveUser(UserInfo userInfo, String passwordMD5) {
        if (checkPhone(userInfo.getPhone(), userInfo)) {
            TblUserInfo tblUserInfo = new TblUserInfo();
            ApplicationUtil.copyProperties(userInfo, tblUserInfo);
            userInfoDao.save(tblUserInfo);
        }
    }

    public UserInfo logoin(String passwordMD5, String phone) {
        TblUserInfo tblUserInfo = userInfoDao.getUserInfo(passwordMD5, phone);
        return transModel(tblUserInfo);

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
