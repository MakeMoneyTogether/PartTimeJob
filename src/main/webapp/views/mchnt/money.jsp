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
</head>
<body ontouchstart>
<div class="weui-flex">
	<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left" style="color:#010101;margin-top: 90%;"></i></a></div>
	<div class="weui-flex__item placeholder">
		<span id="jz-title">资产操作</span>
	</div>
</div>


<div class="weui-cells weui-flex div_label_out">
	<div class="weui-flex__item div_box div_label_in">
		可用资产:<br><span id="moneyable">1000.00</span>元
	</div>
	<div class="weui-flex__item div_box div_label_in">
		冻结资产:<br><span id="moneyice">1000.00</span>元
	</div>
</div>
<div class="weui-cells weui-flex div_label_out">
	<div class="weui-flex__item div_label_in">
		<button onclick="charge();" style="width:50%;" class="weui-btn weui-btn_mini weui-btn_primary">充值</button>
	</div>
	<div class="weui-flex__item div_label_in">
		<button onclick="cash();" style="width:50%;" class="weui-btn weui-btn_mini weui-btn_primary">提现</button>
	</div>
</div>
<div class="weui-cells" style="margin-top:0;">
	<div class="weui-cells__title">提现进度</div>
	<div id="out_moneys">
	
	</div>
	
</div>

<div>
	<h1>&nbsp;</h1>
</div>
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/merchant/money.js"></script>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
	//init();
</script>
</body>
</html>