<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHỨC NĂNG HỆ THỐNG</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<style type="text/css">
.table-feature {
	margin-top: 10px;
}

.table-feature tbody tr td {
	width: 150px;
	height: 50px;
	border: 0px !important;
	padding-top: 5px;
	padding-bottom: 5px;
}

.btn-feature {
	width: 100%;
	height: 50px;
	margin: auto;
	background-color: green;
	line-height: 36px;
	color: white;
	font-weight: 700;
}

.btn-feature:hover {
	background-color: #007bff;
	color: white;
}

.btn-admin {
	background-color: orange;
}

.btn-logout {
	background-color: darkred;
}
</style>
</head>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div class="container">
		<div style="margin-top: 20px;">
			<jsp:include page="viewcommon/message.jsp"></jsp:include>
		</div>
		<div class=" table-feature">
			<form action="AccountantBorrowCouponManagement" method="POST">
				<div class="row" id="menu-set">


					<div class="col-sm-3" style="margin-top: 10px;">
						<button class="btn  btn-feature" type="submit" name="borrow">PHIẾU CHO MƯỢN</button>
					</div>

					<div class="col-sm-3" style="margin-top: 10px;">
						<button class="btn  btn-feature" type="submit" name="loan">PHIẾU MƯỢN</button>
					</div>



				</div>
			</form>
		</div>
		<div class=" table-feature" style="text-align: center;">
			<a class="btn  btn-feature btn-logout" href="FeatureSystem" style="width: 250px; margin: auto;"><i class="fa fa-arrow-circle-left" style="font-size: 24px; margin-right: 10px" aria-hidden="true"></i>QUAY TRỞ LẠI</a>
		</div>
	</div>
	<%-- 	<jsp:include page="viewcommon/footer.jsp"></jsp:include> --%>
</body>
<script type="text/javascript">
	function getBrowserSize() {
		var w, h;

		if (typeof window.innerWidth != 'undefined') {
			w = window.innerWidth; //other browsers
			h = window.innerHeight;
		} else if (typeof document.documentElement != 'undefined'
				&& typeof document.documentElement.clientWidth != 'undefined'
				&& document.documentElement.clientWidth != 0) {
			w = document.documentElement.clientWidth; //IE
			h = document.documentElement.clientHeight;
		} else {
			w = document.body.clientWidth; //IE
			h = document.body.clientHeight;
		}
		return {
			'width' : w,
			'height' : h
		};
	}

	if (parseInt(getBrowserSize().width) < 900) {
		document.getElementById("menu-set").style.display = "none";
		document.getElementById("menu-set-small").style.display = "flex";

	}
</script>
</html>