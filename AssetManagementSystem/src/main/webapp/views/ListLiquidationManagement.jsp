<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ SỰ CỐ</title>
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
</style>
<body onload="Pagination()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin-top: 10px; padding: 0px; margin: 10px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="AssetLiquidationManagement" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								
								<td class="title">Mã DANH SÁCH</td>
								<td><input value="${ListSearchCondition.getList_id()}"
									class="selectList" name="text_list_id">
									</td>
								<td class="title">TÊN DÁNH SÁCH</td>
								<td><input value="${ListSearchCondition.getListName()}"
									class="selectList" name="text_list_name">
									</td>
									
							</tr>
							<tr>
								<td class="title">NGƯỜI TẠO</td>
								<td><input class="selectList"
									value="${ListSearchCondition.getUserCreate()}" 
									name="text_user_create">
								</td>
								<td class="title">NGÀY TẠO</td>
								<td colspan="3"><input
									value="${ListSearchCondition.getDateCreate() }" style="width: 45%"
									type="date" data-date-format="DD/MM/YYYY"
									name="text_date_create">
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
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="register" >
								<i style="font-size: 24px" class="fa">&#xf067;</i>THÊM MỚI
							</button>
							<button type="submit" style="border-radius: 0" name="reportExcel"
								class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT EXCEL
							</button>
							<button type="submit" style="border-radius: 0" name="search"
								class="btn btn-primary">
								<i class="fa fa-file-pdf-o" style="font-size: 24px"></i>TÌM KIẾM
							</button>
							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
								LẠI
							</button>
						</div>
					</div>
					<jsp:include page="viewcommon/message.jsp"></jsp:include>
			
				
				<c:if test="${ListLstSearch.size() > 0}"> 
					<table id="table.data" style="margin-top: 7px"
						class="table   table-data" >
						<thead>
							<tr>
								<th style="width: 8%"> STT</th>
								<th style="width: 10%">LIST_ID</th>
								<th style="width: 350px">TÊN DANH SÁCH</th>
								<th style="width: 160px">NGÀY TẠO</th>
								<th style="width: 200px">NGƯỜI TẠO</th>
								<th style="width: 140px">KHAI BÁO</th>
								<th style="width: 198px;">PHÊ CHUẨN</th>
								<th style="width: 60px;">TÁC VỤ</th>
								

							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${ListLstSearch}">
							<tr>

									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getList_id()}</td>
									<td>${p.getListName()}</td>
									<td>${p.getDateCreate()}</td>
									<td>${p.getUserCreate()}</td>
									<td>
									
											<form action="LiquidAssetDeclare" method="post" style="width: 160px;">
												<input style="display: none;" name="List_id" value="${p.getList_id()}">
												
												<button type="submit" class="view" style="width: 160px; margin: auto; background-color: blue;" 
												name="declare">KHAI BÁO TS</button>
											</form>
										
									</td>
								
									<td>
										<form action="LiquidAssetApprove" method="post"
												style="width: 160px;">
												<input style="display: none;" name="borrow_cd"
													value="}">
												<button
													style="width: 200px; margin: auto; background-color: orange"
													type="submit" class="view" name="view">CHƯA PHÊ
													DUYỆT
												</button>
											</form>
										<%-- <label style="border:1px solid">
											<a href="AssetTroubleEdit?TRID=${p.getTroubleID()}" class="center">Edit</a>
										</label> 
										
										<label style="border:1px solid">
											<a onclick="return myFunction()" href="AssetTroubleDelete?TRID=${p.getTroubleID()}"> Delete</a>
										</label>  --%>
									</td>
									<td>
									

									<button type="submit" class="view" name="delete" onclick="setIdDelete(${p.getList_id()}) ">XÓA</button>
									</td>
									
								</tr>
								<%
									stt++;
								%>
							</c:forEach>
						</tbody>
					</table>
					
					<input type="text" class="form-control" name="delete_id" id="delete_id" value="" style="display: none; height: 30px; margin: 2px;">
					
				</c:if>
				</form>
				
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

function setIdDelete(i)
{
	document.getElementById("delete_id").value = i;
	
}
	function myFunction() {
		if (confirm("Bạn có chắc muốn chuyển !"))
		{
			return true;
		}
		else {
			return false;
		}
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
			for (var i = 19; i < x; i++) {
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