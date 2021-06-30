<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>DUYỆT CHO MƯỢN</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
<script src="./resources/javascript/message/bootbox.all.js"></script>
<script src="./resources/javascript/message/bootbox.all.min.js"></script>
<script src="./resources/javascript/message/bootbox.js"></script>
<script src="./resources/javascript/message/bootbox.locales.js"></script>
<script src="./resources/javascript/message/bootbox.locales.min.js"></script>
<script src="./resources/javascript/message/bootbox.min.js"></script>
<style type="text/css">
.title-table {
	width: 130px;
}

.content_table {
	font-size: 20px;
}

* {
	font-family: "Segoe UI", Arial, sans-serif !important;
}

table.table-data {
	margin-bottom: 0px !important;
	margin-top: -2px !important;
}

table.table-data th {
	background-color: #bdc6e2;
}

table.table-data th, table.table-data td {
	vertical-align: middle;
}

button.btnduyet {
	height: 33px;
	margin-top: -6px;
	font-size: 16px;
	font-weight: 700;
}
</style>
</head>
<body style="overflow: hidden;">

	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div
				class="col-sm-12 general-setting w3-hide-small w3-light-grey w3-card-2">
				<form action="LoanAssetReturn" method="POST">
				<input type="text" class="form-control" style="height: 40px; display: none;"
										name=borrow_cd_exp readonly="readonly"
										value="${borrow_cd_exp}" id="cmpn_na_client">
					<div class="title-feature">
						<div class="text-right">
						<button type="submit" name="print" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>XUẤT LỆNH ĐIỀU ĐỘNG
							</button>
							<button type="submit" name="back" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
								LẠI
							</button>
						</div>
					</div>
				</form>
					<jsp:include page="viewcommon/message.jsp"></jsp:include>
					<div style="margin-top: 10px;"></div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">CÔNG TY CHO MƯỢN:</label>
								<div class="input-group">
									<input type="text" class="form-control" style="height: 40px;"
										name="cmpn_na_master" readonly="readonly"
										value="${master.getCompany_name()}" id="cmpn_na_master">
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">CÔNG TY MƯỢN: </label>
								<div class="input-group">
									<input type="text" class="form-control" style="height: 40px;"
										name="cmpn_na_client" readonly="readonly"
										value="${client.getCompany_name()}" id="cmpn_na_client">

								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MÃ LỆNH:</label> <input type="text"
									value="${number_no}" class="form-control" readonly="readonly"
									name="number_on">
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">LÝ DO CHO MƯỢN: </label>
								<div class="input-group">
									<input type="text" class="form-control" style="height: 40px;"
										name="cmpn_na_client" readonly="readonly" value="${reason}"
										id="cmpn_na_client">

								</div>
							</div>
						</div>
					</div>
					<table id="table.data" style="margin-top: 0px"
						class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								<th>STT</th>
								<th>MÃ RFID</th>
								<th>TÊN TÀI SẢN</th>
								<th>MODEL</th>
								<th>SERIES</th>
								<th>ĐƠN VỊ</th>
								<th>TRẠNG THÁI</th>
								<th>DUYỆT</th>
								<th>KHÔNG DUYỆT</th>
								<th>LÝ DO KHÔNG DUYỆT</th>

							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${lstBr}">
								<tr>
									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getAsset().getRFID()}</td>
									<td>${p.getAsset().getName()}</td>
									<td>${p.getAsset().getModel()}</td>
									<td>${p.getAsset().getSeries()}</td>
									<td>${p.getDept_master().getDept_name()}</td>
									<td>
										<c:if
											   test="${p.getStatus()=='1'}">
											    <span class="view"
											      style="width: 160px; background-color: orange; color: black; border: 1px solid yellow;">CHƯA DUYỆT</span>
											   </form>
											</c:if>
											<c:if test="${p.getStatus()=='2'}">
											   <span class="view"
											      style="width: 160px; background-color: yellow; color: black; border: 1px solid yellow;">CHỜ
											   XÁC NHẬN</span>
											</c:if>
											<c:if test="${p.getStatus()=='3'}">
											   <span class="view"
											      style="border: 1px solid white; width: 160px; background-color: white; color: black">ĐANG
											   CHO MƯỢN</span>
											</c:if>
											<c:if test="${p.getStatus()=='4'}">
											  <span class="view"
											      style="border: 1px solid white; width: 160px; background-color: red; color: black">XÁC NHẬN TRẢ</span>
											</c:if>
											<c:if test="${p.getStatus()=='5'}">
											   <span class="view"
											      style="width: 160px; background-color: green">ĐÃ TRẢ</span>
											</c:if>
											<c:if test="${p.getStatus()=='6'}">
											   <span class="view"
											      style="width: 160px; background-color: grey">KHÔNG
											   DUYỆT</span>
											</c:if>
									</td>
									<td style="width: 120px; text-align: center">
									<form action="BorrowAssetApprove" method="POST">
									<input style="display: none;" name="borrow_cd" value="${p.getId()}">
									<button
											type="submit" class="view" style="width: 100px;"
											name="approve">DUYỆT</button>
									</form>		
											</td>
									<td style="width: 170px; text-align: center">
									<form action="BorrowAssetApprove" method="POST">
									<input style="display: none;" name="borrow_cd" value="${p.getId()}">
									<input type="text" class="form-control"
										name="reason" id="reasonhide<%=stt%>" value="${p.getReason_not_allow()}"
										style="display: none; height: 30px; margin: 2px;">
									<button
											type="submit" class="view" style="width: 150px;"
											name="disApprove">KHÔNG DUYỆT</button>
										</form>
									</td>
									<td><input type="text" class="form-control"
										name="abc" id="reason<%=stt%>" onchange="setReason(<%=stt%>)" value="${p.getReason_not_allow()}"
										style="display: block; height: 30px; margin: 2px;"></td>
								</tr>
								<%
									stt++;
								%>
							</c:forEach>
						</tbody>
					</table>
				
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function setReason(i)
{
	document.getElementById("reasonhide"+i).value = document.getElementById("reason"+i).value;
}
</script>
</html>