<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ TÀI SẢN CHUNG</title>
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
table.table-data tr td
{
	height:30px;
	vertical-align: middle;
}
</style>
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
.table-search tr .title {
    font-weight: 700;
    padding: 5px 4px 0px 4px;
    background-color: #0089FF;
    color: white;
    width: 121px;
}
</style>
<body onload="Pagination()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin-top: 10px; padding: 0px; margin: 10px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="PopupAddAsset" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								<td class="title">Đơn Vị</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input type="text"
											style="height: 30px;" class="form-control inputAssetItem"
											value="${formSearch.getDepartment_name()}"
											readonly="readonly" name="department_name"
											id="department_name"> <input type="text"
											class="form-control inputAssetItem" style="display: none;"
											name="department_cd" value="${formSearch.getDepartment_cd()}"
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
											value="${formSearch.getGroup_name()}"> <input
											type="text" class="form-control inputAssetItem"
											style="display: none;" name="group_asset_cd"
											value="${formSearch.getGroup_id()}" id="group_asset_cd">
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
								<td>
								<input value="${formSearch.getModel()}"
									class="selectList" list="text_model" name="text_model">
									
									<datalist class="selectList-item" id="text_model">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getModel()}">
										</c:forEach>
									</datalist>
									
									</td>
									
									
								
							</tr>
							<tr>
							<td class="title" style="width:90px;">Số Series</td>
								<td><input value="${formSearch.getSeries()}"
									class="selectList" list="text_series" name="text_series">
									<datalist id="text_series" class="selectList-item">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getSeries()}">
										</c:forEach>
									</datalist></td>
								<td class="title">Mã RFID</td>
								<td><input class="selectList"
									value="${formSearch.getRFID()}" list="text_rfid"
									name="text_rfid"> <datalist class="selectList-item"
										id="text_rfid">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.RFID}">
										</c:forEach>
									</datalist></td>
								<td class="title">Tên</td>
								<td><input class="selectList"
									value="${formSearch.getName()}" list="text_asset_name"
									name="text_asset_name"> <datalist
										class="selectList-item" id="text_asset_name">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getName()}">
										</c:forEach>
									</datalist></td>
								
							</tr>
							<tr>
							<td class="title">Mã Kế Toán</td>
								<td><input value="${formSearch.getAccountant_CD()}"
									list="text_accountant" class="selectList"
									name="text_accountant"> <datalist id="text_accountant"
										class="selectList-item">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getAccountant_CD()}">
										</c:forEach>
									</datalist></td>
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
							
