<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PHÂN QUYỀN</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet" href="./resources/css/tabledata.css">
</head>
<style>
input[type="checkbox"] {
	margin: 0px;
	zoom: 150%;
	margin-left: -7px;
	margin-top:3px;
}
.selectAll
{
	width:100%;
	height: 100%;
	font-weight: 700;
}
.disSelectAll
{
	width:100%;
	height: 100%;
	font-weight: 700;
}
</style>
<body onload="CheckRoleUser()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<s:form action="UserRightManagement" id="UserRightManagement" method="POST"
		enctype="multipart/form-data">
		<div style="margin: 10px">
			<div class="title-feature">
				<div class="text-right">
					<button type="submit" name="save" id="save_userRight" class="btn btn-primary">
						<i class="fa fa-save" style="font-size: 24px"></i>LƯU DỮ LIỆU
					</button>
					<button type="submit" name="reload" id="reload"  class="btn btn-primary">
						<i class="fa fa-save" style="font-size: 24px"></i>RELOAD
					</button>
					<button type="submit" name="back" class="btn btn-primary">
						<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
						LẠI
					</button>
				</div>
			</div>
			<jsp:include page="viewcommon/message.jsp"></jsp:include>

		</div>
		<div></div>
		<div style="width:1224px; margin: auto;">

			<div style="margin-left: 10px; margin-right: 10px">
				<table class="table   table-search">
					<thead>
						<tr>
							<td class="title">CÔNG TY</td>
							<td>
								<div class="input-group">
									<span class="input-group-addon"> </span> <input type="text"
										style="height: 30px;" class="form-control inputAssetItem"
										 readonly="readonly" onchange="SubmitFormSearch()" name="cmpn_na" value="${cmpn_na}"
										id="cmpn_na"> <input type="text"
										class="form-control inputAssetItem" style="display: none;"
										name="cmpn_cd" value="${cmpn_cd}" id="cmpn_cd">
									<button type="button" class="select"
										style="height: 30px; margin-right: 2px;"
										onclick="return openDialogue('./PopupCompanyInput?param1=cmpn_cd&param2=cmpn_na')">
										<i class="fa fa-th-list"></i>
									</button>
									<button type="button" class="select" style="height: 30px;"
										onclick="return clearText('cmpn_na','cmpn_cd' )">
										<i class="fa fa-eraser" aria-hidden="true"></i>
									</button>
								</div>
							</td>
						</tr>
						<tr>

							<td class="title">HỌ VÀ TÊN</td>
							<td><input name="employee_name" readonly="readonly"
								type="text" value="${user.getName()}"></td>
							<td class="title">MÃ NHÂN VIÊN</td>
							<td><input name="employee_code" readonly="readonly"
								type="text" value="${user.getEmployee_cd()}"></td>

						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="col-sm-12">
<%-- 					<c:if test="${listRole.size() > 0}"> --%>

					<c:if test="${lstRoleMapping.size() > 0}">
					<%
						int countC = 1;
					%>
						<div class="manage-area">
							<label class="title_input"
								style="margin-top: 10px; font-size: 22px; font-weight: 700; width: 100%; text-align: center;">BẢNG
								PHÂN QUYỀN</label>

							<table id="table.data" class="table   table-data">
								<thead>
									<tr>
										<th style="width:20px">STT</th>
										<th>NGHIỆP VỤ</th>
										<th style="width: 100px;">TRUY CẬP</th>
										<th style="width: 100px;">THÊM</th>
										<th style="width: 100px;">SỬA</th>
										<th style="width: 100px;">XÓA</th>
										<th style="width: 100px;">IN</th>
										<th style="width: 100px;">XUẤT</th>
										<th style="width: 100px; display: none;">ĐỌC</th>
										<th style="width: 90px; background-color: green; color: white">CHỌN HẾT</th>
										<th style="width: 90px; background-color: red; color: white;">HỦY BỎ</th>

									</tr>
								</thead>
								<tbody>

<%-- 									<c:forEach var="role" items="${listRole}"> --%>
<!-- 										<tr> -->

