<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asset management</title>
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
	<div style="margin-top: 10px; padding: 0px; margin: 10px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="RentAssetManagement" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								<td style="width: 180px;" class="title">Đơn vị</td>
								<td><div class="input-group" style="margin-top: 0px;">
										<input type="text" class="form-control inputAssetItem"
											readonly="readonly" name="department_name" value="${rentAsset.getDept_master().getDept_name()}"
											id="department_name"> <input type="text" value="${rentAsset.getDept_master().getDept_cd()}"
											class="form-control inputAssetItem" style="display: none;"
											name="department_cd" id="department_cd">
										<button type="button" class="select" style="height: 30px"
											onclick="return openDialogue('GetListDepartment?param1=department_cd&param2=department_name')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select " style="height: 30px"
											onclick="return clearText('department_cd','department_name' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>
										</button>
									</div></td>
								<td class="title">Tên tài sản</td>
								<td><input type="text" value="${rentAsset.getAsset().getName()}"
									name="text_asset_name"></td>
								<td style="width: 180px;" class="title">Model</td>
								<td><input type="text" value="${rentAsset.getAsset().getModel()}"
									name="text_asset_model"></td>
								<td class="title">Series</td>
								<td><input value="${rentAsset.getAsset().getSeries()}"
									name="texxt_assset_series" type="text"></td>

							</tr>
							<tr>
								<td class="title">Thuê tại</td>
								<td><input value="${rentAsset.getComany_client().getCompany_name()}"
									name="company_name"></td>
								<td class="title">Mã Kế Toán</td>
								<td><input value="${rentAsset.getAsset().getAccountant_CD()}"
									name="text_accountant"></td>
								
								<td class="title">Ngày thuê</td>
								<td colspan="3"><input value="${rentAsset.getDate_start()}"
									style="width: 45%" type="date" data-date-format="DD/MM/YYYY"
									name="text_start_date"> ~ <input
									value="${rentAsset.getDate_start_end()}" style="width: 45%"
									type="date" name="text_date_start_end"></td>
							</tr>
							<tr>
								<td class="title">Ngày trả kế hoạch</td>
								<td style="width: 40%" colspan="3"><input
									value="${rentAsset.getDate_end_schedual()}" style="width: 45%"
									type="date" data-date-format="DD/MM/YYYY"
									name="text_end_schedual_date_start"> ~ <input
									value="${rentAsset.getDate_end_schedual_end()}" style="width: 45%"
									type="date" name="text_end_schedual_date_end"></td>

								<td class="title">Ngày trả thực tế</td>
								<td colspan="3"><input value="${rentAsset.getDate_end_real()}"
									style="width: 45%" type="date" data-date-format="DD/MM/YYYY"
									name="text_end_date_real_start"> ~ <input
									value="${rentAsset.getDate_end_real_end()}" style="width: 45%"
									type="date" name="text_end_date_real_end"></td>
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
							<button type="button" style="border-radius: 0"
								onclick="ToLink('ImportCSVAssetGenneral')"
								class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf1c3;</i>NHẬP EXCEL
							</button>
							<button type="submit" style="border-radius: 0" name="reportExcel"
								class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT EXCEL
							</button>
							<button type="submit" style="border-radius: 0" name="reportPDF"
								class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT PDF
							</button>
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
								<th>STT</th>
								<th>TÊN TÀI SẢN</th>
								<th>MODEL</th>
								<th>SERIES</th>
								<th>ĐƠN VỊ</th>
								<th>MÃ KẾ TOÁN</th>
								<th>THUÊ TẠI</th>
								<th>ĐỊA CHỈ</th>
								<th>NGÀY THUÊ</th>
								<th>NGÀY TRẢ KẾ HOẠCH</th>
								<th>NGÀY TRẢ CHÍNH THỨC</th>
								<th style="width:120px;">TRẠNG THÁI</th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${lst}">
								<tr>
									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getAsset().getName()}</td>
									<td>${p.getAsset().getModel()}</td>
									<td>${p.getAsset().getSeries()}</td>
									<td>${p.getDept_master().getDept_name()}</td>
									<td>${p.getAsset().getAccountant_CD()}</td>
									<td>${p.getComany_client().getCompany_name()}</td>
									<td>${p.getComany_client().getCompany_address()}</td>
									<td>${p.getDate_start()}</td>
									<td>${p.getDate_end_schedual()}</td>
									<td>${p.getDate_end_real()}</td>
									<td>
									<c:if test="${p.getStatus()=='1'}">
									<form action="RentAssetView" method="post"
											style="width: 60px;">
											<input style="display: none;" name="rent_id"
												value="${p.getRent_cd() }">
											<button style="width:113px" type="submit" class="view" name="view_asset">ĐANG THUÊ</button>
										</form>
									</c:if>
									<c:if test="${p.getStatus()=='2'}">
									<form action="RentAssetView" method="post"
											style="width: 60px;">
											<input style="display: none;" name="rent_id"
												value="${p.getRent_cd() }">
											<button  style="width:113px; background-color: white; color:black; border:0px;" type="submit" class="view" name="view_asset">ĐÃ TRẢ</button>
										</form>
									</c:if>
									
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


					</div>
				</c:if>
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