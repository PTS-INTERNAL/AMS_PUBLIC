<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ĐĂNG KÝ THANH LÝ TÀI SẢN</title>
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
				<form action="AssetLiquidationRegister" method="POST">
					<div class="title-feature">
						<div class="text-right">
							<button type="button" data-toggle="modal"
								data-target="#exampleModalCenter" name="save"
								class="btn btn-primary">
								<i class="fa fa-save" style="font-size: 24px"></i>XEM XÉT DUYỆT
							</button>
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
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGƯỜI ĐỀ XUẤT:<span
									class="require">*</span></label> <input type="text"
									value="${asset.getUserInsert()}" readonly="readonly"
									class="form-control" name="">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY ĐỀ XUẤT:<span
									class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly"
									value="${asset.getApproveDT()}" name="">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">TRẠNG THÁI:<span
									class="require">*</span></label> <br>
								<c:if test="${asset.getStatus()=='1'}">


									<p class="view"
										style="width: 250px; height: 45px; font-size: 25px; margin: auto; background-color: orange;"
										name="view_asset">CHỜ PHÊ DUYỆT</p>



								</c:if>
								<c:if test="${asset.getStatus()=='2'}">

									<p class="view"
										style="width: 250px; height: 45px; font-size: 25px; margin: auto; background-color: green;"
										name="view_asset">ĐÃ THANH LÝ</p>



								</c:if>
								<c:if test="${asset.getStatus()=='3'}">

									<p class="view"
										style="width: 250px; height: 45px; font-size: 25px; margin: auto; background-color: red;"
										name="view_asset">KHÔNG DUYỆT</p>

								</c:if>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGƯỜI XEM XÉT:<span
									class="require">*</span></label> <input type="text"
									value="${asset.getUserApprove()}" readonly="readonly"
									class="form-control" name="">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY XEM XÉT:<span
									class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly"
									value="${asset.getApproveDT()}" name="">
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">NHÓM TÀI SẢN:<span
								class="require">*</span></label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text"
									class="form-control inputAssetItem" readonly="readonly"
									name="group_asset_na" id="group_asset_na"
									value="${group_asset_na}"> <input type="text"
									class="form-control inputAssetItem" style="display: none;"
									name="group_asset_cd" value="${group_asset_cd}"
									id="group_asset_cd">

							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">TÊN TÀI SẢN:<span
									class="require">*</span></label> <input type="text"
									value="${asset_name}" readonly="readonly" class="form-control"
									name="asset_name">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MODEL:<span class="require">*</span></label>
								<input type="text" class="form-control" readonly="readonly"
									value="${asset_model}" name="asset_model">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">SỐ SERIES:<span
									class="require">*</span></label> <input type="text"
									class="form-control" value="${asset_series}"
									readonly="readonly" name="asset_series">
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">ĐƠN VỊ:<span class="require">*</span></label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text"
									class="form-control inputAssetItem" value="${department_name}"
									readonly="readonly" name="department_name" id="department_name">
								<input type="text" class="form-control inputAssetItem"
									style="display: none;" name="department_cd"
									value="${department_cd}" id="department_cd">

							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">SỐ LƯỢNG:<span
									class="require">*</span></label> <input type="text"
									class="form-control" readonly="readonly"
									value="${asset_number}" name="asset_number">
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">LÝ DO THANH LÝ:</label>
								<textarea class="form-control" readonly="readonly" name="reason">${reason}</textarea>
							</div>
						</div>

						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">LÝ DO KHÔNG DUYỆT NẾU CÓ:</label>
								<textarea class="form-control" readonly="readonly"
									name="asset_note">${asset.getReason_not_allow()}</textarea>
							</div>
						</div>
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
						style="height: 30px; margin-top: -9px; color: white; font-weight: 700">XEM
						XÉT DUYỆT</p>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="AssetLiquidationView" method="POST">
						<div class="form-group" style="margin-bottom: 0px;">

							<c:if test="${asset.getStatus()=='1'}">
								<label for="email"
									style="font-weight: 700; text-transform: uppercase;">Phương
									thức xét duyệt:</label>
								<br>
								<label style="margin-right: 30px"><input type="radio"
									class="form-control " name="ApproveRadio" checked="checked"
									id="approve" value="2"><span
									style="color: green; font-weight: 700"> CHẤP NHẤP NHẬN</span></label>
								<label> <input type="radio" class="form-control "
									name="ApproveRadio" id="disApprove" value="3"> <span
									style="color: red; font-weight: 700">KHÔNG CHẤP NHẤP
										NHẬN</span>
								</label>
							</c:if>
							<c:if test="${asset.getStatus()=='2'}">
								<label for="email"
									style="font-weight: 700; text-transform: uppercase;">Phương
									thức xét duyệt:</label>
								<br>
								<label style="margin-right: 30px"><input type="radio"
									class="form-control " name="ApproveRadio" checked="checked"
									id="approve" value="2"><span
									style="color: green; font-weight: 700"> CHẤP NHẤP NHẬN</span></label>
								<label> <input type="radio" class="form-control "
									name="ApproveRadio" id="disApprove" value="3"> <span
									style="color: red; font-weight: 700">KHÔNG CHẤP NHẤP
										NHẬN</span>
								</label>
							</c:if>
							<c:if test="${asset.getStatus()=='3'}">
								<label for="email"
									style="font-weight: 700; text-transform: uppercase;">Phương
									thức xét duyệt:</label>
								<br>
								<label style="margin-right: 30px"><input type="radio"
									class="form-control " name="ApproveRadio" id="approve"
									value="2"><span style="color: green; font-weight: 700">
										CHẤP NHẤP NHẬN</span></label>
								<label> <input type="radio" checked="checked"
									class="form-control " name="ApproveRadio" id="disApprove"
									value="3"> <span style="color: red; font-weight: 700">KHÔNG
										CHẤP NHẤP NHẬN</span>
								</label>
							</c:if>



						</div>

						<input type="text" name="liquidation_cd" style="display: none"
							value="${asset.getLiquidation_Cd() }" id="liquidation_cd">
					<input type="text" name="asset_rfid" style="display: none"
							value="${asset_rfid}" id="liquidation_cd">
						<div class="form-group" style="margin-bottom: 0px;">
							<label for="pwd" style="font-weight: 700;">LÝ DO KHÔNG
								PHÊ DUYỆT:</label><br>
							<textarea id="reasonNotAlow" name="reasonNotAlow"
								style="height: 300px; width: 100%; resize: none">${asset.getReason_not_allow()}</textarea>
						</div>
						<button type="submit" onsubmit="return checkValud()"
							name="methodApprove" style="margin-top: 10px;"
							class="btn btn-danger">CẬP NHẬT THÔNG TIN</button>
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