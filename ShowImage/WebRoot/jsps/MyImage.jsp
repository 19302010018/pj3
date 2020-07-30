<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>我的照片</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="css/animate.css" />
<link rel="stylesheet" href="css/sina-nav.css">
<link rel="stylesheet" type="text/css" href="css/partialviewslider.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/sortable.css" />
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

	<div class="container" style="height:auto">
		<main class="sortable">
		<div id="sortable" class="sjs-default">
			<c:forEach items="${pb.beanList}" var="myImage">
				<div data-sjsel="funny">
					<div class="card">
						<img class="card__picture" src="images/${myImage.PATH }" alt="">
						<div class="card-infos">
							<h2 class="card__title">${myImage.getTitle() }</h2>	
							<div style="float:right">
							
								<a href="<c:url value='/MyImageServlet?method=updateImage&ImageID=${myImage.imageId}'/>">
									<Button style="margin-left:20px" class="btn btn-primary">修改</Button>
								</a>
							    <a href="<c:url value='/MyImageServlet?method=deleteImage&imageId=${myImage.imageId }&uid=${myImage.uid }'/>">
							    	<Button style="margin-left:20px" class="btn btn-primary">删除</Button>
							    </a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
		</main>
	</div>
	   

	<!-- JS files -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/sina-nav.js"></script>
	<script src="js/jquery-1.11.0.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="js/jquery.bootstrap-autohidingnavbar.js"></script>
	<script src="js/sortable.js" type="text/javascript" charset="utf-8"></script>
	<script src="../js/partialviewslider.js"></script>
	<script>
		$("div.navbar.fixed-top").autoHidingNavbar();
	</script>
	<script type="text/javascript">
		document.querySelector('#sortable').sortablejs()
	</script>

	<!-- For All Plug-in Activation & Others -->
	<script type="text/javascript">
		$(document).ready(function() {
			new WOW().init();
		});
	</script>
</body>
<%@include file="/jsps/pager/pager.jsp" %>
</html>
