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
				用户管理
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Main row -->
			<div class="row">
				<div class="col-md-6">
					<div class="box box-warning">
					<div class="box-header with-border">
						<h3 class="box-title">用户列表</h3>
					</div>
					<div class="box-body">
					<form class="form-inline" action="adminp/user" method="POST">
						<div class="form-group">
							<input type="text" class="form-control" name="userkey" id="userkey" placeholder="请输入用户名称">
						</div>
						<button type="submit" class="btn btn-default">搜索</button>
					</form>
					<table class="table table-striped table-condensed table-hover">
					<thead>
					<tr>
						<th>用户名称</th>
						<th>手机号码</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody id="users">
						<c:forEach items="${users }" var="user">
						<tr <c:if test="${user.userSt ==0 }">class="info"</c:if>>
							<td>${user.name }</td>
							<td>${user.phone }</td>
							<td><a onclick="showInfo(${user.uid});">查看</a></td>
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
						<h3 class="box-title">交易单</h3>
					</div>
					<div class="box-body">
					<table class="table table-striped table-condensed table-hover">
					<thead>
					<tr>
						<th>时间</th>
						<th>类型</th>
						<th>金额</th>
						<th>状态</th>
					</tr>
					</thead>
					<tbody id="schedule">
						
					</tbody>	
					</table>
					</div>
					</div>
				</div>
			</div>
		</section>
		<!-- /.content -->
	</div>
	<div class="modal fade" id="userInfo" tabindex="-1" role="dialog" aria-labelledby="userName" aria-hidden="true">
		<div class="modal-dialog" style="margin-top:10%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="userName">
						用户名称
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-condensed table-hover">
					<tbody id="users">
						<tr>
							<td>用户名称</td>
							<td id="name"></td>
						</tr>
						<tr>
							<td>性别</td>
							<td id="gender"></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td id="phone"></td>
						</tr>
						<tr>
							<td>学校</td>
							<td id="school"></td>
						</tr>
						<tr>
							<td>专业</td>
							<td id="major"></td>
						</tr>
						<tr>
							<td>资产</td>
							<td id="balance"></td>
						</tr>
					</tbody>	
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button onclick="freeze();" type="button" class="btn btn-info" data-dismiss="modal">冻结用户
					</button>
					<button onclick="passit();" type="button" class="btn btn-success" data-dismiss="modal">用户解冻
					</button>
					<button onclick="showJobs();" type="button" class="btn btn-warning" data-dismiss="modal">查询兼职
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="display:none;">
<div id="op_userid"></div>
<form target="_blank" action="adminp/jobs" id="tojobs">
	<input name="flag" value="0"/>
	<input id="jobUid" name="uid"/>
</form>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/app.min.js"></script>
<script src="static/js/admin/user.js"></script>
<script type="text/javascript">
$('#nav_user').addClass('active');
</script>
</body>
</html>