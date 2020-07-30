<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>登录界面</title>
	<!--
	<link rel="stylesheet" type="text/css" href="<c:url value=''/>">
	<script type="text/javascript" src="<c:url value=''/>"></script>
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value= '/css/demo.css'/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value= '/css/style.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value= '/css/animate-custom.css'/>"/>
  </head>
  
   <body>
        <div class="container">
            <header>
                <h1>欢迎登录</span></h1>
            </header>			
                <div id="container_demo" >
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">
                        <div id="login" class="animate form">
                            <form action="<c:url value='/UserServlet'/>" autocomplete="on"> 
                            	 <input type="hidden" name="method" value="login" />
                                <h1>Log in</h1> 
                                <label  style="color: #f00;margin-top: 10px"> ${msg } </label>
                                <hr>
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > 请输入您的用户名 </label>
                                    <input value="${username }" id="username" name="username" required="required" type="email" placeholder="myusername or mymail@mail.com"/>
                                    
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> 请输入您的密码 </label>
                                    <input id="password" name="password" required="required" type="password" placeholder="eg. X8df!90EO" /> 
                                </p>
                                <p> 
                                    <label for="vcode" class="youpasswd" data-icon="v"> 验证码： </label>
                                    <input style="vertical-align:middle;" id="vcode" name="verifyCode" required="required" type="text" placeholder="eg. A1C2" />
                                </p>
                                <img style="vertical-align:middle;width: 180px;height: 50px " id="vCode" src="<c:url value='/VerifyCodeServlet'/>"/> 
                        		<a href="javascript:_hyz()">换张图</a>
                                <p class="login button"> 
                                    <input type="submit" value="登录" /> 
								</p>
                                <p class="change_link">
									没有账号?
									<a href="<c:url value='/jsps/register.jsp'/>" class="to_register">立即注册</a>
								</p>
                            </form>
                        </div>
					</div>	
        </div>
          </div>
    </body>
    <script src="<c:url value = '/js/jquery.min.js'/>"></script>
  	 <script src="<c:url value = '/js/bootstrap.min.js'/>"></script>
     <script type="text/javascript">
	    function _hyz(){
			$("#vCode").attr("src","/ShowImage/VerifyCodeServlet?a=" + new Date().getTime());
		};
    </script> 
  	
</html>
