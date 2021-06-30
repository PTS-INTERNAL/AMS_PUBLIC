<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ DOANH NGHIỆP</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet" href="./resources/css/tabledata.css">
</head>

<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin: 10px">
		<form action="CompanyManagement" method="POST"
			style="text-align: -webkit-center;">
			<table class="table   table-search"
				style="width: 1024px;">
				<thead>
					<tr>
						<td class="title" style="width: 140px">DOANH NGHIỆP</td>
						<td><div class="input-group">
								<span class="input-group-addon"> </span> <input  value="${cmpn_na}" type="text"
									style="height: 30px;" class="form-control inputAssetItem"
									value="" readonly="readonly" name="cmpn_na" id="cmpn_na">
								<input type="text" class="form-control inputAssetItem"
									style="display: none;" value="${cmpn_cd}" name="cmpn_cd" value="" id="cmpn_cd">
								<button type="button" class="select"
									style="height: 30px; margin-right: 2px;"
									onclick="return openDialogue('PopupCompanyInput?param1=cmpn_cd&param2=cmpn_na')">
									<i class="fa fa-th-list"></i>
								</button>
								<button type="button" class="select" style="height: 30px;"
									onclick="return clearText('cmpn_na','cmpn_cd' )">
									<i class="fa fa-eraser" aria-hidden="true"></i>
								</button>
							</div></td>
						<td class="title" style="width: 160px">TÊN VIẾT TẮT</td>
						<td><input type="text"  value="${shortName}" class="form-control" name="shortName">
						</td>

					</tr>

				</thead>
				<tbody>
				</tbody>
			</table>
			<div style="margin-left: 10px; margin-right: 10px">
				<div class="title-feature">
					<div class="text-right">
						<button type="submit" style="border-radius: 0"
							class="btn btn-primary" name="search">
							<i class="fa fa-search" style="font-size: 24px"
								aria-hidden="true"></i> TÌM KIẾM
						</button>
						<button type="submit" style="border-radius: 0"
							class="btn btn-primary" name="create">
							<i style="font-size: 24px" class="fa">&#xf067;</i>THÊM MỚI
						</button>
<!-- 						<button type="submit" style="border-radius: 0" name="reportExcel" -->
<!-- 							class="btn btn-primary"> -->
<!-- 							<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT EXCEL -->
<!-- 						</button> -->
<!-- 						<button type="submit" style="border-radius: 0" name="reportPDF" -->
<!-- 							class="btn btn-primary"> -->
<!-- 							<i class="fa fa-file-pdf-o" style="font-size: 24px"></i>XUẤT PDF -->
<!-- 						</button> -->
						<button type="submit" style="border-radius: 0" name="back"
							class="btn btn-primary">
							<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
							LẠI
						</button>
					</div>
				</div>
			</div>
		</form>
	</div>

	<div style="margin-left: 10px; margin-right: 10px">
		<c:if test="${lstCompany.size() > 0}">
			<table id="table.data" class="table   table-data"
				style="margin-top: 10px">
				<thead>
					<tr>
						<th style="width: 3%">STT</th>
						<th>TÊN CÔNG TY</th>
						<th>TÊN VIẾT TẮT</th>
						<th>ĐỊA CHỈ</th>
						<th>SỐ ĐIỆN THOẠI</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<%int stt= 1; %>
					<c:forEach var="company" items="${lstCompany}">
						<tr>
							<td><%=stt%></td>

							<td style="text-align: left;">${company.getCompany_name()}</td>
							<td>${company.getCompany_shortname()}</td>
							<td>${company.getCompany_address()}</td>
							<td>${company.getCompany_phone()}</td>
							<td style="width:60px;">
								<form action="CompanyImformationView" method="post"
									style="width: 60px;">
									<input style="display: none;" name="cmpn_cd"
										value="${company.getCompany_cd()}">
									<button type="submit" class="view" name="view">XEM</button>
								</form>
							</td>
							<td style="width:120px;">
								<form action="CompanyRightManagement" method="post"
									style="width: 120px;">
									<input style="display: none;" name="cmpn_cd"
										value="${company.getCompany_cd()}">
									<button style="width:120px;" type="submit" class="view" name="setUserRight">PHÂN QUYỀN</button>
								</form>
							</td>
							<td style="width:100px;">
								<form action="CompanyManagement" method="post"
									style="width: 100px;">
									<input style="display: none;" name="cmpn_cd"
										value="${company.getCompany_cd()}">
									<button style="width:100px;" type="submit" onclick="return myFunction()" class="view" name="delete">XÓA</button>
								</form>
							</td>
						</tr>
						<% stt+=1; %>
					</c:forEach>

				</tbody>
			</table>
		</c:if>
	</div>
</body>
<script type="text/javascript">
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
	
	function myFunction() {
		  if(!confirm("Bạn có chắc muốn xóa doanh nghiệp!"))
		  return false;
		  else
		return true; 
		  
		}
	
</script>
</html>