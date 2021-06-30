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
footer {
	width: 100%;
	position: absolute;
	bottom: 0;
	left: 0;
	padding: 0px;
	margin: 0px
}

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
	<form action="AuthenticationChecking"
		style="width: 90%; margin: auto; margin-top: 10px" method="POST">
		<div class="form-group">
			<label class="title" for="usr" style="color: darkred">TÊN
				KIỂM KÊ:</label> <input type="text"
				style="background-color: #ffffcc !important;" readonly="readonly"
				class="form-control" value="${sess.getInventorySessionName()}"
				name="InventorySessionCD" id="InventorySessionCD"> <label
				class="title" for="usr" style="color: darkred">MÃ QUẢN LÝ</label> <input name="inventoryCD"
				type="text" style="background-color: #ffffcc !important;"
				readonly="readonly" class="form-control"
				value="${sess.getInvenotrySessionCD()}"> <label
				class="title" for="usr" style="color: darkred">MÃ KIỂM KÊ</label> <input
				type="text" style="background-color: #ffffcc !important;"
				readonly="readonly" class="form-control"
				value="${sess.getInventorySessionShortCD()}">
			<div class="form-group">
				<label class="title" for="usr" style="color: darkred">ĐƠN VỊ
					KIỂM KÊ</label> <select onchange="selectIDSession()"
					class="form-control iset3d" name="select_group" id="select_group">
					<c:forEach var="p" items="${lstDept}">
						<option value="${p.getDept_cd()}">${p.getDept_name()}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<center style="font-weight: 700">
			<button type="submit" class="btn btn-primary " name="checking"
				style="width: 250px;">TIẾN HÀNH KIỂM KÊ</button>
			<button type="submit" class="btn btn-primary " name="create_new" style="width: 250px;">BÁO
				MỚI</button>
			<br>
			<button type="submit" class="btn btn-primary "  name="logout"
				style="width: 250px; background-color: darkred;">ĐĂNG XUẤT</button>
			<br>
		</center>
	</form>
	<div>
		<c:if test="${message != null}">
			<div class="alert alert-danger message">
				<strong>${message}</strong>
			</div>
		</c:if>
	</div>

	<footer id="footer-page"
		style="bottom: 0; overflow: hidden; height: 50px;">
		<h3 id="pdaversion"
			style="background-color: #005BB5; margin: 0px; color: white; font-size: 20px; line-height: 50px; text-align: center;">${company}</h3>
	</footer>

</body>
</html>