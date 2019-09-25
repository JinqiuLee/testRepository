<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="keys" content="">
<meta name="author" content="">
<title>❤汇聚点滴的力量，成就非凡的伟业❤</title>
<base
	href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">

</head>

<body>
	<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#bakcBtn").click(function() {
				window.history.back();
			});
		});
	</script>
	<table align="center">
		<tr>
			<td><h1 align="center">${requestScope.exception.message }</h1></td>
		</tr>
		<tr>
			<td><button id="bakcBtn" class="btn btn-lg btn-success btn-block">后退</button></td>
		</tr>
	</table>
	
	

</body>
</html>
