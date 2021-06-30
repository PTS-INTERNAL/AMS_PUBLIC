<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>KHAI BÁO TÀI SẢN CHO MƯỢN</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<style type="text/css">
.title-table {
	width: 130px;
}

.content_table {
	font-size: 20px;
}

* {
	font-family: "Segoe UI", Arial, sans-serif !important;
}

table.table-data {
	margin-bottom: 0px !important;
	margin-top: -2px !important;
}

table.table-data th {
	background-color: #bdc6e2;
}

table.table-data th, table.table-data td {
	vertical-align: middle;
}

button.btnduyet {
	height: 33px;
	margin-top: -6px;
	font-size: 16px;
	font-weight: 700;
}
</style>
</head>
<body>

	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div class="col-sm-12 general-setting w3-hide-small w3-light-grey w3-card-2">
				<form action="LiquidAssetDeclare" method="POST">
					<input type="text" class="form-control" style="height: 40px; display: none;" name="coupon_cd" readonly="readonly" value="${coupon.getCoupon_cd()}">
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" name="reportExcel" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>XUẤT EXEL
							</button>
							<button type="submit" name="save" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>LƯU DỮ LIỆU
							</button>
							<button type="submit" name="back" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY LẠI
							</button>
						</div>
					</div>

					<jsp:include page="viewcommon/message.jsp"></jsp:include>
					<div style="margin-top: 10px;"></div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MÃ DANH SÁCH:</label>
								<div class="input-group">
									<input type="text" class="form-control" style="height: 40px;" name="List_id" readonly="readonly" value="${List.getList_id()}" id="List_id">
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">TÊN DANH SÁCH </label>
								<div class="input-group">
									<input type="text" class="form-control" style="height: 40px;" name="cmpn_na_client" readonly="readonly" value="${List.getListName()}" id="cmpn_na_client">

								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGƯỜI TẠO DANH SÁCH:</label> <input type="text" value="${List.getUserCreate()}" class="form-control" readonly="readonly" name="user_insert">
							</div>
						</div>
						

						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY TẠO DANH SÁCH:</label> <input type="text" value="${List.getDateCreate()}" class="form-control" readonly="readonly" name="number_on">
							</div>
						</div>
						

					</div>
					<table id="table.data" style="margin-top: 0px" class="table   table-data" style="margin-top: 10px">
						<thead>
							<tr>
								<th>STT</th>
								<th style="width: 270px;">MÃ RFID</th>
								<th style="width: 190px;">MODEL</th>
								<th style="width: 190px;">SERIES</th>
								<th>TÊN TÀI SẢN</th>
								<th style="width: 190px;">ĐƠN VỊ</th>
								<th style="width: 35%;">TRẠNG THÁI</th>
								
								<th style="width: 60px;"></th>

							</tr>
						</thead>
						<tbody>
							<%
								int stt = 1;
							%>
							<c:set var="startIndex" scope="page" value="1" />
							<c:set var="endIndex" scope="page" value="${number_row}" />
							<c:forEach begin="${startIndex}" end="${endIndex}" step="1" var="index">
								<tr>
									<td style="text-align: center;"><%=stt%></td>
									<td style="text-align: center;">
										<input type="text" class="form-control" name="Liquid[<%=stt%>]_cd" value="${lstLiquid.get(index-1).getLiquidation_Cd()}" maxlength="100" style="display: none; height: 30px; margin: 2px;"> 
										<input type="text" class="form-control" list="rfid[<%=stt%>]" name="rfid[<%=stt%>]" value="${lstLiquid.get(index-1).getAsset().getRFID()}" style="display: block; height: 30px; margin: 2px;">
										<datalist style="text-align: left;" class="selectList-item"  id="rfid[<%=stt%>]">
											<%-- <c:forEach var="ps" items="${listAssetSearch}">
												<option value="${ps.getRFID()}">
											</c:forEach> --%>
											
										</datalist>
									</td>
									<td style="text-align: center;">
										<input type="text" class="form-control" list="model[<%=stt%>]" name="model[<%=stt%>]" value="${lstLiquid.get(index-1).getAsset().getModel()}" style="display: block; height: 30px; margin: 2px;">
										<datalist class="selectList-item"  id="model[<%=stt%>]">
											<%-- <c:forEach var="ps" items="${listAssetSearch}">
												<option value="${ps.getModel()}">
											</c:forEach> --%>
										</datalist>
									</td>
									<td style="text-align: center;">
										<input type="text" class="form-control" list="series[<%=stt%>]"  name="series[<%=stt%>]" value="${lstLiquid.get(index-1).getAsset().getSeries()}" style="display: block; height: 30px; margin: 2px;">
										<datalist class="selectList-item"  id="series[<%=stt%>]">
											<%-- <c:forEach var="ps" items="${listAssetSearch}">
												<option value="${ps.getSeries()}">
											</c:forEach> --%>
											
										</datalist>
									</td>
									<td style="text-align: center;">
										<input type="text" class="form-control" list="name[<%=stt%>]" name="name[<%=stt%>]" value="${lstLiquid.get(index-1).getAsset().getName()}" style="display: block; height: 30px; margin: 2px;">
								
										<datalist class="selectList-item"  id="name[<%=stt%>]">
											<%-- <c:forEach var="ps" items="${listAssetSearch}">
												<option value="${ps.getName()}">
											</c:forEach> --%>
											
										</datalist>
									</td>
									<td style="text-align: center;">
										
										<input type="text" class="form-control" list="dept[<%=stt%>]" name="dept[<%=stt%>]" value="${lstLiquid.get(index-1).getAsset().getDepartment_name()}" style="display: block; height: 30px; margin: 2px;">
	
										<datalist class="selectList-item"  id="dept[<%=stt%>]">
											<%-- <c:forEach var="ps" items="${listAssetSearch}">
												<option value="${ps.getDepartment_name()}">
											</c:forEach> --%>
											<%-- <option value="${coupon.getDept_client().getDept_name()}">
											 --%>
										</datalist>	
										
									</td>
									<td style="text-align: center;">
										
										<input type="text" class="form-control" list="status[<%=stt%>]" name="status[<%=stt%>]" value="${lstLiquid.get(index-1).getStatus()}" style="display: block; height: 30px; margin: 2px;">
	
				
										
									</td>
									
									
									<td>
										<button type="submit" class="view" name="delete" onclick="setIdDelete(<%=stt%>) ">XÓA</button>
									</td>

								</tr>
								<%
									stt++;
								%>
							</c:forEach>
						</tbody>
					</table>
					<input type="text" class="form-control" name="delete_id" id="delete_id" value="" style="display: none; height: 30px; margin: 2px;">
					
					<button type="submit" name="GetImfor" class="btn btn-primary btn-getImfor">LẤY DỮ LIỆU</button>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function setIdDelete(i,stt)
{
	document.getElementById("delete_id").value = i;
	
}
</script>
</html>