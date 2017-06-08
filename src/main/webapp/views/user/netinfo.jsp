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
</head>
<body ontouchstart>
	<div class="weui-flex">
		<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left my_font_color" style="margin-top: 90%;"></i></a></div>
		<div class="weui-flex__item placeholder">
			<span id="jz-title">${net.jobTitle }</span>
		</div>
		<div class="placeholder"><a href="userp/me"><i class="fa fa-user-o icon_fa"></i></a></div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview__hd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label"></label>
				<span class="weui-form-preview__value" id="jz-name">${net.jobTitle }</span>
				<button id="opBtn" class="weui-btn weui-btn_mini weui-btn_primary"></button>
			</div>
		</div>
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">联系人员：</label>
				<span class="weui-form-preview__value" id="jz-person">${net.connectName }</span>
			</div>
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">联系电话：</label>
				<span class="weui-form-preview__value" id="jz-person">${net.connectPhone }</span>
			</div>
		</div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<label class="weui-form-preview__label">工作内容：</label>
				<br>
				<span class="info-desc" id="jz-des">
					${net.jobDesc }
				</span>
			</div>
		</div>
	</div>
</div>
<div style="display:none;">
<div id="jid_bak"></div>
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/user/netinfo.js"></script>
<script type="text/javascript">
</script>
<script>
getItem();
</script>
</body>
</html>