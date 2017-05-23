<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>兼职-商家端</title>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="static/css/weui.min.css">
<link rel="stylesheet" href="static/css/jquery-weui.min.css">
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link rel="stylesheet" href="static/css/jz.css">
<link rel="stylesheet" href="static/css/col.css">
</head>
<body ontouchstart>
	<div class="weui-flex">
		<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left" style="color:#010101;margin-top: 90%;"></i></a></div>
		<div class="weui-flex__item placeholder">
			<span id="jobTitle">${job.jobTitle }</span>
		</div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">薪资：</label>
				<span class="weui-form-preview__value" style="color:red;" id="paymentMoney">${job.paymentMoney }</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">类型：</label>
				<span class="weui-form-preview__value" id="jobType">${job.jobType }</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">人数：</label>
				<span class="weui-form-preview__value" id="numPeople">${job.numPeople }</span>
			</div>
		</div>
		<div class="weui-form-preview__hd" style="padding:0;"></div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">工作日期：</label>
				<span class="weui-form-preview__value" id="jobStartTime">${job.jobStartTime }</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">结束日期：</label>
				<span class="weui-form-preview__value" id="jobEndTime">${job.jobEndTime }</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">兼职有效期：</label>
				<span class="weui-form-preview__value" id="jobValidateTime">${job.jobValidateTime }</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">详细地址：</label>
				<span class="weui-form-preview__value" id="jobAddress">${job.jobAddress }</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">联系人员：</label>
				<span class="weui-form-preview__value" id="connectName">${job.connectName }</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">联系电话：</label>
				<span class="weui-form-preview__value" id="connectPhone">${job.connectPhone }</span>
			</div>
		</div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">工作内容：</label>
				<br>
				<span class="info-desc" id="jobDesc">
					${job.jobDesc }
				</span>
			</div>
		</div>
	</div>
	<div>
		<h1>&nbsp;</h1>
	</div>
	<div class="weui-tabbar">
		<a href="mchntp/peoplem/${job.jobId }" class="weui-tabbar__item">
			<div class="weui-tabbar__icon">
				<i class="fa fa-users"></i>
			</div>
			<p class="weui-tabbar__label">人员录用</p>
		</a>
		<a href="mchntp/peoplem/${job.jobId }" class="weui-tabbar__item">
			<div class="weui-tabbar__icon">
				<i class="fa fa-comment"></i>
			</div>
			<p class="weui-tabbar__label">人员评价</p>
		</a>
	</div>
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/merchant/merchant.js"></script>
<script src="static/js/merchant/info.js"></script>
<script>
	$(function() {
	FastClick.attach(document.body);
	});
//	loadJob();
</script>
</body>
</html>