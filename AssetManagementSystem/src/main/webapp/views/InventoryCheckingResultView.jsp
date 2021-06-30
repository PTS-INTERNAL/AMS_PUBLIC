<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>XEM THÔNG TIN TÀI SẢN THANH LÝ</title>
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
.title_input {
	font-weight: 700;
}

textarea {
	resize: none;
	border-radius: 0px !important;
	height: 100px !important;
	border: 1px solid #0E0E0E !important;
}
</style>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div
				class="col-sm-12 general-setting w3-hide-small w3-light-grey w3-card-2">
				<form action="InventoryCheckingResultView" method="POST">
					<div class="title-feature">
						<div class="text-right">
						<input type="text"
									value="${asset.getIvn_Session_Cd()}" style="display: none;" readonly="readonly"
									class="form-control" name="session_cd">
									<c:if test="${isExplain!='0'}">
									<c:if test="${asset.getStatus()!='1'}">
							<button type="button" data-toggle="modal"
								data-target="#exampleModalCenter" name="save"
								class="btn btn-primary">
								<i class="fa fa-save" style="font-size: 24px"></i>GIẢI TRÌNH
							</button>
							</c:if>
							</c:if>
<%-- 							<c:if test="${isUpdate!='0'}"> --%>
<!-- 							<button type="submit" name="save" class="btn btn-primary"> -->
<!-- 								<i class="fa fa-save" style="font-size: 24px"></i>CHỈNH SỬA -->
<!-- 							</button> -->
<%-- 							</c:if> --%>
							<button type="submit" name="back" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY
								LẠI
							</button>
						</div>
					</div>
					<jsp:include page="viewcommon/message.jsp"></jsp:include>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGƯỜI KIỂM KÊ:<span
									class="require">*</span></label> <input type="text"
									value="${asset.getAsset().getUser_insert_cd()}" readonly="readonly"
									class="form-control" name="">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY KIỂM KÊ:<span
									class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly"
									value="${asset.getAsset().getInsert_dt()}" name="">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">TRẠNG THÁI:<span
									class="require">*</span></label> <br>
							<c:if test="${asset.getStatus()=='1'}">
								<p class="view"
										style="width: 350px; height: 45px; font-size: 25px; margin: auto; background-color: green;"
										name="view_asset">KIỂM KÊ THÀNH CÔNG</p>
							</c:if>
							<c:if test="${asset.getStatus()=='2'}">
								<p class="view"
										style="width: 250px; height: 45px; font-size: 25px; margin: auto; background-color: yellow;"
										name="view_asset">BÁO MỚI</p>
							</c:if>
							<c:if test="${asset.getStatus()=='0'}">
								<p class="view"
										style="width: 250px; height: 45px; font-size: 25px; margin: auto; background-color: red;"
										name="view_asset">CHƯA KIỂM KÊ</p>
							</c:if>
							<c:if test="${asset.getStatus()=='3'}">
								<p class="view"
										style="width: 350px; height: 45px; font-size: 25px; margin: auto; background-color: orange;"
										name="view_asset">KIỂM KÊ KHÔNG HỢP LỆ</p>
							</c:if>
								
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGƯỜI CHỈNH SỬA:<span
									class="require">*</span></label> <input type="text"
									value="${asset.getAsset().getUser_insert_cd()}" readonly="readonly"
									class="form-control" name="">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY CHỈNH SỬA:<span
									class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly"
									value="${asset.getAsset().getInsert_dt()}" name="">
							</div>
						</div>
						<c:if test="${asset.getExplain().trim().length()>0 }">
						<div class="col-sm-4"></div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGƯỜI GIẢI TRÌNH:<span
									class="require">*</span></label> <input type="text"
									value="${asset.getAsset().getUser_insert_cd()}" readonly="readonly"
									class="form-control" name="">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY GIẢI TRÌNH:<span
									class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly"
									value="${asset.getExpain_dt()}" name="">
							</div>
						</div>
						</c:if>
						<div class="col-sm-4"></div>
						<div class="col-sm-4">
							<label class="title_input">NHÓM TÀI SẢN:<span
								class="require">*</span></label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text"
									class="form-control inputAssetItem" readonly="readonly"
									name="group_asset_na" id="group_asset_na"
									value="${asset.getGroup().getGroup_name()}"> 

							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">TÊN TÀI SẢN:<span
									class="require">*</span></label> <input type="text"
									value="${asset.getAsset().getName()}" readonly="readonly" class="form-control"
									name="asset_name">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MODEL:<span class="require">*</span></label>
								<input type="text" class="form-control" readonly="readonly"
									value="${asset.getAsset().getModel()}" name="asset_model">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">SỐ SERIES:<span
									class="require">*</span></label> <input type="text"
									class="form-control" value="${asset.getAsset().getSeries()}"
									readonly="readonly" name="asset_series">
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">ĐƠN VỊ KIỂM KÊ:<span class="require">*</span></label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text"
									class="form-control inputAssetItem" value="${asset.getDeptChecking().getDept_name()}"
									readonly="readonly" name="department_name" id="department_name">
								<input type="text" class="form-control inputAssetItem"
									style="display: none;" name="department_cd"
									value="${department_cd}" id="department_cd">

							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">ĐƠN VỊ SỬ DỤNG:<span
									class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly"
									value="${asset.getDeptUsing().getDept_name()}" name="asset_number">
							</div>
						</div>
						<c:if test="${asset.getExplain().trim().length()>0 }">
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">GIẢI TRÌNH:</label>
								<textarea class="form-control" readonly="readonly"
									name="asset_note">${asset.getExplain()}</textarea>
							</div>
						</div>
						</c:if>
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">GHI CHÚ:</label>
								<textarea class="form-control" readonly="readonly"
									name="asset_note">${asset_note}</textarea>
							</div>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered"
			role="document">
			<div class="modal-content iset3d"
				style="border-radius: 0px; border: 1px solid gray">

				<!-- Modal Header -->
				<div class="modal-header"
					style="height: 40px; background-color: #005BB5;">
					<p class="modal-title"
						style="height: 30px; margin-top: -9px; color: white; font-weight: 700">GIẢI
						TRÌNH</p>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="InventoryCheckingResultView" method="POST">
							<c:if test="${asset.getStatus()=='1'}">
								<label for="email"
									style="font-weight: 700; text-transform: uppercase;">PHƯƠNG THỨC GIẢI TRÌNH:</label>
								<br>
								<label style="margin-right: 30px"><input type="radio"
									class="form-control " name="accept_radio" checked="checked"
									id="approve" value="1"><span
									style="color: green; font-weight: 700">KIỂM KÊ THÀNH CÔNG</span></label>
								<label> <input type="radio" class="form-control "
									name="accept_radio" id="disApprove" value="${asset.getStatus()}"> <span
									style="color: red; font-weight: 700">KIỂM KÊ KHÔNG THÀNH CÔNG</span>
								</label>
							</c:if>
							<c:if test="${asset.getStatus()!='1'}">
								<label for="email"
									style="font-weight: 700; text-transform: uppercase;">PHƯƠNG THỨC GIẢI TRÌNH:</label>
								<br>
								<label style="margin-right: 30px"><input type="radio"
									class="form-control " name="accept_radio" 
									id="approve" value="1"><span
									style="color: green; font-weight: 700"> KIỂM KÊ THÀNH CÔNG</span></label>
								<label> <input type="radio" class="form-control " checked="checked"
									name="accept_radio" id="disApprove" value="${asset.getStatus()}"> <span
									style="color: red; font-weight: 700">KIỂM KÊ KHÔNG THÀNH CÔNG</span>
								</label>
							</c:if>
						<input type="text" name="inventory_checking_cd"
							style="display: none" value="${asset.getInventory_Checking_Cd() }"
							id="liquidation_cd">
							<input style="display: none;" name="asset_rfid"
												value="${asset.getAsset().getRFID()}">

						<div class="form-group" style="margin-bottom: 0px;">
							<label for="pwd" style="font-weight: 700;">LÝ DO GIẢI
								TRÌNH:</label><br>
							<textarea id="reasonNotAlow" name="reason_explain"
								style="height: 300px; width: 100%; resize: none"></textarea>
						</div>
						<button type="submit" onsubmit="return checkValud()"
							name="expain" style="margin-top: 10px;"
							class="btn btn-danger">LƯU GIẢI TRÌNH</button>
							    <button type="button" style="margin-top: 10px;"
							class="btn btn-danger" data-dismiss="modal">ĐÓNG</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function checkValud() {

		var valueCheck;

		var ele = document.getElementsByName('methodApprove');

		for (i = 0; i < ele.length; i++) {
			if (ele[i].checked) {
				valueCheck = ele[i].value;
			}
		}

		if (valueCheck == 1) {
			var reason = document.getElementById("reason").value
			if (reason.trim().length > 0) {
				document.getElementById("reason").style.border = "1px solid red";
				return false;
			}

		}
		if (valueCheck == 0) {
			var reason = document.getElementById("reason").value
			if (reason.trim().length == 0) {
				document.getElementById("reason").style.border = "1px solid red";
				return false;
			}

		}

		return true;

	}
</script>
</html>