<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asset management</title>
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

.title_input {
	line-height: 29px;
	font-size: 20px;
}

.input-group {
	margin-top: 10px;
}
</style>
<body onload="Pagination()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin-top: 10px; padding: 0px; margin: 10px;">
		<div class="row">
			<div class="col-sm-12 general-setting w3-hide-small w3-light-grey w3-card-2">
				<form action="RentAssetRegister" method="POST">
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" name="save" class="btn btn-primary">
								<i style="font-size: 24px" class="fa">&#xf067;</i>LƯU DỮ LIỆU
							</button>
							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size:24px"></i></i>QUAY LẠI
							</button>
						</div>
					</div>
					<p
						style="width: 100%; text-align: center; color: red; font-weight: 700; margin-top: 10px">${message}</p>
					<p
						style="width: 100%; text-align: center; color: blue; font-weight: 700; margin-top: 10px">${notification}</p>
					<div class="row">
						<div class="col-sm-3">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">TÊN TÀI SẢN:<span class="require">*</span></label>
								</span> <input type="text" style="padding-left: 5px;"
									class="form-control inputAssetName" name="asset_name">
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">MODEL:<span class="require">*</span></label>
								</span> <input type="text" style="padding-left: 5px;"
									class="form-control inputAssetName" name="asset_model">
							</div>
						</div>
						<div class="col-sm-3">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">SERIES:<span class="require">*</span></label>
								</span> <input type="text" style="padding-left: 5px;"
									class="form-control inputAssetName" name="asset_series">
							</div>
						</div>
						<div class="col-sm-3">



							<div class="form-group">
								<label class="title_input">ĐƠN VỊ:<span class="require">*</span></label>

								<div class="input-group" style="margin-top:0px;">
									<input type="text" class="form-control inputAssetItem" readonly="readonly"
										name="department_name" id="department_name"> <input
										type="text" class="form-control inputAssetItem"
										style="display: none;" name="department_cd" id="department_cd">
									<button type="button" class="select"
										onclick="return openDialogue('GetListDepartment?param1=department_cd&param2=department_name')">
										<i class="fa fa-th-list"></i>
									</button>
									<button type="button" class="select " 
										onclick="return clearText('department_cd','department_name' )">
										<i class="fa fa-eraser" aria-hidden="true"></i></button>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">NƠI THUÊ:<span class="require">*</span></label>
								</span> <input type="text" 
									class="form-control inputAssetItem" name="company_name">
							</div>
						</div>

						<div class="col-sm-4">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">ĐỊA CHỈ:<span class="require">*</span></label>
								</span> <input type="text" class="form-control inputAssetItem"
									name="company_address">
							</div>
						</div>

						

						<div class="col-sm-4">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">MÃ KẾ TOÁN:<span class="require">*</span></label>
								</span> <input type="text" class="form-control inputAssetItem"
									name="accountan_cd">
							</div>
						</div>


						<div class="col-sm-4">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">NGÀY THUÊ:<span class="require">*</span></label>
								</span> <input type="date" class="form-control inputAssetItem"
									name="date_rent">

							</div>
						</div>

						<div class="col-sm-4">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">NGÀY TRẢ:</label>
								</span> <input type="date" class="form-control inputAssetItem"
									name="date_paid">
							</div>
						</div>

						<div class="col-sm-4">
							<div class="form-group">
								<span class="input-group-addon"> <label
									class="title_input">GIÁ TIỀN:</label>
								</span> <input type="text" class="form-control inputAssetItem"
									name="price">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function openDialogue(url) {
		window.open(url, "_blank", "scrollbars=yes,width=600,height=700");
	}
</script>
</html>