<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XEM THÔNG TIN</title>
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


<body >
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div  style="margin-top: 10px; padding: 0px;  margin: 10px;">
		<div class="row">
			<div class="col-sm-12 general-setting w3-hide-small w3-light-grey w3-card-2">
				<form action="AssetGeneralView" method="POST">
					<div class="title-feature">
						<div class="text-right">
							<input type="text" value="${asset.getRFID()}"
								style="display: none;" name="rfid_code">
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="update">
								<i style='font-size: 24px' class='far'>&#xf044;</i> CHỈNH SỬA
							</button>
							<button type="submit" style="border-radius: 0"
								class="btn btn-primary" name="delete">
								<i style='font-size: 24px' class='fas'>&#xf2ed;</i>XÓA
							</button>
							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size:24px"></i>QUAY LẠI
							</button>
						</div>
					</div>
				</form>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				<c:if test="${asset!=null}">
						
					<div class="row">
						<div class="col-sm-4">
							<label class="title_input">NHÓM TÀI SẢN:<span class="require">*</span></label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text"
									class="form-control inputAssetItem" readonly="readonly" name="group_asset_na"
									id="group_asset_na" value="${asset.getGroup_name() }"> <input type="text"
									class="form-control inputAssetItem" style="display: none;"
									name="group_asset_cd"  value="${asset.getGroup_id() }" id="group_asset_cd">
								<button type="button" class="select"  disabled="disabled"
									onclick="return openDialogue('GetListGroupAsset')">
									<i class="fa fa-th-list"></i>
								</button>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">TÊN TÀI SẢN:<span class="require">*</span></label> <input
									type="text"  value="${asset.getName() }" readonly="readonly" class="form-control" name="asset_name">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MÃ RFID:<span class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly" value="${asset.getRFID() }" name="asset_rfid">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MODEL:<span class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly" value="${asset.getModel() }" name="asset_model">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">SỐ SERIES:<span class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly" value="${asset.getSeries() }" name="asset_series">
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">ĐƠN VỊ:<span class="require">*</span></label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text"
									class="form-control inputAssetItem"  value="${asset.getDepartment_name() }" readonly="readonly" name="department_name"
									id="department_name" > 
									<input type="text"
									class="form-control inputAssetItem" style="display: none;"
									name="department_cd"   value="${asset.getDepartment_cd() }" id="department_cd">
								<button type="button" class="select" disabled="disabled"
									onclick="return openDialogue('GetListDepartment?param1=department_cd&param2=department_name')">
									<i class="fa fa-th-list"></i>
								</button>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">KHÁCH HÀNG:</label> <input type="text"
									class="form-control" readonly="readonly"  value="${asset.getCustomer() }" name="asset_customer">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">SỐ HỢP ĐỒNG:</label> <input type="text"
									class="form-control" readonly="readonly" value="${asset.getContract() }" name="asset_contract">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MÃ KẾ TOÁN:<span class="require">*</span></label> <input
									type="text" class="form-control" readonly="readonly" value="${asset.getAccountant_CD() }" name="asset_accountant">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">THỜI ĐIỂM TĂNG (MM/DD/YYYY):<span class="require">*</span></label> <input
									type="date" class="form-control" readonly="readonly"  value="${asset.getDateStart() }" name="asset_date">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">HẠN SỬ DỤNG (MM/DD/YYYY):</label> <input
									type="date" class="form-control" readonly="readonly" value="${asset.getDateEnd() }" name="asset_date_end">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">ĐƠN GIÁ:<span class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly"  value="${asset.getPrice() }" name="asset_price">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">SỐ CHỨNG TỪ:</label> <input type="text"
									class="form-control" readonly="readonly"  value="${asset.getOriginal().trim() }" name="asset_original">
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">THỜI HẠN BẢO TRÌ:<span class="require">*</span></label>
							<div class="input-group">
								 <input type="number"
									class="form-control" readonly="readonly"  value="${asset.getMaintaince().trim() }" min="1" name="asset_maintaince"> <span style="line-height:35px;margin-left:10px;" class="title_input">THÁNG</span>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input" >GHI CHÚ:</label>
								<textarea style="resize: none;" class="form-control" readonly="readonly"  name="asset_note">${asset.getNote() }</textarea>
							</div>
						</div>  
<!-- 						<div class="col-sm-12"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<label class="title_input" >LỊCH SỬ DI CHUYỂN TÀI SẢN:</label> -->
<!-- 								<table class="table   table-data"> -->
<!-- 									<thead> -->
<!-- 										<th style="width:200px;">Thời gian</th> -->
<!-- 										<th>Nội dung</th> -->
<!-- 										<th style="width:300px">Ghi chú</th> -->
<!-- 									</thead> -->
<!-- 									<tbody> -->
<!-- 										<td></td> -->
<!-- 										<td></td> -->
<!-- 										<td></td> -->
<!-- 									</tbody> -->
<!-- 								</table> -->
								
<!-- 							</div> -->
<!-- 						</div> -->
						<c:if test="${lstHis!=null}">
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input" >LỊCH SỬ CHỈNH SỬA TÀI SẢN:</label>
								<table class="table   table-data">
									<thead>
										<th style="width:200px;">Thời gian</th>
										<th>Nội dung</th>
										<th style="width:300px">Người chỉnh sửa</th>
									</thead>
									<tbody>
									<c:forEach var="p" items="${lstHis}">
									<tr>
										<td>${ p.getDate()}</td>
										<td>${ p.getReason()}</td>
										<td>${p.getUser().getName()}</td>
									</tr>
									</c:forEach>
										
									</tbody>
								</table>
								
							</div>
						</div>
						</c:if>
					</div>

				</c:if>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

</script>

</html>