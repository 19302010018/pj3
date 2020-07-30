<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'upload.jsp' starting page</title>
<link
	href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="<c:url value = '/css/font-awesome.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value = 'css/animate.css'/>" />
<link rel="stylesheet" href="<c:url value = '/css/sina-nav.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value = '/css/partialviewslider.css'/>" />

<link rel="stylesheet" type="text/css"
	href="<c:url value = '/css/default.css'/>">
<link href="<c:url value = '/css/fileinput.css'/>" media="all"
	rel="stylesheet" type="text/css" />
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
	<div class="htmleaf-container">
		<header class="htmleaf-header">
		<h1>
			正在修改标题为《${myImage.title }》图片 的信息<span></span>
		</h1>
		</header>
		<div class="container kv-main">
		
			<form action="<c:url value='/MyImageServlet?'/>" method="post"
				 method="post" id="form">
				<input type="hidden" name="method" value="retImage" >
				<input type="hidden" name="imageId" value="${myImage.imageId }" >
				<input type="hidden" name="uid"
					value="${sessionScope.sessionUser.uid }">
				<div class="form-group">
					<label for="exampleInputEmail1">标题</label> <input name="title"
						type="text" class="form-control" id="exampleInputEmail1"
						placeholder="图片标题" value="${myImage.title }">
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">图片描述</label>
					<textarea name="description" class="form-control" rows="4">${myImage.description }</textarea>
				</div>
				<hr>
				<label>选择主题：</label> <select name="topic"
					class="bootstrap-select form-control">
					<c:if test="${myImage.topic == 'Scenery' }">
						<option selected="selected" value="Scenery">Scenery</option>
						<option value="Character">Character</option>
						<option value="Comic">Comic</option>
						<option value="Natural">Natural</option>
						<option value="Animal">Animal</option>
					</c:if>
					<c:if test="${myImage.topic == 'Animal' }">
						<option selected="selected" value="Animal">Animal</option>
						<option value="Scenery">Scenery</option>
						<option value="Character">Character</option>
						<option value="Comic">Comic</option>
						<option value="Natural">Natural</option>
					</c:if>
					<c:if test="${myImage.topic == 'Character' }">
						<option selected="selected" value="Character">Character</option>
						<option value="Scenery">Scenery</option>
						<option value="Comic">Comic</option>
						<option value="Natural">Natural</option>
						<option value="Animal">Animal</option>
					</c:if>
					<c:if test="${myImage.topic == 'Comic' }">
						<option selected="selected" value="Comic">Comic</option>
						<option value="Scenery">Scenery</option>
						<option value="Character">Character</option>

						<option value="Natural">Natural</option>
						<option value="Animal">Animal</option>
					</c:if>
					<c:if test="${myImage.topic == 'Natural' }">
						<option selected="selected" value="Natural">Natural</option>
						<option value="Scenery">Scenery</option>
						<option value="Character">Character</option>
						<option value="Comic">Comic</option>
						<option value="Animal">Animal</option>
					</c:if>
				</select>
				<hr>
				<label>选择国家：</label> <select name="countryCodeISO" id="countryId"
					class="bootstrap-select form-control" onchange="loadChildren()">
					<c:forEach items="${countryList }" var="country">
						<option value="${country.iso }" <c:if test="${country.iso == myImage.countryCodeISO }"> selected="selected"</c:if>>${country.countryName }</option>
					</c:forEach>
				</select>
				<hr>
				<label>选择城市：</label> <select name="cityCode" id="cityId"
					class="bootstrap-select form-control">
					<c:forEach items="${cityList }" var="city">
						<option value="${city.geoNameID }" <c:if test="${myImage.cityCode == city.geoNameID }"> selected="selected"</c:if>>${city.asciiName }</option>
					</c:forEach>
				</select>
		</div>
		<hr>
		<div style="height:100px"></div>
		<div class="form-group">
			<div class="col-sm-offset-5 col-sm-10">
				<button type="submit" style="font-size: 22px" class="btn btn-primary">修改图片信息</button>
			</div>
		</div>
		<hr>
		</form>
	</div>
</body>
<script src="<c:url value = '/js/jquery.min.js'/>"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="<c:url value = '/js/wow.min.js'/>"></script>
<script src="<c:url value = '/js/sina-nav.js'/>"></script>
<script src="<c:url value = '/js/partialviewslider.js'/>"></script>
<script src="<c:url value = '/js/fileinput.js'/>" type="text/javascript"></script>
<script>
	function loadChildren() {
		var countryISO = $("#countryId").val();

		// 2. 发送异步请求
		$.ajax({
			async : true,
			cache : false,
			url : "/ShowImage/GeoServlet",
			data : {
				method : "getCity",
				iso : countryISO
			},
			type : "POST",
			dataType : "json",
			success : function(arr) {

				// 3. 得到cid，删除它的内容
				$("#cityId").empty(); //删除元素的子元素
				// 5. 循环遍历数组，把每个对象转换成<option>添加到cid中
				for (var i = 0; i < arr.length; i++) {
					var option = $("<option>").val(arr[i].geoNameID).text(arr[i].asciiName);
					$("#cityId").append(option);
				}
			}
		});
	}
</script>

</html>
