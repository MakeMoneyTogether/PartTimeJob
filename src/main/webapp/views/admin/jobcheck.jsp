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
				兼职审核
			</h1>
		</section>

		<!-- Main content -->
		<section class="content">
			<!-- Main row -->
			<div class="row">
				<div class="col-md-12">
					<div class="box box-warning">
					<div class="box-header with-border">
						<h3 class="box-title">待审核列表</h3>
					</div>
					<div class="box-body">
					<table class="table table-striped table-condensed table-hover">
					<thead>
					<tr>
						<th>兼职名称</th>
						<th>发布商家</th>
						<th>联系人</th>
						<th>联系方式</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody id="jobs">
						<c:forEach items="${jobs }" var="job">
						<tr id="${job.jobId }">
							<td>${job.jobTitle }</td>
							<td>${job.mchntName }</td>
							<td>${job.connectName }</td>
							<td>${job.connectPhone }</td>
							<td><a onclick="showInfo(${job.jobId});">查看</a></td>
						</tr>
						</c:forEach>
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
	<div class="modal fade" id="jobInfo" tabindex="-1" role="dialog" aria-labelledby="jobName" aria-hidden="true">
		<div class="modal-dialog" style="margin-top:10%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="jobName">
						兼职名称
					</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped table-condensed table-hover">
					<tbody>
						<tr>
							<td>兼职名称</td>
							<td id="jobTitle"></td>
						</tr>
						<tr>
							<td>商户名称</td>
							<td id="mchntName"></td>
						</tr>
						<tr>
							<td>联系人</td>
							<td id="connectName"></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td id="connectPhone"></td>
						</tr>
						<tr>
							<td>起始时间</td>
							<td id="jobStartTime"></td>
						</tr>
						<tr>
							<td>结束时间</td>
							<td id="jobEndTime"></td>
						</tr>
						<tr>
							<td>薪资</td>
							<td id="paymentMoney"></td>
						</tr>
						<tr>
							<td>招收人数</td>
							<td id="numPeople"></td>
						</tr>
					</tbody>	
					</table>
					<hr>
					<div id="jobDesc">
						兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述兼职描述，描述，描述
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button onclick="pass();" type="button" class="btn btn-success" data-dismiss="modal">通过
					</button>
					<button onclick="refuse();" type="button" class="btn btn-danger" data-dismiss="modal">拒绝
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="display:none;">
<div id="jid"></div>
</div>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/app.min.js"></script>
<script src="static/js/dateutil.js"></script>
<script src="static/js/admin/jobcheck.js"></script>
<script type="text/javascript">
$('#nav_merchant').addClass('active');
</script>
</body>
</html>