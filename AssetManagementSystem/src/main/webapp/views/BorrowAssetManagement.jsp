<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ CHO MƯỢN</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
<script src="./resources/javascript/message/bootbox.all.js"></script>
<script src="./resources/javascript/message/bootbox.all.min.js"></script>
<script src="./resources/javascript/message/bootbox.js"></script>
<script src="./resources/javascript/message/bootbox.locales.js"></script>
<script src="./resources/javascript/message/bootbox.locales.min.js"></script>
<script src="./resources/javascript/message/bootbox.min.js"></script>
</head>

<body onload="Pagination()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="BorrowAssetManagement" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								<td class="title">ĐƠN VỊ CHO MƯỢN</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input type="text"
											style="height: 30px;" class="form-control inputAssetItem"
											value="${borrow.getDept_master().getDept_name() }"
											readonly="readonly" name="department_name_master"
											id="department_name_master"> <input type="text"
											class="form-control inputAssetItem" style="display: none;"
											name="department_cd_master"
											value="${borrow.getDept_master().getDept_cd() }"
											id="department_cd_master">
										<button type="button" class="select"
											style="height: 30px; margin-right: 2px;"
											onclick="return openDialogue('GetListDepartment?param1=department_cd_master&param2=department_name_master')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;"
											onclick="return clearText('department_cd_master','department_name_master' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>

										</button>
									</div></td>

								<td class="title">Tên Tài Sản</td>
								<td><input class="selectList"
									value="${borrow.getAsset().getName()}" name="text_asset_name"></td>
								<td class="title">Model</td>
								<td><input value="${borrow.getAsset().getModel()}"
									name="text_model"></td>
								<td class="title">Số Series</td>
								<td><input value="${borrow.getAsset().getSeries()}"
									name="text_series"></td>
							</tr>
							<tr>
								<td class="title">CÔNG TY Mượn</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input type="text"
											style="height: 30px;" class="form-control inputAssetItem"
											value="${borrow.getDept_client().getDept_name()}"
											readonly="readonly" name="department_name_client"
											id="department_name_client"> <input type="text"
											class="form-control inputAssetItem" style="display: none;"
											name="department_cd_client"
											value="${borrow.getDept_client().getDept_cd()}"
											id="department_cd_client">
										<button type="button" class="select"
											style="height: 30px; margin-right: 2px;"
											onclick="return openDialogue('GetListDepartment?param1=department_cd_client&param2=department_name_client')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;"
											onclick="return clearText('department_cd_client','department_name_client' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>

										</button>
									</div></td>
								<td class="title">Mã RFID</td>
								<td><input class="selectList"
									value="${borrow.getAsset().getRFID()}" name="text_rfid"></td>

								<td class="title">Ngày cho mượn</td>
								<td colspan="3"><input value="${borrow.getDate_start()}"
									style="width: 45%" type="date" data-date-format="DD/MM/YYYY"
									name="text_start_date"> ~ <input
									value="${borrow.getDate_start_end()}" style="width: 45%"
									type="date" name="text_end_date"></td>
							</tr>
							<tr>
								<td class="title">LỆNH ĐIỀU ĐỘNG</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input type="text"
											style="height: 30px;" class="form-control inputAssetItem"
											value="${borrow.getNumber_on()}"
											 name="number_on"
											> 
									</div></td>

							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="search">
								<i style="font-size: 24px" class="fa">&#xf067;</i>TÌM KIẾM
							</button>
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="create">
								<i style="font-size: 24px" class="fa">&#xf067;</i>THÊM MỚI
							</button>
							<button type="submit" style="border-radius: 0" name="reportExcel"
								class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT EXCEL
							</button>
