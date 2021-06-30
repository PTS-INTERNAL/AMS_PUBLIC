<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kiểm kê</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<style type="text/css">
.titileScreen {
	width: 100%;
	line-height: 60px;
	font-size: 20px;
	color: white;
	text-align: center;
	font-weight: 600;
}

.headerPDA {
	height: 60px;
	background-color: #005BB5;
}

.headerPDA p {
	font-size: 4vw;
}

.logo {
	width: 70px;
	line-height: 40px;
	font-size: 20px;
	color: white;
	text-align: center;
	font-weight: 600;
	position: fixed;
	border: 2px solid white;
	height: 45px;
	margin-top: 7px;
	margin-left: 7px;
}

.logo p {
	width: 100%;
	text-align: center;
	font-weight: 600;
}

.title {
	display: inline-block;
	margin-bottom: 5px;
	margin-top: 5px;
	font-size: 16px;
	font-weight: 700;
}

button[type="submit"] {
	border-radius: 0px;
	border: 1px solid #e0e0e0;
}

button[type="button"] {
	border-radius: 0px;
	border: 1px solid #e0e0e0;
}

.message {
	width: 90%;
	margin: auto;
	margin-top: 60px;
	border: 1px solid;
	border-radius: 0px;
}

input[type="text"]#rfid {
	border-radius: 0px;
	border: 1px solid #e0e0e0;
	height: 50px;
	font-size: 35px;
	font-weight: 700;
	text-align: center;
}
</style>
<script>
	function getfocus() {
		document.getElementById("rfid").focus();
	}

	function losefocus() {
		document.getElementById("myAnchor").blur();
	}
</script>
</head>
<body onload="return getfocus()">
	<div class="row">
		<div style="width: 100%">
			<div class="row logo" style="float: left">
				<p>AMS</p>
			</div>
			<div class="row headerPDA">
				<p class="titileScreen">MÀN HÌNH KIỂM KÊ</p>
			</div>

		</div>
	</div>
	<form action="PDACheckInventory"
		style="width: 90%; margin: auto; margin-top: 10px" method="get">
		<div class="form-group">
			<label class="title" for="usr">MÃ KIỂM KÊ:</label> <input type="text"
				style="background-color: green;" readonly="readonly"
				class="form-control" value="${session_id}" name="InventorySessionCD"
				id="InventorySessionCD">
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="rfid" id="rfid">

		</div>
		<button type="button" class="btn btn-primary "
			onclick="ToLink('inventory')" style="float: right; width: 120px;">QUAY
			LẠI</button>
		<button type="button" class="btn btn-primary "
			onclick="ToLink('PDACreateNewInventory?session_id=${session_id}')"
			style="float: right; width: 120px;">BÁO MỚI</button>
		<button type="submit" class="btn btn-primary "
			style="float: right; width: 120px;">KIỂM TRA</button>
	</form>
	<div>
		<c:if test="${message != null}">
			<div class="alert alert-danger message">
				<strong>${message}</strong>
			</div>
		</c:if>
	</div>

	<c:if test="${Asset != null}">
		<table class="table  "
			style="width: 90%; margin: auto; margin-top: 12px;">
			<tbody>
				<tr>
					<td style="width: 35%; font-weight: 700">Tên tài sản</td>
					<td style="color: red; font-weight: 700">${Asset.getName()}</td>
				</tr>
				<tr>
					<td style="font-weight: 700">Series</td>
					<td>${Asset.getSeries()}</td>
				</tr>
				<tr>
					<td style="font-weight: 700">Model</td>
					<td>${Asset.getModel()}</td>
				</tr>
				<tr>
					<td style="font-weight: 700">Đơn vị</td>
					<td>${Asset.getDepartment()}</td>
				</tr>
				<tr>
					<td style="font-weight: 700">Ngày đầu tư</td>
					<td>${Asset.getDateStart()}</td>
				</tr>
				<tr>
					<td style="font-weight: 700">Trạng thái</td>
					<td style="color: green; font-weight: 700">Kiểm kê thành công</td>
				</tr>

			</tbody>
		</table>
	</c:if>




	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content iset3d"
				style="border-radius: 0px; border: 1px solid gray">

				<!-- Modal Header -->
				<div class="modal-header"
					style="height: 40px; background-color: #005BB5;">
					<p class="modal-title" style="height: 30px; margin-top: -9px;">Đăng
						nhập</p>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="/action_page.php">
						<div class="form-group" style="margin-bottom: 0px;">
							<label for="email" style="font-weight: 700;">Mã nhân
								viên:</label> <input type="text" class="form-control iset3d"
								placeholder="Enter email" id="email">
						</div>
						<div class="form-group" style="margin-bottom: 0px;">
							<label for="pwd" style="font-weight: 700;">Mật khẩu:</label> <input
								type="password" class="form-control iset3d"
								placeholder="Enter password" id="pwd">
						</div>
						<button type="submit" style="margin-top: 10px;"
							class="btn btn-danger" data-dismiss="modal">XÁC THỰC</button>
					</form>
				</div>



			</div>
		</div>
	</div>

</body>
</html>