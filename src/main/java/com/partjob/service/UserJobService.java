package com.partjob.service;

import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
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
    
    public List<UserInfo> getUsersOfJob(int jobId){
    	List<TblRelUserJob> list=userJobDao.getByJob(jobId);
    	List<UserInfo>users=new ArrayList<UserInfo>();
    	for(TblRelUserJob tblRelUserJob:list){
    		UserInfo user=userService.getUser(tblRelUserJob.getUid());
    		users.add(user);
    	}
    	return users;
    }

    public int getRelOfUserJob(String phone, int jid){
        UserInfo user = userService.getByPhone(phone);
        if (user == null) return 500;
        TblRelUserJob relUserJob = userJobDao.getByUidJid(user.getUid(), jid);
        if (relUserJob == null){
            return 0;
        } else return relUserJob.getStatusId();
    }
    
    /**
     * 审核用户拒绝参加兼职
     * @param userId
     * @param jobId
     * @return
     */
    	public int noPassUser(int userId,int jobId){
    		TblRelUserJob user=userJobDao.getByUidJid(userId,jobId);
    		// TODO 检查兼职任务的状态是否有效的，当兼职任务过期以后不能再对用户进行拒绝操作
    		user.setStatusId(CommonCanstant.USER_NOT_PASS);
    		userJobDao.modify(user);
    		return ResponseCode.SUCCESS;
    	}
    	
    	public int passUser(int userId,int jobId){
    		TblRelUserJob user=userJobDao.getByUidJid(userId,jobId);
    		// TODO 检查兼职任务的状态是否有效的，当兼职任务过期以后不能再对用户进行同意操作
    		user.setStatusId(CommonCanstant.USER_PASS);
    		userJobDao.modify(user);
    		return ResponseCode.SUCCESS;
    	}
    	
    	/**
    	 * 商户给用户考评
    	 * @param userId
    	 * @param jobId
    	 * @param status
    	 * @return
    	 */
    	public int checkUserWork(int userId,int jobId,int status){
    		TblRelUserJob user=userJobDao.getByUidJid(userId,jobId);
    		if(user.getStatusId()==CommonCanstant.USER_PASS){
    			user.setStatusId(status);
    			userJobDao.modify(user);
    			return ResponseCode.SUCCESS;
    		}else{
    			return ResponseCode.JOB_USER_UNAVAILABLE;
    		}
    	}
    	
    	/**
    	 * 给用户的工作评分
    	 * @param userId
    	 * @param jobId
    	 * @param score
    	 * @return
    	 */
    	public int scoreUserWork(int userId,int jobId,int score){
    		TblRelUserJob user=userJobDao.getByUidJid(userId,jobId);
    		if(user==null){
    			return ResponseCode.JOB_USER_UNAVAILABLE;
    		}
    		if(user.getStatusId()==CommonCanstant.USER_PASS){
//    			user.setStatusId(status);
    			user.setScore(score);
    			userJobDao.modify(user);
    			return ResponseCode.SUCCESS;
    		}else{
    			return ResponseCode.JOB_USER_UNAVAILABLE;
    		}
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
