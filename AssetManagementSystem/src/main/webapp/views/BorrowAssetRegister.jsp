<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ĐĂNG KÝ CHO MƯỢN</title>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="./resources/javascript/message/bootbox.all.js"></script>
<script src="./resources/javascript/message/bootbox.all.min.js"></script>
<script src="./resources/javascript/message/bootbox.js"></script>
<script src="./resources/javascript/message/bootbox.locales.js"></script>
<script src="./resources/javascript/message/bootbox.locales.min.js"></script>
<script src="./resources/javascript/message/bootbox.min.js"></script>
</head>
<style>
.title_input {
	font-weight: 700;
}

textarea {
	resize: none;
	border-radius: 0px !important;
	height: 100px !important;
	border: 1px solid #0E0E0E !important;
}

button.btn_select {
	height: 38px;
	width: 60px;
}

button.btn_clear {
	height: 38px;
	width: 50px;
}
</style>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div
				class="col-sm-12 general-setting w3-hide-small w3-light-grey w3-card-2">
				<form action="BorrowAssetRegister" onsubmit="return confirmNumberNo()" method="POST"
					style="padding: 10px;">
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" name="save" class="btn btn-primary">
								<i class="fa fa-save" style="font-size: 24px"></i>LƯU DỮ LIỆU
							</button>
							<button type="submit" name="back" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
								LẠI
							</button>
						</div>
					</div>
					<jsp:include page="viewcommon/message.jsp"></jsp:include>
					<div style="margin-top: 10px;"></div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">CÔNG TY CHO MƯỢN:<span
									class="require">*</span></label>
								<div class="input-group">
									<input type="text" class="form-control" name="cmpn_cd_master"
										id="cmpn_cd_master"
										value="${asset.getCmpn_master().getCompany_cd()}"
										style="display: contents"> <input type="text"
										class="form-control" style="height: 40px;"
										name="cmpn_na_master" readonly="readonly"
										value="${asset.getCmpn_master().getCompany_name()}"
										id="cmpn_na_master">
									<button type="button" class="select" style="margin-right: 2px;"
										onclick="return openDialogue('PopupCompanyInput?param1=cmpn_cd_master&param2=cmpn_na_master')">
										<i class="fa fa-th-list"></i>
									</button>
									<button type="button" class="select "
										onclick="return clearText('cmpn_cd_master','cmpn_na_master' )">
										<i class="fa fa-eraser" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">ĐƠN VỊ XUẤT TÀI SẢN: <span
								class="require">*</span></label><br>
							<div class="input-group">

								<span class="input-group-addon"> </span> <input type="text"
									style="height: 40px;" class="form-control inputAssetItem"
									value="${asset.getDept_master().getDept_name()}"
									readonly="readonly" name="department_name_master"
									id="department_name_master"> <input type="text"
									class="form-control inputAssetItem" style="display: none;"
									name="department_cd_master"
									value="${asset.getDept_master().getDept_cd()}"
									id="department_cd_master">
								<button type="button" class="select" style="margin-right: 2px;"
									onclick="return openDialogue('GetListDepartment?param1=department_cd_master&param2=department_name_master')">
									<i class="fa fa-th-list"></i>
								</button>
								<button type="button" class="select"
									onclick="return clearText('department_cd_master','department_name_master' )">
									<i class="fa fa-eraser" aria-hidden="true"></i>

								</button>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY XUẤT ĐƠN VỊ: <span
									class="require">*</span></label> <br> <input type="date"
									value="${asset.getDate_start()}" class="form-control"
									name="borrow_date">
							</div>
						</div>



						<div class="col-sm-3">
							<div class="form-group">
								<label class="title_input">TÊN TÀI SẢN: <span
									class="require">*</span></label> <input type="text"
									value="${asset.getAsset().getName()}" class="form-control"
									name="asset_name" id="name_asset">
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<label class="title_input">MÃ RFID:</label> <input type="text"
									value="${asset.getAsset().getRFID()}" class="form-control"
									name="asset_rfid" id="rfid_asset">
							</div>
						</div>


						<div class="col-sm-3">
							<div class="form-group">
								<label class="title_input">MODEL: </label><input type="text"
									value="${asset.getAsset().getModel()}" class="form-control"
									name="asset_model" id="model_asset">
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<label class="title_input">SỐ SERIES:<span
									class="require">*</span></label> <input type="text"
									value="${asset.getAsset().getSeries()}" id="series_asset"
									class="form-control" name="asset_series">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label  style="float: initial; " class="title_input">MÃ LỆNH:<span class="require">*</span></label><br>
								<div style="width:100%">
								<input style="width:47%;  float:left; margin-right:10px;" id="numberNo" type="text" value="${asset.getNumber_on() }"
									class="form-control" name="number_on"> 
								<select style="width:47%; float:left;"  id="selectNumber" onchange="return changeSelectNumber();" class="form-control">
									<option value="M" selected>M</option>
									<option value="CM">CM</option>
									<option value="TH">TH</option>
									<option value="T">T</option>
									<option value="BG" >BG</option>
								</select>
								</div>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<label class="title_input">LẤY THÔNG TIN TÀI SẢN: <span
									class="require">*</span></label><br>
								<div class="form-group">
									<button type="submit" class="select " name="getImfor"
										style="width: 200px">LẤY THÔNG TIN</button>
								</div>
							</div>
						</div>


						<div class="col-sm-5"></div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">CÔNG TY MƯỢN: <span
									class="require">*</span></label>
								<div class="input-group">
									<input type="text" class="form-control" name="cmpn_cd_client"
										id="cmpn_cd_client"
										value="${asset.getCmpn_client().getCompany_cd()}"
										style="display: contents"> <input type="text"
										class="form-control" style="height: 40px;"
										name="cmpn_na_client" readonly="readonly"
										value="${asset.getCmpn_client().getCompany_name()}"
										id="cmpn_na_client">
									<button type="button" class="select" style="margin-right: 2px;"
										onclick="return openDialogue('PopupCompanyInput?param1=cmpn_cd_client&param2=cmpn_na_client')">
										<i class="fa fa-th-list"></i>
									</button>
									<button type="button" class="select "
										onclick="return clearText('cmpn_cd_client','cmpn_na_client' )">
										<i class="fa fa-eraser" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">ĐƠN VỊ NHẬN TÀI SẢN: </label><br>
							<div class="input-group">

								<span class="input-group-addon"> </span> <input type="text"
									style="height: 40px;" class="form-control inputAssetItem"
									value="${asset.getDept_client().getDept_name()}"
									readonly="readonly" name="department_name_client"
									id="department_name_client"> <input type="text"
									class="form-control inputAssetItem" style="display: none;"
									name="department_cd_client"
									value="${asset.getDept_client().getDept_cd()}"
									id="department_cd_client">
								<button type="button" class="select" style="margin-right: 2px;"
									onclick="return openDialogue('GetListDepartment?param1=department_cd_client&param2=department_name_client')">
									<i class="fa fa-th-list"></i>
								</button>
								<button type="button" class="select"
									onclick="return clearText('department_cd_client','department_name_client' )">
									<i class="fa fa-eraser" aria-hidden="true"></i>
								</button>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY TRẢ TÀI SẢN :<span
									class="require">*</span></label> <br> <input type="date"
									value="${asset.getDate_end()}" class="form-control"
									name="pay_date_schedual">
							</div>
						</div>


						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">LÝ DO CHO MƯỢN TÀI SẢN: <span
									class="require">*</span></label> <br>
								<textarea type="date" class="form-control" name="borrow_reason">${asset.getReason()}</textarea>
							</div>
						</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

