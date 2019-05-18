<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Welcome</title>
</head>
<style>
p{text-align: center;font-style: oblique;font-size: 25px;}
box{
		background-color: #ffffff;
   		width: 300px;
   		border: 5px solid black;
    	padding: 25px;
    	font-style: oblique;
    	border-radius: 5px;
    	color: black;
    	margin-left: auto;
    	margin-right: auto;
    	display: block;
    	opacity: 0.6;
    	text-align: center;
	}
body {
	background-image: url("bg.jpg");
}
</style>
<body>
<box>
<%String data=(String)session.getAttribute("data"); out.print(data);%>
<form action="Servlet"  method="POST">
	<input type="hidden" name="case" value="getall"/>
	<input type="submit" >
</form>
</box>
</body>
</html>