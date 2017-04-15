package com.partjob.service;

import java.util.List;

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

	/**
	 * 保存商户信息
	 * @param mchntInfo
	 * @param password
	 */
	public void saveMchnt(MchntInfo mchntInfo, String password) {

		TblMchntInfo tblMchntInfo = new TblMchntInfo();
		ApplicationUtil.copyProperties(mchntInfo, tblMchntInfo);
		tblMchntInfo.setPassword(CommonUtil.toMD5(password));
		tblMchntInfo.setMchntSt(1);

		mchntInfoDao.save(tblMchntInfo);
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
	 * 数据转换
	 * @param tblMchntInfo
	 * @return
	 */
	private MchntInfo transModel(TblMchntInfo tblMchntInfo) {
		MchntInfo mchntInfo = new MchntInfo();
		if (tblMchntInfo == null)
			return null;
		ApplicationUtil.copyProperties(tblMchntInfo, mchntInfo);
		return mchntInfo;
	}

}
