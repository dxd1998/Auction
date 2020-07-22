<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>拍卖品审核</title>
     <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
  	<style type="text/css">
  		table tr{
  			text-align:center;
  		}
  	</style>
  </head>
  
  <body>
  		<!-- 头部导航条 -->
 		<%@ include file="/static/common/header.jsp" %>
 		<div>
 			<h2>审核拍卖品</h2>
 			 <button type="button" class="btn btn-default" id="checkYes">通过审核</button>
 			 <button type="button" class="btn btn-default" id="checkNo">拒绝审核</button>
 		</div>
 		<br/>
 		<table class="table table-striped">
 			<tr style="font-weight:bolder;">
 				<td>选中</td>
 				<td>拍卖品图片</td>
 				<td>拍卖品名称</td>
 				<td>拍卖品价格(万)</td>
 				<td>上架时间</td>
 				<td>拍卖品状态</td>
 				<td>上传用户</td>
 			</tr>
 			<c:forEach var="pro" items="${requestScope.productList }">
 				<tr>
 					<td><input type="checkbox" name="choose" value="${pro.pId}"/></td>
 					<td><img style="width:55px;height:55px;" src="<%=path%>/static/img/${pro.pImg}"/></td>
 					<td>${pro.pName }</td>
 					<td>${pro.pPrice }</td>
 					<td>${pro.joinDate }</td>
 					<td>${pro.type.tName }</td>
 					<td>${pro.user.uName }</td>
 				</tr>
 			</c:forEach>
		</table>
 		<!-- 底部导航条 -->
		<%@ include file="/static/common/footer.jsp"  %>
	   	<script type="text/javascript" src="<%=path %>/static/js/bootstrap.js"></script>
	   	<script type="text/javascript">
	   	//拒绝审核按钮
	   		$("#checkNo").click(function(){
	   			//得到所有选中的checkbox
	   			var checkboxAll = $("input:checkbox[name='choose']:checked");
	   			//创建数组
	   			var array = new Array();
	   			$.each(checkboxAll,function(j,checkbox){
	   				var checkboxValue = $(checkbox).val();
	   				array.push(checkboxValue);
	   			});
	   			if(array.length == 0){
	   				alert("请选择需要操作的拍卖品!");
	   				return;
	   			}
	   			//使用ajax
	   			$.ajax({
	   				url		:		"<%=path%>/spring/product/updateProduct2",
   					method	:		"post",
   					data	:		{"array":array},
   					traditional	: true,
   					success	:		function(data){
   						var json = eval('('+data+')');
   						if(json.status == 1){
   							alert(json.message);
   							location.href="<%=path%>/spring/user/index";
   						}else{
   							alert(json.message);
   						}
   					}
	   			});
	   		});
	   	//通过审核按钮
	   		$("#checkYes").click(function(){
	   			//得到所有选中的checkbox
	   			var checkboxAll = $("input:checkbox[name='choose']:checked");
	   			//创建数组
	   			var array = new Array();
	   			$.each(checkboxAll,function(j,checkbox){
	   				var checkboxValue = $(checkbox).val();
	   				array.push(checkboxValue);
	   			});
	   			if(array.length == 0){
	   				alert("请选择需要操作的拍卖品!");
	   				return;
	   			}
	   			//使用ajax
	   			$.ajax({
	   				url		:		"<%=path%>/spring/product/updateProduct",
   					method	:		"post",
   					data	:		{"array":array},
   					traditional	: true,
   					success	:		function(data){
   						var json = eval('('+data+')');
   						if(json.status == 1){
   							alert(json.message);
   							location.href="<%=path%>/spring/user/index";
   						}else{
   							alert(json.message);
   						}
   					}
	   			});
	   		});
	   	</script>
  </body>
</html>
