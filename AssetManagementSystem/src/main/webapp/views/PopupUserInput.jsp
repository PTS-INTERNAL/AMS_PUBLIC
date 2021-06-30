<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Màn hình người dùng</title>
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
	font-size: 3vw;
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

table.tablePopup thead tr th {
	padding: 5px;
	text-align: center;
	background-color: green;
	color: white;
	border: 2px solid black;
}

table.tablePopup tbody tr td {
	padding: 5px;
	border: 2px solid black;
}
</style>
</head>
<body>
	<div class="row">
		<div style="width: 100%">
			<div class="row logo" style="float: left">
				<p>AMS</p>
			</div>
			<div class="row headerPDA">
				<p class="titileScreen">${TittleScreen}</p>
			</div>

		</div>
	</div>
	<div>
		<c:if test="${message != null}">
			<div class="alert alert-danger message">
				<strong>${message}</strong>
			</div>
		</c:if>
		<p
			style="width: 100%; text-align: center; color: blue; font-weight: 700; margin-top: 10px">${NOTIFICATION}</p>
	</div>
	<c:if test="${lst != null}">
		<table class="table   tablePopup"
			style="width: 90%; margin: auto; margin-top: 12px;">
			<thead>
				<th>MÃ NGƯỜI DÙNG</th>
				<th colspan="1">TÊN ĐĂNG NHẬP</th>
				<th colspan="1"></th>
			</thead>
			<tbody>
				<c:forEach var="p" items="${lst}">
					<tr>
						<td style="width: 35%; font-weight: 700">${p.getEmployee_cd()}</td>
						<td style="color: red; font-weight: 700">${p.getName()}</td>
						<td style="width: 80px;"><button
								style="width: 100%; height: 100%"
								onclick="return getvalue('${p.getEmployee_cd()}','${p.getName()}')">CHỌN</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
<script type="text/javascript">
	function getvalue(code, name)
	{
		if (window.opener != null && !window.opener.closed) {
            var cd = window.opener.document.getElementById('<%= request.getParameter("param1") %>');
            var na = window.opener.document.getElementById('<%= request.getParameter("param2") %>');
            cd.value = code;
            na.value = name;
            
        }
 		var btnSaveRight = window.opener.document.getElementById('reload');
 		if(btnSaveRight != null)
 		{
 			btnSaveRight.click();
 		}
    window.close();
	}
</script>
</html>