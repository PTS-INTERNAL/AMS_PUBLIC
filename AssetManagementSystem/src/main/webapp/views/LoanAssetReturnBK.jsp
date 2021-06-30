<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>DUYỆT CHO MƯỢN</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<jsp:include page="viewcommon/library.jsp"></jsp:include>
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
</style>
</head>
<body style="overflow: hidden;">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>

	<div class="container">
		<div class="w3-card-4"
			style="width: 600px; margin: auto; margin-top: 20px;">

			<div class="w3-container w3-center" style="padding: 10px;">
				<h4>${master.getCompany_name()}</h4>

				<h4>PHIẾU CHO XÁC NHẬN TRẢ TÀI SẢN TÀI SẢN</h4>
				<p style="margin: 0px;">----</p>
				<table style="width: 100%; text-align: left">
					<tr>
						<th class="title-table">Bên cho mượn:</th>
						<td class="content_table"
							style="text-transform: uppercase; font-weight: bold;">${master.getCompany_name()}</td>
					</tr>
					<tr>
						<th class="title-table">Địa chỉ:</th>
						<th style="font-weight: normal;">${master.getCompany_address()}</th>
					</tr>
					<tr>
						<th class="title-table">Đơn vị:</th>
						<th style="font-weight: normal;">${borrow.getDept_master().getDept_name()}</th>
					</tr>
					<tr>
						<th class="title-table">Bên mượn:</th>
						<td class="content_table"
							style="text-transform: uppercase; font-weight: bold;">${client.getCompany_name()}</td>
					</tr>
					<tr>
						<th class="title-table">Địa chỉ:</th>
						<th style="font-weight: normal;">${client.getCompany_address()}</th>
					</tr>
					<tr>
						<th class="title-table">Đơn vị:</th>
						<th style="font-weight: normal;">${borrow.getDept_client().getDept_name()}</th>
					</tr>
				</table>
				<table style="width: 100%; text-align: left">
					<tr>
						<th class="title-table">Người đăng ký:</th>
						<td class="content_table"
							style="text-transform: uppercase; font-weight: bold;">${userInsert.getName()}</td>
					</tr>
					<tr>
						<th class="title-table">Ngày đăng ký:</th>
						<th style="font-weight: normal;">${borrow.getInsertDt()}</th>
					</tr>
					<tr>
						<th class="title-table">Người xem xét:</th>
						<td class="content_table"
							style="text-transform: uppercase; font-weight: bold;">${userApprove.getName()}</td>
					</tr>
					<tr>
						<th class="title-table">Ngày xem xét:</th>
						<th style="font-weight: normal;">${dateApprove}</th>
					</tr>
				</table>
				<br>
				<h4>THÔNG TIN TÀI SẢN</h4>
				<table id="table.data" class="table   table-data">
					<tr>
						<th style="width: 139px;">Tên tài sản</th>
						<td colspan="5">${borrow.getAsset().getName()}</td>
					</tr>
				</table>
				<table id="table.data" class="table   table-data">
					<tr>
						<th style="width: 69px;">MODEL</th>
						<td>${borrow.getAsset().getModel()}</td>
						<th style="width: 58px;">SERIES</th>
						<td>${borrow.getAsset().getSeries()}</td>
						<th style="width: 43px;">RFID</th>
						<td>${borrow.getAsset().getRFID()}</td>
					</tr>
				</table>
				<table id="table.data" class="table   table-data">
					<tr>
						<th style="width: 139px;">Ngày cho mượn</th>
						<td colspan="2">${borrow.getDate_start()}<td>
						<th style="width: 153px;">Ngày trả kế hoạch</th>
						<td>${borrow.getDate_end()}
					</tr>
				</table>


				<table id="table.data" class="table   table-data">
					<tr>
						<th style="width: 139px;">Lý do cho mượn</th>
						<td colspan="5">${borrow.getReason()}</td>
					</tr>
				</table>

				<div class="w3-section" style="width:300px; margin:auto;">
					<form action="LoanAssetReturn" method="post" style="width: 60px; float:left; margin-right:120px">
						<input style="display: none;" name="loan_cd" value="${borrow.getId()}">
						<button type="submit" class="w3-button w3-green" name="assetReturn">TRẢ TÀI SẢN</button>
					</form>
					<form action="LoanAssetApprove" method="post" style="width: 60px; float: left">
						<input style="display: none;" name=""loan_cd"" value="${borrow.getId()}">
						<button type="submit" class="w3-button w3-red" name="disApprove">KHÔNG XÁC NHẬN</button>
					</form>
				</div>
			</div>

		</div>

	</div>


</body>
</html>