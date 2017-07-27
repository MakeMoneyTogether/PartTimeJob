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
</head>
<body ontouchstart>
	<div class="weui-flex">
		<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left my_font_color" style="margin-top: 90%;"></i></a></div>
		<div class="weui-flex__item placeholder">
			<span id="jz-title">${user.name }</span>
		</div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
				<div class="weui-cell__bd">
					${user.name }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">联系方式</label></div>
				<div class="weui-cell__bd">
					<a>${user.connectPhone }</a>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">性别</label></div>
				<div class="weui-cell__bd">
					${user.gender }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">评分</label></div>
				<div class="weui-cell__bd">
					${user.grade }
				</div>
			</div>
			<hr>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">生日</label></div>
				<div class="weui-cell__bd">
					${user.birthday }	
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">学校</label></div>
				<div class="weui-cell__bd">
					${user.school }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">专业</label></div>
				<div class="weui-cell__bd">
					${user.major }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">意向方向</label></div>
				<div class="weui-cell__bd">
					${user.direction }
				</div>
			</div>
		</div>
	</div>
<div style="display:none;">
<div id="code_bak"></div>
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/dateutil.js"></script>
<script type="text/javascript">
</script>
</body>
</html>