<!-- 							<button type="submit" style="border-radius: 0" name="reportPDF" -->
<!-- 								class="btn btn-primary"> -->
<!-- 								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT PDF -->
<!-- 							</button> -->
							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i></i>QUAY
								LẠI
							</button>
						</div>
					</div>
				</form>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				<c:if test="${lst.size() > 0}">
					<table id="table.data" style="margin-top: 0px"
						class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								<th colspan="1"></th>
								<th colspan="4">DOANH NGHIỆP CHO MƯỢN</th>
								<th colspan="4">THÔNG TIN TÀI SẢN</th>
								<th colspan="5">DOANH NGHIỆP MƯỢN</th>

							</tr>
							<tr>
								<th style="width: 25px;">STT</th>
								<th>LỆNH</th>
								<th>DN CHO MƯỢN</th>
								<th>ĐV CHO MƯỢN</th>
								<th>NGÀY CHO MƯỢN</th>
								<th>TÊN TÀI SẢN</th>
								<th>RFID</th>
								<th>SERIES</th>
								<th>MODEL</th>
								<th>DN MƯỢN</th>
								<th>ĐV MƯỢN</th>
								<th>NGÀY TRẢ KH</th>
								<th>TRẠNG THÁI</th>
								<th style="width: 60px;">XEM</th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${lst}">
								<tr>
									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getNumber_on()}</td>
									<td>${p.getCmpn_master().getCompany_shortname()}</td>
									<td>${p.getDept_master().getDept_name()}</td>
									<td align="right">${p.getDate_start()}</td>
									<td>${p.getAsset().getName()}</td>
									<td>${p.getAsset().getRFID()}</td>
									<td>${p.getAsset().getSeries()}</td>
									<td>${p.getAsset().getModel()}</td>
									<td>${p.getCmpn_client().getCompany_shortname()}</td>
									<td>${p.getDept_client().getDept_name()}</td>
									<td align="right">${p.getDate_end()}</td>
									<td style="width: 160px;">
									<c:if
											test="${p.getStatus()=='1'}">
											<form action="BorrowAssetApprove" method="post"
												style="width: 160px;">
												<input style="display: none;" name="borrow_cd"
													value="${p.getId()}">
												<button
													style="width: 160px; margin: auto; background-color: orange"
													type="submit" class="view" name="view">CHƯA PHÊ
													DUYỆT</button>
											</form>
										</c:if> <c:if test="${p.getStatus()=='2'}">
											<span class="view"
												style="width: 160px; background-color: yellow; color: black; border: 1px solid yellow;">CHỜ
												XÁC NHẬN</span>
										</c:if> <c:if test="${p.getStatus()=='3'}">
											<span class="view"
												style="border: 1px solid white; width: 160px; background-color: white; color: black">ĐANG
												CHO MƯỢN</span>
										</c:if> <c:if test="${p.getStatus()=='4'}">
											<form action="BorrowAssetConfirmReturn" method="post"
												style="width: 160px;">
												<input style="display: none;" name="borrow_cd"
													value="${p.getId()}">
												<button type="submit" class="view"
													style="width: 160px; margin: auto; background-color: red;"
													name="view_asset">XÁC NHẬN TRẢ</button>
											</form>
										</c:if> <c:if test="${p.getStatus()=='5'}">
											<span class="view"
												style="width: 160px; background-color: green">ĐÃ TRẢ</span>
										</c:if> <c:if test="${p.getStatus()=='6'}">
											<span class="view"
												style="width: 160px; background-color: red">KHÔNG
												DUYỆT</span>
										</c:if></td>
									<td>
										<form action="BorrowAssetView" method="post"
											style="width: 60px;">
											<input style="display: none;" name="id_borrow"
												value="${p.getId()}">
											<button type="submit" class="view" name="view_asset">XEM</button>
										</form>
									</td>
								</tr>
								<%
									stt++;
								%>
							</c:forEach>
						</tbody>
					</table>

					<div class="text-right">
						<%
							if (stt > 10) {
									double countPage = stt / 10;
									if (stt % 10 > 0) {
										countPage += 1;
									}
									int j = 1;
									while (j <= countPage) {
										int startIndex = j * 10 - 9;
										int endIndex = startIndex + 9;
						%>
						<a class="btn btn-default btn-paging" id='pagging.btn<%=j%>'
							onclick="movePage('<%=startIndex%>','<%=endIndex%>', '<%=j%>', '<%=countPage%>')"><%=j%></a>
						<%
							j++;
									}
								}
						%>
					
				</c:if>
			</div>

		</div>
	</div>
	</div>
</body>
<script type="text/javascript">
	function abc() {
		bootbox
				.confirm({
					title : "Destroy planet?",
					message : "Do you want to activate the Deathstar now? This cannot be undone.",
					buttons : {
						cancel : {
							label : '<i class="fa fa-times"></i> Cancel'
						},
						confirm : {
							label : '<i class="fa fa-check"></i> Confirm'
						}
					},
					callback : function(result) {
						Example.show('This was logged in the callback: '
								+ result);
					}
				});
	}
	//Hàm click button chuyển trang
	function movePage(start, end, indexCurrent, countPage) {
		var x = document.getElementById("table.data").rows.length;
		for (var i = 1; i < x; i++) {
			var index = i + 1;
			if (i >= start && i <= end) {
				document.getElementById("table.data").rows[i].style.display = '';
			} else {
				document.getElementById("table.data").rows[i].style.display = 'none';
			}

		}
		for (var i = 1; i <= countPage; i++) {
			x = document.getElementById("pagging.btn" + i);
			x.style.backgroundColor = "";
			x.style.color = "black";
		}
		x = document.getElementById("pagging.btn" + indexCurrent);
		x.style.backgroundColor = "red";
		x.style.color = "white";

	}
	//Hàm phân trang cho dữ liệu
	function Pagination() {
		var x = document.getElementById("pagging.btn1");
		x.style.backgroundColor = "red";
		x.style.color = "white";
		x = document.getElementById("table.data").rows.length;
		if (x > 10) {
			for (var i = 10; i < x; i++) {
				document.getElementById("table.data").rows[i + 1].style.display = 'none';
			}

		}
	}

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
				color = "#bdc6e2";
			}

			var row = checkBoxes[i].parentNode.parentNode;
			for (var j = 0; j < row.cells.length; j++) {
				row.cells[j].style.backgroundColor = color;
			}
		}

	}
	$(document).ready(function() {
		Pagination();
		$('#example').DataTable({
			columnDefs : [ {
				orderable : false,
				className : 'select-checkbox',
				targets : 0
			} ],
			select : {
				style : 'os',
				selector : 'td:first-child'
			},
			order : [ [ 1, 'asc' ] ]
		});
	});

	$(function() {
		$('#datetimepicker1').datetimepicker();
	});
</script>
</html>