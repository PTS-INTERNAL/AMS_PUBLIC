<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ XUẤT BÁO CÁO</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<style type="text/css">
.table-feature {
	margin-top: 10px;
}

.table-feature tbody tr td {
	width: 150px;
	height: 50px;
	border: 0px !important;
	padding-top: 5px;
	padding-bottom: 5px;
}

.btn-feature {
	width: 100%;
	height: 50px;
	margin: auto;
	background-color: green;
	line-height: 36px;
	color: white;
	font-weight: 700;
}

.btn-feature:hover {
	background-color: #007bff;
	color: white;
}
</style>
</head>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div class="container">
		<div style="margin-top:20px;">
		<jsp:include page="viewcommon/message.jsp"></jsp:include>
		</div>
		<div class=" table-feature">
			<div class="row">
				<div class="col-sm-3" style="margin-top:10px;">
					<a class="btn  btn-feature" href="AssetMotherAndChild">BÁO CÁO THIẾT BỊ MẸ + CON</a>
				</div>	
				
				<div class="col-sm-3" style="margin-top:10px;">
					<a class="btn  btn-feature" href="ExportAssetCompany">BÁO CÁO TÌNH HÌNH THIẾT BỊ</a>
				</div>	
				
				<div class="col-sm-3" style="margin-top:10px;">
					<a class="btn  btn-feature" href="ExportAssetCompanyInternal">BÁO CÁO THIẾT BỊ NỘI BỘ</a>
				</div>
			</div>
		</div>
		<div class=" table-feature" style="text-align: center;">
		<a class="btn  btn-feature btn-logout"
						href="FeatureSystem" style="width:250px; margin:auto;"><i class="fa fa-arrow-circle-left" style="font-size:24px; margin-right:10px" aria-hidden="true"></i>QUAY TRỞ
							LẠI</a>
		</div>
	</div>
	<jsp:include page="viewcommon/footer.jsp"></jsp:include>
</body>
</html>