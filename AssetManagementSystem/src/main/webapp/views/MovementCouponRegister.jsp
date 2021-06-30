<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ĐĂNG KÝ LỆNH ĐIỀU ĐỘNG</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
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
<style>
 .radio-inline{
 	position: relative;
    display: inline-block;
    padding-left: 20px;
    margin-bottom: 0;
    font-weight: 400;
    vertical-align: middle;
    cursor: pointer;
}
input[type="radio"] {
    width: 25px !important;
    height: 25px !important;
    vertical-align: sub;
    margin-right:10px;
}
label span {
    font-size: 20px;
    font-weight: 700;
    margin-right: 5px;
    
}
div.backradio{
	margin-top: 10px;
    margin-bottom: 10px;
    background-color: #f8be09;
    padding: 7px 0px;
}
</style>
<body onload="changeSelectNumber()">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="padding: 0px;">
		<div class="row">
			<div class="col-sm-12 general-setting w3-hide-small w3-light-grey w3-card-2">
				<form action="MovementCouponRegister" onsubmit="return confirmNumberNo()" method="POST" style="padding: 10px;">
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" name="save" class="btn btn-primary">
								<i class="fa fa-save" style="font-size: 24px"></i>LƯU DỮ LIỆU
							</button>
							<button type="submit" name="back" class="btn btn-primary">
								<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i>QUAY LẠI
							</button>
						</div>
					</div>
					<jsp:include page="viewcommon/message.jsp"></jsp:include>			
					<div class="backradio">
					<label class="radio-inline"><input type="radio" name="rdXuat" checked="checked" onchange="changeXuat()"  value="0"><span>NỘI BỘ</span></label>
					<label class="radio-inline"><input type="radio" name="rdXuat" value="1"  onchange="changeXuat()"  ><span>BÊN NGOÀI</span></label>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">CÔNG TY XUẤT TÀI SẢN:<span class="require">*</span></label>
								<div class="input-group">
									<input type="text" class="form-control" name="cmpn_cd_master" id="cmpn_cd_master" value="${object.getCmpn_master().getCompany_cd()}" style="display: contents"> <input type="text" class="form-control" style="height: 40px;" name="cmpn_na_master" readonly="readonly" value="${object.getCmpn_master().getCompany_name()}" id="cmpn_na_master">
									<button type="button" class="select" id="btn_sel_cmpn_master" style="margin-right: 2px;" onclick="return openDialogue('PopupCompanyInput?param1=cmpn_cd_master&param2=cmpn_na_master')">
										<i class="fa fa-th-list"></i>
									</button>
									<button type="button" class="select " id="btn_clr_cmpn_master" onclick="return clearText('cmpn_cd_master','cmpn_na_master' )">
										<i class="fa fa-eraser" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						</div>
						<div class="col-sm-4" id="div_dept_master" style="margin-top:9px;">
							<label class="title_input">ĐƠN VỊ XUẤT TÀI SẢN:</label><br>
							<div class="input-group">

								<span class="input-group-addon"> </span> <input type="text" style="height: 40px;" class="form-control inputAssetItem" value="${object.getDept_master().getDept_name()}" readonly="readonly" name="department_name_master" id="department_name_master"> <input type="text" class="form-control inputAssetItem" style="display: none;" name="department_cd_master" value="${object.getDept_master().getDept_cd()}" id="department_cd_master">
								<button type="button" class="select"  style="margin-right: 2px;" onclick="return openDialogue('GetListDepartment?param1=department_cd_master&param2=department_name_master')">
									<i class="fa fa-th-list"></i>
								</button>
								<button type="button" class="select"  onclick="return clearText('department_cd_master','department_name_master' )">
									<i class="fa fa-eraser" aria-hidden="true"></i>

								</button>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY XUẤT ĐƠN VỊ: <span class="require">*</span></label> <br> <input type="date" value="${object.getDate_start()}" class="form-control" name="borrow_date">
							</div>
						</div>
						</div>
						<div class="row">
						<div class="col-sm-4">
							<div class="form-group">
								<label style="float: initial;" class="title_input">SỐ LỆNH:<span class="require">*</span></label><br>
								<div style="width: 100%">
									<input readonly style="width: 100%; float: left; margin-right: 10px;" id="numberNo" type="text" value="${object.getNumber_no() }" class="form-control" name="number_on">
								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label style="float: initial;" class="title_input">MÃ LỆNH:<span class="require">*</span></label><br>
								<div style="width: 100%">
									<div class="form-group">
										<select class="form-control" name="move_sticker" id="sel1">
											<option value="" selected="selected"></option>
											<option value="CM">CM</option>
											<option value="M">M</option>
											<option value="T">T</option>
											<option value="BG">BG</option>
											<option value="TH">TH</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-4" style="padding: 15px;"></div>
						<div class="backradio col-sm-12">
					<label class="radio-inline"><input type="radio" name="rdNhap" onchange="changeNhap()" checked="checked"  value="0"><span>NỘI BỘ</span></label>
					<label class="radio-inline"><input type="radio" name="rdNhap" onchange="changeNhap()" value="1"><span>BÊN NGOÀI</span></label>
					</div>
						<div class="col-sm-4" style="margin-top: 9px;">
							<div class="form-group">
								<label class="title_input">CÔNG TY NHẬP TÀI SẢN: <span class="require">*</span></label>
								<div class="input-group">
									<input type="text" class="form-control" name="cmpn_cd_client" id="cmpn_cd_client" value="${object.getCmpn_client().getCompany_cd()}" style="display: contents"> <input type="text" class="form-control" style="height: 40px;" name="cmpn_na_client" readonly="readonly" value="${object.getCmpn_client().getCompany_name()}" id="cmpn_na_client">
									<button type="button" class="select" id="btn_sel_cmpn_client" style="margin-right: 2px;" onclick="return openDialogue('PopupCompanyInput?param1=cmpn_cd_client&param2=cmpn_na_client')">
										<i class="fa fa-th-list"></i>
									</button>
									<button type="button" class="select " id="btn_clr_cmpn_client" onclick="return clearText('cmpn_cd_client','cmpn_na_client' )">
										<i class="fa fa-eraser" aria-hidden="true"></i>
									</button>
								</div>
							</div>
						</div>
						<div class="col-sm-4" style="margin-top: 15px;" id="div_dept_client">
							<label class="title_input">ĐƠN VỊ NHẬN TÀI SẢN: </label><br>
							<div class="input-group">
								<span class="input-group-addon"> </span> <input type="text" style="height: 40px;" class="form-control inputAssetItem" value="${object.getDept_client().getDept_name()}" readonly="readonly" name="department_name_client" id="department_name_client"> <input type="text" class="form-control inputAssetItem" style="display: none;" name="department_cd_client" value="${object.getDept_client().getDept_cd()}" id="department_cd_client">
								<button type="button" class="select" style="margin-right: 2px;" onclick="return openDialogue('GetListDepartment?param1=department_cd_client&param2=department_name_client')">
									<i class="fa fa-th-list"></i>
								</button>
								<button type="button" class="select" onclick="return clearText('department_cd_client','department_name_client' )">
									<i class="fa fa-eraser" aria-hidden="true"></i>
								</button>
							</div>
						</div>
						<div class="col-sm-4" style="margin-top: 9px;">
							<div class="form-group">
								<label class="title_input">NGÀY NHẬP TÀI SẢN :<span class="require">*</span></label> <br> <input type="date" value="${object.getDate_end_schedule()}" class="form-control" name="pay_date_schedual">
							</div>
						</div>
						<div class="col-sm-12">
							<div class="form-group">
								<label class="title_input">LÝ DO ĐIỀU ĐỘNG TÀI SẢN: <span class="require">*</span></label> <br>
								<textarea type="date" style="text-transform: uppercase;" class="form-control" name="borrow_reason">${object.getReason()}</textarea>
							</div>
						</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function changeSelectNumber() {
		var numberNo = document.getElementById("numberNo");
		var selectNumber = ""

		if (numberNo.value.length == 1) {
			numberNo.value = "0" + numberNo.value;
		}

	}
	
	function changeNhap()
	{
		//Ngoại bộ
		if(document.getElementsByName("rdNhap")[0].checked ==  false) {
			var elm = document.getElementById("cmpn_na_client");
			elm.value = "";
			elm.readOnly =false;
			elm.focus();
			elm = document.getElementById("cmpn_cd_client");
			elm.value = "";
			
			elm = document.getElementById("btn_sel_cmpn_client");
			elm.style.display = "none";
			elm = document.getElementById("btn_clr_cmpn_client");
			elm.style.display = "none";
			
			elm = document.getElementById("div_dept_client");
			elm.style.display = "none";
			
			elm = document.getElementById("cmpn_cd_client");
			elm.value = "";
			
		} else {
			var elm = document.getElementById("cmpn_na_client");
			elm.value = "";
			elm.readOnly =true;
			elm = document.getElementById("cmpn_cd_master_client");
			elm.value = "";
			
			elm = document.getElementById("btn_sel_cmpn_client");
			elm.style.display = "block";
			elm = document.getElementById("btn_clr_cmpn_client");
			elm.style.display = "block";
			
			elm = document.getElementById("div_dept_client");
			elm.style.display = "block";
		}
	}
	
	function changeXuat()
	{
		//Ngoại bộ
		if(document.getElementsByName("rdXuat")[0].checked ==  false) {
			var elm = document.getElementById("cmpn_na_master");
			elm.value = "";
			elm.readOnly =false;
			elm.focus();
			elm = document.getElementById("cmpn_cd_master");
			elm.value = "";
			
			elm = document.getElementById("btn_sel_cmpn_master");
			elm.style.display = "none";
			elm = document.getElementById("btn_clr_cmpn_master");
			elm.style.display = "none";
			
			elm = document.getElementById("div_dept_master");
			elm.style.display = "none";
			
			elm = document.getElementById("div_dept_master");
			elm.style.display = "none";
			
		} else {
			var elm = document.getElementById("cmpn_na_master");
			elm.value = "";
			elm.readOnly =true;
			elm = document.getElementById("cmpn_cd_master");
			elm.value = "";
			
			elm = document.getElementById("btn_sel_cmpn_master");
			elm.style.display = "block";
			elm = document.getElementById("btn_clr_cmpn_master");
			elm.style.display = "block";
			
			elm = document.getElementById("div_dept_master");
			elm.style.display = "block";
		}
	}
</script>
</html>