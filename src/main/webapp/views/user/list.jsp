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
		<div class="weui-flex__item">
			<div class="weui-search-bar" id="searchBar">
				<form class="weui-search-bar__form">
					<div class="weui-search-bar__box">
					<i class="weui-icon-search"></i>
						<input type="search" class="weui-search-bar__input" id="searchInput" placeholder="搜索" required="">
						<a href="javascript:" class="weui-icon-clear" id="searchClear"></a>
					</div>
					<label class="weui-search-bar__label" id="searchText" style="transform-origin: 0px 0px 0px; opacity: 1; transform: scale(1, 1);">
						<i class="weui-icon-search"></i>
						<span>搜索</span>
					</label>
				</form>
				<a onclick="searchText();" class="weui-search-bar__cancel-btn" id="searchBtn">搜索</a>
			</div>
		</div>
	</div>
	<div class="weui-panel weui-panel_access">
		
		<div class="weui-panel__bd" id="jz-all">
			<div id="jz-infos">
			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
				<div class="weui-media-box__hd"><div class="i-circle">
					类型
				</div></div>
				<div class="weui-media-box__bd i-jz">
				<span class="weui-media-box__title i-jz-title">标题一</span><br>
				<span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br>
				<span class="i-jz-money">100元/天</span>
				<span class="i-jz-date">4分钟前</span>
				</div>
			</a>

			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
				<div class="weui-media-box__hd"><div class="i-circle">
					类型
				</div></div>
				<div class="weui-media-box__bd i-jz">
				<span class="weui-media-box__title i-jz-title">标题一</span><br>
				<span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br>
				<span class="i-jz-money">100元/天</span>
				<span class="i-jz-date">4分钟前</span>
				</div>
			</a>

			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
				<div class="weui-media-box__hd"><div class="i-circle">
					类型
				</div></div>
				<div class="weui-media-box__bd i-jz">
				<span class="weui-media-box__title i-jz-title">标题一</span><br>
				<span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br>
				<span class="i-jz-money">100元/天</span>
				<span class="i-jz-date">4分钟前</span>
				</div>
			</a>

			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
				<div class="weui-media-box__hd"><div class="i-circle">
					类型
				</div></div>
				<div class="weui-media-box__bd i-jz">
				<span class="weui-media-box__title i-jz-title">标题一</span><br>
				<span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br>
				<span class="i-jz-money">100元/天</span>
				<span class="i-jz-date">4分钟前</span>
				</div>
			</a>

			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
				<div class="weui-media-box__hd"><div class="i-circle">
					类型
				</div></div>
				<div class="weui-media-box__bd i-jz">
				<span class="weui-media-box__title i-jz-title">标题一</span><br>
				<span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br>
				<span class="i-jz-money">100元/天</span>
				<span class="i-jz-date">4分钟前</span>
				</div>
			</a>

			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
				<div class="weui-media-box__hd"><div class="i-circle">
					类型
				</div></div>
				<div class="weui-media-box__bd i-jz">
				<span class="weui-media-box__title i-jz-title">标题一</span><br>
				<span class="i-jz-desc">江宁 5月1日开始（共10天）</span><br>
				<span class="i-jz-money">100元/天</span>
				<span class="i-jz-date">4分钟前</span>
				</div>
			</a>
			</div>
			<div class="weui-loadmore">
				<i class="weui-loading"></i>
				<span class="weui-loadmore__tips">正在加载</span>
			</div>
		</div>
		
	</div>
<div style="display:none;">
<div id="city_bak">320100</div>
<div id="index_bak">0</div>
<div id="keys_bak"></div>
</div>	
<script src="static/js/jquery-2.1.4.js"></script>
<script src="static/js/fastclick.js"></script>
<script src="static/js/jquery-weui.min.js"></script>
<script src="static/js/jquery.cookie.js"></script>
<script src="static/js/user/list.js"></script>
<script type="text/javascript">
$('#searchInput').val($.cookie('inputKey'));
$('#keys_bak').html($.cookie('inputKey'));

function searchText(){
	searchKey = $('#searchInput').val();
	cityCode = $.cookie('citycode');
	$('#searchInput').val(searchKey);
	$('#keys_bak').html(searchKey);
	onLoad();
}
onLoad();
</script>
<script>
</script>
</body>
</html>