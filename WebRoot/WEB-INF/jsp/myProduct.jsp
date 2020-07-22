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
    <title>我的拍品</title>
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
 			 <c:choose>
 			 	<c:when test="${sessionScope.loginUser.role.rId == 1 }">
 			 		<h2>所有拍卖品</h2>
 			 	</c:when>
 			 	<c:otherwise>
 			 		<h2>我的拍品</h2>
 			 	</c:otherwise>
 			 </c:choose>
 			 <button type="button" class="btn btn-default" id="checkNo">下架拍品</button>
 			 <button type="button" class="btn btn-default" id="checkYes">上架拍品</button>
 			 <button type="button" class="btn btn-default" id="deleteProduct">删除拍品</button>
 		</div>
 		<br/>
 		<table class="table table-striped">
 			<tr style="font-weight:bolder;">
 				<td>选中</td>
 				<td>拍卖品图片</td>
 				<td>拍卖品名称</td>
 				<td>总价格(万)</td>
 				<td>起拍价(万)</td>
 				<td>上架时间</td>
 				<td>拍卖品状态</td>
 				<c:if test="${sessionScope.loginUser.role.rId == 1 }">
 					<td>上架用户</td>
 				</c:if>
 			</tr>
 			<c:forEach var="pro" items="${requestScope.productList }">
 				<tr>
 					<td><input type="checkbox" name="choose" value="${pro.pId}"/></td>
 					<td><img style="width:55px;height:55px;" src="<%=path%>/static/img/${pro.pImg}"/></td>
 					<td>${pro.pName }</td>
 					<td>${pro.pPrice }</td>
 					<td>${pro.firstPrice }</td>
 					<td>${pro.joinDate }</td>
 					<td>${pro.type.tName }</td>
 					<c:if test="${sessionScope.loginUser.role.rId == 1 }">
 						<td>${pro.user.uName }</td>
 					</c:if>
 				</tr>
 			</c:forEach>
		</table>
 		<!-- 底部导航条 -->
		<%@ include file="/static/common/footer.jsp"  %>
	   	<script type="text/javascript" src="<%=path %>/static/js/bootstrap.js"></script>
	   	<script type="text/javascript">
	   		//上架拍卖品
	   		$("#checkYes").click(function(){
	   			var flag = window.confirm("是否确认上架选中的拍品?");
	   			if(flag){
	   				//获得所有选中的checkbox
	   				var checkboxAll = $("input:checkbox[name='choose']:checked");
	   				//创建数组
	   				var array = new Array();
	   				$.each(checkboxAll,function(j,checkbox){
	   					var checkboxValue = $(checkbox).val();
	   					array.push(checkboxValue);
	   				});
	   				//判断
	   				if(array.length == 0){
	   					alert("请选择需要操作的拍品!");
	   					return;
	   				}
	   				//使用ajax
	   				$.ajax({
	   					url		:		"<%=path%>/spring/product/alterUp",
	   					method	:		"post",
	   					data	:		{"array":array},
	   					traditional : true,
	   					success	:		function(data){
	   						var json = eval('('+data+')');
	   						if(json.status == 1){
	   							alert(json.message);
	   							location.reload();
	   						}else{
	   							alert(json.message);
	   						}
	   					}
	   				});
	   			}
	   		});
	   		//下架按钮点击事件
	   		$("#checkNo").click(function(){
	   			var flag = window.confirm("确认下架选中的拍品么?");
	   			if(flag){
	   				//获得所有选中的checkbox
	   				var checkboxAll = $("input:checkbox[name='choose']:checked");
	   				//创建数组
	   				var array = new Array();
	   				$.each(checkboxAll,function(j,checkbox){
	   					var checkboxValue = $(checkbox).val();
	   					array.push(checkboxValue);
	   				});
	   				//判断
	   				if(array.length == 0){
	   					alert("请选择需要操作的拍品!");
	   					return;
	   				}
	   				//使用ajax
	   				$.ajax({
	   					url		:		"<%=path%>/spring/product/dropDown",
	   					method	:		"post",
	   					data	:		{"array":array},
	   					traditional : true,
	   					success	:		function(data){
	   						var json = eval('('+data+')');
	   						if(json.status == 1){
	   							alert(json.message);
	   							location.reload();
	   						}else{
	   							alert(json.message);
	   						}
	   					}
	   				});
	   			}
	   		});
	   		
	   		//删除拍品
	   		$("#deleteProduct").click(function(){
	   			var flag = window.confirm("确认删除选中的拍品么?");
	   			if(flag){
	   				//获得所有选中的checkbox
	   				var checkboxAll = $("input:checkbox[name='choose']:checked");
	   				//创建数组
	   				var array = new Array();
	   				$.each(checkboxAll,function(j,checkbox){
	   					var checkboxValue = $(checkbox).val();
	   					array.push(checkboxValue);
	   				});
	   				//判断
	   				if(array.length == 0){
	   					alert("请选择需要操作的拍品!");
	   					return;
	   				}
	   				//使用ajax
	   				$.ajax({
	   					url		:		"<%=path%>/spring/product/deleteProduct",
	   					method	:		"post",
	   					data	:		{"array":array},
	   					traditional : true,
	   					success	:		function(data){
	   						var json = eval('('+data+')');
	   						if(json.status == 1){
	   							alert(json.message);
	   							location.reload();
	   						}else{
	   							alert(json.message);
	   						}
	   					}
	   				});
	   			}
	   		});
	   	</script>
  </body>
</html>
