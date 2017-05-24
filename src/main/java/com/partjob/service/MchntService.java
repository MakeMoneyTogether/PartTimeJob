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
import com.partjob.dao.UserJobDao;
import com.partjob.entity.TblJobInfo;
import com.partjob.entity.TblMchntInfo;
import com.partjob.entity.TblRelUserJob;
import com.partjob.entity.TblUserInfo;
import com.partjob.model.CheckTransResult;
import com.partjob.model.JobInfo;
import com.partjob.model.MchntInfo;
import com.partjob.model.WcPay;
import com.partjob.utils.ApplicationUtil;
import com.partjob.utils.BigDecimalUtil;
import com.partjob.utils.CommonUtil;

import org.apache.log4j.Logger;
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
	private UserInfoDao userInfoDao;
	@Autowired
	private UserJobDao userJobDao;
	/**
	 * 保存商户信息
	 * @param mchntInfo
	 * @param password
	 */
	public void saveMchnt(MchntInfo mchntInfo, String password) {

		TblMchntInfo tblMchntInfo = new TblMchntInfo();
		ApplicationUtil.copyProperties(mchntInfo, tblMchntInfo);
		tblMchntInfo.setBalance(0);
		tblMchntInfo.setFrozenMoney(0);
		tblMchntInfo.setPassword(CommonUtil.toMD5(password));
		tblMchntInfo.setMchntSt(CommonCanstant.UNCHECKED);

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
		TblMchntInfo mchntInfo=mchntInfoDao.getMchntInfo(CommonUtil.toMD5(password), phone);
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
	public WcPay pay(String totalFee, String ip,String openId,int mchntCd){
		return transService.pay(totalFee, ip, openId);				
	}
	
	/**
	 * 检查充值结果，并作用于账户余额
	 * @param outTradeNo
	 * @param mchntCd
	 * @return
	 */
	public int checkPay(String outTradeNo,int mchntCd){
		CheckTransResult checkResult=transService.checkPay(outTradeNo);
		
		if(checkResult.getReturn_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getResult_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getTrade_state().equalsIgnoreCase("SUCCESS")){
			
			TblMchntInfo tblMchntInfo = mchntInfoDao.get(mchntCd);
			tblMchntInfo.setBalance(tblMchntInfo.getBalance()+Integer.parseInt(checkResult.getTotal_fee()));
			mchntInfoDao.modify(tblMchntInfo);
			return ResponseCode.SUCCESS;
			
		}else if(checkResult.getReturn_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getResult_code().equalsIgnoreCase("SUCCESS")&&
				checkResult.getTrade_state().equalsIgnoreCase("USERPAYING")){
			//如果检查出是用户正在支付，那么睡眠1s重新进行检查
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return ResponseCode.PAY_FAIL;
			}
			transService.checkPay(outTradeNo);
		}else{
			return ResponseCode.PAY_FAIL;
		}
		return ResponseCode.PAY_FAIL;
	}
	
	/**
	 * 发送兼职信息
	 * @param job
	 */
	public int postJob(JobInfo job,int mchntCd){
		TblJobInfo tblJob=new TblJobInfo();
		ApplicationUtil.copyProperties(job, tblJob);
		tblJob.setMchntCd(mchntCd);
		tblJob.setJobSt(CommonCanstant.UNCHECKED);
		
		//检查兼职是否满足要求
		int result=checkJob(job);
		if(result!=ResponseCode.SUCCESS){
			return result;
		}
		
		//扣除商家账户金额
		int money=0;
		int paymentMoney=Integer.parseInt(BigDecimalUtil.mult100(job.getPaymentMoney()));
		long time=job.getJobEndTime().getTime()-job.getJobStartTime().getTime();
		//判断商户的计费类型
		if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_HOUR)){
			int hour=(int) (time/1000/60);
			money=paymentMoney*job.getNumPeople()*hour;
			
		}else if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_DAY)){
			int day=(int)(time/1000/60/24);
			money=paymentMoney*job.getNumPeople()*day;
		}
		
		TblMchntInfo tblMchntInfo=mchntInfoDao.get(mchntCd);
		//如果商户的账户余额太低则不能发布兼职
		if(tblMchntInfo.getBalance()<money){
			return ResponseCode.NOT_ENOUGH_MONEY;
		}
		tblMchntInfo.setBalance(tblMchntInfo.getBalance()-money);
		tblMchntInfo.setFrozenMoney(money);
		
		//最后在提交
		jobInfoDao.save(tblJob);
		mchntInfoDao.modify(tblMchntInfo);
		return ResponseCode.SUCCESS;
	}
	
	/**
	 * 清算
	 * @param jobId
	 * @return
	 */
	public int clearJob(int jobId){
		List<TblRelUserJob>list=userJobDao.getByJob(jobId);
		TblJobInfo job=jobInfoDao.get(jobId);
		
		//扣除商家账户金额
		int money=0;
		long time=job.getJobEndTime().getTime()-job.getJobStartTime().getTime();
		//判断商户的计费类型
		if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_HOUR)){
			int hour=(int) (time/1000/60);
			money=job.getPaymentMoney()*hour;
			
		}else if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_DAY)){
			int day=(int)(time/1000/60/24);
			money=job.getPaymentMoney()*day;
		}		
		
		int numpeople=job.getNumPeople();
		
		//计算有效参加工作的人数，同时给用户进行结算
		int validateNum =0;//有效参加工作的人
		for(TblRelUserJob userJob:list){
			TblUserInfo user=userInfoDao.get(userJob.getUid());
			if(userJob.getStatusId()==CommonCanstant.USER_WORK_FULL){
				validateNum++;
				user.setBalance(user.getBalance()+money+CommonCanstant.USER_WORK_CHECK_MONRY);
				userInfoDao.modify(user);
			}
		}
		//将为参加兼职人数的资金回返到商户
		TblMchntInfo mchnt=mchntInfoDao.get(job.getMchntCd());
		mchnt.setBalance(mchnt.getBalance()+(numpeople-validateNum)*money);
		mchntInfoDao.modify(mchnt);
		
		return ResponseCode.SUCCESS;
	}
	
	
	
	
	/**
	 * 获取该商户下的所有兼职
	 * @param mchntCd
	 * @return
	 */
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
		String frozenMonry=Integer.toString(tblMchntInfo.getFrozenMoney());
		mchntInfo.setFrozenMoney(BigDecimalUtil.divide100(frozenMonry));
		
		return mchntInfo;
	}
	
	
	
	/**
	 * 检查兼职任务
	 * @param job
	 * @return
	 */
	private int checkJob(JobInfo job){
		int paymentMoney=Integer.parseInt(BigDecimalUtil.mult100(job.getPaymentMoney()));
		
		if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_HOUR)){
			if(paymentMoney<CommonCanstant.MONEY_LEV_HOUR){
				return ResponseCode.JOB_PAYMONEY_TO_LOW;
			}
			return ResponseCode.SUCCESS;
			
		}else if(job.getPaymentType().equals(CommonCanstant.PAY_TYPE_DAY)){
			if(paymentMoney<CommonCanstant.MONEY_LEV_DAY){
				return ResponseCode.JOB_PAYMONEY_TO_LOW;
			}
			return ResponseCode.SUCCESS;
		}
		return ResponseCode.SUCCESS;
	}

	public static void main(String[] args) {
		String str="<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx2421b1c4370ec43b]]></appid><mch_id><![CDATA[10000100]]></mch_id><device_info><![CDATA[1000]]></device_info><nonce_str><![CDATA[TN55wO9Pba5yENl8]]></nonce_str><sign><![CDATA[BDF0099C15FF7BC6B1585FBB110AB635]]></sign><result_code><![CDATA[SUCCESS]]></result_code></xml> ";
		CheckTransResult result=CommonUtil.xml2Object(str, CheckTransResult.class);
		System.out.println(result);
	}
}
