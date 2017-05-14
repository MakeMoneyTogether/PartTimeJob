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
			<span id="jz-title">个人中心</span>
		</div>
		<div class="placeholder"><a href="user"><i style="color:#010101;margin-top: 60%;" class="fa fa-home"></i></a></div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview__hd">
			<div class="col-20 div_left">
				<img style="width:100%;" alt="统一头像" src="static/images/1.jpg">
			</div>
			<div class="col-50 div_left">
				<span style="margin-left:5%;float:left;" id="uname">姓名啊</span><br>
				<span style="margin-left:5%;float:left;" id="ugpa">5.0</span>
			</div>
			<div class="col-30 div_right">
				<a href="user/editcv">编辑简历></a>
			</div>
			<div style="clear:both"></div>
		</div>
		<div class="weui-form-preview__hd" style="text-align:center;padding:0;padding-bottom:0;">
			<div class="weui-flex">
				<div onclick="jump(0,this);" class="weui-flex__item div_box">已报名
					<span id="s0"></span>
				</div>
				<div onclick="jump(1,this);" class="weui-flex__item div_box">已录用
					<span id="s1"></span>
				</div>
				<div onclick="jump(2,this);" class="weui-flex__item div_box">已到岗
					<span id="s2"></span>
				</div>
				<div onclick="jump(3,this);" class="weui-flex__item div_box">已结算
					<span id="s3"></span>
				</div>
			</div>
		</div>
	</div>
	<div class="weui-cells">
		<div class="weui-cell weui-cell_access" onclick="jump(4);">
			<div class="weui-cell__hd">
				<i class="fa fa-money"></i>
			</div>
			<div class="weui-cell__bd">
				<p style="margin-left:10%;">钱包</p>
			</div>
			<div id="money" class="weui-cell__ft">
				0.00
			</div>
		</div>
		<div class="weui-cell weui-cell_access" onclick="jump(5);">
			<div class="weui-cell__hd">
				<i class="fa fa-cog"></i>
			</div>
			<div class="weui-cell__bd">
				<p style="margin-left:10%;">修改密码</p>
			</div>
			<div class="weui-cell__ft">
			</div>
		</div>
		<div class="weui-cell weui-cell_access" onclick="jump(6);">
			<div class="weui-cell__hd">
				<i class="fa fa-comment"></i>
			</div>
			<div class="weui-cell__bd">
				<p style="margin-left:10%;">邀请好友</p>
			</div>
			<div class="weui-cell__ft">
			</div>
		</div>
		<div class="weui-cell weui-cell_access" onclick="jump(7);">
			<div class="weui-cell__hd">
				<i class="fa fa-sign-out"></i>
			</div>
			<div class="weui-cell__bd">
				<p style="margin-left:10%;">退出登录</p>
			</div>
			<div class="weui-cell__ft">
			</div>
		</div>
	</div>
	<div class="weui-panel weui-panel_access">
		
		<div class="weui-panel__bd" id="jz-all">
			<div id="jz-infos">
				<div class="weui-form-preview__bd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label">关于我们：</label>
						<br>
						<span class="info-desc" id="jz-des">
							我们是特别厉害的兼职平台。我们是特别厉害的兼职平台。我们是特别厉害的兼职平台。
							我们是特别厉害的兼职平台。我们是特别厉害的兼职平台。我们是特别厉害的兼职平台。
							我们是特别厉害的兼职平台。我们是特别厉害的兼职平台。
							我们是特别厉害的兼职平台。我们是特别厉害的兼职平台。
							我们是特别厉害的兼职平台。我们是特别厉害的兼职平台。我们是特别厉害的兼职平台。
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="repass" class="weui-popup__container popup-bottom" style="background: rgba(0, 0, 0, 0.63);">
	<div class="weui-popup__overlay" ></div>
	<div class="weui-popup__modal" style="height:70%;background: rgba(0, 0, 0, 0);">
		<div class="weui-form-preview">
			<center><h2>修改密码</h2></center>
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">原密码</label></div>
				<div class="weui-cell__bd">
					<input id="pwd" class="weui-input" type="password" placeholder="请输入原密码">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">新密码</label></div>
				<div class="weui-cell__bd">
					<input id="npwd" class="weui-input" type="password" placeholder="请输入新密码">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd"><label class="weui-label">确认密码</label></div>
				<div class="weui-cell__bd">
					<input id="rnpwd" class="weui-input" type="password" placeholder="请重复输入新密码">
				</div>
			</div>
		</div>
		<div class="weui-flex">
		<div class="weui-flex__item" style="padding:5%;">
			<a class="weui-btn weui-btn_mini weui-btn_default close-popup" style="width:80%;">返回</a>
		</div>
		<div class="weui-flex__item" style="padding:5%;">
			<a onclick="repwd();" class="weui-btn weui-btn_mini weui-btn_primary" style="width:80%;">修改</a>
		</div>
	</div>
	</div>
	</div>
</div>
<div style="display:none;">
<div id="uid_bak">007</div>
<div id="invitation_bak">007</div>
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/user/me.js"></script>
<script type="text/javascript">
</script>
<script>
</script>
</body>
</html>