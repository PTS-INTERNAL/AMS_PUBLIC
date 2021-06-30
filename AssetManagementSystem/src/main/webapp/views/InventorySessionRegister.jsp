<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>General management</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet" href="./resources/css/tabledata.css">
</head>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>'
	<div class="w3-hide-small w3-light-grey w3-card-2">
	<form action="InventorySessionRegister" method="POST">
		<div style="margin: 10px">
			<div class="title-feature">
				<div class="text-right">
					<button type="submit" style="border-radius: 0"
						name="save"
						class="btn btn-primary">
						<i style="font-size: 24px" class="fa">&#xf067;</i>LƯU PHIÊN
					</button>
					<button type="submit" name="back" style="border-radius: 0"
						class="btn btn-primary" name="back">
						<i style="font-size: 24px" class="fa">&#xf067;</i>QUAY TRỞ LẠI
					</button>
				</div>
			</div>
		</div>
		<div style="margin-left: 10px; margin-right: 10px">
			<div class="row">
				<div class="col-sm-6">
					<div class="form-group">
						<label for="text" class="title_input">TÊN KIỂM KÊ:<span class="require">(*)</span></label> <input type="text"
							class="form-control" value="${inSes.getInventorySessionName() }" name="name">
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="text" class="title_input">NGÀY BẮT ĐẦU:<span class="require">(*)</span> </label> <input type="date"
							class="form-control" value="${ inSes.getInventorySessionStart()}" name="startdate">
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="text" class="title_input">NGÀY KẾT THÚC:<span class="require">(*)</span></label> <input type="date"
							 class="form-control" value="${inSes.getInventorySessionEnd() }" name="enddate">
					</div>
				</div>
				<div class="col-sm-3">
					<div class="form-group">
						<label for="text" class="title_input">MÃ QUẢN LÝ:<span class="require">(*)</span></label> <input value="${inSes.getInventorySessionShortCD() }" type="text" 
							class="form-control" name="code">
					</div>
				</div>
				<div style="width:100%">
				
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				</div>
				<div style="margin:auto;" class="alert alert-danger message">
			
		</div>
				<div class="col-sm-12" style="display: none;">
					<div class="form-group">
						<label for="text" class="title_input">PHÂN QUYỀN:<span class="require">(*)</span></label>
						<hr style="margin-top: 0px;">
						<div class="row" style="width: 60%; margin: auto;">
							<div class="col-sm-5">
								<div class="form-group">
									<label for="text" class="title_input">MÃ NHÂN VIÊN:<span class="require">(*)</span> </label> <input type="text"
										 class="form-control" id="employee_code"
										name="employee_code">
								</div>
							</div>
							<div class="col-sm-5">
								<div class="form-group">
									<label for="text" class="title_input">TÊN NHÂN VIÊN: </label> <input type="text"
										 class="form-control" id="employee_name"
										name="employee_name">
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<label for="text"></label> <br> <input type="button"
										style="margin-top: 8px;" id="btnSearchPermission"
										 class="btn" value="TÌM KIẾM">
								</div>
							</div>
						</div>
						<input type="number" style="display: none;" id="count_column" name="count_employee" value="0">
						<div class="row">
							<table style="width:600px; margin: auto;" id="table.data" class="table   table-data"
								style="margin-top: 10px">
								<thead>
									<tr>
										<th style="width: 280">MÃ NHÂN VIÊN</th>
										<th style="width: 280">TÊN NHÂN VIÊN</th>
										<th >XÓA</th>
									</tr>
								</thead>
								<tbody style="background-color: white;">
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	</div>
</body>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$('#btnSearchPermission')
								.click(
										function() {
											var employee_code = document
													.getElementById('employee_code').value;
											var employee_name = document
													.getElementById('employee_name').value;
											var urlDerection = 'http://'
													+ window.location.hostname
													+ ':8080/AssetManagementSystem/GetUserPermission';
											var pagrameter = '?employee_code='
													+ employee_code
													+ '&employee_name='
													+ employee_name;
											$
													.ajax({
														type : 'GET',
														url : urlDerection
																+ pagrameter,
														contentType : "application/x-www-form-urlencoded;charset=utf-8",
														success : function(
																result) {
															var res = result
																	.split("_");
															if (res[0] == 'message') {
																document
																		.getElementById("error").innerHTML = res[1];
															} else {
																var table = document
																		.getElementById("table.data");
																var count = table.rows.length;
																if(document.getElementById("item.emloyee[" + res[0] + "]") == null)
																{
																var row = table
																		.insertRow(count);
																var cell1 = row
																		.insertCell(0);
																var cell2 = row
																		.insertCell(1);
																var cell3 = row
																		.insertCell(2);
																var idCount = count-1;
																
																	var x = document.createElement("INPUT");
																	  x.setAttribute("type", "text");
																	  x.setAttribute("style", "border:0px; background-color: transparent !important;");
																	  x.setAttribute("value", res[0]);
																	  x.setAttribute("name", "item.emloyee[" +  idCount + "]");
																	  x.setAttribute("id", "item.emloyee[" +  res[0] + "]");
																	cell1.appendChild(x);
																	cell2.innerHTML = res[1];
																	cell3.innerHTML ="<input type='button' style='width:90%' value='XÓA' onclick='SomeDeleteRowFunction(this)'>";
																	
																	var storeValue = document.getElementById("count_column").value
																	document.getElementById("count_column").value = Number(storeValue) + Number('1');

																	document.getElementById("error").innerHTML = "";
																}
																else
																{
																	document.getElementById("error").innerHTML = "Người dùng đã tồn tại";
																}
																  
															}

														}

													});
										});

					});
</script>
<script>
    function SomeDeleteRowFunction(o) {
     //no clue what to put here?
     var p=o.parentNode.parentNode;
         p.parentNode.removeChild(p);
    }
    </script>
</html>