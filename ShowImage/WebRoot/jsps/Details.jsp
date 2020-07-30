<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'Details.jsp' starting page</title>
		<!--
			<link rel="stylesheet" type="text/css" href="<c:url value=''/>">
			<script type="text/javascript" src="<c:url value=''/>"></script>
			-->
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<link rel="stylesheet" href="<c:url value = '/css/bootstrap.min.css'/>">
		<link rel="stylesheet" href="<c:url value = '/css/font-awesome.css'/>">
		<link rel="stylesheet" type="text/css"href="<c:url value = '/css/animate.css'/>" />
		<link rel="stylesheet" href="<c:url value = '/css/sina-nav.css'/>">
		<link rel="stylesheet" href="<c:url value = '/css/Details.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sortable.css'/>" />
		<link rel="stylesheet" type="text/css" href="<c:url value = '/css/partialviewslider.css'/>" />
	</head>
	<body>
	<nav class="sina-nav mobile-sidebar navbar-fixed" data-top="0">
			<div class="container">
				<div class="search-box">
					<form role="search" method="get" action="<c:url value='/MyImageServlet'/>">
						<input type="hidden" name="method" value="findByTitle">
						<input type="hidden" name="uid" value="${sessionScope.sessionUser.uid }">
						<span class="search-addon close-search"><i class="fa fa-times"></i></span>
						<div class="search-input">
							<input type="search" name="title" class="form-control" placeholder="请输入图片的标题" >
						</div>
						<span class="search-addon search-icon"><i class="fa fa-search"></i></span>
					</form>
				</div><!-- .search-box -->

				<div class="extension-nav">
					<ul>
						<c:if test="${!empty sessionScope.sessionUser }">
							<li><a href="<c:url value='/UserServlet?method=quit'/>">退出</a></li>
						</c:if>
						<li class="search"><a href="#">搜索</a></li>
						<li class="widget-bar-btn"><a href="#">好友列表</a></li>
					</ul>
				</div><!-- .extension-nav -->

				<div class="sina-nav-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-menu">
						<i class="fa fa-bars"></i>
					</button>
					<a class="sina-brand social-on" href="#">
						<h2>
							图片
						</h2>
						<p>发现美好世界</p>
					</a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-menu">
					<ul class="sina-menu" data-in="fadeInLeft" data-out="fadeInOut">
						<li class="scroll"><a href="<c:url value='/IndexServlet'/>">首页</a></li>
						<li class="active scroll"><a href="<c:url value='/MyImageServlet?method=getMyCollectioon&uid=${sessionScope.sessionUser.uid }'/>">我的收藏</a></li>
						<li class="active scroll">
							<a href="<c:url value='/MyImageServlet?method=getMyImage&uid=${sessionScope.sessionUser.uid}&pc=1'/>" >我的照片</a>
						</li>
						<li class="scroll"><a href="<c:url value='/GeoServlet?method=getCountry'/>">上传</a></li>
						<li class="scroll"><a href="<c:url value='/MyImageServlet?method=search'/>">高级查询</a></li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- .container -->

			<!-- Start widget-bar -->
			<div class="widget-bar">
				<a href="#" class="close-widget-bar"><i class="fa fa-times"></i></a>
				<div class="widget">
					<h6 class="title">我的好友</h6>
					<ul class="link">
						<c:forEach items="${sessionScope.friendList }" var= "friend">
							<li style="padding:10px 0"><a href="<c:url value='/FriendServlet?method=getFriendCollectioon&fid=${friend.fid }&uid=${sessionScope.sessionUser.uid }&fname=${friend.fname }&isShow=${friend.isShow }'/>">${friend.fname }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- End widget-bar -->
		</nav>
		<div class="wrap">
		<div class="tile">
			<img src="images/${details.path }" />
			<div class="text">
				<h1>${details.title }</h1>
				<h3 class="animate-text">Describe：</h3>
				<p style="padding-left: 80px;" class="animate-text">${details.describe } </p>
				<h3 class="animate-text">Uploader：</h3>
				<p style="padding-left: 80px;" class="animate-text">${details.uname } </p>
				<h3 class="animate-text">Country：</h3>
					<p style="padding-left: 80px;" class="animate-text">${details.country } </p>
				<h3 class="animate-text">Theme：</h3>
					<p style="padding-left: 80px;" class="animate-text">${details.theme } </p>				
				<c:choose>
					<c:when test="${details.isCollection ==0 }">
						<a href="<c:url value='/MyImageServlet?method=addCollection&uid=${sessionScope.sessionUser.uid }&imageId=${details.imageId }'/>">
							<div class="coll_btn" >
								<span>收藏</span>
							</div>
						</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value='/MyImageServlet?method=deleteCollection&uid=${sessionScope.sessionUser.uid }&imageId=${details.imageId }'/>">
							<div class="coll_btn" >
								<span>取消收藏</span>
							</div>
						</a>
					</c:otherwise>
				</c:choose>
		
			</div>
		</div>
	</div>
	</body>
	<script src="<c:url value = '/js/jquery.min.js'/>"></script>
	<script src="<c:url value = '/js/bootstrap.min.js'/>"></script>
	<script src="<c:url value = '/js/wow.min.js'/>"></script>
	<script src="<c:url value = '/js/sina-nav.js'/>"></script>
	<script src="<c:url value = '/js/partialviewslider.js'/>"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			new WOW().init();
		});
	</script>
</html>