function changeSelectNumber()
{
	var numberNo = document.getElementById("numberNo");
	var selectNumber = document.getElementById("selectNumber");
	
	 var res = numberNo.value .split("/");
	if(res.length=2)
	{
		numberNo.value = res[0] + "/" +selectNumber.value;
	} else
	{
		numberNo.value = numberNo.value + "/" +selectNumber.value;
	}
	
	
	
}

function confirmNumberNo()
{
	if(confirm("Bạn đã chọn mã lệnh chưa?"))
	{
		return true;
	} else {
		return false
	}
}


	// 	$(document)
	// 			.ready(
	// 					function() {
	// 						$('#series_asset')
	// 								.change(
	// 										function() {
	// 											var series = document
	// 													.getElementById("series_asset").value;
	// 											$
	// 													.ajax({
	// 														crossDomain : true,
	// 														type : 'GET',
	// 														url : 'http://asset.viettien.com.vn:8081/AssetManagementSystem/getAssetBySeries?series='
	// 																+ series,
	// 														success : function(
	// 																result) {
	// 															var str = result;
	// 															if (str.length > 3) {
	// 																var res = str
	// 																		.split("_");
	// 																if (res.length = 3) {
	// 																	document
	// 																			.getElementById("name_asset").value = res[0];
	// 																	document
	// 																			.getElementById("model_asset").value = res[1];
	// 																	document
	// 																			.getElementById("rfid_asset").value = res[2];
	// 																} else {
	// 																	alert("KHÔNG TÌM THẤY TÀI SẢN CÓ SỐ SERIES NÀY");
	// 																	document
	// 																			.getElementById("name_asset").value = "";
	// 																	document
	// 																			.getElementById("model_asset").value = "";
	// 																	document
	// 																			.getElementById("rfid_asset").value = "";
	// 																}
	// 															}

	// 														},
	// 														error : function(
	// 																request,
	// 																status, error) {
	// 															alert("KHÔNG TÌM THẤY TÀI SẢN CÓ SỐ SERIES NÀY"
	// 																	+ error);
	// 														}

	// 													});

	// 										});
	// 					});
<%-- </script> --%>
	
</script>
</html>