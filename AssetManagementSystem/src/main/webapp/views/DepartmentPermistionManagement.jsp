<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ ĐƠN VỊ</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<style type="text/css">
input[type="checkbox"] {
	margin: 0px;
	zoom: 150%;
	margin-left: -7px;
	margin-top: 3px;
}
</style>
</head>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="DepartmentPermistionManagement" method="POST">
					<table class="table   table-search"
						style="width: 1024px; margin: auto; margin-bottom: 10px;">
						<thead>
							<tr>
								<td class="title" style="width: 140px">DOANH NGHIỆP</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input
											value="${cmpn_na}" type="text" style="height: 30px;"
											class="form-control inputAssetItem" value=""
											readonly="readonly" name="cmpn_na" id="cmpn_na"> <input
											type="text" class="form-control inputAssetItem"
											style="display: none;" value="${cmpn_cd}" name="cmpn_cd"
											value="" id="cmpn_cd">
										<button type="button" class="select"
											style="height: 30px; margin-right: 2px;"
											onclick="return openDialogue('PopupCompanyInput?param1=cmpn_cd&param2=cmpn_na')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;"
											onclick="return clearText('cmpn_na','cmpn_cd' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>
										</button>
									</div></td>
								<td class="title" style="width: 160px">TÊN NGƯỜI DÙNG</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input
											value="${user_na}" type="text" style="height: 30px;"
											class="form-control inputAssetItem" value=""
											readonly="readonly" name="user_na" id="user_na"> <input
											type="text" class="form-control inputAssetItem"
											style="display: none;" value="${user_cd}" name="user_cd"
											value="" id="user_cd">
										<button type="button" class="select"
											style="height: 30px; margin-right: 2px;"
											onclick="return openDialogue('PopupUserInput?param1=user_cd&param2=user_na')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;"
											onclick="return clearText('user_cd','user_na' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>
										</button>
									</div></td>

							</tr>

						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="search">
								<i style="font-size: 24px" class="fa">&#xf067;</i>TÌM KIẾM
							</button>

							<button type="submit" style="border-radius: 0" name="delete"
								class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XÓA
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
					<table id="table.data" style="margin-top: 10px"
						class="table table-data">
						<thead>
							<tr>
								<th style="width: 50px;">STT</th>
								<th>MÃ ĐƠN VỊ</th>
								<th>TÊN ĐƠN VỊ</th>
								<th>CHO PHÉP</th>
								<th style="width: 120px;">PHÂN QUYỀN</th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${lst}">
								<tr>
									<form action="DepartmentPermistionManagement" method="POST">
									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getDept_cd()}</td>
									<td>${p.getDept_name()}</td>
									<td style="text-align: center; width: 108px;">
									<c:if test="${p.getValue()=='1'}">
									<input type="checkbox" class="form-check-input" checked value="1" name="isAllow"></td>
									</c:if>
									<c:if test="${p.getValue()=='0'}">
									<input type="checkbox" class="form-check-input" value="1" name="isAllow"></td>
									</c:if>
									<td><div style="width: 120px; margin: auto;">
									
											<input style="display: none;" name="cmpn_cd" value="${cmpn_cd}"> 
											<input style="display: none;" name="cmpn_na" value="${cmpn_na}">
											
											<input style="display: none;" name="user_cd" value="${user_cd}"> 
											<input style="display: none;" name="user_na" value="${user_na}"> 
											
											<input style="display: none;" name="dept_cd" value="${p.getDept_cd()}">
											
											<input style="display: none;" name="per_cd"  value="${p.getPerCd()}"> 
											
											<button style="width: 120px;" type="submit" class="view" name="btnSave">CẬP NHẬT</button>
										</div></td>
									</form>
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