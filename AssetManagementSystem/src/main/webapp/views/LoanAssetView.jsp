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
.title-feature button i {
	margin-right: 10px;
}

.title-feature button {
	height: 100%;
	margin-top: 0px;
}

.title-feature {
	background-color: #bdc6e2;
	padding-left: 10px;
	font-size: 22px;
	height: 40px;
}

.table-data {
	border: 2px solid black;
}

.table-data tbody tr {
	line-height: 22px;
}

.table-data thead th {
	padding: 1px 1px 1px 1px;
}

.table-data td {
	border-right: 2px solid black;
	border-bottom-style: dashed;
	height: 18px;
	padding: 1px 4px 1px 4px;
}

.table-data th {
	border: 1px solid black;
}

.table-data thead th {
	border: 2px solid black;
	text-align: center;
	background-color: #bdc6e2;
}

.table-search tr {
	height: 22px;
}

.table-search tr td input {
	width: 100%;
	height: 30px;
	border: 1px solid black;
}

.table-search tr td select {
	width: 100%;
	height: 30px;
}

.table-search tr td {
	padding: 2px 2px 2px 2px;
	border: 2px solid black;
}

.table-search tr .title {
	font-weight: 700;
	padding: 5px 4px 0px 4px;
	background-color: #0089FF;
	color: white;
}

.selectList {
	cursor: pointer;
}

.selectList-item option {
	cursor: pointer;
}

textarea:focus, input:focus {
	outline: none;
}

.title_input {
	font-weight: 700;
	float: left;
}

.content_input {
	float: left;
	margin-left: 10px;
}
</style>
<body onload="Pagination()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding:0px;">
		<div class="row">
			<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
				<form action="LoanAssetView" method="POST">
					<div class="title-feature">
						<div class="text-right">
							<input type="text" value="${asset.getId()}"
								style="display: none;" name="rfid_code">
<!-- 							<button type="submit" style="border-radius: 0" -->
<!-- 								class="btn btn-primary" name="update"> -->
<!-- 								<i style='font-size: 24px' class='far'>&#xf044;</i> CHỈNH SỬA -->
<!-- 							</button> -->
<!-- 							<button type="submit" style="border-radius: 0" -->
<!-- 								class="btn btn-primary" name="delete"> -->
<!-- 								<i style='font-size: 24px' class='fas'>&#xf2ed;</i>XÓA -->
<!-- 							</button> -->
							<c:if test="${isApprove != '0'}">
							<c:if test="${asset.getStatus()=='1'}">
								<button type="button"  data-toggle="modal"
								data-target="#ModalApprove"  style="border-radius: 0" name="back"
									class="btn btn-primary">
									<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>CHƯA XÁC NHẬN
								</button>
							</c:if>
							</c:if>

							<c:if test="${isConfirm != '0'}">
							<c:if test="${asset.getStatus()=='2'}">
								<button type="button" data-toggle="modal"
								data-target="#ModalConfirm"  style="border-radius: 0" name="back"
									class="btn btn-primary">
									<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>TRẢ TÀI SẢN
								</button>
							</c:if>
							</c:if>
							<button type="submit" style="border-radius: 0" name="back"
								class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size:24px"></i></i>QUAY LẠI
							</button>
						</div>
					</div>
				</form>
				<jsp:include page="viewcommon/message.jsp"></jsp:include>
				<c:if test="${asset!=null}">
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">TÊN TÀI SẢN:</label>
								<input class="form-control" readonly="readonly"  value = "${asset.getAsset().getName()}"/>
							</div>
						</div>
