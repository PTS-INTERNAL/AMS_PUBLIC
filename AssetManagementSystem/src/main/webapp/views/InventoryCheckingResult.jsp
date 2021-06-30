<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XEM KẾT QUẢN KIỂM KÊ</title>
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

<style>
 .radio-inline{
 	position: relative;
    display: inline-block;
    padding-left: 40px;
    margin-bottom: 0;
    font-weight: 400;
    vertical-align: middle;
    cursor: pointer;
}
input[type="radio"] {
    width: 25px !important;
    height: 25px !important;
    vertical-align: sub;
    margin-right:10px;
}
label span {
    font-size: 20px;
    font-weight: 700;
    margin-right: 5px;
    
}
</style>
<body onload="Pagination()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin-top: 10px; padding: 0px; margin: 10px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="InventoryCheckingResult" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								<td class="title">Đơn Vị</td>
								<td><div class="input-group">
								<input type="text" style="display:none;" name="session_cd" value="${session_cd}"/>
										<span class="input-group-addon"> </span> <input type="text"
											style="height: 30px;" class="form-control inputAssetItem"
											value="${formSearch.getAsset().getDepartment_name()}"
											readonly="readonly" name="department_name"
											id="department_name"> <input type="text"
											class="form-control inputAssetItem" style="display: none;"
											name="department_cd" value="${formSearch.getAsset().getDepartment_cd()}"
											id="department_cd">
										<button type="button" class="select"
											style="height: 30px; margin-right: 2px;"
											onclick="return openDialogue('GetListDepartment?param1=department_cd&param2=department_name')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;"
											onclick="return clearText('department_cd','department_name' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>

										</button>
									</div></td>
								<td class="title">Nhóm</td>
								<td>
									<div class="input-group">
										<span class="input-group-addon"> </span> <input type="text"
											style="height: 30px;" class="form-control inputAssetItem"
											readonly="readonly" name="group_asset_na" id="group_asset_na"
											value="${formSearch.getAsset().getGroup_name()}"> <input
											type="text" class="form-control inputAssetItem"
											style="display: none;" name="group_asset_cd"
											value="${formSearch.getAsset().getGroup_id()}" id="group_asset_cd">
										<button type="button" class="select"
											style="height: 30px; margin-right: 2px;"
											onclick="return openDialogue('GetListGroupAsset')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;"
											onclick="return clearText('group_asset_na','group_asset_cd' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>
										</button>
									</div>
								</td>
								<td class="title">Model</td>
								<td><input value="${formSearch.getAsset().getModel()}"
									class="selectList" list="text_model" name="text_model">
									<datalist class="selectList-item" id="text_model">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getModel()}">
										</c:forEach>
									</datalist></td>
								<td class="title">Số Series</td>
								<td><input value="${formSearch.getAsset().getSeries()}"
									class="selectList" list="text_series" name="text_series">
									<datalist id="text_series" class="selectList-item">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getSeries()}">
										</c:forEach>
									</datalist></td>
							</tr>
							<tr>
								<td class="title">Mã RFID</td>
								<td><input class="selectList"
									value="${formSearch.getAsset().getRFID()}" list="text_rfid"
									name="text_rfid"> <datalist class="selectList-item"
										id="text_rfid">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.RFID}">
										</c:forEach>
									</datalist></td>
								<td class="title">Tên</td>
								<td><input class="selectList"
									value="${formSearch.getAsset().getName()}" list="text_asset_name"
									name="text_asset_name"> <datalist
										class="selectList-item" id="text_asset_name">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getName()}">
										</c:forEach>
									</datalist></td>
								<td class="title">Ngày Kiểm Kê</td>
								<td colspan="3"><input
									value="${formSearch.getAsset().getDateStart_Start()}" style="width: 45%"
									type="date" data-date-format="DD/MM/YYYY"
									name="text_start_date_s"> ~ <input
									value="${formSearch.getAsset().getDateStart_End()}" style="width: 45%"
									type="date" name="text_start_date_e"></td>								
							</tr>
							<tr>
 								<td class="title">Loại Kiểm Kê</td>
 								<td colspan="8">
 									<c:if test="${formSearch.getStatus()=='9999'}">
	 									<label class="radio-inline" style="padding-left:5px !important;"><input value="9999" type="radio" name="optradio" checked><span>TẤT CẢ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="1"><span>HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="3"><span>KHÔNG HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="2"><span>BÁO MỚI</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="0"><span>CHƯA KIỂM KÊ</span></label>
									</c:if>
									
									<c:if test="${formSearch.getStatus()=='1'}">
	 									<label class="radio-inline" style="padding-left:5px !important;"><input value="9999" type="radio" name="optradio" ><span>TẤT CẢ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="1" checked><span>HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="3"><span>KHÔNG HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="2"><span>BÁO MỚI</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="0"><span>CHƯA KIỂM KÊ</span></label>
									</c:if>
									
									<c:if test="${formSearch.getStatus()=='2'}">
	 									<label class="radio-inline" style="padding-left:5px !important;"><input value="9999" type="radio" name="optradio" ><span>TẤT CẢ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="1"><span>HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="3"><span>KHÔNG HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="2" checked><span>BÁO MỚI</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="0"><span>CHƯA KIỂM KÊ</span></label>
									</c:if>
									
									<c:if test="${formSearch.getStatus()=='3'}">
	 									<label class="radio-inline" style="padding-left:5px !important;"><input value="9999" type="radio" name="optradio" ><span>TẤT CẢ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="1"><span>HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="3" checked><span>KHÔNG HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="2" ><span>BÁO MỚI</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="0"><span>CHƯA KIỂM KÊ</span></label>
									</c:if>
									
									<c:if test="${formSearch.getStatus()=='0'}">
	 									<label class="radio-inline" style="padding-left:5px !important;"><input value="9999" type="radio" name="optradio" ><span>TẤT CẢ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="1"><span>HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="3"><span>KHÔNG HỢP LỆ</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="2"><span>BÁO MỚI</span></label>
										<label class="radio-inline"><input type="radio" name="optradio" value="0" checked><span>CHƯA KIỂM KÊ</span></label>
									</c:if>
									
 								</td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="search">
								<i class="fa fa-search" style="font-size: 24px"
									aria-hidden="true"></i> TÌM KIẾM
							</button>
