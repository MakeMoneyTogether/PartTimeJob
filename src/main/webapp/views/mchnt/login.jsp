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
	<div class="weui-flex__item placeholder">
		<span>商户登录</span>
	</div>
</div>
<div class="weui-form-preview">
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd">
				<label class="weui-label">手机号</label>
			</div>
			<div class="weui-cell__bd">
				<input id="phone" class="weui-input" type="tel" placeholder="请输入手机号">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">密码</label></div>
			<div class="weui-cell__bd">
				<input id="password" class="weui-input" type="password" placeholder="请输入密码">
			</div>
		</div>
	</div>
</div>
<div class="weui-btn-area">
	<a onclick="merlogin();" style="width:80%;" class="weui-btn weui-btn_primary">登录</a>
	<a href="register.html" style="width:80%;" class="weui-btn weui-btn_default">注册</a>
</div>
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/merchant/merchant.js"></script>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
</script>
</body>
</html>