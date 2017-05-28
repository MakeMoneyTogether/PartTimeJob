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
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-home"></i>主页</a></li>
				<li class="active">主页</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Main row -->
			<div class="row">
				
				<div class="col-md-4">
					<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">登录</h3>
						</div>
						<div class="box-body">
							<form>
								<div class="form-group">
									<label for="user">用户名</label>
									<input type="text" class="form-control" id="user" placeholder="Email">
								</div>
								<div class="form-group">
									<label for="pwd">密码</label>
									<input type="password" class="form-control" id="pwd" placeholder="Password">
								</div>
								<button type="submit" class="btn btn-default">登录</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /.content -->
	</div>
	<div class="control-sidebar-bg"></div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/app.min.js"></script>
<script type="text/javascript">
$('#nav_index').addClass('active');
</script>
</body>
</html>