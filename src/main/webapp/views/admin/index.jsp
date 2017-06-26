<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小蜜蜂兼职后台管理</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="static/css/bootstrap.min.css">
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link rel="stylesheet" href="static/css/AdminLTE.min.css">
<link rel="stylesheet" href="static/css/skins/skin-blue-light.min.css">
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
<div class="wrapper">
<jsp:include page="nav.jsp"></jsp:include>

	<div class="content-wrapper">
		<section class="content-header">
			<h1>
				主页
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Main row -->
			<div class="row">
				<c:if test="${islogin == 0}">
				<div class="col-md-4">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">登录</h3>
						</div>
						<div class="box-body">
							<form action="adminp/login" method=POST>
								<div class="form-group">
									<label for="user">用户名</label>
									<input type="text" class="form-control" id="user" name="phone" placeholder="Email">
								</div>
								<div class="form-group">
									<label for="pwd">密码</label>
									<input type="password" class="form-control" name="pwd" id="pwd" placeholder="Password">
								</div>
								<button type="submit" class="btn btn-default">登录</button>
							</form>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${islogin == 1}">
				<div class="col-md-8">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">主页图片上传</h3>
						</div>
						<div class="box-body">
							<form id="swiper" action="adminp/swiper" enctype="multipart/form-data" method="POST" target="hidden_frame">
								<div class="form-group">
									<label for="file">图片上传</label>
									<input type="file" class="form-control" name="file" id="file" placeholder="上传的图片">
								</div>
								<div class="form-group">
									<label for="dex">图片编号</label>
									<input type="text" class="form-control" id="dex" name="fileName" placeholder="编号即将替代已有的图片">
								</div>
								<div class="form-group">
									<label for="url">链接地址</label>
									<input type="text" class="form-control" id="url" name="url" placeholder="点击图片跳转的路径">
								</div>
								<button onclick="upImg();" class="btn btn-default">上传</button>
							</form>
						</div>
					</div>
				</div>
				</c:if>
				
			</div>
		</section>
		<!-- /.content -->
	</div>
	<div class="control-sidebar-bg"></div>
</div>
<iframe name="hidden_frame" id="hidden_frame" style="display:none;"></iframe>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/app.min.js"></script>
<script type="text/javascript">
$('#nav_index').addClass('active');
function upImg(){
	$('#swiper').submit();
	setTimeout(checkSuccess,2000); 
}
function checkSuccess(){
	var win = document.getElementById('hidden_frame').contentWindow;
	code = win.document.body.innerText;
	if(code == 'success'){
		alert('图片上传成功');
		win.document.body.innerText = 'fail';
	}else{
		alert('图片上传失败');
	}
}
</script>
</body>
</html>