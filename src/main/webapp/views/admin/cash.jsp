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
				提现管理
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Main row -->
			<div class="row">
				<div class="col-md-6">
					<div class="box box-warning">
					<div class="box-header with-border">
						<h3 class="box-title">商家提现列表</h3>
					</div>
					<div class="box-body">
					<table class="table table-striped table-condensed table-hover">
					<thead>
					<tr>
						<th>商家名称</th>
						<th>联系人</th>
						<th>联系方式</th>
						<th>提现金额</th>
						<th>提现时间</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody id="mchnts">
						<c:forEach items="${mchnts }" var="mchnt">
						<tr>
							<td>${mchnt.mname }</td>
							<td>${mchnt.cname }</td>
							<td>${mchnt.phone }</td>
							<td>${mchnt.money }</td>
							<td>${mchnt.time }</td>
							<td><a onclick="mpass(${mchnt.id},this)">批准</a> <a onclick="munpass(${mchnt.id},this)">拒绝</a></td>
						</tr>
						</c:forEach>
					</tbody>	
					</table>
					</div>
					</div>
				</div>		
				<div class="col-md-6">
					<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">用户提现列表</h3>
					</div>
					<div class="box-body">
					<table class="table table-striped table-condensed table-hover">
					<thead>
					<tr>
						<th>用户名称</th>
						<th>联系方式</th>
						<th>提现金额</th>
						<th>提现时间</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody id="users">
						<c:forEach items="${users }" var="user">
						<tr>
							<td>${user.uname }</td>
							<td>${user.phone }</td>
							<td>${user.money }</td>
							<td>${user.time }</td>
							<td><a onclick="upass(${user.id},this)">批准</a> <a onclick="uunpass(${user.id},this)">拒绝</a></td>
						</tr>
						</c:forEach>
					</tbody>	
					</table>
					</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /.content -->
	</div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/app.min.js"></script>
<script src="static/js/admin/cash.js"></script>
<script type="text/javascript">
$('#nav_cash').addClass('active');
</script>
</body>
</html>