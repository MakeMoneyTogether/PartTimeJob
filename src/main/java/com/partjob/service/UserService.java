package com.partjob.service;

import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.dao.InvitationRecordDao;
import com.partjob.dao.UserInfoDao;
import com.partjob.dao.UserJobDao;
import com.partjob.entity.TblInvitationRecord;
import com.partjob.entity.TblUserInfo;
import com.partjob.model.InvitationInfo;
import com.partjob.model.UserInfo;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.BigDecimalUtil;
import com.partjob.utils.CommonUtil;
import com.partjob.utils.InvitationCodeUtil;
import com.partjob.utils.ListUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
    @Autowired
    private InvitationRecordDao invitationRecordDao;

    public int register(UserInfo userInfo,String code, String invitation) {
        // 先检查手机有没有注册过
        TblUserInfo tblUserInfo = userInfoDao.getByPhone(userInfo.getPhone());
        if (tblUserInfo != null) return ResponseCode.FAIL;
        // TODO 验证码检查
        tblUserInfo = new TblUserInfo();
        tblUserInfo.setName(userInfo.getName());
    	tblUserInfo.setSchool(userInfo.getSchool());
    	tblUserInfo.setMajor(userInfo.getMajor());
    	tblUserInfo.setDirection(userInfo.getDirection());
        tblUserInfo.setPhone(userInfo.getPhone());
        tblUserInfo.setPwd(CommonUtil.toMD5(userInfo.getPwd()));
        tblUserInfo.setUserSt(CommonCanstant.AVAILAB);
        tblUserInfo.setBalance(0);
        userInfoDao.save(tblUserInfo);
        tblUserInfo = userInfoDao.getByPhone(userInfo.getPhone());
        // 生成邀请码
        tblUserInfo.setShareCode(InvitationCodeUtil.toSerialCode(tblUserInfo.getUid()));
        userInfoDao.update(tblUserInfo);
        // 更新邀请信息表
        if (invitation != null && invitation.length() == 6){
            int invitor = InvitationCodeUtil.codeToId(invitation);
            TblInvitationRecord record = new TblInvitationRecord();
            record.setUid(tblUserInfo.getUid());
            record.setInvitorId(invitor);
            record.setStatus(0); //使用邀请码注册
            invitationRecordDao.save(record);
        }
        return ResponseCode.SUCCESS;
    }

    public UserInfo login(String phone, String pwd) {
        pwd = CommonUtil.toMD5(pwd);
        TblUserInfo tblUserInfo = userInfoDao.getUserInfo(phone, pwd);
        userInfoDao.setScore(tblUserInfo.getUid());
        return transModel(tblUserInfo);
    }
    
    public UserInfo getUser(int id){
    	TblUserInfo tblUserInfo = userInfoDao.get(id);
    	return transModel(tblUserInfo);
    }

    public List<UserInfo> getAllUser(){
    	List<TblUserInfo>list= userInfoDao.getAllUser();
    	List<UserInfo>users=new ArrayList<UserInfo>();
    	for(TblUserInfo tblUser:list){
    		UserInfo user=transModel(tblUser);
    		users.add(user);
    	}
    	return users;
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
    
    public int forgot(String phone,String pwd){
    	pwd = CommonUtil.toMD5(pwd);
        TblUserInfo tblUserInfo = userInfoDao.getByPhone(phone);
        if (tblUserInfo == null) return ResponseCode.FAIL;
        else {
            tblUserInfo.setPwd(CommonUtil.toMD5(pwd));
            userInfoDao.update(tblUserInfo);
            return ResponseCode.SUCCESS;
        }
    }
    
    public int updatecv(UserInfo userInfo,String phone,String pwd){
    	TblUserInfo tblUserInfo = userInfoDao.getUserInfo(phone, pwd);
    	tblUserInfo.setName(userInfo.getName());
    	tblUserInfo.setBirthday(userInfo.getBirthday());
    	tblUserInfo.setSchool(userInfo.getSchool());
    	tblUserInfo.setGender(userInfo.getGender());
    	tblUserInfo.setMajor(userInfo.getMajor());
    	tblUserInfo.setDirection(userInfo.getDirection());
    	tblUserInfo.setConnectPhone(userInfo.getConnectPhone());
    	userInfoDao.update(tblUserInfo);
    	return ResponseCode.SUCCESS;
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
    
    public boolean checkPhone(String phone){
    	TblUserInfo tblUserInfo = userInfoDao.findUniqueByProperty("phone", phone);
        if (tblUserInfo == null){
        	return false;
        }else {
			return true;
		}
    }

    /**
     * 冻结用户
     * @param userId
     * @return
     */
    public int freezeUser(int userId){
    	TblUserInfo userInfo=userInfoDao.get(userId);
    	userInfo.setUserSt(CommonCanstant.UNAVAILAB);
    	userInfoDao.modify(userInfo);
    	return ResponseCode.SUCCESS;
    }
    public List<InvitationInfo> getInvitations(String phone){
        // 先检查手机有没有注册过
        TblUserInfo tblUserInfo = userInfoDao.getByPhone(phone);
        if (tblUserInfo == null) return null;
        List<TblInvitationRecord> records = invitationRecordDao.getByInviterId(tblUserInfo.getUid());
        if (ListUtil.isEmpty(records)) return null;
        List<InvitationInfo> invitationInfos =  new ArrayList<InvitationInfo>();
        for (TblInvitationRecord record : records) {
            TblUserInfo user = userInfoDao.get(record.getUid());
            invitationInfos.add(new InvitationInfo(user.getName(), record.getStatus()));
        }
        return invitationInfos;
    }

    private UserInfo transModel(TblUserInfo tblUserInfo) {
        UserInfo userInfo = new UserInfo();
        if (tblUserInfo == null)
            return null;
        ApplicationUtil.copyProperties(tblUserInfo, userInfo);
        String balance=Integer.toString(tblUserInfo.getBalance());
        userInfo.setBalance(BigDecimalUtil.divide100(balance));
        return userInfo;
    }

	public int passUser(int userId) {
    	TblUserInfo userInfo=userInfoDao.get(userId);
    	userInfo.setUserSt(CommonCanstant.AVAILAB);
    	userInfoDao.modify(userInfo);
    	return ResponseCode.SUCCESS;
	}

	/**
	 * 模糊搜索用户
	 * @param userkey
	 * @return
	 */
	public List<UserInfo> getUserByKey(String userkey) {
		// TODO Auto-generated method stub
		List<TblUserInfo> tblUserInfos=userInfoDao.getUserByName(userkey);
		List<UserInfo> list=new ArrayList<UserInfo>();
		for(TblUserInfo tblUserInfo:tblUserInfos){
			list.add(transModel(tblUserInfo));
		}
		return list;
	}

}
