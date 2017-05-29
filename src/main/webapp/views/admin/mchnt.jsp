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
<style>
td > a{
	cursor:pointer;
}
</style>
</head>
<body class="hold-transition skin-blue-light sidebar-mini">
<div class="wrapper">
<jsp:include page="nav.jsp"></jsp:include>

	<div class="content-wrapper">
		<section class="content-header">
			<h1>
				商户管理
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Main row -->
			<div class="row">
				<div class="col-md-12">
					<div class="box box-warning">
					<div class="box-header with-border">
						<h3 class="box-title">商户列表</h3>
					</div>
					<div class="box-body">
					<table class="table table-striped table-condensed table-hover">
					<thead>
					<tr>
						<th>商户名称</th>
						<th>联系人</th>
						<th>联系电话</th>
						<th>浏览</th>
					</tr>
					</thead>
					<tbody id="mchnts">
						<tr>
							<td>大马蜂巢</td>
							<td>蜂仙人</td>
							<td>10086</td>
							<td><a onclick="showInfo(123);">查看</a></td>
						</tr>
						<tr>
							<td>大马蜂巢</td>
							<td>蜂仙人</td>
							<td>10086</td>
							<td><a onclick="showInfo(123);">查看</a></td>
						</tr>
					</tbody>	
					</table>
					</div>
					</div>
				</div>		
				<div class="col-md-12">
				</div>
			</div>
		</section>
		<!-- /.content -->
	</div>
	<div class="modal fade" id="mchntInfo" tabindex="-1" role="dialog" aria-labelledby="mchntName" aria-hidden="true">
		<div class="modal-dialog" style="margin-top:10%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="mchntName">
						商户名称
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-condensed table-hover">
					<tbody id="mchnts">
						<tr>
							<td>商户名称</td>
							<td>大马蜂巢</td>
						</tr>
						<tr>
							<td>商户地址</td>
							<td>江苏-南京-佛成西路101号</td>
						</tr>
						<tr>
							<td>联系人</td>
							<td>蜂仙人</td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td>10086</td>
						</tr>
					</tbody>	
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/app.min.js"></script>
<script src="static/js/admin/mchnt.js"></script>
<script type="text/javascript">
$('#nav_merchant').addClass('active');
</script>
</body>
</html>