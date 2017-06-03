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
<title>兼职</title>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<link rel="stylesheet" href="static/css/weui.min.css">
<link rel="stylesheet" href="static/css/jquery-weui.min.css">
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link rel="stylesheet" href="static/css/jz.css">
<link rel="stylesheet" href="static/css/user/me.css">
<link rel="stylesheet" href="static/css/col.css">
</head>
<body ontouchstart>
	<div class="weui-flex">
		<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left" style="color:#010101;margin-top: 90%;"></i></a></div>
		<div class="weui-flex__item placeholder">
			<span id="jz-title">参与兼职</span>
		</div>
		<div class="placeholder"><a href="userp"><i style="color:#010101;margin-top: 60%;" class="fa fa-home"></i></a></div>
	</div>
	<div class="weui-tab">
		<div class="weui-navbar">
			<a id="s22" class="weui-navbar__item" href="#s2">
				被拒绝
			</a>
			<a id="s21" class="weui-navbar__item" href="#s1">
				已报名
			</a>
			<a id="s23" class="weui-navbar__item" href="#s3">
				已结算
			</a>
		</div>
		<div class="weui-tab__bd">
			<div id="s2" class="weui-tab__bd-item">
			</div>
			<div id="s1" class="weui-tab__bd-item">
			</div>
			<div id="s3" class="weui-tab__bd-item">
			</div>
		</div>
	</div>
<div style="display:none;">
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/dateutil.js"></script>
<script src="static/js/user/myjob.js"></script>
<script type="text/javascript">
</script>
<script>
$(window.location.hash).click();
</script>
</body>
</html>