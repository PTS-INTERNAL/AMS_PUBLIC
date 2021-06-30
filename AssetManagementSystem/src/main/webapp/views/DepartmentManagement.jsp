<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ ĐƠN VỊ</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding:0px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="DepartmentManagement" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								<td class="title" width="150px">TÊN ĐƠN VỊ</td>
								<td><input value="${formSearch.getRFID()}"
									placeholder="Tên đơn vị" name="department_name"></td>
							</tr>
							<tr>
								<td class="title" width="150px">MÔ TẢ</td>
								<td style="height: 60px;"><textarea
										name="department_describe"
										style="width: 100%; height: 60px; resize: none; vertical-align: inherit;"
										placeholder="Diễn tả đơn vị này?"></textarea></td>
							</tr>

						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="save">
								<i style="font-size: 24px" class="fa">&#xf067;</i>THÊM MỚI
							</button>

							<button type="submit" style="border-radius: 0" name="delete"
								class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XÓA
							</button>
							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size:24px"></i></i>QUAY LẠI
							</button>
						</div>
					</div>
				</form>
				<p
					style="width: 100%; text-align: center; color: red; font-weight: 700; margin-top: 10px">${message}</p>
				<c:if test="${lst != null}">
					<table id="table.data" style="margin-top: 0px"
						class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								<th style="width: 2%"></th>
								<th style="width: 5%">STT</th>
								<th style="width: 8%;">MÃ ĐƠN VỊ</th>
								<th style="width: 15%;">TÊN ĐƠN VỊ</th>
								<th style="width: 14%;">DIỄN TẢ</th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${lst}">
								<tr>
									<td><input name="checkboxrow"
										onclick="return GetSelected()" onchange="GetSelected()"
										type="checkbox" class="form-check-input"
										style="margin: 0px; padding: 0px; margin-top: 7px" value=""></td>
									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getDept_cd()}</td>
									<td>${p.getDept_name()}</td>
									<td>${p.getDept_desciption()}</td>
								</tr>
								<%
									stt++;
								%>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>