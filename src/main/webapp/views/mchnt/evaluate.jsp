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
<style>
.star{
	color:#ffcc02;
}
</style>
</head>
<body ontouchstart>
<div class="weui-flex">
	<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left" style="color:#010101;margin-top: 90%;"></i></a></div>
	<div class="weui-flex__item placeholder">
		<span id="jobTitle">人员评价</span>
	</div>
</div>
<div class="bd">
	<div class="weui-cells__title">参与兼职的人员</div>
	<div id="list" class="weui-cells">
		<div class="weui-cell">
			<div class="weui-cell__hd" ><button onclick="deny(1001);" class="weui-btn weui-btn_mini weui-btn_warn">缺勤</button>&nbsp;</div>
			<div class="weui-cell__bd">
				<p>用户名字</p>
			</div>
			<div id="1001" value="0" class="weui-cell__ft rate">
				<i value="1" class="fa fa-star"></i>
				<i value="2" class="fa fa-star"></i>
				<i value="3" class="fa fa-star"></i>
				<i value="4" class="fa fa-star"></i>
				<i value="5" class="fa fa-star"></i>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd" ><button onclick="deny(1002);" class="weui-btn weui-btn_mini weui-btn_warn">缺勤</button>&nbsp;</div>
			<div class="weui-cell__bd">
				<p>用户名字</p>
			</div>
			<div id="1002" value="0" class="weui-cell__ft rate">
				<i value="1" class="fa fa-star"></i>
				<i value="2" class="fa fa-star"></i>
				<i value="3" class="fa fa-star"></i>
				<i value="4" class="fa fa-star"></i>
				<i value="5" class="fa fa-star"></i>
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd" ><button onclick="deny(1003);" class="weui-btn weui-btn_mini weui-btn_warn">缺勤</button>&nbsp;</div>
			<div class="weui-cell__bd">
				<p>用户名字</p>
			</div>
			<div id="1003" value="0" class="weui-cell__ft rate">
				<i value="1" class="fa fa-star"></i>
				<i value="2" class="fa fa-star"></i>
				<i value="3" class="fa fa-star"></i>
				<i value="4" class="fa fa-star"></i>
				<i value="5" class="fa fa-star"></i>
			</div>
		</div>
	</div>
	<div class="weui-btn-area">
		<button onclick="apply();" style="width:80%;" class="weui-btn weui-btn_primary">评价完成</button>
	</div>
	<h1>&nbsp;</h1>
</div>
<div style="display:none;">
<p id="jid">001</p>
</div>
	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/merchant/merchant.js"></script>
<script src="static/js/merchant/evaluate.js"></script>
<script>
	$(function() {
	FastClick.attach(document.body);
	});
</script>
</body>
</html>