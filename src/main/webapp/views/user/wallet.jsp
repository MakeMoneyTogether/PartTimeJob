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
<title>小蜜蜂兼职</title>
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
			<span id="jz-title">钱包</span>
		</div>
	</div>
	<div class="weui-form-preview">
		<div class="weui-form-preview__hd">
			<div class="col-40 div_left">
				<span style="margin-left:5%;float:left;">当前余额</span><br>
				<span style="margin-left:5%;float:left;" id="moneyable">${me.balance }</span>
			</div>
			<div class="col-50 div_right" style="padding-top:5%;">
				<button style="background-color:#41f3da;" class="weui-btn weui-btn_mini" onclick="pay();">充值</button>
				<button class="weui-btn weui-btn_mini weui-btn_primary" onclick="cash();">提现</button>
			</div>
			<div style="clear:both"></div>
		</div>
			<div class="weui-panel__hd">
				<button class="weui-btn weui-btn_mini weui-btn_default" style="margin-left: 10%;width:80%;" onclick="showSechedule();">交易账单</button>
			</div>
		<div class="weui-panel weui-panel_access" style="margin-top:0;">
			<div class="weui-panel__hd">提现进度</div>
			<div id="schedule">
				<div class="weui-cell weui-cell_access">
					<div class="weui-cell__hd">
						<i class="fa fa-twitter"></i>
					</div>
					<div class="weui-cell__bd">
						<p style="margin-left:10%;">张三</p>
					</div>
					<div class="">
						+5.00
					</div>
				</div>
			</div>
		</div>
	</div>
<div id="pymodal" class="weui-popup__container">
	<div class="weui-popup__overlay"></div>
	<div class="weui-popup__modal">
		<div class="weui-flex">
			<div class="placeholder"><a><i class="fa fa-chevron-left my_font_color close-popup" style="margin-top: 90%;"></i></a></div>
			<div class="weui-flex__item placeholder">
				<span>交易流水</span>
			</div>
		</div>
		<div class="weui-panel weui-panel_access" style="margin-top:0;">
			<div class="weui-panel__hd">交易流水</div>
			<div id="pyschedule">
				
			</div>
		</div>
		<br>
		<button class="weui-btn weui-btn_primary close-popup">关闭</button>
	</div>
</div>
<div style="display:none;">
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/user/wallet.js"></script>
<script type="text/javascript">
</script>
<script>
getSchedule();
</script>
</body>
</html>