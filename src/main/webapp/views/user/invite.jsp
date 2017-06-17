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
<title>兼职</title>
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
		<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left my_font_color" style="margin-top: 90%;"></i></a></div>
		<div class="weui-flex__item placeholder">
			<span id="jz-title">邀请好友</span>
		</div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-panel__hd">邀请码</div>
		<div class="weui-cell weui-cell_access">
			<div class="weui-cell__hd">
				<i class="fa fa-paper-plane-o"></i>
			</div>
			<div class="weui-cell__bd">
				<p style="margin-left:10%;">${userInfo.shareCode }</p>
			</div>
		</div>
		<div class="weui-panel weui-panel_access" style="margin-top:0;">
			<div class="weui-panel__hd">已邀请</div>
			<div id="inv_person">
			</div>
		</div>
	</div>
<div style="display:none;">
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/user/invite.js"></script>
<script type="text/javascript">
</script>
<script>
getInvitation();
</script>
</body>
</html>