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
button.btnduyet
{
	height: 33px;
	margin-top:-6px;
	font-size: 16px;
	font-weight: 700;
}
</style>
</head>
<body style="overflow: hidden;">

	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	
<form action="BorrowAssetApprove" method="post">
	<div class="container">
		<div class="w3-card-4"
			style="margin: auto; margin-top: 20px;">

			<div  style="padding: 10px; width:100%; border 2px solid;">
				<div class="title-feature" style="margin-bottom:10px; width:100%;  margin:auto; height:33px;">
					<div class="text-right">
						
							<input style="display: none;" name="borrow_cd" value="${borrow.getId()}">
							<button type="submit"  class="w3-button w3-blue btnduyet" name="approve">DUYỆT CHO MƯỢN</button>
							<button type="submit" class="w3-button w3-red btnduyet"  name="disApprove">KHÔNG DUYỆT</button>
							<button type="submit" class="w3-button w3-blue btnduyet"  name="back">QUAY LẠI</button>
						
<!-- 						<form action="BorrowAssetApprove" method="post" style="width: 60px; float: left"> -->
<%-- 							<input style="display: none;" name="borrow_cd" value="${borrow.getId()}"> --%>
<!-- 							<button type="submit" class="w3-button w3-blue btnduyet"  name="back">QUAY LẠI</button> -->
<!-- 						</form> -->
					</div>
				</div>
				<div style="width:100%">
				<h4 style="width:100%; text-align: center;">XÉT DUYỆT CHO MƯỢN TÀI SẢN</h4>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				</div>
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
						<td>${borrow.getDate_start()}</td>
						<th style="width: 153px;">Ngày trả kế hoạch</th>
						<td>${borrow.getDate_end()}</td>					
					</tr>
				</table>


				<table id="table.data" class="table   table-data">
					<tr>
						<th style="width: 139px;">Lý do cho mượn</th>
						<td colspan="5" style="text-align: left;">${borrow.getReason()}</td>
					</tr>
				</table>
				<div class="title-table" style="width:100%; padding: 10px; font-weight: 700;">LÝ DO KHÔNG DUYỆT CHO MƯỢN (Chỉ sử dụng trường hợp không phê duyệt)<span class="require">(*)</span></div>
				<textarea name="reason" style="width:100%; height:100px; resize: none;">
				</textarea>
				
			</div>

		</div>

	</div>
</form>

</body>
</html>