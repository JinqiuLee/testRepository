<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="UTF-8">
<div id="confirmModal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">尚筹网系统弹窗</h4>
			</div>
			<div class="modal-body">
				<p>您确定要删除下面的显示的内容吗？</p>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th width="50">编号</th>
							<th align="center">名称</th>
						</tr>
					</thead>
					<tbody id="confirmModalTableBody"></tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" id="removeConfirm" class="btn btn-primary">确认</button>
			</div>
		</div>
	</div>
</div>

</html>