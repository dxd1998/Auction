<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>拍卖品上架</title>
     <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
  </head>
  <body>
  		<!-- 头部导航条 -->
 		<%@ include file="/static/common/header.jsp" %>
  		<form style="width:500px;margin-left:520px;">
		  <div class="form-group">
		    <label for="exampleInputEmail1">拍卖品名称</label>
		    <input type="text" class="form-control" id="pName" placeholder="拍卖品名称">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">拍卖品总价(万)</label>
		    <input type="text" class="form-control" id="pPrice" placeholder="价格">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">拍卖起拍价(玩)</label>
		    <input type="text" class="form-control" id="firstPrice" placeholder="拍卖起拍价">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">拍卖开始日</label>
		    <input type="text" class="form-control" id="startDate" placeholder="拍卖开始日">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">拍卖结束日</label>
		    <input type="text" class="form-control" id="endDate" placeholder="拍卖结束日">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputFile">拍卖品图片</label>
		    <input type="file" id="pImg">
		    <p class="help-block">Example block-level help text here.</p>
		  </div>
		  <button type="button" class="btn btn-default" id="load">上传</button>
		</form>
		<!-- 底部导航条 -->
		<%@ include file="/static/common/footer.jsp"  %>
	   	<script type="text/javascript" src="<%=path %>/static/js/bootstrap.js"></script>
	   	<script type="text/javascript">
	   		//上传按钮点击事件
	   		$("#load").click(function(){
	   			//用户输入
	   			var pName = $("#pName").val();
	   			var pPrice = $("#pPrice").val();
	   			var firstPrice = $("#firstPrice").val();
	   			var startDate = $("#startDate").val();
	   			var endDate = $("#endDate").val();
	   			var fileName = $("#pImg").val();
	   			var $zDate = /^(19[0-9]2|200[0-9]|201[0-9]|2020)-([1-9]|1[0-2])-([1-9]|1[0-9]|2[0-9]|3[0-1])$/;
	   			//非空判断
	   			if(pName == ""){
	   				alert("拍卖品名称不能为空!");
	   				return;
	   			}
	   			if(pPrice == ""){
	   				alert("拍卖品金额不能为空!");
	   				return;
	   			}
	   			if(isNaN(pPrice) == true){
	   				alert("请输入正确金额!");
	   				return;
	   			}
	   			if(firstPrice == ""){
	   				alert("起拍价不能为空!");
	   				return;
	   			}
	   			if(isNaN(firstPrice) == true){
	   				alert("请输入正确金额!");
	   				return;
	   			}
	   			if(startDate == ""){
	   				alert("拍卖日不能为空!");
	   				return;
	   			}
	   			if($zDate.test(startDate) == false){
	   				alert("拍卖日格式错误!yyyy-MM-dd");
	   				return;
	   			}
	   			if(endDate == ""){
	   				alert("拍卖结束日不能为空!");
	   				return;
	   			}
	   			if($zDate.test(endDate) == false){
	   				alert("拍卖结束日格式错误!yyyy-MM-dd");
	   				return;
	   			}
	   			if(fileName == ""){
	   				alert("请上传拍卖品图片!");
	   				return;
	   			}
	   			var formData = new FormData();
	   			formData.append('file',$('#pImg')[0].files[0]);
	   			formData.append('pName', pName);
	   			formData.append('pPrice',pPrice);
	   			formData.append('startDate',startDate);
	   			formData.append('endDate',endDate);
	   			formData.append('firstPrice',firstPrice);
	   			console.log(formData.get('pName'));
	   			
	   			//使用ajax
	   			$.ajax({
	   				url		:		"<%=path%>/spring/product/add",
	   				method	:		"post",
	   				data	:		formData,
	   				contentType : false,
					processData : false,
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
