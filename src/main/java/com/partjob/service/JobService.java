package com.partjob.service;

import com.partjob.dao.JobInfoDao;
import com.partjob.dao.NetJobDao;
import com.partjob.dao.UserJobDao;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblNetJob;
import com.partjob.entity.TblRelUserJob;
import com.partjob.model.JobInfo;
import com.partjob.model.NetJob;
import com.partjob.model.UserInfo;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.BigDecimalUtil;

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
public class JobService {
    @Autowired
    private JobInfoDao jobInfoDao;
    @Autowired
    private NetJobDao netJobDao;
   

    public JobInfo getById(int jid){
        TblJobInfo tblJobInfo = jobInfoDao.getById(jid);
        return transJob(tblJobInfo);
    }

    public JobInfo addOnePeople(int jid){
    	TblJobInfo tblJobInfo = jobInfoDao.getById(jid);
    	tblJobInfo.setJoinNum(tblJobInfo.getJoinNum() +1);
    	if(tblJobInfo.getJoinNum() > tblJobInfo.getNumPeople()){
    		return null;
    	}else {
			jobInfoDao.saveOrUpdate(tblJobInfo);
			return transJob(tblJobInfo);
		}
    }
    public JobInfo mulOnePeople(int jid){
    	TblJobInfo tblJobInfo = jobInfoDao.getById(jid);
    	int nnum = tblJobInfo.getJoinNum() -1;
    	if(nnum < 0){
    		return null;
    	}
    	tblJobInfo.setJoinNum(nnum);
		jobInfoDao.saveOrUpdate(tblJobInfo);
		return transJob(tblJobInfo);
    }
    
    public NetJob getNetJobById(int jid){
        TblNetJob netJob = netJobDao.getById(jid);
        return transNetJob(netJob);
    }

    public List<NetJob> getNetJobPage(int offset, int length){
        List<TblNetJob> tblNetJobs = netJobDao.getJobPage(offset, length);
        assert tblNetJobs != null;
        return transNetJobList(tblNetJobs);
    }

    public List<JobInfo> getJobPage(int offset, int length){
        List<TblJobInfo> tblJobInfos = jobInfoDao.getJobPage(offset, length);
        assert tblJobInfos != null;
        return transJobList(tblJobInfos);
    }
    
    public List<JobInfo> getJobPage(int offset,int length,
    		String dises,String labels, String dates,int city){
    	
    	List<TblJobInfo> tblJobInfos=jobInfoDao.getJobPage(offset, length, dises, labels, dates, city);
    	if(tblJobInfos==null||tblJobInfos.size()==0){
    		return null;
    	}
    	else{
    		return transJobList(tblJobInfos);
    	}
    }
    
    public List<JobInfo> searchJobPage(int offset,int length,
    		String keys,int city){
    	
    	List<TblJobInfo> tblJobInfos=jobInfoDao.searchJobPage(offset, length, keys, city);
    	if(tblJobInfos==null||tblJobInfos.size()==0){
    		return null;
    	}
    	else{
    		return transJobList(tblJobInfos);
    	}
    }

   
    private JobInfo transJob(TblJobInfo tblJobInfo) {
        JobInfo jobInfo = new JobInfo();
        if (tblJobInfo == null)
            return null;
        ApplicationUtil.copyProperties(tblJobInfo, jobInfo);
        int smoney = tblJobInfo.getPaymentMoney();
        String tmoney = ""+smoney;
        tmoney = BigDecimalUtil.divide100(tmoney);
        jobInfo.setPaymentMoney(tmoney);
        return jobInfo;
    }
    
    

    private List<JobInfo> transJobList(List<TblJobInfo> tblJobInfos) {
        if (tblJobInfos == null || tblJobInfos.size() == 0)
            return null;
        List<JobInfo> jobInfos = new ArrayList<JobInfo>();
        for (TblJobInfo tblJobInfo : tblJobInfos) {
            JobInfo jobInfo = transJob(tblJobInfo);
            jobInfos.add(jobInfo);
        }
        return jobInfos;
    }

    private NetJob transNetJob(TblNetJob tblNetJob) {
        NetJob jobInfo = new NetJob();
        if (tblNetJob == null)
            return null;
        ApplicationUtil.copyProperties(tblNetJob, jobInfo);
        return jobInfo;
    }

    private List<NetJob> transNetJobList(List<TblNetJob> tblNetJobs) {
        if (tblNetJobs == null || tblNetJobs.size() == 0)
            return null;
        List<NetJob> netJobs = new ArrayList<NetJob>();
        for (TblNetJob tblNetJob : tblNetJobs) {
            NetJob netJob = new NetJob();
            ApplicationUtil.copyProperties(tblNetJob, netJob);
            netJobs.add(netJob);
        }
        return netJobs;
    }

}
