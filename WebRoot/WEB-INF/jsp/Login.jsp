<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>拍卖系统-登录</title>
	<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
</head>
<body onkeydown="keyLogin(event);" style="background: url(<%=path%>/static/img/263f0171ca02b0b5f66d254f199705b1.jpg) top center no-repeat; background-size:cover;">
	<h1 style="position: absolute;left:700px;top:100px;color:#00FF00;font-weight:bolder;">拍卖系统</h1>
	<!-- 登录表单 -->
	<form class="form-horizontal" style="margin-left:500px;margin-top:220px;width:1000px;">
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label" style="color:#C71585;font-weight:bolder;font-size:18px;">用户账户:</label>
			<div class="col-sm-10">
				<input type="email" class="form-control" id="uLoginName" style="width:280px;"
					placeholder="用户账户">
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label" style="color:#C71585;font-weight:bolder;font-size:18px;">密码:</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" id="uPassword"
					placeholder="密码" style="width:280px;">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				&nbsp;
				<button type="button" class="btn btn-success" id="login">登录</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-info" id="clear">重置</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" id="regist">立即注册</button>
			</div>
		</div>
	</form>


	<script type="text/javascript" src="<%=path%>/static/js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="<%=path%>/static/js/bootstrap.js"></script>
    <script type="text/javascript">
    	//登录按钮点击事件
    	$("#login").click(function(){
    		//用户输入
    		var uLoginName = $("#uLoginName").val();
    		var uPassword = $("#uPassword").val();
    		
    		//非空判断
    		if(uLoginName == ""){
    			alert("登录名不能为空哟!");
    			return;
    		}
    		if(uPassword == ""){
    			alert("密码不能为空哟!");
    			return;
    		}
    		//使用ajax
    		var ise = {
    			uLoginName:uLoginName,
    			uPassword:uPassword
    		}
    		$.ajax({
    			url		:		"<%=path%>/spring/user/login",
    			method	:		"post",
    			data	:		ise,
    			success	:		function(data){
    				var json = eval('('+data+')');
    				if(json.status == 1){
    					//跳转至主页
    					location.href="<%=path%>/spring/user/index";
    				}else{
    					alert(json.message);
    				}
    			}
    		});
    	});
    	
    	$("#regist").click(function(){
    		location.href="<%=path%>/spring/user/toRegist";
    	});
    	//重置按钮点击事件
    	$("#clear").click(function(){
    		
    	});
    	//键盘enter键登录
    	 function keyLogin(event){
		  	if (event.keyCode==13)//按Enter键的键值为13  
		    document.getElementById("login").click();  //调用登录按钮的登录事件
		    // or  
		    //document.getElementById('login_form').submit();
		}
    </script>
</body>
</html>
