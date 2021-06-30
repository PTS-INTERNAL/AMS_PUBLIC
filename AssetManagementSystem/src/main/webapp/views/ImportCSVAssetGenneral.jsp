<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NHẬP TÀI SẢN</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet" href="./resources/css/tabledata.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style type="text/css">
.title_input {
	font-weight: 700;
}
</style>
</head>

<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<s:form method="POST" commandName="excelFile"
		action="ImportCSVAssetGenneral"
		style="width:60%; margin:auto; margin-top:20px;"
		enctype="multipart/form-data">
		<div class="col-sm-12">
			<label class="title_input">Chọn file excel:</label>

			<div class="custom-file">
				<input type="file" type="file" name="file" class="custom-file-input" required="required"
					id="customFile"> <label class="custom-file-label"
					for="customFile"></label>
			</div>
			<div class="text-center">
				<button type="submit"
					style="margin: auto; border-radius: 0; margin-top: 10px;"
					class="btn btn-primary" name="upload">
					<i style='font-size: 24px' class='far'>&#xf044;</i> UPLOAD
				</button>
				<button type="button" onclick="ToLink('AssetManagementGeneral')"
					style="margin: auto; border-radius: 0; margin-top: 10px;"
					class="btn btn-primary" name="back">
					<i class="fa fa-arrow-circle-right" style="font-size:24px"></i>QUAY LẠI
				</button>
			</div>

		</div>
	</s:form>
	<c:if test="${lst != null}">
		<table class="table   tablePopup"
			style="width: 90%; margin: auto; margin-top: 12px;">
			<thead>
				<th colspan="1">Lỗi Dòng</th>
				<th colspan="1">Nội dung</th>
			</thead>
			<tbody>
				<c:forEach var="p" items="${lst}">
					<tr>
						<td style="color: red; font-weight: 700">${p.getLine()}</td>
						<td style="color: red; font-weight: 700">${p.getContent()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
<script type="text/javascript">
	// Add the following code if you want the name of the file appear on select
	$(".custom-file-input").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
</script>
</html>