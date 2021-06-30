<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ NHÓM TÀI SẢN</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="GroupAssetManagement" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								<td class="title" width="150px">NHÓM TÀI SẢN</td>
								<td><input value="${formSearch.getRFID()}"
									placeholder="Tên nhóm tài sản" name="department_name"></td>
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
								<i style="font-size: 24px" class="fa">&#xf067;</i>ĐĂNG KÝ
							</button>

							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i></i>QUAY
								LẠI
							</button>
						</div>
					</div>
				</form>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				<c:if test="${lst != null}">
					<table id="table.data" style="margin-top: 0px"
						class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								
								<th style="width:60px">STT</th>
								<th >MÃ NHÓM</th>
								<th >TÊN NHÓM</th>
								<th >DIỄN TẢ</th>
								<th style="width: 120px;"></th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${lst}">
								<tr>
									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getGroup_id()}</td>
									<td>${p.getGroup_name()}</td>
									<td>${p.getGroup_desciption()}</td>
									<td style="background-color: white; width:120px;">
										<button type="button" class="view"
											style="height: 30px; margin-right: 2px; width:115px; text-transform: uppercase;"
											onclick="return openDialogue('UpdateGroupAsset?group_id=${p.getGroup_id()}')">Cập nhật</button>
									</td>
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