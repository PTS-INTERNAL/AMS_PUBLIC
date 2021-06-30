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

</style>
<body onload="Pagination()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin-top: 10px; padding: 0px; margin: 10px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="ExportAssetCompanyInternal" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
<!-- 									<td class="title">Tên Tài Sản</td> -->
<!-- 								<td><input class="selectList" -->
<%-- 									value="${formSearch.getName()}" list="text_asset_name" --%>
<!-- 									name="text_asset_name"> <datalist -->
<!-- 										class="selectList-item" id="text_asset_name"> -->
<%-- 										<c:forEach var="ps" items="${listAssets}"> --%>
<%-- 											<option value="${ps.getName()}"> --%>
<%-- 										</c:forEach> --%>
<!-- 									</datalist></td> -->
<!-- 								<td class="title">Model</td> -->
<%-- 								<td><input value="${formSearch.getModel()}" --%>
<!-- 									class="selectList" list="text_model" name="text_model"> -->
<!-- 									<datalist class="selectList-item" id="text_model"> -->
<%-- 										<c:forEach var="ps" items="${listAssets}"> --%>
<%-- 											<option value="${ps.getModel()}"> --%>
<%-- 										</c:forEach> --%>
<!-- 									</datalist></td> -->
								<td class="title" style="width:185px;">Tên báo cáo</td>
								<td><input value="${reportName}"
									class="selectList" list="text_series" name="reportName">
									<datalist id="text_series" class="selectList-item">
										<c:forEach var="ps" items="${listAssets}">
											<option value="${ps.getSeries()}">
										</c:forEach>
									</datalist></td>
							</tr>
							
<!-- 							<tr> -->
<!-- 								<td class="title">DOANH NGHIỆP</td> -->
<!-- 								<td colspan="4"> -->
<%-- 								<c:if test="${lst.size() > 0}">  --%>
<%--  								<c:forEach var="itemCmpn" items="${lst}">  --%>
<%--  								<label style="width:100px; margin-bottom:0px; text-align: center;"><input type="checkbox"/><span style="font-weight:700">${itemCmpn.getCompany_shortname()}</span></label>  --%>
<%--  								</c:forEach>  --%>
<%-- 								</c:if>  --%>
															
								
<!-- 								</td> -->

<!-- 							</tr> -->
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
				<c:if test="${lst.size() > 0}">
					
					<table id="table.data" style="margin-top: 10px"
						class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								<th rowspan="2" style="width: 50px">STT</th>
								<th rowspan="2" style="width: 50px">NHÓM</th>
								<th rowspan="2" >TÊN THIẾT BỊ</th>
								<th rowspan="2" >KÝ HIỆU</th>
								<th colspan="${lst.size()}" >SỐ LƯỢNG</th>
								
								<th rowspan="2" style="width:115px;" >TỔNG CỘNG</th>
							</tr>
							<tr>
								
								<c:forEach var="itemCmpn" items="${lst}">
								<th style="width:120px;" >${itemCmpn.getDept_name()}</th>
								</c:forEach>
								
								
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="itemAsset" items="${lstAsset}">
							<tr>
								<td><%=stt%></td>
								<td>${itemAsset.getAsset().getGroup_name()}</td>
								<td>${itemAsset.getAsset().getName()}</td>
								<td>${itemAsset.getAsset().getModel()}</td>
								<c:forEach var="itemCmpns" items="${itemAsset.getLstColumn()}">
								
								
								<td style="text-align:center " >${itemCmpns}</td>
								
								
								
								</c:forEach>
								<td style="text-align:center ">${itemAsset.getSum()}</td>
							</tr>
								<%stt++;%>
							</c:forEach>
							
							<tr style="border: 3px solid black;">
								<td></td>
								<td colspan="3" style="text-align: center; color: red; font-weight: 700">TỔNG CỘNG</td>
								<c:forEach var="pi" items="${lstsum}">
								<td style="text-align:center " >${pi}</td>
								</c:forEach>
								<td style="text-align:center ">${sumToal}</td>
							</tr>
							
							
							
						</tbody>
					</table>
					<div class="text-right">
						<%
							if (stt > 19) {
									double countPage = stt / 19;
									if (stt % 19 > 0) {
										countPage += 1;
									}
									int j = 1;
									while (j <= countPage) {
										int startIndex = j * 19 - 18;
										int endIndex = startIndex + 18;
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
		for (var i = 2; i < x; i++) {
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
				document.getElementById("table.data").rows[i+1].style.display = 'none';
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