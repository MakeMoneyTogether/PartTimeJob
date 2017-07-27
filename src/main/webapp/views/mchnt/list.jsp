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
<title>小蜜蜂兼职-商家端</title>
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
	<div class="weui-navbar__item weui-bar__item--on" onclick="jump(2);">
		兼职列表
	</div>
	<div class="weui-navbar__item" onclick="jump(3);">
		商户中心
	</div>
</div>
<div class="weui-tab">
	<div class="weui-navbar" style="position:fixed;">
		<span class="weui-navbar__item weui-bar__item--on" href="#tab0">
			待审核
		</span>
		<span class="weui-navbar__item" href="#tab1">
			准备中
		</span>
		<span class="weui-navbar__item" href="#tab2">
			待评价
		</span>
		<span class="weui-navbar__item" href="#tab3">
			已结束
		</span>
	</div>
	<div class="weui-tab__bd">
		<div id="tab0" class="weui-tab__bd-item weui-tab__bd-item--active">
		</div>
		<div id="tab1" class="weui-tab__bd-item">
		</div>
		<div id="tab2" class="weui-tab__bd-item">
		</div>
		<div id="tab3" class="weui-tab__bd-item">

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
<script src="static/js/merchant/list.js"></script>
<script>
	$(function() {
	FastClick.attach(document.body);
	});
</script>
</body>
</html>