<!-- 							<button type="submit" style="border-radius: 0" -->
<!-- 								class="btn btn-primary" name="register" onclick="ToLink('CreateAssetGeneral')"> -->
<!-- 								<i style="font-size: 24px" class="fa">&#xf067;</i>THÊM MỚI -->
<!-- 							</button> -->
<!-- 							<button type="submit" name="import" style="border-radius: 0" -->
<!-- 								class="btn btn-primary"> -->
<!-- 								<i style="font-size: 24px" class="fa">&#xf1c3;</i>NHẬP EXCEL -->
<!-- 							</button> -->
<!-- 							<button type="submit" style="border-radius: 0" name="reportExcel" -->
<!-- 								class="btn btn-primary"> -->
<!-- 								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT EXCEL -->
<!-- 							</button> -->
 							<button type="submit" style="border-radius: 0" name="reportPDF" 
 								class="btn btn-primary">
 								<i class="fa fa-file-pdf-o" style="font-size: 24px"></i>XUẤT PDF 
 							</button>
							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
								LẠI
							</button>
						</div>
					</div>
				</form>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				<c:if test="${listAssetSearch.size() > 0}">
					<table style="margin-top:10px; margin-bottom:10px;">
						<tr>
							<td style="text-align:center;font-weight:700; width: 30px; height: 10px; background-color: white; border: 1px solid">${ok}</td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">KIỂM KÊ HỢP LỆ</td>
							<td style="text-align:center;font-weight:700; width: 30px; height: 10px; background-color: orange;border: 1px solid">${news}</td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">KIỂM KÊ KHÔNG HỢP LỆ</td>
							<td style="text-align:center;font-weight:700; width: 30px; height: 10px; background-color: yellow;border: 1px solid">${ng}</td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">BÁO MỚI</td>
							<td style="text-align:center;font-weight:700; color:white; width: 30px; height: 10px; background-color: red;border: 1px solid black">${notok}</td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">CHƯA KIỂM KÊ</td>
						</tr>
					</table>
					<table id="table.data" style="margin-top: 0px"
						class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								<th style="width: 2%">STT</th>
								<th style="width: 8%;">NHÓM</th>
								<th style="width: 8%;">RFID</th>
								<th style="width: 15%;">TÊN MÁY</th>
								<th style="width: 14%;">MODEL</th>
								<th style="width: 14%;">SỐ SERIES</th>
								<th style="width: 13%;">ĐƠN VỊ KIỂM</th>
								<th style="width: 10%;">ĐƠN VỊ DÙNG</th>
								<th style="width: 10%;">NGÀY KIỂM</th>
								<th style="width: 9%;">TRẠNG THÁI</th>
								<th style="width: 2%"></th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${listAssetSearch}">						
							<c:if test="${p.getStatus()=='1'}">
								<tr>
							</c:if>
							<c:if test="${p.getStatus()=='2'}">
								<tr style="background-color: yellow">
							</c:if>
							<c:if test="${p.getStatus()=='0'}">
								<tr style="background-color: red; color:white">
							</c:if>
							<c:if test="${p.getStatus()=='3'}">
								<tr style="background-color: orange">
							</c:if>
									<td style="text-align: center; background-color: white; color:black"><%=stt%></td>
									<td>${p.getGroup().getGroup_name()}</td>
									<td>${p.getAsset().getRFID()}</td>
									<td>${p.getAsset().getName()}</td>
									<td>${p.getAsset().getModel()}</td>
									<td>${p.getAsset().getSeries()}</td>
									<td>${p.getDeptChecking().getDept_name()}</td>
									<td>${p.getDeptUsing().getDept_name()}</td>
									<td>${p.getAsset().getInsert_dt()}</td>
									<td>
									<c:if test="${p.getStatus_Explain() != null}">
										<form action="InventoryCheckingResultView" method="post"
											style="width: 100px;">
											
											<p style="margin: 0px;width: 123px;" class="view" name="view_asset">CÓ GIẢI TRÌNH</p>
										</form>
									</c:if>
									</td>
									<td style="background-color: white;">
										<form action="InventoryCheckingResultView" method="post"
											style="width: 60px;">
											<input style="display: none;" name="inventory_checking_cd"
												value="${p.getInventory_Checking_Cd()}">
												<input style="display: none;" name="asset_rfid"
												value="${p.getAsset().getRFID()}">
											<c:if test="${p.getStatus()=='0'}">
											<button type="submit" disabled="disabled"  style="background-color: gray" class="view" name="view_asset">XEM</button>
											</c:if>
											<c:if test="${p.getStatus()!='0'}">
											<button type="submit"  class="view" name="view_asset">XEM</button>
											</c:if>
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
							if (stt > 13) {
									double countPage = stt / 13;
									if (stt % 13 > 0) {
										countPage += 1;
									}
									int j = 1;
									while (j <= countPage) {
										int startIndex = j * 13 - 12;
										int endIndex = startIndex + 12;
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
			for (var i = 13; i < x; i++) {
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