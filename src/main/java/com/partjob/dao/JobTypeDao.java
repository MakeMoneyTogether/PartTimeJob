package com.partjob.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.partjob.entity.TblJobType;
import com.partjob.utils.HibernateBaseDao;
@Repository
public class JobTypeDao extends HibernateBaseDao<TblJobType,Serializable>{

}
