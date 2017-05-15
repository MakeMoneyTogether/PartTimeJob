package com.partjob.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.Response;
import com.partjob.constant.CommonCanstant;
import com.partjob.constant.ResponseCode;
import com.partjob.dao.JobInfoDao;
import com.partjob.dao.MchntInfoDao;
import com.partjob.dao.UserInfoDao;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblMchntInfo;
import com.partjob.model.JobInfo;
import com.partjob.model.MchntInfo;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.BigDecimalUtil;
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
	@Autowired
	private TransService transService;
	@Autowired
	private JobInfoDao jobInfoDao;
	@Autowired
	private UserInfoDao suerInfoDao;
	/**
	 * 保存商户信息
	 * @param mchntInfo
	 * @param password
	 */
	public void saveMchnt(MchntInfo mchntInfo, String password) {

		TblMchntInfo tblMchntInfo = new TblMchntInfo();
		ApplicationUtil.copyProperties(mchntInfo, tblMchntInfo);
		tblMchntInfo.setBalance(Integer.parseInt(BigDecimalUtil.mult100(mchntInfo.getBalance())));
		tblMchntInfo.setPassword(CommonUtil.toMD5(password));
		tblMchntInfo.setMchntSt(1);

		mchntInfoDao.save(tblMchntInfo);
	}

	/**
	 * 修改密码，先验证原密码是否正确，正确再进行修改
	 * @param password 原密码
	 * @param phone
	 * @param rePassword 新密码
	 * @return
	 */
	public boolean updatePassword(String password,String phone,String rePassword){
		TblMchntInfo mchntInfo=mchntInfoDao.getMchntInfo(password, phone);
		if(mchntInfo==null){
			return false;
		}else{
			mchntInfo.setPassword(CommonUtil.toMD5(rePassword));
			
			mchntInfoDao.modify(mchntInfo);
			return true;
		}
	}
	/**
	 * 登录
	 * @param password
	 * @param phone
	 * @return
	 */
	public MchntInfo logoin(String password, String phone) {
		String passwordMd5 = CommonUtil.toMD5(password);
		TblMchntInfo tblMchntInfo = mchntInfoDao.getMchntInfo(passwordMd5,
				phone);
		return transModel(tblMchntInfo);

	}

	/**
	 * 检查手机号是否存在
	 * @param phone
	 * @param mchntInfo
	 * @return
	 */
	public boolean checkPhone(String phone, MchntInfo mchntInfo) {
		TblMchntInfo tblMchntInfo = mchntInfoDao.findUniqueByProperty("phone",
				phone);

		if ((tblMchntInfo == null && mchntInfo == null)
				|| (tblMchntInfo != null && mchntInfo.getMchntCd() == tblMchntInfo
						.getMchntCd())) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取商户信息
	 * @param mchntCd
	 * @return
	 */
	public MchntInfo getMchntInfo(int mchntCd) {
		TblMchntInfo tblMchntInfo = mchntInfoDao.get(mchntCd);
		return transModel(tblMchntInfo);
	}

	/**
	 * 更新商户信息
	 * @param mchntInfo
	 */
	public void updateMchtInfo(MchntInfo mchntInfo) {
		TblMchntInfo tblMchntInfo = mchntInfoDao.get(mchntInfo.getMchntCd());
		tblMchntInfo.setMchntName(mchntInfo.getMchntName());
		tblMchntInfo.setMchntAddress(mchntInfo.getMchntAddress());
		tblMchntInfo.setConnName(mchntInfo.getConnName());
		tblMchntInfo.setConnPhone(mchntInfo.getConnPhone());

		mchntInfoDao.modify(tblMchntInfo);
	}
	
	/**
	 * 商户充值
	 * @param totalFee	充值金额
	 * @param ip	交易发起ip
	 * @param openId	充值用户的openid
	 * @param mchntCd	商户充值的商户号
	 * @return
	 */
	public int pay(String totalFee, String ip,String openId,int mchntCd){
		
//		int transResult=transService.pay(totalFee, ip, openId);
//		if(transResult==ResponseCode.SUCCESS){
//			TblMchntInfo tblMchntInfo = mchntInfoDao.get(mchntCd);
//			tblMchntInfo.setBalance(tblMchntInfo.getBalance()+Integer.parseInt(totalFee));
//			mchntInfoDao.modify(tblMchntInfo);
//			return ResponseCode.SUCCESS;
//		}
//		return transResult;
		
		return 0;				
	}
	
	/**
	 * 发送兼职信息
	 * @param job
	 */
	public void postJob(JobInfo job,int mchntCd){
		TblJobInfo tblJob=new TblJobInfo();
		ApplicationUtil.copyProperties(job, tblJob);
		tblJob.setMchntCd(mchntCd);
		jobInfoDao.save(tblJob);
		
		//扣除商家账户金额
		int money=0;
		if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_HOUR)){
			
			
		}else if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_DAY)){
			
		}
		
		TblMchntInfo tblMchntInfo=mchntInfoDao.get(mchntCd);
		tblMchntInfo.setBalance(tblMchntInfo.getBalance()-money);
		mchntInfoDao.modify(tblMchntInfo);
	}
	
	
	public List<JobInfo> getJobInfoList(int mchntCd){
		List<TblJobInfo> tblJobInfoList=jobInfoDao.findByProperty("mchntCd", mchntCd);
		List<JobInfo>result=new ArrayList<JobInfo>();
		for(TblJobInfo temp:tblJobInfoList){
			JobInfo jobInfo=new JobInfo();
			ApplicationUtil.copyProperties(temp, jobInfo);
			result.add(jobInfo);
		}
		
		return result;
	}

	
	/**
	 * 数据转换
	 * @param tblMchntInfo
	 * @return
	 */
	private MchntInfo transModel(TblMchntInfo tblMchntInfo) {
		MchntInfo mchntInfo = new MchntInfo();
		if (tblMchntInfo == null)
			return null;
		ApplicationUtil.copyProperties(tblMchntInfo, mchntInfo);
		String balance=Integer.toString(tblMchntInfo.getBalance());
		mchntInfo.setBalance(BigDecimalUtil.divide100(balance));
		
		return mchntInfo;
	}

	
}
