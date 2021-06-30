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
</style>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding:0px;">
		<div class="row">
			<div class="col-sm-12 general-setting w3-hide-small w3-light-grey w3-card-2">
				<form action="AssetTroubleEdit" method="POST">
					<div class="title-feature">
						<div class="text-right">
						<!-- 	<button type="submit" name="search" class="btn btn-primary">
								<i class="fa fa-save" style="font-size:24px"></i>TÌM KIẾM
							</button> -->
							<button type="submit" name="save" class="btn btn-primary">
								<i class="fa fa-save" style="font-size:24px"></i>LƯU DỮ LIỆU
							</button>
							<button type="submit" name="back" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size:24px"></i>QUAY LẠI
							</button>
						</div>
					</div>
					<jsp:include page="viewcommon/message.jsp"></jsp:include>
					<div class="row">
					
					<input  type="text"  value="${AssetSearch.getTroubleID() }" class="form-control" readonly="readonly" name="asset_troubleID">
					<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">TÊN TÀI SẢN:<span class="require">*</span></label> 
								
								<input style="background-color: #C0C0C0" type="text"  value="${AssetSearch.getAsset().getName() }" class="form-control" readonly="readonly" name="asset_name">
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">NHÓM TÀI SẢN:<span class="require">*</span></label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text"
									class="form-control inputAssetItem" readonly="readonly" name="group_asset_na"
									id="group_asset_na" value="${AssetSearch.getAsset().getGroup_name() }"> <input type="text"
									class="form-control inputAssetItem" style="display: none;"
									readonly="readonly" name="group_asset_cd"  value="${AssetSearch.getAsset().getGroup_id() }" id="group_asset_cd">
								<button type="button" class="select" 
									onclick="return openDialogue('GetListGroupAsset')">
									<i class="fa fa-th-list"></i>
								</button>
							</div>
						</div>
						
						
					 
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">MODEL:<span class="require">*</span></label>
								
								 <input type="text"
									class="form-control"  value="${AssetSearch.getAsset().getModel() }"  readonly="readonly" name="assetModel">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">SỐ SERIES:<span class="require">*</span></label> <input type="text"
									class="form-control"  value="${AssetSearch.getAsset().getSeries() }" readonly="readonly" name="asset_series">
							</div>
						</div>
						<div class="col-sm-4">
							<label class="title_input">ĐƠN VỊ:<span class="require">*</span></label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> 
								<input type="text"
									class="form-control inputAssetItem"  value="${AssetSearch.getAsset().getDepartment_name()}" readonly="readonly" name="department_name"
									id="department_name" > 
									
								<%-- <input type="text"
									class="form-control inputAssetItem" style="display: none;"
									name="department_cd"   value="${ AssetSearch.getDept().getDept_cd() }" id="department_cd"> --%>
									
								
							</div>
						</div>
							<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGƯỜI SỬ DỤNG:<span class="require">*</span></label> <input type="text"
									class="form-control"  value="${AssetSearch.getUserUse() }" readonly="readonly" name="asset_user">
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY GẶP SỰ CỐ (MM/DD/YYYY):<span class="require">*</span></label> <input
									type="text" class="form-control"   value="${AssetSearch.getDateTrouble() }" readonly="readonly" name="asset_date">
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">THỜI GIAN:<span class="require">*</span></label> 
								 <input type="text" readonly="readonly" name="time"  value="${AssetSearch.getTimeTrouble() }"  class="form-control"  >
							</div>
						</div>
					
						
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">SỰ CỐ:<span class="require">*</span></label>
								<textarea class="form-control"   name="asset_trouble">${AssetSearch.getTrouble() }</textarea>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">TRẠNG THÁI:<span class="require">*</span> &emsp;</label>
								
								<select  name="trouble_status" readonly="readonly"  >
										<option value="Ổn định" <c:if test="${AssetSearch.getTroubleStatus()=='Ổn định'}">selected </c:if>>Ổn định</option>
										<option value="Không ổn định" <c:if test="${AssetSearch.getTroubleStatus()=='Không ổn định'}"> selected </c:if>>Không ổn định</option>
										<option value="Không hoạt động được"<c:if test="${AssetSearch.getTroubleStatus()=='Không hoạt động được'}">selected  </c:if>>Không hoạt động được</option>
										<option value="nostatus" <c:if test="${AssetSearch.getTroubleStatus()=='nostatus'}"> selected </c:if>>nostatus</option>										
								</select>
							</div>
						</div>
						
						
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">LÝ DO:<span class="require">*</span></label>
								<textarea class="form-control" readonly="readonly"  name="asset_reason">${AssetSearch.getReason() }</textarea>
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">GHI CHÚ:</label>
								<textarea class="form-control" readonly="readonly"  name="asset_note">${AssetSearch.getNote() }</textarea>
							</div>
						</div>
						
						
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
</html>