package com.partjob.dao;

import com.partjob.constant.CommonCanstant;
import com.partjob.entity.TblRelUserJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.utils.HibernateBaseDao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sloriac on 2017/4/16.
 * 用户信息的DAO类
 */
@Repository
public class UserJobDao extends HibernateBaseDao<TblRelUserJob, Serializable> {

    public void update(TblRelUserJob tblRelUserJob) {
        super.saveOrUpdate(tblRelUserJob);
    }

    public List<TblRelUserJob> getByUid(int uid) {
        String hql = "from TblRelUserJob ujob where ujob.uid=?";
        return find(hql, uid);
    }
    public List<TblRelUserJob> getByStatus(int uid, int statusId) {
        String hql = "from TblRelUserJob ujob where ujob.uid=? and statusId=?";
        return find(hql, uid, statusId);
    }

    public TblRelUserJob getByUidJid(int uid, int jid) {
        String hql = "from TblRelUserJob ujob where ujob.uid=? and jobId=?";
        return findUnique(hql, uid, jid);
    }
    
    public List<TblRelUserJob>getByJob(int jobId){
    	String hql="from TblRelUserJob ujob where ujob.jobId=? and ujob.statusId=? order by recCrtTime desc";
    	return find(hql, jobId,CommonCanstant.USER_PASS);
    }
    
    public List<TblRelUserJob>getAvailByUid(int uid){
    	String hql = "from TblRelUserJob ujob where ujob.uid=? and statusId <> ?";
        return find(hql, uid,CommonCanstant.USER_REFUSE);
    }
    public List<TblRelUserJob>getAvailByJid(int jid){
    	String hql = "from TblRelUserJob ujob where ujob.jobId=? and statusId <> ?";
        return find(hql, jid,CommonCanstant.USER_REFUSE);
    }
    
}
