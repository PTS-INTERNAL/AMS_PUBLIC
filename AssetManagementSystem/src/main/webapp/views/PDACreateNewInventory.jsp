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
	position: absolute;
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

.form-group {
	margin-bottom: 0px !important;
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
	<form action="PDACreateNewInventory"
		style="width: 90%; margin: auto; margin-top: 10px" method="POST">
		<div class="form-group">
			<label class="title" for="usr">MÃ KIỂM KÊ:</label> <input type="text"
				style="background-color: green;" readonly="readonly"
				class="form-control" value="${session_id}" name="InventorySessionCD"
				id="InventorySessionCD">
		</div>
		<div class="form-group" style="margin-top: 10px; margin-bottom: 10px;">
			<button type="button" class="btn btn-primary "
				onclick="ToLink('inventory')" style="float: right; width: 120px;">QUAY
				LẠI</button>
			<button type="submit" class="btn btn-primary " name="save"
				style="float: right; width: 120px;">LƯU LẠI</button>

		</div>
		<br>
		<div>
			<c:if test="${message != null}">
				<div class="alert alert-danger message"
					style="margin-top: 20px !important">
					<strong>${message}</strong>
				</div>
			</c:if>
			<c:if test="${NOTIFICATION != null}">
				<div class="alert alert-success message"
					style="margin-top: 20px !important">
					<strong>${NOTIFICATION}</strong>
				</div>
			</c:if>
		</div>
		<div class="form-group">
			<label class="title" for="usr">MÃ RFID (Nếu có):</label> <input
				type="text" style="background-color: green;" class="form-control"
				value="" name="rfid">
		</div>
		<div class="form-group">
			<label class="title" for="usr">TÊN TÀI SẢN:</label> <input
				type="text" required style="background-color: green;"
				class="form-control" value="" name="asset_name">
		</div>
		<div class="form-group">
			<label class="title" for="usr">VỊ TRÍ:</label> <input type="text"
				style="background-color: green;" required class="form-control"
				value="" name="department">
		</div>
		<div class="form-group">
			<label class="title" for="usr">LÝ DO BÁO MỚI:</label>
			<textarea name="reason" required
				style="width: 100%; height: 200px; resize: none;"></textarea>
		</div>
		<div class="form-group">
			<label class="title" for="usr">GHI CHÚ:</label>
			<textarea name="note"
				style="width: 100%; height: 100px; resize: none;"></textarea>
		</div>

	</form>

</body>
</html>