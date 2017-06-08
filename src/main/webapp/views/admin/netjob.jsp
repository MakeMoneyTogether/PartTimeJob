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
<link href="static/css/font-awesome.min.css">
<link rel="stylesheet" href="static/css/summernote.css">
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
				手机兼职
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Main row -->
			<div class="row">
				<div class="col-md-12">
					<div class="box box-warning">
					<div class="box-header with-border">
						<h3 class="box-title">发布手机兼职</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<div class="box-body">
						<div class="form-group">
							<label class="control-label col-md-2" for="jobName">手机兼职名称</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="jobName" placeholder="请输入手机兼职名称">
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-md-2" for="jobName">联系人姓名</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="connectName" placeholder="联系人姓名">
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-md-2" for="jobName">联系方式</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="connectPhone" placeholder="QQ/手机/微信号">
							</div>
						</div>
						<br>
						<label class="control-label col-md-2">兼职描述</label><br>
						<div id="netdes"></div>
						<button onclick="push();" class="btn btn-success">发布</button>
					</div>
					</div>
				</div>		
				<div class="col-md-12">
					<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">手机兼职列表</h3>
					</div>
					<div class="box-body">
						<table class="table table-striped table-condensed table-hover">
						<thead>
						<tr>
							<th>兼职名称</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody id="netjobs">
							<c:forEach items="${netjobs }" var="netjob">
							<tr>
								<td>${netjob.jobTitle }</td>
								<td>
									<a onclick="showInfo(${netjob.jobId });">查看</a>
									<a onclick="showInfo(123);">删除</a>
								</td>
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
	<div class="modal fade" id="jobInfo" tabindex="-1" role="dialog" aria-labelledby="jobName" aria-hidden="true">
		<div class="modal-dialog" style="margin-top:10%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="ijobName">
						兼职名称
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-condensed table-hover">
					<tbody>
						<tr>
							<td>兼职名称</td>
							<td id="ijobTitle"></td>
						</tr>
						<tr>
							<td>联系人姓名</td>
							<td id="iconnectName"></td>
						</tr>
						<tr>
							<td>联系方式</td>
							<td id="iconnectPhone"></td>
						</tr>
					</tbody>	
					</table>
					<hr>
					<div id="ijobDesc">
						兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述
					</div>
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
<script src="static/js/summernote.min.js"></script>
<script src="static/js/app.min.js"></script>
<script src="static/js/admin/netjob.js"></script>
<script src="static/js/summernote-zh-CN.js"></script>
<script type="text/javascript">
$('#nav_netjob').addClass('active');
$('#netdes').summernote({
	lang: 'zh-CN',
	height: 300,
});
</script>
</body>
</html>