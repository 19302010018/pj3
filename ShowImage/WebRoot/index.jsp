<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> 
	<link rel="stylesheet" href="<c:url value = '/css/bootstrap.min.css'/>">
	<link rel="stylesheet" href="<c:url value = '/css/font-awesome.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value = '/css/animate.css'/>" />
	<link rel="stylesheet" href="<c:url value = '/css/sina-nav.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value = '/css/sortable.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value = '/css/partialviewslider.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value = '/css/main.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value = '/css/index.css'/>" />
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
						<c:choose>
							<c:when test="${empty sessionScope.sessionUser }">
								<li class="scroll"><a href="<c:url value='/jsps/login.jsp'/>">未登录，马上登录</a></li>
							</c:when>
							<c:otherwise>
								<li class="scroll"><a>欢迎您： ${sessionScope.sessionUser.username }</a></li>
							</c:otherwise>
						</c:choose>
					
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

		<div class="container">
			<div class="row">
				<div class="col-6 col-lg-12">
					<div id="home">
						<div class="container-img">
							<ul id="partial-view" >
								<c:forEach items="${hotImage }" var="image">
									<li style="height: 500px">
										<img src="images/${image.PATH }" />
									</li>
								</c:forEach>
							</ul>
						</div>
						<main class="sortable">
							<div class="container-main">
								<div class="wrapper">
									<ul class="sortable__nav nav">
										<li>
											<a data-sjslink="all" class="main_nav__link">
												All
											</a>
										</li>
										<li>
											<a data-sjslink="Natural" class="main_nav__link">
												Natural
											</a>
										</li>
										<li>
											<a data-sjslink="Scenery" class="main_nav__link">
												Scenery
											</a>
										</li>
												<li>
											<a data-sjslink="Animal" class="main_nav__link">
												Animal
											</a>
										</li>
												<li>
											<a data-sjslink="Scenery" class="main_nav__link">
												Scenery
											</a>
										</li>
												<li>
											<a data-sjslink="Character" class="main_nav__link">
												Character
											</a>
										</li>
									</ul>
									<div id="sortable" class="sjs-default">
										<c:forEach items="${imageList}" var="myImage">
											<div data-sjsel="${myImage.topic }">
												<a href="<c:url value='/MyImageServlet?method=imageDetails&imageId=${myImage.imageId }&uid=${sessionScope.sessionUser.uid }'/>">
												<div class="card">
													<img class="card__picture" src="images/${myImage.PATH }" alt="">
													<div class="card-infos">
														<h2 class="card__title">${myImage.getTitle() }</h2>
														<p class="card__text">
															${myImage.getDescription() }
														</p>
													</div>
												</div>
												</a>
											</div>
										</c:forEach>
								</div>
								</div>
							</div>
						</main>
					</div>
				</div>
			</div>
		</div>
		<script src="<c:url value = '/js/jquery.min.js'/>"></script>
		<script src="<c:url value = '/js/bootstrap.min.js'/>"></script>
		<script src="<c:url value = '/js/wow.min.js'/>"></script>
		<script src="<c:url value = '/js/sina-nav.js'/>"></script>
		<script src="<c:url value = '/js/sortable.js'/>" type="text/javascript" charset="utf-8"></script>
		<script src="<c:url value = '/js/partialviewslider.js'/>"></script>
	
		<script type="text/javascript">
			document.querySelector('#sortable').sortablejs()
		</script>
		<script>
			$(document).ready(function() {
				var partialView = $('#partial-view').partialViewSlider();
				$('#prev').on('click', function() {
					partialView.prev();
				});
				$('#next').on('click', function() {
					partialView.next();
				});
			});
		</script>
		<script type="text/javascript">
			$(document).ready(function() {
				new WOW().init();
			});
		</script>
	</body>
</html>
