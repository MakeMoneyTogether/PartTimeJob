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
</head>
<body ontouchstart>
	<div class="weui-flex">
		<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left" style="color:#010101;margin-top: 90%;"></i></a></div>
		<div class="weui-flex__item placeholder">
			<span id="jz-title">编辑简历</span>
		</div>
		<div class="placeholder"><a href="user"><i style="color:#010101;margin-top: 60%;" class="fa fa-home"></i></a></div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
				<div class="weui-cell__bd">
					<input id="name" class="weui-input" type="text" placeholder="请输入真实姓名">
				</div>
			</div>
			<hr>
			<div class="weui-cells__title">性别</div>
			<div class="weui-cells weui-cells_radio">
				<label class="weui-cell weui-check__label" for="man">
					<div class="weui-cell__bd">
						<p>男</p>
					</div>
					<div class="weui-cell__ft">
						<input type="radio" class="weui-check" name="sex" id="man">
						<span class="weui-icon-checked"></span>
					</div>
				</label>
				<label class="weui-cell weui-check__label" for="women">
					<div class="weui-cell__bd">
						<p>女</p>
					</div>
					<div class="weui-cell__ft">
						<input type="radio" name="sex" class="weui-check" id="women" checked="checked">
						<span class="weui-icon-checked"></span>
					</div>
				</label>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">生日</label></div>
				<div class="weui-cell__bd">
					<input id="birthday" class="weui-input" style="background:#bdbdbd;" type="date" value="">
				</div>
			</div>
			<hr>
			<div class="weui-cells__title">教育</div>
			<div class="weui-cells weui-cells_radio">
				<label class="weui-cell weui-check__label" for="studying">
					<div class="weui-cell__bd">
						<p>在读</p>
					</div>
					<div class="weui-cell__ft">
						<input type="radio" class="weui-check" name="edu" id="studying">
						<span class="weui-icon-checked"></span>
					</div>
				</label>
				<label class="weui-cell weui-check__label" for="studied">
					<div class="weui-cell__bd">
						<p>毕业</p>
					</div>
					<div class="weui-cell__ft">
						<input type="radio" name="edu" class="weui-check" id="studied" checked="checked">
						<span class="weui-icon-checked"></span>
					</div>
				</label>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">学校</label></div>
				<div class="weui-cell__bd">
					<input id="school" class="weui-input" type="text" placeholder="请输入学校名称">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">微信号</label></div>
				<div class="weui-cell__bd">
					<input id="wechat" class="weui-input" type="text" placeholder="请输入微信号">
				</div>
			</div>
		</div>
	</div>
		<a onclick="update();" class="weui-btn weui-btn_primary commitBtn">更新</a>
<div style="display:none;">
<div id="code_bak"></div>
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/user/editcv.js"></script>
<script type="text/javascript">
</script>
<script>
</script>
</body>
</html>