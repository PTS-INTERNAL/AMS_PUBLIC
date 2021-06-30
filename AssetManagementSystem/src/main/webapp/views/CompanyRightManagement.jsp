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
	margin-top: 3px;
}

.selectAll {
	width: 100%;
	height: 100%;
	font-weight: 700;
}

.disSelectAll {
	width: 100%;
	height: 100%;
	font-weight: 700;
}
</style>
<body onload="CheckRoleUser()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin: 10px">
		<form action="CompanyRightManagement" method="POST">
			<div class="title-feature">
				<div class="text-right">
<!-- 					<button type="submit" name="save" class="btn btn-primary"> -->
<!-- 						<i class="fa fa-save" style="font-size: 24px"></i>LƯU DỮ LIỆU -->
<!-- 					</button> -->
					<button type="submit" name="back" class="btn btn-primary">
						<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
						LẠI
					</button>
				</div>
			</div>
		</form>
		<jsp:include page="viewcommon/message.jsp"></jsp:include>

	</div>
	<div></div>
	<div style="width: 1224px; margin: auto;">

		<div style="margin-left: 10px; margin-right: 10px">
		<form id="formSearch" action="CompanyRightManagement" method="POST">
			<table class="table   table-search">
				<thead>
					<tr>
						<td class="title">CÔNG TY</td>
						<td>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text"
									style="height: 30px;" class="form-control inputAssetItem"
									readonly="readonly" name="cmpn_na" onchange="return SubmitForm()"
									value="${cmpn.getCompany_name()}" id="cmpn_na"> <input
									type="text" class="form-control inputAssetItem"
									style="display: none;" name="cmpn_cd"
									value="${cmpn.getCompany_cd()}" id="cmpn_cd">
							</div>
						</td>
					</tr>
					<tr>

						<td class="title">TÊN VIẾT TẮT</td>
						<td><input name="employee_name" readonly="readonly"
							type="text" value="${cmpn.getCompany_shortname()}"></td>
						<td class="title">ĐỊA CHỈ</td>
						<td><input name="employee_code" readonly="readonly"
							type="text" value="${cmpn.getCompany_address()}"></td>

					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			</form>
			<div class="col-sm-12">
				<c:if test="${lstuserpermis.size() > 0}">
					<div class="manage-area">
						<label class="title_input"
							style="margin-top: 10px; font-size: 22px; font-weight: 700; width: 100%; text-align: center;">BẢNG
							PHÂN QUYỀN</label>

						<table id="table.data" class="table   table-data"
							style="width: 940px; margin: auto;">
							<thead>
								<tr>
									<th style="width: 50px">STT</th>
									<th style="width: 200px;">TÊN NGƯỜI DÙNG</th>
									<th style="width: 150px;">MÃ NHÂN VIÊN</th>
									<th style="width: 80px;">CHO PHÉP</th>
									<th style="width: 60px;">CẬP NHẬT</th>

								</tr>
							</thead>
							<tbody>
								<%
									int i = 1;
								%>
								<c:forEach var="user" items="${lstuserpermis}">
									<tr>
										<form action="CompanyRightManagement" method="POST">
											<input type="text" class="form-control inputAssetItem"
												style="display: none;" name="cmpn_cd"
												value="${cmpn.getCompany_cd()}" id="cmpn_cd">
											<td><%=i%></td>
											<td style="text-align: left; font-weight: 700">${user.getUser().getName()}</td>
											<td style="text-align: right;"><input name="employee_cd"
												style="border: none; height: auto;" readonly="readonly"
												type="text" value="${user.getUser().getEmployee_cd()}" /></td>
											<td style="text-align: center;"><c:if
													test="${user.getValueCheck()=='0'}">
													<input type="checkbox" class="form-check-input"
														checked="checked" value="1" name="valueCheck">
												</c:if> <c:if test="${user.getValueCheck()=='1'}">
													<input type="checkbox" class="form-check-input" value="1"
														name="valueCheck">
												</c:if></td>
											<td>
												<div style="width: 120px; margin: auto;">
													<input style="display: none;" name="cmpn_cd"
														value="${company.getCompany_cd()}">
													<button style="width: 120px;" type="submit" class="view"
														name="btnSave">CẬP NHẬT</button>
												</div>
											</td>
										</form>
									</tr>
									<%
										i++;
									%>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function SubmitForm() {
	 document.getElementById("formSearch").submit();
}
</script>
</html>