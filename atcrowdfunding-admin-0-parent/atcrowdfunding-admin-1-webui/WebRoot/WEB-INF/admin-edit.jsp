<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<%@ include file="/WEB-INF/include-head.jsp"%>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="admin/to/main/page.html">首页</a></li>
					<li><a href="admin/query/for/search.html">数据列表</a></li>
					<li class="active">新增</li>
				</ol>

				<form role="form" action="admin/update.html" method="post">
					<!-- 模型对象中没有的属性不能使用form:hidden -->
					<input type="hidden" name="pageNum" value="${param.pageNum }" /> <input
						type="text" name="id" value="${requestScope.admin.id }"
						hidden="true" />
					<div class="form-group">
						<label for="exampleInputPassword1">登陆账号</label> <input type="text"
							name="loginAcct" value="${requestScope.admin.loginAcct }"
							class="form-control" id="exampleInputPassword1"
							placeholder="请输入登陆账号" readonly="readonly">
						<p align="right" style="color:red">${requestScope.MESSAGE }</p>
					</div>

					<div class="form-group">
						<label for="exampleInputPassword1">登录密码</label> <input type="text"
							name="userPswd" value="${requestScope.admin.userPswd }"
							class="form-control" id="exampleInputPassword1"
							placeholder="请输入用户名称">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">用户姓名</label> <input type="text"
							name="userName" value="${requestScope.admin.userName }"
							class="form-control" id="exampleInputPassword1"
							placeholder="请输入用户名称">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">邮箱地址</label> <input type="email"
							name="email" value="${requestScope.admin.email }"
							class="form-control" id="exampleInputEmail1"
							placeholder="请输入邮箱地址">
						<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
							xxxx@xxxx.com</p>
					</div>
					<button type="submit" class="btn btn-success">
						<i
							class="glyphicon glyphicon-edit
						
						
						
						
						0
						"></i>
						修改
					</button>
					<button type="button" class="btn btn-danger">
						<i class="glyphicon glyphicon-refresh"></i> 重置
					</button>
				</form>

			</div>
		</div>
	</div>

</body>
</html>