<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XEM THÔNG TIN CÔNG TY</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<script src="../resources/javascript/common.js"></script>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link rel="stylesheet" href="../resources/css/common.css">
<link rel="stylesheet" href="../resources/css/default.css">
<link rel="stylesheet" href="../resources/css/tabledata.css">
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
	<s:form action="companyUpdateAction" method="POST"
		commandName="excelFile" enctype="multipart/form-data">
		<div class="title-feature">
			<div class="text-right">
				<input type="text" value="${cmpn.getCompany_cd()}" style="display: none;"
					name="cmpn_cd">
				<button type="submit" style="border-radius: 0"
					class="btn btn-primary" name="update">
					<i style='font-size: 24px' class='far'>&#xf044;</i> CHỈNH SỬA
				</button>
				<button type="submit" style="border-radius: 0"
					class="btn btn-primary" name="delete">
					<i style='font-size: 24px' class='fas'>&#xf2ed;</i>XÓA
				</button>
				<button type="submit" style="border-radius: 0" name="back"
					class="btn btn-primary">
					<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
					LẠI
				</button>
			</div>
		</div>
		<div style="margin-left: 10px; margin-right: 10px">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">TÊN CÔNG TY:</label> <input type="text"
							class="form-control" value="${cmpn.getCompany_name()}" name="name">
					</div>
				</div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-2">
							<img id="logoimage"
								style="width: 80px; height: 80px; border: 2px solid black"
								src="${cmpn.getFile_name()}" />
						</div>
						<div class="col-sm-10">
							<label class="title_input">LOGO CÔNG TY:</label>

							<div class="custom-file">
								<input type="file" id="logocompany" name="file-name"
									class="custom-file-input" id="customFile"> <label
									class="custom-file-label" for="customFile">Chọn hình
									ảnh</label>
							</div>

						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">ĐỊA CHỈ DOANH NGHIỆP:</label> <input
							type="text" value="${cmpn.getAddress()}" name="address"
							class="form-control">
					</div>
				</div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-6" style="padding-left: 0px;">
							<div class="form-group">
								<label class="title_input">MÃ SỐ THUẾ</label> <input name="tax"
									type="text" value="${cmpn.getTax()}" class="form-control">
							</div>
						</div>
						<div class="col-sm-6" style="padding-right: 0px;">
							<div class="form-group">
								<label class="title_input">SỐ ĐIỆN THOẠI</label> <input
									type="text" value="${cmpn.getPhone()}" name="phone"
									class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">EMAIL</label> <input name="email"
							type="text" value="${cmpn.getEmail()}" class="form-control">
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">WEBSITE</label> <input name="website"
							type="text" value="${cmpn.getWebsite()}" class="form-control">
					</div>
				</div>
				<div class="col-sm-6">
					<label class="title_input">CẤP DOANH NGHIỆP:</label> <select
						name="level" id="selectLevel" class="custom-select">
						<c:if test="${lstCompanyLevel.size() > 0}">
							<c:forEach var="item" items="${lstCompanyLevel}">
								<option value="${item.getCmp_level_cd()}">${item.getCmp_level_name()}</option>
							</c:forEach>
						</c:if>
					</select>
					<div class="manage-area">
						<label class="title_input" style="margin-top: 10px">DANH
							SÁCH CÔNG TY CẤP DƯỚI</label>
						<table id="table.data" class="table   table-data">
							<thead>
								<tr>
									<th style="width: 2%"></th>
									<th style="width: 20%">TÊN CÔNG TY</th>
									<th style="width: 78%;">ĐỊA CHỈ</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input name="checkboxrow"
										onclick="return GetSelected()" onchange="GetSelected()"
										type="checkbox" class="form-check-input"
										style="margin: 0px; padding: 0px; margin-top: 7px" value=""></td>
									<td style="text-align: center;">${company.getCompany_name()}</td>
									<td>${company.getCompany_address()}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">MÔ TẢ - GHI CHÚ</label>
						<textarea name="description" class="form-control"
							style="resize: none;" rows="5" id="comment">${cmpn.getDesciption()}</textarea>
					</div>
				</div>

			</div>
		</div>
	</s:form>
</body>
<script type="text/javascript">
	var valueX = $
	{
		form.getLevel()
	};
	window.onload = function(e) {
		var str = "'";
		document.getElementById("selectLevel").value = str.concat(valueX, "'");
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#btnabc").click(function(event) {

			// Stop default form Submit.
			event.preventDefault();

			// Call Ajax Submit.

			ajaxSubmitForm();

		});

		$('#logocompany').change(function() {
			$.ajax({
				type : 'GET',
				url : 'http://localhost:8080/AssetMangement/testajax',
				success : function(result) {
					//$('#logoimage').href(result);
					alert("dssds");
					document.getElementById("logoimage").src = result;
				}

			});
		});

	});

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