<button type="button" style="border-radius: 0" class="btn btn-primary" id="CLearDataSearch">
								<i class="fa fa-trash" style="font-size: 24px"aria-hidden="true"></i> LÀM TRỐNG
							</button>
		
						</div>
					</div>
					
					<c:if test="${listAssetSearch.size() > 0}">
				<div style="width:100%; font-size: 16px;">
				<p style="text-transform: uppercase; text-align: center;font-size: 20px;font-weight: 700;color: blue;padding: 10px; padding-bottom: 0px; margin-bottom: -10px;">Tìm kiếm được ${listAssetSearch.size()} tài sản</p>
				</div>
				</c:if>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				<c:if test="${listAssetSearch.size() > 0}">
					<table style="margin-top:10px; margin-bottom:10px;">
						<tr>
							<td style="width: 30px; height: 10px; background-color: white; border: 1px solid"></td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">TÀI SẢN BIÊN CHẾ</td>
							<td style="width: 30px; height: 10px; background-color: #b3ffb3;border: 1px solid"></td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">TÀI SẢN CHO MƯỢN</td>
							<td style="width: 30px; height: 10px; background-color: #ffffcc;border: 1px solid"></td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">TÀI SẢN  MƯỢN</td>
							<td style="width: 30px; height: 10px; background-color: #ffc2b3;border: 1px solid"></td>
							<td style="font-weight: 700; padding-left:10px;padding-right:10px;">TÀI SẢN THUÊ</td>
						</tr>
					</table>
					<table id="table.data" style="margin-top: 0px"
						class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								<th style="width: 2%">STT</th>
								<th>NHÓM</th>
								<th>RFID</th>
								<th >TÊN MÁY</th>
								<th>MODEL</th>
								<th>SỐ SERIES</th>
								<th>ĐƠN VỊ</th>				
								<th>MÃ KẾ TOÁN</th>
								<th style="width: 2%"></th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${listAssetSearch}">
							<c:if test="${p.getMode()=='GEN'}">
								<tr>
							</c:if>
							<c:if test="${p.getMode()=='BORROW'}">
								<tr style="background-color: #b3ffb3">
							</c:if>
							<c:if test="${p.getMode()=='LOAN'}">
								<tr style="background-color: #ffffcc">
							</c:if>
							<c:if test="${p.getMode()=='RENT'}">
								<tr style="background-color: #ffc2b3">
							</c:if>

									<td style="text-align: center;">${p.getSTT()} </td>
									<td>${p.getGroup_name()}</td>
									<td>${p.getRFID()}</td>
									<td >${p.getName()}</td>
									<td>${p.getModel()}</td>
									<td>${p.getSeries()}</td>
									<td>${p.getDepartment_name()}</td>
									
									<td>${p.getAccountant_CD()}</td>
									
									
									<td style="background-color: white;">
	
											<button type="button" onClick="selectAsset('${p.getRFID()}')" class="view" name="view_asset">CHỌN</button>
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
					
					int pagenum=(int)request.getAttribute("pagenum");
					stt=(int)request.getAttribute("numberofrow")+1;
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
							
							<button <% if(j==pagenum){%> style="background-color: red" <%} else{ %> 
							
							style="background-color: #C0C0C0"
							<%} %>
							 type="submit" id='pagging.btn<%=j%>' class="btn btn-default btn-paging" name="search" onclick="setPage(<%=j%>)">
								<%=j%>
							</button>
						<%
							j++;
									}
								}
						%> 
						
					
					</div>

				</c:if>
				<input type="text" class="form-control" name="Page_id" id="Page_id" value="" style="display: none; height: 30px; margin: 2px;">
				</form>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
	function selectAsset(index)
	{
		var i = opener.document.getElementById('select_id').value;
		opener.document.getElementById('rfid['+i+']').value = index;
		window.close();
		
	}
	function setPage(i)
	{
		document.getElementById("Page_id").value = i;
		x = document.getElementById("pagging.btn" + i);
		x.style.backgroundColor = "silver";
	}
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
		
		x.style.color = "silver";

	}
	//Hàm phân trang cho dữ liệu
	function Pagination() {
		var x = document.getElementById("pagging.btn1");
		/* x.style.backgroundColor = "red";
		x.style.color = "white"; */
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
	
	function ClearData(){
		//Đơn vị
		$('input[name="department_name"]').val("");
		$('input[name="department_cd"]').val("");
		//Nhóm
		$('input[name="group_asset_na"]').val("");
		$('input[name="group_asset_cd"]').val("");
		//Model
		$('input[name="text_model"]').val("");
		//Series
		$('input[name="text_series"]').val("");
		//RFID
		$('input[name="text_rfid"]').val("");
		//Tên
		$('input[name="text_asset_name"]').val("");
		//Mã kế toán
		$('input[name="text_accountant"]').val("");
		//Giá
		$('input[name="text_start_price"]').val("");
		$('input[name="text_end_price"]').val("");
		//Ngày đầu tư
		$('input[name="text_start_date_s"]').val("");
		$('input[name="text_start_date_e"]').val("");
		//Ngày kết thúc
		$('input[name="text_end_date_s"]').val("");
		$('input[name="text_end_date_e"]').val("");
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
	
	$(document).ready(function(){
		$("#CLearDataSearch").click(function(){
			ClearData();
			$('button[name="search"]').click();
		});
	});

	$(function() {
		$('#datetimepicker1').datetimepicker();
	});
</script>
</html>