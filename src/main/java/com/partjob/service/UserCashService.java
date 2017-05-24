package com.partjob.service;

import com.partjob.dao.UserScheduleDao;
import com.partjob.entity.TblNetJob;
import com.partjob.entity.TblUserSchedule;
import com.partjob.model.NetJob;
import com.partjob.model.UserInfo;
import com.partjob.model.UserSchedule;
import com.partjob.utils.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sloriac on 2017/5/24.
 */
@Service
@Transactional
public class UserCashService {

    @Autowired
    private UserScheduleDao userScheduleDao;
    @Autowired
    private UserService userService;

    public int cash(String phone, double rmb){
        UserInfo userInfo = userService.getByPhone(phone);
        //用户不存在
        if(userInfo == null){
            return 500;
        }
        //余额不足
        if (rmb > userInfo.getBalance()){
            return 1;
        }
        //写入用户提现请求
        TblUserSchedule tblUserSchedule = new TblUserSchedule();
        tblUserSchedule.setUid(userInfo.getUid());
        tblUserSchedule.setMoney(rmb);
        tblUserSchedule.setStatus(1); //正在处理
        userScheduleDao.add(tblUserSchedule);
        return 0;
    }

    public List<UserSchedule> getByPhone(String phone) {
        UserInfo userInfo = userService.getByPhone(phone);
        //用户不存在
        if(userInfo == null){
            return null;
        }
        return transModelList(userScheduleDao.getByUid(userInfo.getUid()));
    }

    private UserSchedule transModel(TblUserSchedule tblUserSchedule) {
        UserSchedule userSchedule = new UserSchedule();
        if (tblUserSchedule == null)
            return null;
        ApplicationUtil.copyProperties(tblUserSchedule, userSchedule);
        return userSchedule;
    }

    private List<UserSchedule> transModelList(List<TblUserSchedule> tblUserSchedules) {
        if (tblUserSchedules == null || tblUserSchedules.size() == 0)
            return null;
        List<UserSchedule> userSchedules = new ArrayList<UserSchedule>();
        for (TblUserSchedule tblUserSchedule : tblUserSchedules) {
            UserSchedule userSchedule = new UserSchedule();
            ApplicationUtil.copyProperties(tblUserSchedule, userSchedule);
            userSchedules.add(userSchedule);
        }
        return userSchedules;
    }
}
