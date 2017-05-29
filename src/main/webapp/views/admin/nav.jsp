<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	<header class="main-header">
		<!-- Logo -->
		<a class="logo">
			<!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><img style="margin:15%;width:70%;" alt="小蜜蜂" src="static/img/logo.png"></span>
			<!-- logo for regular state and mobile devices -->
			<span class="logo-lg">小蜜蜂兼职管理界面</span>
		</a>

		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top">
			<!-- Sidebar toggle button-->
			<a class="sidebar-toggle" data-toggle="offcanvas" role="button">
				<span class="sr-only">Toggle navigation</span>
			</a>
		</nav>
	</header>
	<!-- Left side column. contains the logo and sidebar -->
	<aside class="main-sidebar">
		<!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar">
			<!-- sidebar menu: : style can be found in sidebar.less -->
			<ul class="sidebar-menu">
				<li class="header">导航</li>
				<li id="nav_index" class="treeview">
					<a href="adminp/index">
						<i class="fa fa-home"></i>
						<span>主页</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-right"></i>
						</span>
					</a>
				</li>
				<li id ="nav_merchant" class="treeview">
					<a href="adminp/mchnt">
						<i class="fa fa-tripadvisor"></i>
						<span>商户管理</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-right pull-right"></i>
						</span>
					</a>
				</li>
				<li id="nav_user" class="treeview">
					<a href="adminp/user">
						<i class="fa fa-users"></i>
						<span>用户管理</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-right pull-right"></i>
						</span>
					</a>
				</li>
				<li id="nav_jobcheck" class="treeview">
					<a href="adminp/jobcheck">
						<i class="fa fa-search"></i>
						<span>兼职审核</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-right pull-right"></i>
						</span>
					</a>
				</li>
				<li id="nav_netjob" class="treeview">
					<a href="adminp/netjob">
						<i class="fa fa-mobile"></i>
						<span>手机兼职</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-right pull-right"></i>
						</span>
					</a>
				</li>
				<li id="nav_cash" class="treeview">
					<a href="adminp/cash">
						<i class="fa fa-dollar"></i>
						<span>提现管理</span>
						<span class="pull-right-container">
							<i class="fa fa-angle-right pull-right"></i>
						</span>
					</a>
				</li>
			</ul>
		</section>
		<!-- /.sidebar -->
	</aside>