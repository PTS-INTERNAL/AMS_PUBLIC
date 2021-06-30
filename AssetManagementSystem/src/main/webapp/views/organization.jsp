<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ TỔ CHỨC</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
</head>
<style>
.menu-general-manager-item {
	float: left;
	width: 195px;
	margin-left: 20px;
	cursor: pointer;
	transition: transform .2s;
}

.menu-general-manager-item p {
	margin-top: 8px
}

.menu-general-manager-item  a {
	text-decoration: none;
}

img {
	display: block;
	margin-left: auto;
	margin-right: auto;
}

.menu-general-manager {
	width: fit-content;
	height: 100px;
	margin: auto;
	margin-top: 60px
}

.zoom {
	transition: transform .2s;
}

.zoom:hover {
	-ms-transform: scale(1.5); /* IE 9 */
	-webkit-transform: scale(1.5); /* Safari 3-8 */
	transform: scale(1.5);
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

.btn-admin {
	background-color: orange;
}
</style>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div class="container">
		<div class="menu-general-manager">
			<p
				style="width: 100%; text-align: center; color: red; font-weight: 700; margin-top: 10px">${message}</p>
			
			<c:if test="${lstCompany.size() > 0}">
				<c:forEach var="p" items="${lstCompany}">
				<form action="organization" method="POST" style="float:left">
					<div
						class="menu-general-manager-item  zoom shadow-sm p-3 mb-5 bg-white rounded">
						<button type="submit" style="width:100%"> 
						<input type="text" name="company_cd" value="${p.getCompany_cd()}" style="display: none;">
						<img alt="discuss" style="width: 100%" src="${p.getCompany_logo()}">
							<p style="font-size: 18px; text-align: center; font-weight: 700">${p.getCompany_shortname()}</p>
						</button>
					</div>
					</form>
				</c:forEach>
			</c:if>
			<c:if test="${role == 'admin'}">
			<td><a class="btn  btn-feature btn-admin" href="SystemManagement">QUẢN TRỊ HỆ THỐNG</a></td>
			</c:if>
		</div>

	</div>





<!-- 	<div class="only-view-pc" id="footer-hide"> -->
<%-- 		<jsp:include page="viewcommon/footer.jsp"></jsp:include> --%>
<!-- 	</div> -->
</body>
<script type="text/javascript">

function getBrowserSize(){
    var w, h;

      if(typeof window.innerWidth != 'undefined')
      {
       w = window.innerWidth; //other browsers
       h = window.innerHeight;
      } 
      else if(typeof document.documentElement != 'undefined' && typeof      document.documentElement.clientWidth != 'undefined' && document.documentElement.clientWidth != 0) 
      {
       w =  document.documentElement.clientWidth; //IE
       h = document.documentElement.clientHeight;
      }
      else{
       w = document.body.clientWidth; //IE
       h = document.body.clientHeight;
      }
    return {'width':w, 'height': h};
}

if(parseInt(getBrowserSize().width) < 900){
	document.getElementById("footer-content").style.display = "none";
	document.getElementById("username").style.display = "none";
	document.getElementById("footer-div").style.height = "50px";
	document.getElementById("name_screen").style.textAlign  = "center";
	document.getElementById("name-div").style.paddingLeft  = "0px";
	document.getElementById("footer-div").style.paddingLeft = "0px";
	document.getElementById("pdaversion").style.display = "block";
}
	

</script>
</html>