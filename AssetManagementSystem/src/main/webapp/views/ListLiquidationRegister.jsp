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
				<form action="LiquidationListRegister" method="POST">
					<input type="text" class="form-control" style="height: 40px; display: none;" name="coupon_cd" readonly="readonly" value="${coupon.getCoupon_cd()}">
					<div class="title-feature">
						<div class="text-right">
							<button type="submit" name="save" class="btn btn-primary">
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
								<label class="title_input">TÊN DANH SÁCH </label>
								<div class="input-group">
									<input type="text" class="form-control" style="height: 40px;" name="Liquid_List_name"  value="${List.getListName()}" id="cmpn_na_client">

								</div>
							</div>
						</div>
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGƯỜI TẠO DANH SÁCH:</label> <input type="text" value="${List.getUserCreate()}" class="form-control"  name="user_create_liquid">
							</div>
						</div>
						
						<div class="col-sm-4">
							<div class="form-group">
								<label class="title_input">NGÀY TẠO DANH SÁCH (MM/DD/YYYY):<span class="require">*</span></label> <input
									type="date" class="form-control"   value="${List.getDateCreate() }" name="date_create">
							</div>
						</div>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function setIdDelete(i)
{
	document.getElementById("delete_id").value = i;
}
</script>
</html>