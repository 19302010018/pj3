<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册界面</title>
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
                <h1>欢迎加入分享世界</span></h1>
            </header>			
                <div id="container_demo" >
                    <a class="hiddenanchor" id="toregister"></a>
                    <a class="hiddenanchor" id="tologin"></a>
                    <div id="wrapper">	
                        <div id="register" class="animate form">
                            <form  id="form" action="<c:url value='/UserServlet'/>" autocomplete="on"> 
                            <input type="hidden" name="method" value="register" />
                                <h1> Sign up </h1> 
                                <label  style="color: #f00;margin-top: 10px"> ${regsiter_msg } </label>
                                <hr>
                                <p> 
                                    <label for="usernamesignup" class="uname" data-icon="u">账号</label>
                                    <input value="${regsiter_username }" id="usernamesignup" name="username" required="required" type="email" placeholder="mysuperusername690" />
                                </p>
                                <p> 
                                    <label for="passwordsignup" class="youpasswd" data-icon="p">密码 </label>
                                    <input id="passwordsignup" onKeyUp="CheckIntensity(this.value)"  name="password" required="required" type="password" placeholder="eg. X8df!90EO"/>
                                    <table style='margin-top: 10px;width: 100%' border="0" cellpadding="0" cellspacing="0">
										<tr align="center">
										<td id="pwd_Weak" class="pwd pwd_c"> </td>
										<td id="pwd_Medium" class="pwd pwd_c pwd_f">无</td>
										<td id="pwd_Strong" class="pwd pwd_c pwd_c_r"> </td>
										</tr>
									</table>
                                </p>
                                <p> 
                                    <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">确认密码</label>
                                    <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="eg. X8df!90EO"/>    
                                </p>
                                <p> 
                                    <label for="vcode" class="youpasswd" data-icon="v"> 验证码： </label>
                                    <input style="vertical-align:middle;" id="vcode" name="verifyCode" required="required" type="text" placeholder="eg. A1C2" />
                                </p>
                                <img style="vertical-align:middle;width: 180px;height: 50px " id="vCode2" src="<c:url value='/VerifyCodeServlet'/>"/> 
                        			<a href="javascript:_hyz()">换张图</a>
                                <p class="signin button"> 
									<input type="submit" value="注册"/> 
								</p>
                                <p class="change_link">  
									已有账号 ?
									<a href="<c:url value='/jsps/login.jsp'/>" class="to_register"> 立即登录 </a>
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
			$("#vCode2").attr("src","/ShowImage/VerifyCodeServlet?a=" + new Date().getTime());
		};
		function CheckIntensity(pwd) {
        var Mcolor, Wcolor, Scolor, Color_Html;       
        var m = 0;      
        //匹配数字
        if (/\d+/.test(pwd)) {
            debugger;
            m++;
        };
        //匹配字母
        if (/[A-Za-z]+/.test(pwd)) {         
            m++;
        };
        //匹配除数字字母外的特殊符号
        if (/[^0-9a-zA-Z]+/.test(pwd)) {            
            m++;
        };
       
        if (pwd.length <= 6) { m = 1; }
        if (pwd.length <= 0) { m = 0; }       
        switch (m) {
            case 1:
                Wcolor = "pwd pwd_Weak_c";
                Mcolor = "pwd pwd_c";
                Scolor = "pwd pwd_c pwd_c_r";
                Color_Html = "弱";
                break;
            case 2:
                Wcolor = "pwd pwd_Medium_c";
                Mcolor = "pwd pwd_Medium_c";
                Scolor = "pwd pwd_c pwd_c_r";
                Color_Html = "中";
                break;
            case 3:
                Wcolor = "pwd pwd_Strong_c";
                Mcolor = "pwd pwd_Strong_c";
                Scolor = "pwd pwd_Strong_c pwd_Strong_c_r";
                Color_Html = "强";
                break;
            default:
                Wcolor = "pwd pwd_c";
                Mcolor = "pwd pwd_c pwd_f";
                Scolor = "pwd pwd_c pwd_c_r";
                Color_Html = "无";
                break;
        }
        document.getElementById('pwd_Weak').className = Wcolor;
        document.getElementById('pwd_Medium').className = Mcolor;
        document.getElementById('pwd_Strong').className = Scolor;
        document.getElementById('pwd_Medium').innerHTML = Color_Html;
    } ;
    </script>
</html>
