<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core" %>
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
		<span id="jobTitle">人员录用管理</span>
	</div>
</div>
<div class="bd">
	<div class="weui-cells__title">报名人员<strong style="color:red;">（不拒绝即被认为录用）</strong></div>
	<div id="list" class="weui-cells">
		<c:forEach items="${users }" var="user">
			<div class="weui-cell">
				<div class="weui-cell__hd" style="color:red;">${user.grade }</div>
				<div onclick="showInfo(${user.uid });" class="weui-cell__bd">
					<p>${user.name }</p>
				</div>
				<div class="weui-cell__ft">
					<button onclick="refuse(this,${user.uid});" class="weui-btn weui-btn_mini weui-btn_warn">拒绝</button>
				</div>
			</div>
		</c:forEach>
	</div>
	<h1>&nbsp;</h1>
</div>
<div style="display:none;">
<p id="jid">${jid }</p>
</div>
	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/merchant/merchant.js"></script>
<script src="static/js/merchant/peoplem.js"></script>
<script>
	$(function() {
	FastClick.attach(document.body);
	});
	//getPeople();
	function showInfo(uid){
		window.location.href="mchntp/userinfo/"+uid;
	}
</script>
</body>
</html>