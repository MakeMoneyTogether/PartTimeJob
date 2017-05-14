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
		<div class="placeholder"><a id="ncity" value="320100" href="javascript:;" class="open-popup" data-target="#localSelect">南京市</a></div>
		<div class="weui-flex__item">
			<div class="weui-search-bar" id="searchBar">
				<form class="weui-search-bar__form">
					<div class="weui-search-bar__box">
					<i class="weui-icon-search"></i>
						<input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
					</div>
					<label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
						<i class="weui-icon-search"></i>
						<span>搜索</span>
					</label>
				</form>
				<a onclick="searchText();" class="weui-search-bar__cancel-btn" id="searchBtn">搜索</a>
			</div>

		</div>
		<div class="placeholder"><a href="user/me"><i style="color:#010101;margin-top: 60%;" class="fa fa-user-o"></i></a></div>
	</div>
	<div>
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide"><img src="static/images/swiper-1.jpg" /></div>
				<div class="swiper-slide"><img src="static/images/swiper-2.jpg" /></div>
				<div class="swiper-slide"><img src="static/images/swiper-3.jpg" /></div>
			</div>
			<div class="swiper-pagination"></div>
		</div>
	</div>
	<div class="weui-panel weui-panel_access">
		<div class="weui-panel__hd" style="padding:0;">
			<div class="weui-flex">
				<div class="weui-flex__item placeselect">
					<i style="color:#010101;" class="fa fa-calendar"></i>  实体兼职
				</div>
				<hr width="1" color="#999999">
				<div class="weui-flex__item placeselect">
					<a href="user/net">
					<i style="color:#010101;" class="fa fa-mobile-phone"></i>  手机兼职
					</a>
				</div>
			</div>
		</div>
	</div>
	<div class="weui-panel weui-panel_access">
		<div class="weui-panel__hd" style="padding:0;">
			<div class="weui-flex">
				<div id="plocal" class="weui-flex__item placeselect">
					区域 <i style="color:#010101;" class="fa fa-chevron-down"></i>
				</div>
				<div id="ptype" class="weui-flex__item placeselect">
					类型 <i style="color:#010101;" class="fa fa-chevron-down"></i>
				</div>
				<div id="ptime" class="weui-flex__item placeselect">
					时间 <i style="color:#010101;" class="fa fa-chevron-down"></i>
				</div>
			</div>
		</div>
		<div id="slocal" style="display:none;">
			<div class="weui-form-preview__bd" style="text-align:left;">
				选择区域
			</div>
			<div id="districts" class="weui-panel__bd checkDiv">

			</div>
		</div>
		<div id="stype" style="display:none;">
			<div class="weui-form-preview__bd" style="text-align:left;">
				选择类型
			</div>
			<div id="labels" class="weui-panel__bd checkDiv">

			</div>
		</div>
		<div id="stime" style="display:none;">
			<div class="weui-form-preview__bd" style="text-align:left;">
				选择时间
			</div>
			<div class="weui-panel__bd checkDiv">
				<span class="weui-btn weui-btn_mini weui-btn_defualt checkBtn" value="all">不限时间</span><br><br>
				<div class="weui-cell">
					<div class="weui-cell__hd"><label for="" class="weui-label">兼职日期</label></div>
					<div class="weui-cell__bd">
						<input id="jzstdate" class="weui-input" style="background:#bdbdbd;" type="text" data-toggle="date">
					</div>
				</div>
				<br>
				<button class="weui-btn weui-btn_mini commitBtn">筛选</button>
				<br>
				<div class="weui-panel__hd" style="padding:0;"></div>

			</div>
		</div>
		<div class="weui-panel__bd" id="jz-all">
			<div id="jz-infos">
			</div>
			<div class="weui-loadmore">
				<i class="weui-loading"></i>
				<span class="weui-loadmore__tips">正在加载</span>
			</div>
		</div>
		<div id="localSelect" class="weui-popup__container">
			<div class="weui-popup__overlay"></div>
			<div class="weui-popup__modal">
				<div class="weui-form-preview__hd">
					<div class="weui-form-preview__item">
						<label class="weui-form-preview__label"></label>
						<span class="weui-form-preview__value">选择城市</span>
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd"><label for="name" class="weui-label">城市</label></div>
					<div class="weui-cell__bd">
					  <input class="weui-input" id="city-picker" type="text">
					</div>
				  </div>
				<br>
				<a href="javascript:;" onclick="setLocal();" class="weui-btn weui-btn_mini weui-btn_primary close-popup" style="width:80%;margin-left:10%;">提交</a>
				<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary close-popup" style="width:80%;margin-left:10%;">关闭</a>
			</div>
		</div>
	</div>
<div style="display:none;">
<div id="dises_bak">all</div>
<div id="labels_bak">all</div>
<div id="dates_bak">all</div>
<div id="index_bak">0</div>
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/swiper.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/user/index.js"></script>
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
	localset();
	getDistrict();
	getLabels();
	onLoad();
	$("#city-picker").cityPicker({
		showDistrict: false
	});
	$('#jzstdate').calendar();
	
	//$.cookie('citycode','320100',{expires:30,path:'/'});
	function searchText(){
		$.cookie('inputKey',$('#searchInput').val());
		window.location.href = 'user/list';
	}
</script>
</body>
</html>