package com.partjob.service;

import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.dao.JobInfoDao;
import com.partjob.dao.JobTypeDao;
import com.partjob.dao.NetJobDao;
import com.partjob.dao.UserJobDao;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblJobType;
import com.partjob.entity.TblNetJob;
import com.partjob.entity.TblRelUserJob;
import com.partjob.model.JobInfo;
import com.partjob.model.NetJob;
import com.partjob.model.UserInfo;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.BigDecimalUtil;

import org.apache.struts.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    MchntService mchntService;
    @Autowired
    JobTypeDao jobTypeDao;
    
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
    
    /**
     * 结束兼职
     * @param jid
     * @return
     */
    public int endJob(int jid){
    	TblJobInfo tblJobInfo = jobInfoDao.getById(jid);
    	tblJobInfo.setJobSt(CommonCanstant.JOB_END);
    	jobInfoDao.modify(tblJobInfo);
    	return ResponseCode.SUCCESS;
    }
    
    public void addNetJob(NetJob netJob){
    	TblNetJob tblNetJob  = new TblNetJob();
    	tblNetJob.setJobTitle(netJob.getJobTitle());
    	tblNetJob.setJobDesc(netJob.getJobDesc());
    	tblNetJob.setConnectName(netJob.getConnectName());
    	tblNetJob.setConnectPhone(netJob.getConnectPhone());
    	netJobDao.save(tblNetJob);
    }
    
    public NetJob getNetJobById(int jid){
        TblNetJob netJob = netJobDao.getById(jid);
        return transNetJob(netJob);
    }

    public List<NetJob> getAllNetJob(){
    	List<TblNetJob> tblNetJobs = netJobDao.getAll();
    	assert tblNetJobs != null;
    	return transNetJobList(tblNetJobs);
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

   
    public String getJobTypeName(int id){
    	TblJobType tblJobType=jobTypeDao.get(id);
    	return tblJobType.getName();
    }
    
    public List<Map<String, Object>> getAllJobType(){
    	List<TblJobType>list=jobTypeDao.getAll();
    	List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
    	
    	for(TblJobType type:list){
    		Map<String, Object>map=new HashMap<String, Object>();
//    		map.put(Integer.toString(type.getId()), type.getName());
        	map.put("lid", type.getId());
        	map.put("name", type.getName());
    		res.add(map);
    	}
    	return res;
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
        jobInfo.setMchntName(mchntService.getMchntInfo(tblJobInfo.getMchntCd()).getMchntName());
        jobInfo.setJobTypeName(getJobTypeName(jobInfo.getJobType()));
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

	public List<JobInfo> getUncheckJobs() {
		List<TblJobInfo> tJobInfos = jobInfoDao.getJobByStatus(CommonCanstant.JOB_AUDIT);
		return transJobList(tJobInfos);
	}

	/**
	 * 兼职进入准备中状态
	 * @param jid
	 */
	public void passJob(int jid) {
		TblJobInfo tblJobInfo = jobInfoDao.getById(jid);
		tblJobInfo.setJobSt(CommonCanstant.JOB_PENDING);
		jobInfoDao.modify(tblJobInfo);
	}

	public void refuseJob(int jid) {
		TblJobInfo tblJobInfo = jobInfoDao.getById(jid);
		tblJobInfo.setJobSt(CommonCanstant.JOB_REJECTED);
		jobInfoDao.modify(tblJobInfo);
	}

}