<!-- 						<div class="col-sm-4"> -->
<!-- 							<div class="form-group"> -->
<!-- 								<label class="title_input">MÃ RFID:</label> -->
<%-- 								<p class="content_input">${asset.getAsset().getAsset_rfid()}</p> --%>
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MODEL:</label>
								<div class="input-group">
								<input class="form-control" readonly="readonly"  value = "${asset.getAsset().getModel()}"/>
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">SỐ SERIES:</label>
								<input class="form-control" readonly="readonly" value = "${asset.getAsset().getSeries()}"/>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">DOANH NGHIỆP MƯỢN:</label>
								<input class="form-control" readonly="readonly" value = "${asset.getCmpn_master().getCompany_shortname()}"/>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">ĐƠN VỊ  MƯỢN:</label>
								<input class="form-control" readonly="readonly" value = "${asset.getDept_master().getDept_name()}"/>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY CHO MƯỢN:</label>
								<input class="form-control" readonly="readonly" value = "${asset.getDate_start()}"/>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">DOANH NGHIỆP CHO MƯỢN:</label>
								<input class="form-control" readonly="readonly" value = "${asset.getCmpn_client().getCompany_shortname()}"/>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">ĐƠN VỊ CHO MƯỢN:</label>
								<input class="form-control" readonly="readonly" value = "${asset.getDept_client().getDept_name()}"/>
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY TRẢ:</label>
								<input class="form-control" readonly="readonly" value = "${asset.getDate_end()}"/>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">LÝ DO:</label>
								<textarea class="form-control" style="resize: none;" readonly="readonly" ">${asset.getReason()}</textarea>
							</div>
						</div>

					</div>
				</c:if>
			</div>
		</div>
	</div>
	<!-- MODAL PHÊ DUYỆT -->
	<!-- Modal -->
	<div class="modal fade" id="ModalApprove" tabindex="-1"
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
						style="height: 30px; margin-top: -9px; color: white; font-weight: 700">XÁC NHẬN MƯỢN TÀI SẢN</p>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="LoanAssetConfirm" method="POST">
					<input
								style="display: none;" name="loan_cd" value="${asset.getId()}">
							<c:if test="${asset.getStatus()=='1'}">
								<label for="email"
									style="font-weight: 700; text-transform: uppercase;">PHƯƠNG THỨC XÁC NHẬN:</label>
								<br>
								<label style="margin-right: 30px"><input type="radio"
									class="form-control " name="accept_radio" checked="checked"
									id="approve" value="2"><span
									style="color: green; font-weight: 700">XÁC NHẬN CÓ MƯỢN</span></label>
								<label> <input type="radio" class="form-control "
									name="accept_radio" id="disApprove" value="6"> <span
									style="color: red; font-weight: 700">KHÔNG XÁC NHẬN</span>
								</label>
							</c:if>
							<c:if test="${asset.getStatus()!='1'}">
								<label for="email"
									style="font-weight: 700; text-transform: uppercase;">PHƯƠNG THỨC XÁC NHẬN:</label>
								<br>
								<label style="margin-right: 30px"><input type="radio"
									class="form-control " name="accept_radio" 
									id="approve" value="2"><span
									style="color: green; font-weight: 700">XÁC NHẬN CÓ MƯỢN</span></label>
								<label> <input type="radio" class="form-control " checked="checked"
									name="accept_radio" id="disApprove" value="6"> <span
									style="color: red; font-weight: 700">KHÔNG XÁC NHẬN</span>
								</label>
							</c:if>
					
						<div class="form-group" style="margin-bottom: 0px;">
							<label for="pwd" style="font-weight: 700;">LÝ DO KHÔNG XÁC NHẬN:</label><br>
							<textarea id="reasonNotAlow" name="reason_explain"
								style="height: 100px; width: 100%; resize: none"></textarea>
						</div>
						<button type="submit" onsubmit="return checkValud()"
							name="confirm" style="margin-top: 10px;"
							class="btn btn-danger">LƯU XÁC NHẬN</button>
							    <button type="button" style="margin-top: 10px;"
							class="btn btn-danger" data-dismiss="modal">ĐÓNG</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- MODAL XÁC NHẬN TRẢ -->
	<!-- Modal -->
	<div class="modal fade" id="ModalConfirm" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalCenterTitle"
		aria-hidden="true">
		<div class="modal-dialog modal-sm modal-dialog-centered"
			role="document">
			<div class="modal-content iset3d"
				style="border-radius: 0px; border: 1px solid gray">

				<!-- Modal Header -->
				<div class="modal-header"
					style="height: 40px; background-color: #005BB5;">
					<p class="modal-title"
						style="height: 30px; margin-top: -9px; color: white; font-weight: 700">CHẤP NHẬN TRẢ TÀI SẢN</p>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="LoanAssetReturn" method="POST">
						
				
								<label for="email"
									style="font-weight: 700; text-transform: uppercase;">PHƯƠNG THỨC TRẢ TÀI SẢN:</label>
								<br>
								<label style="margin-right: 30px"><input type="radio"
									class="form-control " name="accept_radio" 
									id="approve" value="1"><span
									style="color: green; font-weight: 700">TRẢ TÀI SẢN</span></label>
								<label> <input type="radio" class="form-control " checked="checked"
									name="accept_radio" id="disApprove" value="${asset.getStatus()}"> <span
									style="color: red; font-weight: 700">CHƯA TRẢ TRẢ</span>
								</label>
							<br>
						<input style="display: none;" name="loan_cd" value="${asset.getId()}">

						
						<button type="submit" onsubmit="return checkValud()"
							name="assetReturn" style="margin-top: 10px;"
							class="btn btn-danger">TRẢ TÀI SẢN</button>
							    <button type="button" style="margin-top: 10px;"
							class="btn btn-danger" data-dismiss="modal">ĐÓNG</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

</script>

</html>