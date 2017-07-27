package com.partjob.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.partjob.entity.TblAboutMe;
import com.partjob.utils.HibernateBaseDao;

@Repository
public class AboutMeDao extends HibernateBaseDao<TblAboutMe, Serializable>{

}
