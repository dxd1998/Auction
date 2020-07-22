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
    <title>拍卖会首页</title>
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
  	<style type="text/css">
  		
  	</style>
  </head>
  
  <body style="height:1400px;">
  		<!-- 头部导航条 -->
 		<%@ include file="/static/common/header.jsp" %>
 		<div class="jumbotron">
		  <div class="container">
		    <h1 style="display: inline-block;">欢乐拍卖会!</h1>
		    <div id="timer" style="color:red;font-size:20px;display: inline-block;margin-left:200px;"></div>
		  </div>
		</div>
		<div class="row" style="margin-left:200px;">
		  <c:forEach var="pro" items="${requestScope.proList}" varStatus="status">
		  		<!-- 显示状态为待竞拍拍卖品 并且只能同时8件拍卖品-->
		  		<c:if test="${pro.type.tId == 2 && status.index+1 != 8}">
		  			<div class="col-sm-6 col-md-4" style="width:300px;">
					    <div class="thumbnail" style="height:480px;">
					      <img src="<%=path%>/static/img/${pro.pImg}" style="width:250px;height:210px;">
					      <div class="caption">
					        <h3>${pro.pName }</h3>
					        <h4>总金额:${pro.pPrice }万</h4>
					        <h4 style="font-weight:bolder;color:red;">起拍金额:${pro.firstPrice}万</h4>
					        <h4>每次加价不少于<span style="font-weight:bolder;color:red;">5000</span>元</h4>
					        <p>拍卖日期:${pro.startDate }</p>
					        <p>结束日期:${pro.endDate }</p>
					        <p>
					        	<input type="button" class="btn btn-primary" role="button" onclick="settime(this)" value="开始竞拍">
					        	<input type="hidden" value="${pro.pId }"/>
					        </p>
					      </div>
					    </div>
			  		</div>
		  		</c:if>
		  </c:forEach>
		</div>
 		<!-- 底部导航条 -->
		<%@ include file="/static/common/footer.jsp"  %>
	   	<script type="text/javascript" src="<%=path %>/static/js/bootstrap.js"></script>
	   	<script type="text/javascript">
			 var countdown=20;        //初始值
			    function settime(val) {
			        if (countdown == -1) {
			            val.removeAttribute("disabled");
			            val.value="开始竞拍";
			            countdown = 20;                     
			            return false;
			        } else {
			            val.setAttribute("disabled", true);
			            val.value="竞拍开始(" + countdown + ")";
			            var pId = $(val).next().val(); //竞拍商品id
			            //开始竞拍
			           $.ajax({
		             		url		:		"<%=path%>/spring/product/startAuction",
		             		method	:		"post",
		             		data	:		{"pId":pId,"countdown":countdown},
		             		success	:		function(data){
		             			var json = eval('('+data+')');
		             			if(json.status == 1){
		             				alert(json.message);
		             				countdown = -1;
		             				location.reload();
		             			}else{
		             				alert(json.message);
		             				countdown--;
		             			}
		             		}
		             	});
			        }
			        setTimeout(function() {   //设置一个定时器，每秒刷新一次
			            settime(val);
			        },1000);
			    }

	   		/*  var maxtime = 10*60; //一个小时，按秒计算，自己调整!   
              function CountDown() {
                  if (maxtime >= 0) {
                      minutes = Math.floor(maxtime / 60);
                     seconds = Math.floor(maxtime % 60);
                     msg = "距离结束还有" + minutes + "分" + seconds + "秒";
                     document.all["timer"].innerHTML = msg;
                     if (maxtime == 5 * 60)alert("还剩5分钟");
                         --maxtime;
                 } else{
                    clearInterval(timer);
                     alert("时间到，结束!");
                 }
             }
             timer = setInterval("CountDown()", 1000);  */
             //开始竞拍按钮
             <%-- function onstart(pId,pPrice){
             	//使用ajax
             	$.ajax({
             		url		:		"<%=path%>/spring/product/startAuction",
             		method	:		"post",
             		data	:		{"pId":pId,"pPrice":pPrice},
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
             } --%>
	   	</script>
  </body>
</html>
