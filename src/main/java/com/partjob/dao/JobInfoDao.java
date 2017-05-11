package com.partjob.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblMchntInfo;
import com.partjob.utils.HibernateBaseDao;
@Repository
public class JobInfoDao extends HibernateBaseDao<TblJobInfo,Serializable>{

}
