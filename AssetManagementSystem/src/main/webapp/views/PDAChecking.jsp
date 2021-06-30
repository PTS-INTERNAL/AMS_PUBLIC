<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KIỂM KÊ TÀI SẢN</title>
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
.table-data td
{
	white-space: inherit;
}
</style>
</head>
<body style="overflow: hidden;" onload="SetFocus()">
	<div class="row">
		<div style="width: 100%">
			<div class="row logo" style="float: left">
				<p>AMS</p>
			</div>
			<div class="row headerPDA">
				<p class="titileScreen">KIỂM KÊ TÀI SẢN</p>
			</div>

		</div>
	</div>
	<form action="InventoryChecking"
		style="width: 90%; margin: auto; margin-top: 10px" method="POST">
		<div class="form-group">
			<div style="display: none;">
				<label class="title" for="usr" style="color: darkred">TÊN
					KIỂM KÊ:</label> <input type="text"
					style="background-color: #ffffcc !important;" readonly="readonly"
					class="form-control" value="${session}" name="InventorySessionCD"
					id="InventorySessionCD">
				<div class="form-group">
					<label class="title" for="usr" style="color: darkred">ĐƠN
						VỊ KIỂM KÊ</label> <input name="select_group" type="text"
						style="background-color: #ffffcc !important;" readonly="readonly"
						class="form-control" value="${dept}">
				</div>
			</div>
			<input name="uhf_rfid" type="text"
				style="background-color: #ffffcc !important;" id="uhf_rfid"
				class="form-control" value="${uhf_rfid}">
			<center>
				<button type="submit" class="btn btn-primary " name="checking"
					style="width: 250px; margin-top: 5px;">KIỂM TRA</button>
			</center>
		</div>
	</form>
	<div>

		<jsp:include page="viewcommon/message.jsp"></jsp:include>

	</div>
	<div class="row">
		
				<div id="table-show "
					style="overflow-y: scroll; overflow-x: hidden; height:540px; padding: 5px;">
					<c:if test="${lstCheck.size() > 0}">
						<table  id="table.data" style="margin-top: 0px; margin-top: 10px;"
							class="table   table-data">
							<thead>
								<tr>
									<th>STT</th>
									<th>Tên tài sản</th>
									<th>Model</th>
									<th>Series</th>
									<!-- 						<th>Trạng thái</th> -->
								</tr>
							</thead>
							<tbody>
								<%
									int stt = 1;
								%>
								<c:forEach var="p" items="${lstCheck}">
									<c:if test="${p.getStatus()=='0'}">
										<tr>
									</c:if>
									<c:if test="${p.getStatus()!='0'}">
										<tr style="background-color: #b3ffb3">
									</c:if>


									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getAsset().getName()}</td>

									<td>${p.getAsset().getModel()}</td>
									<td>${p.getAsset().getSeries()}</td>
									<%-- 						<td>${p.getStatus()}</td> --%>

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
		

	<footer id="footer-page"
		style="bottom: 0; overflow: hidden; height: 50px;">
		<h3 id="pdaversion"
			style="background-color: #005BB5; margin: 0px; color: white; font-size: 20px; line-height: 50px; text-align: center;">${company}</h3>
	</footer>

</body>
<script>
	function SetFocus() {
		document.getElementById("uhf_rfid").focus();
	}
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
	document.getElementById("table-show").style.height = getBrowserSize().height
			- 200 + 'px';
</script>
</html>