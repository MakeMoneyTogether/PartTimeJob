package com.partjob.service;

import com.partjob.dao.JobInfoDao;
import com.partjob.dao.NetJobDao;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblNetJob;
import com.partjob.model.JobInfo;
import com.partjob.model.NetJob;
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
public class JobService {
    @Autowired
    private JobInfoDao jobInfoDao;
    @Autowired
    private NetJobDao netJobDao;

    public JobInfo getById(int jid){
        TblJobInfo tblJobInfo = jobInfoDao.getById(jid);
        return transModel(tblJobInfo);
    }

    public NetJob getNetJobById(int jid){
        TblNetJob netJob = netJobDao.getById(jid);
        return transModel(netJob);
    }

    public List<JobInfo> getJobPage(int offset, int length){
        List<TblJobInfo> tblJobInfos = jobInfoDao.getJobPage(offset, length);
        assert tblJobInfos != null;
        return transModelList(tblJobInfos);
    }

    private JobInfo transModel(TblJobInfo tblJobInfo) {
        JobInfo jobInfo = new JobInfo();
        if (tblJobInfo == null)
            return null;
        ApplicationUtil.copyProperties(tblJobInfo, jobInfo);
        return jobInfo;
    }

    private List<JobInfo> transModelList(List<TblJobInfo> tblJobInfos) {
        if (tblJobInfos == null || tblJobInfos.size() == 0)
            return null;
        List<JobInfo> jobInfos = new ArrayList<JobInfo>();
        for (TblJobInfo tblJobInfo : tblJobInfos) {
            JobInfo jobInfo = new JobInfo();
            ApplicationUtil.copyProperties(tblJobInfo, jobInfo);
            jobInfos.add(jobInfo);
        }
        return jobInfos;
    }

    private NetJob transModel(TblNetJob tblJobInfo) {
        NetJob jobInfo = new NetJob();
        if (tblJobInfo == null)
            return null;
        ApplicationUtil.copyProperties(tblJobInfo, jobInfo);
        return jobInfo;
    }

}
