<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户注册</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
  </head>
  <body onkeydown="keyLogin(event);" style="background:#EEEEEE">
 		<h1 style="position: absolute;left:700px;top:100px;font-weight:bolder;">用户信息注册</h1>
		<!-- 登录表单 -->
		<form class="form-horizontal" style="margin-left:500px;margin-top:220px;width:1000px;">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label" style="color:#666666;font-weight:bolder;font-size:18px;">用户姓名:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="pName" style="width:280px;"
						placeholder="用户姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label" style="color:#666666;font-weight:bolder;font-size:18px;">用户登录名:</label>
				<div class="col-sm-10">
					<span id="tishi" style="color:red;font-weight:bolder;display:none;"></span>
					<input type="text" class="form-control" id="pLoginName"
						placeholder="登录名" style="width:280px;" onblur="checkLoginName()">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label" style="color:#666666;font-weight:bolder;font-size:18px;">用户密码:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="pPassword"
						placeholder="密码" style="width:280px;">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label" style="color:#666666;font-weight:bolder;font-size:18px;">重复密码:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="rePassword"
						placeholder="重复密码" style="width:280px;">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				&nbsp;
				<button type="button" class="btn btn-success" id="regist">注册</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-info" id="clear">重置</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" id="login">立即登录</button>
				</div>
			</div>
		</form>
		<script type="text/javascript" src="<%=path%>/static/js/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/bootstrap.js"></script>
		<script type="text/javascript">
			function checkLoginName(){
				var uLoginName = $("#pLoginName").val();
				if(uLoginName != ""){
					$.ajax({
						url		:		"<%=path%>/spring/user/checkLoginName",
						method	:		"post",
						data	:		{"uLoginName":uLoginName},
						success	:		function(data){
							var json = eval('('+data+')');
							if(json.status == -1){
								$("#tishi").html(json.message);
								$("#tishi").show();
								$("#regist").attr("disabled",true);
							}else{
								$("#tishi").hide();
								$("#regist").removeAttr("disabled");
							}
						}
					});
				}
			};
			//注册按钮点击事件
			$("#regist").click(function(){
				var pName = $("#pName").val();
				var pLoginName = $("#pLoginName").val();
				var pPassword = $("#pPassword").val();
				var rePassword = $("#rePassword").val();
				//非空判断
				if(pName == ""){
					alert("用户姓名不能为空!");
					return;
				}
				if(pLoginName == ""){
					alert("登录名不能为空!");
					return;
				}
				if(pPassword == ""){
					alert("密码不能为空!");
					return;
				}
				if(rePassword != pPassword){
					alert("二次密码输入不相同!");
					return;
				}
				
				//使用ajax
				var ise = {
					uName:pName,
					uLoginName:pLoginName,
					uPassword:pPassword
				};
				$.ajax({
					url		:		"<%=path%>/spring/user/regist",
					method	:		"post",
					data	:		ise,
					success	:		function(data){
						var json = eval('('+data+')');
						if(json.status == 1){
							alert(json.message);
							location.href="<%=path%>/spring/user/toLogin";
						}else{
							alert(json.message);
						}
					}
				});
			});
			$("#login").click(function(){
				location.href="<%=path%>/spring/user/toLogin";
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
