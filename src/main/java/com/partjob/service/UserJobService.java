package com.partjob.service;

import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.constant.TransCanstant;
import com.partjob.dao.JobInfoDao;
import com.partjob.dao.UserInfoDao;
import com.partjob.dao.UserJobDao;
import com.partjob.dao.UserScheduleDao;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblRelUserJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.model.ApplyJobResponse;
import com.partjob.model.JobInfo;
import com.partjob.model.RelUserJob;
import com.partjob.model.UserInfo;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.CommonUtil;
import com.partjob.utils.ListUtil;
import com.partjob.utils.VerificationUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private JobInfoDao jobInfoDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserScheduleDao userScheduleDao;

    public List<RelUserJob> getUserJobsByStatus(int uid, int statusId){
        List<TblRelUserJob> tblRelUserJobs = userJobDao.getByStatus(uid, statusId);
        return transModelList(tblRelUserJobs);
    }
    
    public List<RelUserJob> getUserJobsByStatus(int uid){
        List<TblRelUserJob> tblRelUserJobs = userJobDao.getByStatus(uid);
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
            jobInfo.setUserJobStatu(relUserJob.getStatusId());
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
            return CommonCanstant.USER_NOT_JOIN;
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
    		user.setStatusId(CommonCanstant.USER_REFUSE);
    		userJobDao.modify(user);
    		//参与人数减一
    		jobService.mulOnePeople(jobId);
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
//        UserInfo userInfo= userService.getByPhone(phone);
        TblUserInfo userInfo=userInfoDao.getByPhone(phone);
        JobInfo jobInfo = jobService.getById(jid);
        //如果兼职开始时间小于当前时间,就不允许报名
        if(jobInfo.getJobValidateTime().getTime()<new Date().getTime()){
        	return null;
        }
        if(!checkUserJob(userInfo.getUid(), jobInfo)){
        	return null;
        }
        if(userInfo.getBalance()<CommonCanstant.USER_WORK_CHECK_MONRY){
        	return null;
        }
        //检查用户
        // 检查兼职
        if(jobInfo.getJobSt() != CommonCanstant.JOB_PENDING){//如果兼职不是准备中
        	return null;
        }
        if(jobInfo.getJoinNum() == jobInfo.getNumPeople()){//兼职人满了
        	return null;
        }
        TblRelUserJob tblRelUserJob = new TblRelUserJob();
        tblRelUserJob.setUid(userInfo.getUid());
        tblRelUserJob.setJobId(jobInfo.getJobId());
        tblRelUserJob.setJobTitle(jobInfo.getJobTitle());
        tblRelUserJob.setRecCrtTime(CommonUtil.getTimestamp());
        tblRelUserJob.setStatusId(CommonCanstant.USER_PASS);
        try {
        	jobInfo = jobService.addOnePeople(jid);	//参与人数加一
        	if(jobInfo == null){
        		return null;
        	}
        	
        	userJobDao.save(tblRelUserJob);
        	
        	//报名成功，用户的余额要扣除两元，并且记录交易流水
        	TblUserInfo tblUser=userInfoDao.get(userInfo.getUid());
        	tblUser.setBalance(tblUser.getBalance()-CommonCanstant.USER_WORK_CHECK_MONRY);
        	userScheduleDao.add(userInfo.getUid(), CommonCanstant.USER_WORK_CHECK_MONRY, CommonCanstant.MONEY_TYPE_DEPOSIT, "", false);
        	
        	response.setCode(ResponseCode.SUCCESS);
        	response.setAll(jobInfo.getNumPeople());
        	response.setApplied(jobInfo.getJoinNum());
        	
        	try {
				VerificationUtil.sendNotify(phone,userInfo.getName() , jobInfo.getMchntName(), jobInfo.getJobTitle());
			} catch (Exception e) {
			}
        	
        	return response;
		} catch (Exception e) {
			return null;
		}
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
    
    /**
     * 检查用户要报名的兼职和已报名的兼职是否发生冲突
     * @param uid
     * @param job
     * @return
     */
    private boolean checkUserJob(int uid,JobInfo job){
    	List<TblRelUserJob>list=userJobDao.getAvailByUid(uid);
    	for(TblRelUserJob userJob:list){
    		TblJobInfo tblJobInfo=jobInfoDao.get(userJob.getJobId());
    		
    		
    		//判断要报名的兼职开始时间或者结束时间在已报名兼职的工作时间之内，认为出现时间交叉
    		if((job.getJobStartTime().getTime()>tblJobInfo.getJobStartTime().getTime()
    				&&job.getJobStartTime().getTime()<tblJobInfo.getJobEndTime().getTime())
    				||(job.getJobEndTime().getTime()>tblJobInfo.getJobStartTime().getTime()
    	    				&&job.getJobEndTime().getTime()<tblJobInfo.getJobEndTime().getTime()))
    		
    			return false;
    	}
    	
    	return true;
    	
    }
}
