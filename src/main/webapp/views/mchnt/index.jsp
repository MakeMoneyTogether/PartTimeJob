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
<div class="weui-tabbar" style="position:fixed;">
	<div class="weui-navbar__item" onclick="jump(1);">
		发布兼职
	</div>
	<div class="weui-navbar__item" onclick="jump(2);">
		兼职列表
	</div>
	<div class="weui-navbar__item weui-bar__item--on" onclick="jump(3);">
		商户中心
	</div>
</div>
<div class="weui-form-preview">
	<div class="weui-form-preview__hd">
		<div class="col-20 div_left">
			<img style="width:100%;" alt="统一头像" src="static/images/1.jpg">
		</div>
		<div class="col-50 div_left">
			<span style="margin-left:5%;float:left;" id="mname">商户名称</span><br>
		</div>
		<div style="clear:both"></div>
	</div>
</div>
<div class="weui-cells weui-flex div_label_out">
	<div class="weui-flex__item div_box div_label_in">
		可用资产:<br><span id="moneyable">1000.00</span>元
	</div>
	<div class="weui-flex__item div_box div_label_in">
		冻结资产:<br><span id="moneyice">1000.00</span>元
	</div>
</div>
<div class="weui-cells" style="margin-top:0;">
	<div class="weui-cell weui-cell_access" onclick="jump(4);">
		<div class="weui-cell__hd">
			<i class="fa fa-fax"></i>
		</div>
		<div class="weui-cell__bd">
			<p style="margin-left:10%;">修改信息</p>
		</div>
		<div class="weui-cell__ft">
		</div>
	</div>
	<div class="weui-cell weui-cell_access" onclick="jump(5);">
		<div class="weui-cell__hd">
			<i class="fa fa-money"></i>
		</div>
		<div class="weui-cell__bd">
			<p style="margin-left:10%;">资产操作</p>
		</div>
		<div class="weui-cell__ft">
		</div>
	</div>
	<div class="weui-cell weui-cell_access" onclick="jump(6);">
		<div class="weui-cell__hd">
			<i class="fa fa-cog"></i>
		</div>
		<div class="weui-cell__bd">
			<p style="margin-left:10%;">修改密码</p>
		</div>
		<div class="weui-cell__ft">
		</div>
	</div>
	<div class="weui-cell weui-cell_access" onclick="jump(7);">
		<div class="weui-cell__hd">
			<i class="fa fa-sign-out"></i>
		</div>
		<div class="weui-cell__bd">
			<p style="margin-left:10%;">退出登录</p>
		</div>
		<div class="weui-cell__ft">
		</div>
	</div>
</div>
<div id="repass" class="weui-popup__container popup-bottom" style="background: rgba(0, 0, 0, 0.63);">
<div class="weui-popup__overlay" ></div>
<div class="weui-popup__modal" style="height:70%;background: rgba(0, 0, 0, 0);">
	<div class="weui-form-preview">
		<center><h2>修改密码</h2></center>
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">原密码</label></div>
			<div class="weui-cell__bd">
				<input id="pwd" class="weui-input" type="password" placeholder="请输入原密码">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">新密码</label></div>
			<div class="weui-cell__bd">
				<input id="npwd" class="weui-input" type="password" placeholder="请输入新密码">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">确认密码</label></div>
			<div class="weui-cell__bd">
				<input id="rnpwd" class="weui-input" type="password" placeholder="请重复输入新密码">
			</div>
		</div>
	</div>
	<div class="weui-flex">
	<div class="weui-flex__item" style="padding:5%;">
		<a class="weui-btn weui-btn_mini weui-btn_default close-popup" style="width:80%;">返回</a>
	</div>
	<div class="weui-flex__item" style="padding:5%;">
		<a onclick="repwd();" class="weui-btn weui-btn_mini weui-btn_primary" style="width:80%;">修改</a>
	</div>
</div>
</div>
</div>
<div>
	<h1>&nbsp;</h1>
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/merchant/merchant.js"></script>
<script src="static/js/merchant/index.js"></script>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
	mercheck();
	getMeInfo();
</script>
</body>
</html>