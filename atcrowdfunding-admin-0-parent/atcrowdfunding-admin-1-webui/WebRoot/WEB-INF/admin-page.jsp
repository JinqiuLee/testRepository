<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<%@ include file="/WEB-INF/include-head.jsp"%>
<link rel="stylesheet" href="css/pagination.css" />
<script type="text/javascript" src="script/jquery.pagination.js"></script>
<script type="text/javascript">
	$(function() {
		//对分页导航条显示进行初始化
		initPagination();

		// 全选/全不选功能
		$("#summaryBox").click(function() {
			var checked = $(this).prop("checked");
			$(".itemBox").prop("checked", checked);
			//当没有选中全选的时候，删除按钮不可用
			if (checked == true) {
				$("#batchRemoveBtn").removeAttr("disabled", "");
			} else {
				$("#batchRemoveBtn").attr("disabled", "disabled");
			}
		});

		//用flag设置删除按钮的disabled属性，true为不可用，false为可用。默认为true不可用
		//当点击单个数据项的勾选框的时候，若勾选，则标记false；
		//若为取消勾选，则遍历所有的勾选框元素，只要有一个为勾选，则标记为false，并且return，如果遍历所有的勾选框元素都不存在勾选状态的，那么flag值不做改变
		$(".itemBox").click(function(i) {
			var flag = true;
			if ($(this).prop("checked")) {
				flag = false;
			} else {
				$(".itemBox").each(function() {
					if ($(this).prop("checked")) {
						flag = false;
						//优化逻辑，只要遇到一个选中状态的单项，each循环至此结束，不再做无意义的遍历
						return false;
					}
				});
			}
			$("#batchRemoveBtn").attr("disabled", flag);
		});

		//批量删除
		$("#batchRemoveBtn").click(function() {
			var adminArray = new Array();
			var loginAcctArray = new Array();
			$(".itemBox:checked").each(function() {
				var adminId = $(this).attr("adminId");
				adminArray.push(adminId);
				var loginAcct = $(this).parent().next().text();
				loginAcctArray.push(loginAcct);
			});
			remove(adminArray, loginAcctArray);
		});

		//单条记录删除
		$(".uniqueRemoveBtn").click(function() {
			var adminId = $(this).attr("adminId");
			var adminArray = new Array();
			var loginAcctArray = new Array();
			var loginAcct = $(this).parent().parent().children().eq(2).text();
			adminArray.push(adminId);
			loginAcctArray.push(loginAcct);
			remove(adminArray, loginAcctArray);
		});

	});

	function remove(adminArray, loginAcctArray) {
		var data = JSON.stringify(adminArray);
		var confirmResult = confirm("您真的要删除" + loginAcctArray + "等"
				+ loginAcctArray.length + "条记录吗？");

		if (!confirmResult) {
			return;
		}

		$
				.ajax({
					type : "POST",
					url : "admin/batch/remove.json",
					contentType : "application/json;charset=UTF-8",
					data : data,
					success : function(msg) {
						alert("数据已删除");
						window.location.href = "admin/query/for/search.html?pageNum=${requestScope['PAGE-INFO'].pageNum}&keyword=${param.keyword}";
					}
				});
	}

	// 声明函数封装导航条初始化操作
	function initPagination() {

		// 声明变量存储总记录数
		var totalRecord = ${requestScope['PAGE-INFO'].total};

		// 声明变量存储分页导航条显示时的属性设置
		var paginationProperties = {
			num_edge_entries : 3, //边缘页数
			num_display_entries : 5, //主体页数
			callback : pageselectCallback, //回调函数
			items_per_page : ${requestScope['PAGE-INFO'].pageSize}, //每页显示数据数量，就是pageSize
			current_page : ${requestScope['PAGE-INFO'].pageNum - 1},//当前页页码
			prev_text : "上一页", //上一页文本
			next_text : "下一页" //下一页文本
		};

		// 显示分页导航条
		$("#Pagination").pagination(totalRecord, paginationProperties);
	}

	// 在每一次点击“上一页”、“下一页”、“页码”时执行这个函数跳转页面
	function pageselectCallback(pageIndex, jq) {

		// pageIndex从0开始，pageNum从1开始
		var pageNum = pageIndex + 1;

		// 跳转页面
		window.location.href = "admin/query/for/search.html?pageNum=" + pageNum
				+ "&keyword=${param.keyword}";

		return false;
	}
</script>
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
						<form action="admin/query/for/search.html" class="form-inline"
							role="form" style="float:left;" method="post">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input name="keyword" class="form-control has-success"
										type="text" placeholder="请输入查询条件">
								</div>
							</div>
							<button type="submit" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchRemoveBtn" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;" disabled="disabled">
							<i class=" glyphicon glyphicon-remove"></i> 删除
						</button>

						<a class="btn btn-primary" style="float:right;"
							href="admin/to/add/page.html"> <i
							class="glyphicon glyphicon-plus"></i> 新增
						</a> <br>
						<hr style="clear:both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input id="summaryBox" type="checkbox"></th>
										<th>账号</th>
										<th>名称</th>
										<th>邮箱地址</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty requestScope['PAGE-INFO'].list }">
										<tr>
											<td style="text-align: center;" colspan="6">抱歉！没有符合您要求的查询结果！</td>
										</tr>
									</c:if>
									<c:if test="${!empty requestScope['PAGE-INFO'].list }">
										<c:forEach items="${requestScope['PAGE-INFO'].list }"
											var="admin" varStatus="myStatus">
											<tr>
												<td>${myStatus.count }</td>
												<td><input adminId="${admin.id }" class="itemBox"
													type="checkbox"></td>
												<td>${admin.loginAcct }</td>
												<td>${admin.userName }</td>
												<td>${admin.email }</td>
												<td>
													<button type="button" class="btn btn-success btn-xs">
														<i class=" glyphicon glyphicon-check"></i>
													</button> <a
													href="admin/to/edit/page.html?adminId=${admin.id }&pageNum=${requestScope['PAGE-INFO'].pageNum}"
													class="btn btn-primary btn-xs"><i
														class=" glyphicon glyphicon-pencil"></i></a>

													<button adminId="${admin.id }" type="button"
														class="btn btn-danger btn-xs uniqueRemoveBtn">
														<i class=" glyphicon glyphicon-remove"></i>
													</button>

												</td>
											</tr>
										</c:forEach>
									</c:if>
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

</body>
</html>