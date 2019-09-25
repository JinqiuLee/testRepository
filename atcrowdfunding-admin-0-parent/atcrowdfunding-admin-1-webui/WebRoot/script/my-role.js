/**
 * 
 */
function showPage() {
	// 查询主体获取，包含数据以及页码，每页显示的条数以及查询关键词
	var pageInfo = getPageInfo();
	// initGlobalVariable();
	generateTableBody(pageInfo);

	// 在页面上的表格中tfoot标签内显示分页的页码导航条
	initPagination(pageInfo);

}

function generateTableBody(pageInfo) {

	$("#tbody").empty();
	var array = new Array();
	array = pageInfo.list;
	if (array.length == 0) {
		var noData = "<tr><td style='text-align: center;' colspan='5'>未查询到任何数据</td></tr>";
		$("#tbody").append(noData);
		return;
	}
	for (var i = 0; i < array.length; i++) {
		var tableHTML = "<tr>";
		var tableIndex = "<td>" + (i + 1) + "</td>";
		var checkbox = "<td><input roleId=" + array[i].id
				+ " class='itemBox' type='checkbox'></td>";
		var roleId = "<td>" + array[i].id + "</td>";
		var roleName = "<td>" + array[i].name + "</td>";
		var btnHTML = "<td><button type='button' class='btn btn-success btn-xs'><i class=' glyphicon glyphicon-check'></i></button> ";
		var editBtn = "<a roleId="
				+ array[i].id
				+ " class='btn btn-primary btn-xs edit-btn'><i class=' glyphicon glyphicon-pencil'></i></a> ";
		var removeBtn = "<button roleId="
				+ array[i].id
				+ " type='button' class='btn btn-danger btn-xs uniqueRemoveBtn'> <i class=' glyphicon glyphicon-remove'></i></button> </td></tr>";
		$("#tbody").append(
				tableHTML + tableIndex + checkbox + roleId + roleName + btnHTML
						+ editBtn + removeBtn);
	}
}

// 初始化全局变量,每页显示数量，当前页码，搜索关键词
function initGlobalVariable() {
	window.pageSize = 5;
	window.pageNum = 1;
	window.keyword = "";
}

function getPageInfo() {

	var ajaxResponse = $.ajax({
		url : "role/search/by/keyword.json",
		data : {
			"pageNum" : (window.pageNum == undefined) ? 1 : window.pageNum,
			"pageSize" : (window.pageSize == undefined) ? 5 : window.pageSize,
			"keyword" : (window.keyword == undefined) ? "" : window.keyword
		},
		dataType : "json",
		contentType : "application/json,charset=ctf-8",
		async : false
	});

	var resultEntity = ajaxResponse.responseJSON;
	var result = resultEntity.result;
	if ("SUCCESS" == result) {
		return resultEntity.data;
	} else if ("FAILED" == result) {
		layer.msg(resultEntity.message);
	}
	return null;
}

// 声明函数封装导航条初始化操作
function initPagination(pageInfo) {

	// 声明变量存储分页导航条显示时的属性设置
	var paginationProperties = {
		num_edge_entries : 3, // 边缘页数
		num_display_entries : 5, // 主体页数
		callback : pageselectCallback, // 回调函数
		items_per_page : window.pageSize, // 每页显示数据数量，就是pageSize
		current_page : (window.pageNum - 1),// 当前页页码
		prev_text : "上一页", // 上一页文本
		next_text : "下一页" // 下一页文本
	};

	// 显示分页导航条
	$("#Pagination").pagination(pageInfo.total, paginationProperties);
}

// 在每一次点击“上一页”、“下一页”、“页码”时执行这个函数跳转页面
function pageselectCallback(pageIndex, jq) {

	// 将全局变量中的pageNum修改为最新值
	// pageIndex从0开始，pageNum从1开始
	window.pageNum = pageIndex + 1;
	// 调用分页函数重新执行分页
	showPage();
	$("#summaryBox").attr("checked", false);
	$("#batchRemoveBtn").attr("disabled", "disabled");
	return false;
}

