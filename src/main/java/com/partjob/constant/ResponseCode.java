package com.partjob.constant;

public class ResponseCode {

	//**********************系统返回代码********************//
	public static int SUCCESS=0;
	public static int FAIL=500;
	public static int FORBIDDEN=403;
	
	//**********************账户相关返回代码********************//
	public static int PHONE_PASSWORD_ERROR=1;//登录名或密码错误
	public static int VER_PASSWORD_ERROR=1;//原密码不正确
	public static int PHONE_EXIST=1;//手机号已被注册
	public static int VERCODE_SEND_ERROR=2;//验证码发送失败
	public static int VERCODE_ERROR=1;//验证码出错
	
	
	public static int PAY_FAIL=1;
	public static int NOT_ENOUGH_MONEY=1;
	
	//**********************工作相关返回代码********************//
	public static int JOB_UNAVAILABLE=2;
	public static int JOB_USER_UNAVAILABLE=3;
	public static int JOB_PAYMONEY_TO_LOW=4;
	
}
