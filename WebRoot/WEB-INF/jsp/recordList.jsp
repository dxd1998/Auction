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
    <title>竞拍记录</title>
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
 				<td>出价者</td>
 				<td>出价(万)</td>
 				<td>出价时间</td>
 				<td>竞拍商品</td>
 			</tr>
 			<c:forEach var="record" items="${requestScope.recordList }">
 				<tr>
 					<td>${record.uName}</td>
 					<td>${record.onePrice}</td>
 					<td>${record.giveDate}</td>
 					<td>${record.pName}</td>
 				</tr>
 			</c:forEach>
		</table>
		<!-- 分页按钮 -->
			<nav aria-label="...">
			  <ul class="pager">
			  	<!-- 判断 -->
			  	<c:choose>
			  		<c:when test="${pager.currentPage > 1 }">
			  			<li class="previous"><a href="<%=path%>/${requestScope.pager.url}?currentPage=${requestScope.pager.currentPage-1}&pName=${requestScope.pName == null ?'':requestScope.pName}&dId=${requestScope.dId == null ? '0':requestScope.dId}"><span aria-hidden="true">&larr;</span> 上一页</a></li>
			  		</c:when>
			  		<c:otherwise>
			  		</c:otherwise>
			  	</c:choose>
			  	<div style="position:absolute;left:700px;bottom:180px;font-size:16px;font-weight:bold;">
			  		共有${pager.rowCount}条数据
			  		页数:${pager.currentPage}/${pager.pageCount}
			  	</div>
			  	<c:choose>
			  		<c:when test="${pager.currentPage < pager.pageCount }">
			  			<li class="next"><a href="<%=path%>/${requestScope.pager.url}?currentPage=${requestScope.pager.currentPage+1}&pName=${requestScope.pName == null ?'':requestScope.pName}&dId=${requestScope.dId == null ? '0':requestScope.dId}">下一页 <span aria-hidden="true">&rarr;</span></a></li>
			  		</c:when>
			  		<c:otherwise>
			  		</c:otherwise>
			  	</c:choose>
			  </ul>
			</nav>
 		<!-- 底部导航条 -->
		<%@ include file="/static/common/footer.jsp"  %>
	   	<script type="text/javascript" src="<%=path %>/static/js/bootstrap.js"></script>
  </body>
</html>
