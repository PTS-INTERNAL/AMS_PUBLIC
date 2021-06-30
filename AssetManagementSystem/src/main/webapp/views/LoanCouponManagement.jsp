<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ CHO MƯỢN</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script>
<script src="./resources/javascript/message/bootbox.all.js"></script>
<script src="./resources/javascript/message/bootbox.all.min.js"></script>
<script src="./resources/javascript/message/bootbox.js"></script>
<script src="./resources/javascript/message/bootbox.locales.js"></script>
<script src="./resources/javascript/message/bootbox.locales.min.js"></script>
<script src="./resources/javascript/message/bootbox.min.js"></script>
<style type="text/css">
#number_no {
	text-transform: uppercase;
}
</style>
</head>
<body onload="Pagination(); setStatus();">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="LoanCouponManagement" method="POST">
					<table class="table   table-search">
						<thead>
							<tr>
								<td class="title">ĐƠN VỊ MƯỢN</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> 
										<input type="text" style="height: 30px;" class="form-control inputAssetItem" value="${coupon.getDept_master().getDept_name()}" readonly="readonly" name="department_name_master" id="department_name_master"> <input type="text" class="form-control inputAssetItem" style="display: none;" name="department_cd_master" value="${coupon.getDept_master().getDept_cd()}" id="department_cd_master">
										<button type="button" class="select" style="height: 30px; margin-right: 2px;" onclick="return openDialogue('GetListDepartment?param1=department_cd_master&param2=department_name_master')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;" onclick="return clearText('department_cd_master','department_name_master' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>
										</button>
									</div></td>
								<td class="title">CÔNG TY CHO MƯỢN</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input type="text" style="height: 30px;" class="form-control inputAssetItem" value="${coupon.getCmpn_client().getCompany_name()}" readonly="readonly" name="cmpn_na" id="cmpn_na"> <input type="text" class="form-control inputAssetItem" style="display: none;" name="cmpn_cd" value="${coupon.getCmpn_client().getCompany_cd()}" id="cmpn_cd">
										<button type="button" class="select" style="height: 30px; margin-right: 2px;" onclick="return openDialogue('PopupCompanyInput?param1=cmpn_cd&param2=cmpn_na')">
											<i class="fa fa-th-list"></i>
										</button>
										<button type="button" class="select" style="height: 30px;" onclick="return clearText('cmpn_cd','cmpn_na' )">
											<i class="fa fa-eraser" aria-hidden="true"></i>
										</button>
									</div></td>
								<td class="title">Ngày cho mượn</td>
								<td colspan="3"><input value="${coupon.getDate_start()}" style="width: 45%" type="date" data-date-format="DD/MM/YYYY" name="text_start_date"> ~ <input value="${coupon.getDate_start_e()}" style="width: 45%" type="date" name="text_end_date"></td>
							</tr>
							<tr>
								<td class="title">LỆNH ĐIỀU ĐỘNG</td>
								<td><div class="input-group">
										<span class="input-group-addon"> </span> <input type="text" style="height: 30px;" class="form-control inputAssetItem" value="${coupon.getNumber_no()}" id="number_no" name="number_no">
									</div></td>
								<td class="title">TRẠNG THÁI</td>
								<td><select style="padding: 0px; border: 1px solid black;" class="form-control" name="status_coupon">
										<option selected="selected" value="9999">TẤT CẢ</option>
										<option value="1">CHỜ DUYỆT</option>
										<option value="2">ĐÃ DUYỆT</option>
										<option value="0">KHÔNG DUYỆT</option>
										<option value="3">ĐÃ TRÃ</option>
										<option value="4">TRỄ HẠN</option>
								</select></td>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" style="border-radius: 0" class="btn btn-primary" name="search">
								<i style="font-size: 24px" class="fa">&#xf067;</i>TÌM KIẾM
							</button>
							<button type="submit" style="border-radius: 0" class="btn btn-primary" name="returnAsset">
								<i style="font-size: 24px" class="fa">&#xf067;</i>TRẢ TÀI SẢN
							</button>
							<button type="submit" style="border-radius: 0" name="reportExcel" class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT EXCEL
							</button>
							<!-- 							<button type="submit" style="border-radius: 0" name="reportPDF" -->
							<!-- 								class="btn btn-primary"> -->
							<!-- 								<i style="font-size: 24px" class="fa">&#xf1c3;</i>XUẤT PDF -->
							<!-- 							</button> -->
							<button type="submit" style="border-radius: 0" name="back" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i></i>QUAY LẠI
							</button>
						</div>
					</div>
				</form>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				<c:if test="${lst.size() > 0}">
					<table id="table.data" style="margin-top: 0px" class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								<th style="width: 40px;">STT</th>
								<th style="width: 100px;">MÃ LỆNH</th>
								<th>ĐƠN VỊ MƯỢN</th>
								<th>DOANH NGHIỆP CHO MƯỢN</th>
								<th style="width: 180px;">NGÀY CHO MƯỢN</th>
								<th style="width: 200px;">NGÀY TRẢ KẾ HOẠCH</th>
								<th style="width: 200px;">NGÀY TRẢ THỰC TẾ</th>
								<th>NGƯỜI DUYỆT</th>
								<th style="width: 160px">KHAI BÁO</th>
								<th style="width: 198px;">PHÊ CHUẨN</th>
								<th style="width: 60px;">TÁC VỤ</th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:forEach var="p" items="${lst}">
								<tr>
									<td style="text-align: center;"><%=stt%></td>
									<td>${p.getNumber_no()}</td>
									<td>${p.getDept_master().getDept_name() }</td>
									<td>${p.getCmpn_client().getCompany_shortname()}</td>
									<td align="right">${p.getDate_start()}</td>

									<td align="right">${p.getDate_end_schedule()}</td>
									<td align="right">${p.getDate_end_real()}</td>
									<td align="right">${p.getUser_approve_name()}</td>
									<td align="center">
										<c:if test="${p.getStatus()=='1'}">
											<form action="LoanAssetDeclare" method="post" style="width: 160px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button type="submit" class="view" style="width: 160px; margin: auto; background-color: blue;" name="declare">KHAI BÁO TS</button>
											</form>
										</c:if>
										
										<c:if test="${p.getStatus()=='3'}">
											<form action="LoanCouponReturnDeptApprove" method="post" style="width: 160px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button type="submit" class="view" style="width: 160px; margin: auto; background-color: blue;" name="init">TRẢ TÀI SẢN</button>
											</form>
										</c:if>
									</td>
									<td style="width: 160px;"><c:if test="${p.getStatus()=='1'}">
											<form action="LoanCouponManagmentDeptApprove" method="post" style="width: 190px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button style="width: 190px; margin: auto; background-color: orange" type="submit" class="view" name="init">XÁC NHẬN MƯỢN TS</button>
											</form>
										</c:if> <c:if test="${p.getStatus()=='2'}">
											<form action="LoanCouponManagmentDeptApprove" method="post" style="width: 190px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button style="width: 190px; margin: auto; background-color: yellow; color: black;" type="submit" class="view" name="approveExport">XÁC NHẬN KẾ TOÁN</button>
											</form>
										</c:if> <c:if test="${p.getStatus()=='3'}">
											<form action="LoanCouponManagmentDeptApprove" method="post" style="width: 190px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button style="width: 190px; margin: auto; background-color: yellow; color: red;" type="submit" class="view" name="approveExport">ĐANG MƯỢN</button>
											</form>
										</c:if> <c:if test="${p.getStatus()=='4'}">
											<form action="LoanCouponManagmentDeptApprove" method="post" style="width: 190px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button style="width: 190px; margin: auto; background-color: yellow; color: red;" type="submit" class="view" name="approveExport">KẾ TOÁN XN TRẢ</button>
											</form>
										</c:if> <c:if test="${p.getStatus()=='5'}">
											<form action="LoanCouponManagmentDeptApprove" method="post" style="width: 190px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button style="width: 190px; margin: auto; background-color: yellow; color: red;" type="submit" class="view" name="approveExport">KHO VẬN DUYỆT</button>
											</form>
										</c:if> 
										
										<c:if test="${p.getStatus()=='6'}">
											<form action="LoanCouponManagmentDeptApprove" method="post" style="width: 190px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button style="width: 190px; margin: auto; background-color: Green; color: white;" type="submit" class="view" name="approveExport">ĐANG TRẢ</button>
											</form>
										</c:if>
										<c:if test="${p.getStatus()=='7'}">
											<form action="LoanCouponManagmentDeptApprove" method="post" style="width: 190px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button style="width: 190px; margin: auto; background-color: Green; color: white;" type="submit" class="view" name="approveExport">ĐÃ TRẢ</button>
											</form>
										</c:if>
										
										<c:if test="${p.getStatus()=='0'}">
											<form action="LoanCouponManagmentDeptApprove" method="post" style="width: 190px;">
												<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
												<button style="width: 190px; margin: auto; background-color: red; color: white" type="submit" class="view" name="init">KHÔNG DUYỆT</button>
											</form>
										</c:if> <c:if test="${p.getStatus()=='9'}">
											<span class="view" style="width: 190px; background-color: red">KẾ TOÁN KHÔNG DUYỆT</span>
										</c:if></td>

									<td>
										<form action="BorrowCouponManagement" method="post" style="width: 60px;">
											<input style="display: none;" name="coupon_cd" value="${p.getCoupon_cd()}">
											<c:choose>
											    <c:when test="${p.getStatus() == '1'}">
											       <button type="submit" class="view" name="delete">XÓA</button>
											    </c:when>
											    <c:when test="${p.getStatus() == '0'}">
											     	<button type="submit" class="view" name="delete">XÓA</button> 
											    </c:when>
											    <c:otherwise>
											        <button type="submit" class="view" disabled="disabled" style="background-color: gray;" name="delete">XÓA</button>
											    </c:otherwise>
											</c:choose>
											
											
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
						<a class="btn btn-default btn-paging" id='pagging.btn<%=j%>' onclick="movePage('<%=startIndex%>','<%=endIndex%>', '<%=j%>', '<%=countPage%>')"><%=j%></a>
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
	function setStatus()
	{
		var status = '9999';
		if(${coupon.getStatus()} !=null)
		{
			status = ${coupon.getStatus()};
		}
		document.getElementsByName('status_coupon')[0].value=status;
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
		if(x != null && x != 'undefined')
		{
			x.style.backgroundColor = "red";
			x.style.color = "white";
			x = document.getElementById("table.data").rows.length;
			if (x > 10) {
				for (var i = 10; i < x; i++) {
					document.getElementById("table.data").rows[i + 1].style.display = 'none';
				}

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

</script>
</html>