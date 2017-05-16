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
</head>
<body ontouchstart>
<div class="weui-flex">
		<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left" style="color:#010101;margin-top: 90%;"></i></a></div>
		<div class="weui-flex__item placeholder">
			<span id="jz-title">商户注册</span>
		</div>
	</div>
<div class="weui-form-preview">
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">商户名称</label></div>
			<div class="weui-cell__bd">
				<input id="mchntName" class="weui-input" type="text" placeholder="请输入商户名称">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">商户区域</label></div>
			<div class="weui-cell__bd">
				<input class="weui-input" id="city-picker" type="text">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">详细地址</label></div>
			<div class="weui-cell__bd">
				<input id="mchntAddress" class="weui-input" type="text" placeholder="请输入商户地址">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">密码</label></div>
			<div class="weui-cell__bd">
				<input id="password" class="weui-input" type="password" placeholder="请输入密码">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">确认密码</label></div>
			<div class="weui-cell__bd">
				<input id="repassword" class="weui-input" type="password" placeholder="请输入密码">
			</div>
		</div>
		<hr>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">联系人姓名</label></div>
			<div class="weui-cell__bd">
				<input id="connName" class="weui-input" type="text" placeholder="请输入联系人姓名">
			</div>
		</div>
		<div class="weui-cell weui-cell_vcode">
			<div class="weui-cell__hd">
				<label class="weui-label">手机号</label>
			</div>
			<div class="weui-cell__bd">
				<input id="phone" class="weui-input" type="tel" onkeypress="return event.keyCode>=48&&event.keyCode<=57" placeholder="请输入手机号">
			</div>
			<div class="weui-cell__ft">
				<button id="code_btn" class="weui-vcode-btn">获取</button>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">验证码</label></div>
			<div class="weui-cell__bd">
				<input id="code" class="weui-input" type="text" placeholder="请输入验证码">
			</div>
		</div>
	</div>
</div>
<div class="weui-btn-area">
	<button onclick="register();" style="width:80%;" class="weui-btn weui-btn_primary">注册</button>
</div>
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/merchant/merchant.js"></script>
<script src="static/js/merchant/register.js"></script>
<script src="static/js/city-picker.min.js" charset="utf-8"></script>
<script>
	$(function() {
	FastClick.attach(document.body);
	});
	$("#city-picker").cityPicker({
	});
</script>
</body>
</html>