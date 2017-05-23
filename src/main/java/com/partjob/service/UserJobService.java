package com.partjob.service;

import com.partjob.dao.JobInfoDao;
import com.partjob.dao.UserInfoDao;
import com.partjob.dao.UserJobDao;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblRelUserJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.model.ApplyJobResponse;
import com.partjob.model.JobInfo;
import com.partjob.model.RelUserJob;
import com.partjob.model.UserInfo;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.ListUtil;
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
    private UserJobDao userJobDao;
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;

    public List<RelUserJob> getUserJobsByStatus(int uid, int statusId){
        List<TblRelUserJob> tblRelUserJobs = userJobDao.getByStatus(uid, statusId);
        return transModelList(tblRelUserJobs);
    }

    public List<JobInfo> getJobsByRels(List<RelUserJob> relUserJobs){
        if (ListUtil.isEmpty(relUserJobs)){
            return null;
        }
        List<JobInfo> jobInfos = new ArrayList<JobInfo>();
        for (RelUserJob relUserJob : relUserJobs) {
            JobInfo jobInfo = jobService.getById(relUserJob.getJobId());
            assert jobInfo != null;
            jobInfos.add(jobInfo);
        }
        return jobInfos;
    }

    public int getRelOfUserJob(String phone, int jid){
        UserInfo user = userService.getByPhone(phone);
        if (user == null) return 500;
        TblRelUserJob relUserJob = userJobDao.getByUidJid(user.getUid(), jid);
        if (relUserJob == null){
            return 0;
        } else return relUserJob.getStatusId();
    }

    public ApplyJobResponse applyJob(String phone, int jid){
        ApplyJobResponse response = new ApplyJobResponse();
        //检查用户
        // 检查兼职
        // 检查用户是不是已经满了
        return response;
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
