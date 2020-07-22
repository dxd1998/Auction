    <%@ page pageEncoding="utf8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>
    <!-- 顶部导航条 -->
  	<nav class="navbar navbar-inverse">
	 	<div class="container-fluid">
	    	<!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="${pageContext.request.contextPath}/spring/user/index"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>拍卖系统</a>
	    </div>
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	          <li class="active"><a href="${pageContext.request.contextPath}/spring/product/toAdd">上架拍品 <span class="sr-only">(current)</span></a></li>
	     	  <li><a href="${pageContext.request.contextPath}/spring/product/myproduct">我的拍品 <span class="sr-only">(current)</span></a></li>
	     	  <li><a href="${pageContext.request.contextPath}/spring/product/toOrder">我的订单 <span class="sr-only">(current)</span></a></li>
		      <x:if test="${sessionScope.loginUser.role.rId == 1 }">
		        <li><a href="${pageContext.request.contextPath}/spring/product/checkproduct">审核拍卖品 <span class="sr-only">(current)</span></a></li>
		         <li><a href="${pageContext.request.contextPath}/spring/product/toRecord">竞拍记录 <span class="sr-only">(current)</span></a></li>
		      </x:if>
	      </ul>
	       <form class="navbar-form navbar-left">
	        <div class="form-group">
	          <input type="text" class="form-control" style="width:350px;" placeholder="站内搜索">
	        </div>
	        <button type="button" class="btn btn-default">搜索</button>
	      </form>
	      <ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	          	<a style="color:#FFFFFF;font-size:17px;">欢迎您:<strong style="color:red;font-size:16px;font-weight:bolder;">${sessionScope.loginUser.uName}</strong>&nbsp;&nbsp;&nbsp;亲爱的:<strong style="color:red;font-size:16px;font-weight:bolder;">${sessionScope.loginUser.role.rName}</strong></a>
	        </li>
	        <li style="margin-left:100px;margin-right:80px;">
	        	<button type="button" class="btn btn-danger" style="margin-top:8px;" id="exit">退出</button>
	        	<%-- <a href="${pageContext.request.contextPath}/spring/login/exit">退出</a> --%>
	        </li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.js"></script>
	<script type="text/javascript">
		//退出按钮点击事件
    	$("#exit").click(function(){
    		var flag = window.confirm("确认退出系统吗?");
    		if(flag){
    			alert("谢谢您的使用!再见!");
    			location.href="${pageContext.request.contextPath}/spring/user/exit"
    		}
    	});
	</script>
