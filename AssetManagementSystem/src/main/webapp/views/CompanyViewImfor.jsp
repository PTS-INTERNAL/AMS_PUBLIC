<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>THÊM DOANH NGHIỆP</title>
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
	<div class="w3-hide-small w3-light-grey w3-card-2" style="margin: 10px;; margin-top:10px; border-radius: 0px;">
	<s:form action="CompanyImformationView" method="POST"
		commandName="excelFile" enctype="multipart/form-data">
		<div class="title-feature">
			<div class="text-right">
				<input type="text" value="${cmpn.getCompany_cd()}" style="display: none;"
					name="cmpn_cd">
				<button type="submit" style="border-radius: 0"
					class="btn btn-primary" name="update">
					<i style='font-size: 24px' class='far'>&#xf044;</i> CHỈNH SỬA
				</button>
				<button type="delete" style="border-radius: 0"
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
		<jsp:include page="viewcommon/message.jsp"></jsp:include>
		<div style="margin-left: 10px; margin-right: 10px">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">TÊN CÔNG TY: <span class="require">*</span></label> <input type="text"
							class="form-control" value="${cmpn.getCompany_name()}" name="name">
					</div>
				</div>
				<div class="col-sm-6">
					<div class="row">

						<div class="col-sm-5">
							<div class="form-group">
								<label class="title_input">TÊN GỌN:  <span class="require">*</span></label> <input type="text"
									class="form-control" value="${cmpn.getCompany_shortname()}" name="shortName">
							</div>
						</div>
						<div class="col-sm-2">
							<img id="logoimage"
								style="width: 80px; height: 80px; border: 2px solid black"
								src="${cmpn.getCompany_logo()}" />
						</div>
						<div class="col-sm-5">
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
						<label class="title_input">ĐỊA CHỈ DOANH NGHIỆP:  <span class="require">*</span></label> <input
							type="text" name="address"  value="${cmpn.getCompany_address()}"class="form-control">
					</div>
				</div>
				<div class="col-sm-6">
					<div class="row">
						<div class="col-sm-6" style="padding-left: 0px;">
							<div class="form-group">
								<label class="title_input">MÃ SỐ THUẾ</label> <input name="tax"
									type="text" value="${cmpn.getCompany_tax_number()}" class="form-control">
							</div>
						</div>
						<div class="col-sm-6" style="padding-right: 0px;">
							<div class="form-group">
								<label class="title_input">SỐ ĐIỆN THOẠI</label> <input
									type="text" value="${cmpn.getCompany_phone()}" name="phone" class="form-control">
							</div>
						</div>
					</div>

				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">EMAIL</label> <input name="email"
							type="text" value="${cmpn.getCompany_email()}" class="form-control">
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">WEBSITE</label> <input name="website"
							type="text" value="${cmpn.getCompany_website()}" class="form-control">
					</div>
				</div>
				
				
				<div class="col-sm-6">
					<div class="form-group">
						<label class="title_input">MÔ TẢ - GHI CHÚ</label>
						<textarea name="description" class="form-control"
							style="resize: none;" rows="5" id="comment">${cmpn.getCompany_description()}</textarea>
					</div>
				</div>

			</div>
		</div>
	</s:form>
	</div>
</body>
<script type="text/javascript">
function deleteRow(index) {
	 document.getElementById("table_company").deleteRow(index);
	
}
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