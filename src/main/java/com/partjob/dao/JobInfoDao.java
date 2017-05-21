package com.partjob.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblMchntInfo;
import com.partjob.utils.HibernateBaseDao;
@Repository
public class JobInfoDao extends HibernateBaseDao<TblJobInfo,Serializable>{

    public TblJobInfo getById(int jid){
        String hql = "from TblJobInfo job where job.jobId=:jid";
        return findUnique(hql, jid);
    }

    public List<TblJobInfo> getJobPage(int offset, int length){
        String hql = "from TblJobInfo job";
        return findPage(hql, offset, length);
    }

}
