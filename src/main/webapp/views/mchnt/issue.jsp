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
<div class="weui-tabbar" style="position:fixed;">
	<div class="weui-navbar__item weui-bar__item--on" onclick="jump(1);">
		发布兼职
	</div>
	<div class="weui-navbar__item" onclick="jump(2);">
		兼职列表
	</div>
	<div class="weui-navbar__item" onclick="jump(3);">
		商户中心
	</div>
</div>
<div class="weui-flex">
		<div class="weui-flex__item placeholder">
			<span id="jz-title">发布兼职</span>
		</div>
	</div>
<div class="weui-form-preview">
	<div class="weui-cells weui-cells_form">
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">兼职名称</label></div>
			<div class="weui-cell__bd">
				<input id="jzname" class="weui-input" type="text" placeholder="请输入兼职名称">
			</div>
		</div>
		<div class="weui-cell weui-cell_select weui-cell_select-after">
			<div class="weui-cell__hd"><label class="weui-label">兼职类型</label></div>
			<div class="weui-cell__bd">
				<select id="jzlabel" name="jzlabel" class="weui-select">
					<option value="1">发单</option>
					<option value="2">销售</option>
					<option value="3">传销</option>
				</select>
			</div>
		</div>
		<div class="weui-cell weui-cell_select weui-cell_select-after">
			<div class="weui-cell__hd"><label class="weui-label">薪资待遇</label></div>
			<div class="col-70">
				<input id="salary" class="weui-input" type="text" placeholder="单位时间薪水">
			</div>
			<div class="col-30">
				<select id="unit" name="unit" class="weui-select" onchange="change(this.options[this.options.selectedIndex].value);">
					<option value="day">/天</option>
					<option value="hour">/小时</option>
				</select>
			</div>
		</div>
		<div id="for_hour" style="display:none;" class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">工作强度</label></div>
			<div class="weui-cell__bd">
				<input id="hourOfDay" class="weui-input" type="text" placeholder="平均一天工作多少小时">
			</div>
			小时/天
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">开始时间</label></div>
			<div class="weui-cell__bd">
				<input id="stdate" class="weui-input" type="date" placeholder="请选择时间">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">结束时间</label></div>
			<div class="weui-cell__bd">
				<input id="endate" class="weui-input" type="date" >
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">报名时间</label></div>
			<div class="weui-cell__bd">
				<input id="pausedate" class="weui-input" type="date" >
			</div>
				前
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">需求人数</label></div>
			<div class="weui-cell__bd">
				<input id="jznum" class="weui-input" type="text" placeholder="请输入需求人数">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">兼职地点</label></div>
			<div class="weui-cell__bd">
				<input id="jzlocal" class="weui-input" type="text" placeholder="请输入兼职地点">
			</div>
		</div>
		<hr>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">联系人姓名</label></div>
			<div class="weui-cell__bd">
				<input id="connectName" class="weui-input" type="text" placeholder="请输入联系人姓名">
			</div>
		</div>
		<div class="weui-cell">
			<div class="weui-cell__hd"><label class="weui-label">联系人电话</label></div>
			<div class="weui-cell__bd">
				<input id="connectPhone" class="weui-input" type="text" placeholder="请输入联系人电话">
			</div>
		</div>
		<hr>
		<div class="weui-cells__title">兼职描述</div>
		<div class="weui-cell">
			<div class="weui-cell__bd">
				<textarea id="jzdes" class="weui-textarea" placeholder="请输入文本" rows="6"></textarea>
			</div>
		</div>
		<div class="weui-btn-area">
		<button onclick="check();" style="width:80%;" class="weui-btn weui-btn_primary">发布</button>
		</div>
	</div>
</div>
<div>
	<h1>&nbsp;</h1>
</div>
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/merchant/merchant.js"></script>
<script src="static/js/merchant/issue.js"></script>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
</script>
</body>
</html>