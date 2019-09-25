<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<%@ include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="script/jquery.pagination.js"></script>
<script type="text/javascript" src="script/my-role.js"></script>
<body>

	<%@ include file="/WEB-INF/include-nav.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/include-sidebar.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float:left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="keywordInput" class="form-control has-success"
										type="text" placeholder="请输入查询条件"
										onkeydown="if (event.keyCode == 13) return false;">
								</div>
							</div>
							<button id="roleSearch" type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchRemoveBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;" disabled="disabled">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>

						<a id="addRole" class="btn btn-primary" style="float:right;">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</a> <br>
						<hr style="clear:both;">

						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="summaryBox" type="checkbox"></th>
										<th>编号</th>
										<th>名称</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody id="tbody">

								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<div id="Pagination" class="pagination">
												<!-- 这里显示分页 -->
											</div>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/include-modal-role-add.jsp"%>
	<%@ include file="/WEB-INF/include-modal-role-edit.jsp"%>
	<%@ include file="/WEB-INF/include-modal-role-confirm.jsp"%>
</body>
</html>