function getRoleListById() {
	var ajaxResponse = $.ajax({
		type : "post",
		url : "role/get/list/by/id/list.json",
		data : window.roleIdList,
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		async : false
	});

	var resultEntity = ajaxResponse.responseJSON;
	var result = resultEntity.result;
	if ("SUCCESS" == result) {
		return resultEntity.data;
	} else if ("FAILED" == result) {
		layer.msg(resultEntity.message);
	}
	return null;
}

// 打开删除确认模态框
function showRemoveConfirmModal(data) {

	// 1.将模态框显示出来
	$("#confirmModal").modal("show");

	// 2.根据roleIdList获取roleList
	// var roleList = getRoleListById(roleIdList);

	// 3.清空#confirmModalTableBody
	$("#confirmModalTableBody").empty();

	// 4.填充#confirmModalTableBody
	for (var i = 0; i < data.length; i++) {

		// 5.获取角色相关数据
		var role = data[i];

		var id = role.id;

		var name = role.name;

		var trHTML = "<tr><td>" + id + "</td><td align='center'>" + name
				+ "</td></tr>";

		// 6.执行填充
		$("#confirmModalTableBody").append(trHTML);
	}

}

$(function() {

	// 调用分页参数初始化方法
	initGlobalVariable();
	// 初始化页面数据
	showPage();

	// 增
	$(document).on("click", "#addRole", function() {
		$("#addModal").modal("show");
		$("#roleNameAdd").val();
	});

	$(document).on(
			"click",
			"#save",
			function() {

				var roleName = $.trim($("#roleNameAdd").val());
				if (roleName == null || roleName == "") {
					layer.msg("请输入有效角色名称！");
					return;
				}
				$.ajax({
					type : "POST",
					url : "role/save/role.json",
					data : {
						"roleName" : roleName
					},

					success : function(resultEntity) {
						if ("SUCCESS" == resultEntity.result
								&& resultEntity.message == "NO_MESSAGE") {

							layer.msg("操作成功");
							var pageInfo = getPageInfo();
							window.pageNum = pageInfo.pages;
							showPage();
						} else {
							layer.msg(resultEntity.message);
						}
						// 4.不管成功还是失败，关闭模态框
						$("#addModal").modal("hide");

						// 5.清理本次在文本框填写的数据
						$("#roleNameAdd").val("");
					},
					error : function(response) {
						layer.msg(response.message);
					}
				});

			});

	// 删
	$(document).on("click", "#batchRemoveBtn", function() {
		var roleIdArray = new Array();
		$(".itemBox:checked").each(function() {
			var roleId = $(this).attr("roleId");
			roleIdArray.push(roleId);
		});
		window.roleIdList = JSON.stringify(roleIdArray);

		var data = getRoleListById(window.roleIdList);
		if (data != null) {
			// 调用函数打开模态框
			showRemoveConfirmModal(data);
		}
	});

	$(document).on("click", ".uniqueRemoveBtn", function() {
		var roleId = $(this).attr("roleId");
		var roleIdArray = new Array();
		roleIdArray.push(roleId);
		window.roleIdList = JSON.stringify(roleIdArray);
		var data = getRoleListById(window.roleIdList);
		if (data != null) {
			// 调用函数打开模态框
			showRemoveConfirmModal(data);
		}

	});
	$(document)
			.on(
					"click",
					"#removeConfirm",
					function() {
						$
								.ajax({
									type : "POST",
									url : "role/batch/remove.json",
									dataType : "json",
									data : window.roleIdList,
									contentType : "application/json;charset=UTF-8",
									success : function(resultEntity) {
										if ("SUCCESS" == resultEntity.result
												&& resultEntity.message == "NO_MESSAGE") {
											layer.msg("操作成功");
										} else {
											layer.msg(resultEntity.message, {
												time : 6000
											});
										}
										// 4.不管成功还是失败，关闭模态框
										$("#confirmModal").modal("hide");

										var pageInfo = getPageInfo();

										window.pageNum = (pageInfo.pages > window.pageNum) ? window.pageNum
												: pageInfo.pages;

										// 重新加载当前页面
										showPage();
									},
									error : function(response) {
										layer.msg(response.message);
									}
								});
					});

	// 改
	$(document).on("click", ".edit-btn", function() {
		$(roleNameEdit).attr("roleId", $(this).attr("roleId"));
		var roleName = $(this).parent().prev().text();
		$("#roleNameEdit").val(roleName);
		$("#editModal").modal("show");
	});

	$(document).on(
			"click",
			"#update",
			function() {
				var roleId = $(roleNameEdit).attr("roleId");
				var roleName = $("#roleNameEdit").val();
				$.ajax({
					type : "POST",
					url : "role/update/role.json",
					contentType : "application/json;charset=UTF-8",
					data : JSON.stringify({
						"id" : roleId,
						"name" : roleName
					}),
					success : function(resultEntity) {
						if ("SUCCESS" == resultEntity.result
								&& resultEntity.message == "NO_MESSAGE") {
							layer.msg("操作成功");
						} else {
							layer.msg(resultEntity.message);
						}
						// 4.不管成功还是失败，关闭模态框
						$("#addModal").modal("hide");

						// 5.清理本次在文本框填写的数据
						$("#roleNameEdit").val("");
					},
					error : function(response) {
						layer.msg(response.message);
					}
				});
				// 执行完操作成功与否都关闭窗口
				$("#editModal").modal("hide");
			});

	// 查
	$(document).on("click", "#roleSearch", function() {
		// 在单击响应函数中获取文本框中输入的数据
		var keyword = $.trim($("#keywordInput").val());

		// 验证输入数据是否有效，因为如果放行该验证，那么无法再查询全部数据
		/*
		 * if (keyword == null || keyword == "") { // 如果无效，提示，停止函数执行
		 * layer.msg("请输入关键词！"); return; }
		 */
		// 如果有效，赋值给window.keyword
		window.keyword = keyword;
		showPage();

	});
	$("#keywordInput").keydown(function() {
		var keyword = $.trim($("#keywordInput").val());

		// 屏蔽该条验证，因为如果放行该验证，那么无法再查询全部数据
		// 验证输入数据是否有效
		/*
		 * if (keyword == null || keyword == "") { // 如果无效，提示，停止函数执行
		 * layer.msg("请输入关键词！"); return; }
		 */
		// 如果有效，赋值给window.keyword
		window.keyword = keyword;
		showPage();
	});

	// 勾选
	$(document).on("click", "#summaryBox", function() {
		var checked = $(this).prop("checked");

		$(".itemBox").prop("checked", checked);
		if (checked == true) {
			$("#batchRemoveBtn").removeAttr("disabled", "");
		} else {
			$("#batchRemoveBtn").attr("disabled", "disabled");
		}
	});
	// 用flag设置删除按钮的disabled属性，true为不可用，false为可用。默认为true不可用
	// 当点击单个数据项的勾选框的时候，若勾选，则标记false；
	// 若为取消勾选，则遍历所有的勾选框元素，只要有一个为勾选，则标记为false，并且return，如果遍历所有的勾选框元素都不存在勾选状态的，那么flag值不做改变
	$(document).on("click", ".itemBox", function(i) {
		var flag = true;
		if ($(this).prop("checked")) {
			flag = false;
		} else {
			$(".itemBox").each(function() {
				if ($(this).prop("checked")) {
					flag = false;
					// 优化逻辑，只要遇到一个选中状态的单项，each循环至此结束，不再做无意义的遍历
					return false;
				}
			});
		}
		$("#batchRemoveBtn").attr("disabled", flag);
	});

});