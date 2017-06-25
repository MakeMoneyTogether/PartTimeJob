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
		<div class="placeholder"><a onclick="history.go(-1);"><i class="fa fa-chevron-left my_font_color" style="margin-top: 90%;"></i></a></div>
		<div class="weui-flex__item placeholder">
			<span id="jz-title">手机兼职</span>
		</div>
		<div class="placeholder"><a href="userp/me"><i class="fa fa-user-o icon_fa"></i></a></div>
	</div>
	<div class="weui-panel weui-panel_access" style="margin-top:0;">
		<div class="weui-panel__hd" style="padding:0;">
			<div class="weui-flex">
				<div class="weui-flex__item placeselect" style="padding: 10px;">
					<a href="userp/index" class="my_font_color">
					<i style="color:#010101;" class="fa fa-calendar"></i>  实体兼职
					</a>
				</div>
				<div class="weui-flex__item my_font_color placeselect hr_left" style="padding: 10px;">
					<i style="color:#010101;" class="fa fa-mobile-phone"></i>  手机兼职
				</div>
			</div>
		</div>
	</div>
	<div class="weui-panel weui-panel_access" style="margin-top:0;">
		<div class="weui-panel__bd" id="jz-all">
			<div id="jz-infos">
			</div>
			<div class="weui-loadmore">
				<i class="weui-loading"></i>
				<span class="weui-loadmore__tips">正在加载</span>
			</div>
		</div>
	</div>
<div style="display:none;">
<div id="index_bak">0</div>
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/swiper.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/user/net.js"></script>
<script type="text/javascript" src="static/js/city-picker.min.js" charset="utf-8"></script>
<script type="text/javascript">

</script>
<script>
	$(function() {
	FastClick.attach(document.body);
	});
	$(".swiper-container").swiper({
		loop: true,
		autoplay: 2000,
	});
	onLoad();
</script>
</body>
</html>