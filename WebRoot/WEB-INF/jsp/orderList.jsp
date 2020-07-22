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
    <title>我的订单</title>
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
 		<table class="table table-striped">
 			<tr style="font-weight:bolder;">
 				<td>拍卖品图片</td>
 				<td>拍卖品名称</td>
 				<td>总价格(万)</td>
 				<td>用户名</td>
 				<td>拍下时间</td>
 				<td>拍卖品状态</td>
 			</tr>
 			<c:forEach var="order" items="${requestScope.orderList }">
 				<tr>
 					<td><img style="width:55px;height:55px;" src="<%=path%>/static/img/${order.product.pImg}"/></td>
 					<td>${order.product.pName }</td>
 					<td>${order.product.pPrice}</td>
 					<td>${order.user.uName }</td>
 					<td>${order.createDate }</td>
 					<td>竞拍成功</td>
 				</tr>
 			</c:forEach>
		</table>
 		<!-- 底部导航条 -->
		<%@ include file="/static/common/footer.jsp"  %>
	   	<script type="text/javascript" src="<%=path %>/static/js/bootstrap.js"></script>
  </body>
</html>
