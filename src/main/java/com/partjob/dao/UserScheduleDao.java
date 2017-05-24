package com.partjob.dao;

import com.partjob.entity.TblUserSchedule;
import com.partjob.utils.HibernateBaseDao;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sloriac on 2017/5/24.
 */
@Repository
public class UserScheduleDao extends HibernateBaseDao<TblUserSchedule, Serializable> {

    public void add(TblUserSchedule tblUserSchedule){
        save(tblUserSchedule);
    }

    public List<TblUserSchedule> getByUid(int uid){
        String hql="from TblUserSchedule us where us.uid=?";
        return find(hql, uid);
    }
}
