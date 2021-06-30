<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Trang lỗi</title>

<jsp:include page="viewcommon/library.jsp"></jsp:include>
<body style="overflow: hidden;">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>

	<div class="container">

		<h1 style="font-size: 80px;font-weight:700;color: red;text-align:center;">ERROR !</h1>
	</div>
	<jsp:include page="viewcommon/message.jsp"></jsp:include>
	<jsp:include page="viewcommon/footer.jsp"></jsp:include>
</body>
</html>