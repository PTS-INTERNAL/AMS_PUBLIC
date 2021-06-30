<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ PHIÊN KIỂM KÊ</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet" href="./resources/css/tabledata.css">
<style type="text/css">
a.viewitem {
	cursor: pointer;
}
</style>
</head>

<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin: 10px">
	<form method="POST" action="InventorySessionManagement">
	
		<div class="title-feature">
			<div class="text-right">
				<button type="submit" style="border-radius: 0"
					name="register" class="btn btn-primary">
					<i style="font-size: 24px" class="fa">&#xf067;</i>TẠO PHIÊN KIỂM KÊ
				</button>
				<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size:24px"></i></i>QUAY LẠI
							</button>
			</div>
		</div>
		</form>
	</div>
	<div style="margin-left: 10px; margin-right: 10px">

		<table id="table.data" class="table   table-data"
			style="margin-top: 10px">
			<thead>
				<tr>
					<th style="width: 50px">STT</th>
					<th >MÃ QUẢN LÝ</th>
					<th >TÊN PHIÊN KIỂM KÊ</th>
					<th >NGÀY BẮT ĐẦU</th>
					<th >NGÀY KẾT THÚC</th>
					<th style="width: 150px">KẾT QUẢ</th>
					<th style="width: 60px"></th>
				</tr>
			</thead>
			<tbody>
			<%
				int stt=1;
			%>
				<c:forEach var="inventorySession" items="${lstInventorySession}">
					<tr>
						<td><%=stt%></td>
						<td style="text-align: left;">${inventorySession.getInventorySessionShortCD()}</td>
						<td style="text-align: left;">${inventorySession.getInventorySessionName()}</td>
						<td style="text-align: right;">${inventorySession.getInventorySessionStart()}</td>
						<td style="text-align: right;">${inventorySession.getInventorySessionEnd()}</td>
						<td style="width:65px;">
							<form action="InventoryCheckingResult" method="post" style="width: 60px;">
											<input style="display: none;" name="session_cd" value="${inventorySession.getInvenotrySessionCD()}">
											<button style="width:140px" type="submit" class="view" name="view_result">KẾT QUẢ KIỂM</button>
										</form>	
						<td style="width:65px;">
							<form action="InventorySessionUpdate" method="post" style="width: 60px;">
											<input style="display: none;" name="session_cd" value="${inventorySession.getInvenotrySessionCD()}">
											<button type="submit" class="view" name="view_inventory_session">XEM</button>
										</form>	
						</td>
					</tr>
					<%stt++;%>
				</c:forEach>

			</tbody>
		</table>

	</div>
</body>
<script type="text/javascript">
	//hàm chọn dữ liệu
	function GetSelected() {
		//Reference the Table.
		var grid = document.getElementById("table.data");

		//Reference the CheckBoxes in Table.
		var checkBoxes = document.getElementsByName("checkboxrow");

		//Loop through the CheckBoxes.
		for (var i = 0; i < checkBoxes.length; i++) {
			var color = "white";
			if (checkBoxes[i].checked) {
				color = "yellow";
			}

			var row = checkBoxes[i].parentNode.parentNode;
			for (var j = 0; j < row.cells.length; j++) {
				row.cells[j].style.backgroundColor = color;
			}
		}

	}
</script>
</html>