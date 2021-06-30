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
	margin-top: 20px;
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
.table-search tr .title {
    font-weight: 700;
    padding: 5px 4px 0px 4px;
    background-color: #0089FF;
    color: white;
    margin-bottom: 0px;
    border: 0px;
}
.table-search tr td input {
    width: 100%;
    height: 30px;
    border: 0px;
}
textarea {
	border: 0px;
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
		<jsp:include page="viewcommon/message.jsp"></jsp:include>

	</div>
	<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
		<form action="UpdateGroupAsset" method="POST">
		<input style="display: none;" value="${formSearch.getGroup_id()}"
							 name="group_id">
			<table class="table   table-search" style="border:0px;">
				<thead>
					<tr>
						<td class="title" width="150px">NHÓM TÀI SẢN</td>
					</tr>
					<tr>
						<td><input value="${formSearch.getGroup_name()}"
							placeholder="Tên nhóm tài sản" name="group_name"></td>
					</tr>
					<tr>
						<td class="title" width="150px">MÔ TẢ</td>
					</tr>
					<tr>
						<td style="height: 60px;"><textarea 
								name="group_describe"
								style="width: 100%; height: 60px; resize: none; vertical-align: inherit;"
								placeholder="Diễn tả đơn vị này?">${formSearch.getGroup_desciption()}</textarea></td>
					</tr>

				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="title-feature">
				<div class="text-right">
					<button type="submit" style="border-radius: 0"
						class="btn btn-primary" name="save">
						<i style="font-size: 24px" class="fa">&#xf067;</i>LƯU CẬP NHẬT
					</button>

					<button type="submit" style="border-radius: 0" name="delete"
						class="btn btn-primary">
						<i style="font-size: 24px" class="fa">&#xf1c3;</i>XÓA
					</button>
					<button type="button" style="border-radius: 0" name="back"
						class="btn btn-primary">
						<i class="fa fa-arrow-circle-right" onclick="window.close()"
							style="font-size: 24px"></i></i>ĐÓNG
					</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	function getvalue(code, name)
	{
		if (window.opener != null && !window.opener.closed) {
            var txtName = window.opener.document.getElementById('<%=request.getParameter("param2")%>');
            var txtcd = window.opener.document.getElementById('<%=request.getParameter("param1")%>
	');
			txtName.value = name;
			txtcd.value = code;
		}
		window.close();
	}
</script>
</html>