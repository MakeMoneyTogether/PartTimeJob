package com.partjob.service;

import com.partjob.dao.MchntInfoDao;
import com.partjob.entity.TblMchntInfo;
import com.partjob.model.MchntInfo;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MPJ on 17/4/8.
 */
@Service
@Transactional
public class MchntService {
    @Autowired
    private MchntInfoDao mchntInfoDao;
    public void saveMchnt(MchntInfo mchntInfo,String password){


        TblMchntInfo tblMchntInfo=new TblMchntInfo();
        ApplicationUtil.copyProperties(mchntInfo,tblMchntInfo);
        tblMchntInfo.setPassword(CommonUtil.toMD5(password));
        tblMchntInfo.setMchntSt(1);
        
        mchntInfoDao.save(tblMchntInfo);
    }
}
