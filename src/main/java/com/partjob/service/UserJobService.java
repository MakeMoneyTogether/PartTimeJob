package com.partjob.service;

import com.partjob.dao.UserInfoDao;
import com.partjob.dao.UserJobDao;
import com.partjob.entity.TblRelUserJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.model.RelUserJob;
import com.partjob.utils.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 2017/5/21.
 */
@Service
@Transactional
public class UserJobService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserJobDao userJobDao;

    public List<RelUserJob> getUserJobsByStatus(int uid, int statusId){
        List<TblRelUserJob> tblRelUserJobs = userJobDao.getByStatus(uid, statusId);
        return transModelList(tblRelUserJobs);
    }

    public int getRelOfUserJob(String phone, int jid){
        TblUserInfo user = userInfoDao.getByPhone(phone);
        //检查用户是否存在, return -1 ?
        assert user != null;
        TblRelUserJob relUserJob = userJobDao.getByUidJid(user.getUid(), jid);
        if (relUserJob == null){
            return 0;
        } else return relUserJob.getStatusId();
    }

    private RelUserJob transModel(TblRelUserJob tblRelUserJob) {
        RelUserJob relUserJob = new RelUserJob();
        if (tblRelUserJob == null)
            return null;
        ApplicationUtil.copyProperties(tblRelUserJob, relUserJob);
        return relUserJob;
    }

    private List<RelUserJob> transModelList(List<TblRelUserJob> tblRelUserJobs) {
        if (tblRelUserJobs == null || tblRelUserJobs.size() == 0)
            return null;
        List<RelUserJob> userJobs = new ArrayList<RelUserJob>();
        for (TblRelUserJob tblRelUserJob : tblRelUserJobs) {
            RelUserJob relUserJob = new RelUserJob();
            ApplicationUtil.copyProperties(tblRelUserJob, relUserJob);
            userJobs.add(relUserJob);
        }
        return userJobs;
    }
}