<%-- 											<td style="font-weight: 700">${role.getService_name() }</td> --%>
<!-- 											<td style="text-align: center;"><input type="checkbox" -->
<!-- 												class="form-check-input" value="1" -->
<%-- 												name="R_ACCESS[${role.getService_id().trim()}]"></td> --%>
<!-- 											<td style="text-align: center;"><input type="checkbox" -->
<!-- 												class="form-check-input" value="1" -->
<%-- 												name="R_WRITE[${role.getService_id().trim()}]"></td> --%>
<!-- 											<td style="text-align: center;"><input type="checkbox" -->
<!-- 												class="form-check-input" value="1" -->
<%-- 												name="R_UPDATE[${role.getService_id().trim()}]"></td> --%>
<!-- 											<td style="text-align: center;"><input type="checkbox" -->
<!-- 												class="form-check-input" value="1" -->
<%-- 												name="R_DELETE[${role.getService_id().trim()}]"></td> --%>
<!-- 											<td style="text-align: center;"><input type="checkbox" -->
<!-- 												class="form-check-input" value="1" -->
<%-- 												name="R_PRINT[${role.getService_id().trim()}]"></td> --%>
<!-- 											<td style="text-align: center;"><input type="checkbox" -->
<!-- 												class="form-check-input" value="1" -->
<%-- 												name="R_EXPORT[${role.getService_id().trim()}]"></td> --%>
<!-- 											<td style="text-align: center;"><input type="checkbox" -->
<!-- 												class="form-check-input" value="1" -->
<%-- 												name="R_READ[${role.getService_id().trim()}]"></td> --%>
<!-- 												<td><button type="button" class="selectAll">CHỌN</button></td> -->
<!-- 												<td><button type="button" class="disSelectAll">BỎ</button></td> -->
<!-- 										</tr> -->
<%-- 									</c:forEach> --%>
										<c:forEach var="role" items="${lstRoleMapping}">
										<tr>
											<td><%=countC%></td>
											<td style="font-weight: 700">${role.getService_name() }</td>
											<td style="text-align: center;">
											<c:if test="${role.getR_access()!=null}">
												<input type="checkbox" class="form-check-input" checked="checked" value="1" id="R_ACCESS[${role.getService_id().trim()}]" name="R_ACCESS[${role.getService_id().trim()}]">
											</c:if>
											<c:if test="${role.getR_access()== null}">
												<input type="checkbox" class="form-check-input"  value="1" name="R_ACCESS[${role.getService_id().trim()}]" id="R_ACCESS[${role.getService_id().trim()}]">
											</c:if>
											</td>
											<td style="text-align: center;">
											<c:if test="${role.getR_write()!=null}">
												<input type="checkbox" class="form-check-input" checked="checked" value="1" name="R_WRITE[${role.getService_id().trim()}]" id="R_WRITE[${role.getService_id().trim()}]">
											</c:if>
											<c:if test="${role.getR_write()==null}">
												<input type="checkbox" class="form-check-input" value="1" name="R_WRITE[${role.getService_id().trim()}]" id="R_WRITE[${role.getService_id().trim()}]">
											</c:if>
											</td>
											<td style="text-align: center;">
											<c:if test="${role.getR_update()!=null}">
												<input type="checkbox" class="form-check-input" checked="checked" value="1" id="R_UPDATE[${role.getService_id().trim()}]" name="R_UPDATE[${role.getService_id().trim()}]">
											</c:if>
											<c:if test="${role.getR_update()==null}">
												<input type="checkbox" class="form-check-input" value="1" id="R_UPDATE[${role.getService_id().trim()}]" name="R_UPDATE[${role.getService_id().trim()}]">
											</c:if>
											</td>
											<td style="text-align: center;">
											<c:if test="${role.getR_delete()!=null}">
												<input type="checkbox" class="form-check-input" checked="checked" value="1" id="R_DELETE[${role.getService_id().trim()}]" name="R_DELETE[${role.getService_id().trim()}]">
											</c:if>
											<c:if test="${role.getR_delete()==null}">
												<input type="checkbox" class="form-check-input" value="1" id="R_DELETE[${role.getService_id().trim()}]" name="R_DELETE[${role.getService_id().trim()}]">
											</c:if>
											</td>
											<td style="text-align: center;">
											<c:if test="${role.getR_print()!=null}">
												<input type="checkbox" class="form-check-input" checked="checked" value="1" id="R_PRINT[${role.getService_id().trim()}]" name="R_PRINT[${role.getService_id().trim()}]">
											</c:if>
											<c:if test="${role.getR_print()==null}">
												<input type="checkbox" class="form-check-input" value="1" id="R_PRINT[${role.getService_id().trim()}]" name="R_PRINT[${role.getService_id().trim()}]">
											</c:if>
											</td>
											<td style="text-align: center;">
											<c:if test="${role.getR_export()!=null}">
												<input type="checkbox" class="form-check-input" checked="checked" id="R_EXPORT[${role.getService_id().trim()}]" value="1" name="R_EXPORT[${role.getService_id().trim()}]">
											</c:if>
											<c:if test="${role.getR_export()==null}">
												<input type="checkbox" class="form-check-input" value="1" id="R_EXPORT[${role.getService_id().trim()}]" name="R_EXPORT[${role.getService_id().trim()}]">
											</c:if>
											</td>
											<td style="text-align: center; display: none;">
											
											<input type="checkbox"  class="form-check-input" value="1" id="R_READ[${role.getService_id().trim()}]" name="R_READ[${role.getService_id().trim()}]"></td>
												<td><button type="button" onclick="selectAllItem('${role.getService_id().trim()}')" class="selectAll">CHỌN</button></td>
												<td><button type="button" onclick="disSelectAllItem('${role.getService_id().trim()}')" class="disSelectAll">BỎ</button></td>
										</tr>
										<%countC ++; %>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</s:form>
</body>
<script type="text/javascript">
function SubmitFormSearch() {
	document.getElementById("UserRightManagement").submit();
}
function selectAllItem(code) {
	document.getElementById("R_ACCESS["+code+"]").checked = true;
	document.getElementById("R_WRITE["+code+"]").checked = true;
	document.getElementById("R_UPDATE["+code+"]").checked = true;
	document.getElementById("R_DELETE["+code+"]").checked = true;
	document.getElementById("R_PRINT["+code+"]").checked = true;
	document.getElementById("R_EXPORT["+code+"]").checked = true;
	document.getElementById("R_READ["+code+"]").checked = true;
}
function disSelectAllItem(code) {
	document.getElementById("R_ACCESS["+code+"]").checked = false;
	document.getElementById("R_WRITE["+code+"]").checked = false;
	document.getElementById("R_UPDATE["+code+"]").checked = false;
	document.getElementById("R_DELETE["+code+"]").checked = false;
	document.getElementById("R_PRINT["+code+"]").checked = false;
	document.getElementById("R_EXPORT["+code+"]").checked = false;
	document.getElementById("R_READ["+code+"]").checked = false;
}
	
</script>
</html>