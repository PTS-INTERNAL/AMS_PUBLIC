<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<body style="overflow: hidden;">
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>

	<div class="container">
		<h2 style="text-align: center; margin-top: 30px">ĐỔI MẬT KHẨU</h2>
		<hr style="width: 100px; height: 5px;">
		<form action="ChangePasswordDefault" method="post"
			style="width: 400px; margin: auto; margin-top: 15px">
			<div class="form-group">
				<label for="id" style="font-weight: 700; font-size: 16px">Mã
					nhân viên:</label> <input type="text" class="form-control" id="usn"
					placeholder="Nhập vào mã nhân viên" value="${usn}" name="usn">
			</div>
			<div class="form-group">
				<label for="pwd" style="font-weight: 700; font-size: 16px">Mật
					khẩu cũ: </label> <input value="${pswdOld}" type="password" class="form-control" id="pwd"
					placeholder="Nhập vào mật khẩu" name="pswdOld">
			</div>
			<div class="form-group">
				<label for="pwd" style="font-weight: 700; font-size: 16px">Mật
					khẩu mới: </label> <input value="${pswdNew}" type="password" class="form-control" id="pwd"
					placeholder="Nhập vào mật khẩu" name="pswdNew">
			</div>
			<div class="form-group">
				<label for="pwd" style="font-weight: 700; font-size: 16px">Mật
					khẩu mới xác nhận: </label> <input value="${pswdReNew}" type="password" class="form-control" id="pwd"
					placeholder="Nhập vào mật khẩu" name="pswdReNew">
			</div>
			<jsp:include page="viewcommon/message.jsp"></jsp:include>
			<button type="submit" class="btn " name="save"
				style="color: white; background-color: #0090DD">ĐỔI MẬT KHẨU</button>
		</form>
	</div>
	
	<div class="only-view-pc" id="footer-hide">
		<jsp:include page="viewcommon/footer.jsp"></jsp:include>
	</div>
	
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
	document.getElementById("footer-div").style.height = "50px";
	document.getElementById("name_screen").style.textAlign  = "center";
	document.getElementById("name-div").style.paddingLeft  = "0px";
	document.getElementById("footer-div").style.paddingLeft = "0px";
	document.getElementById("pdaversion").style.display = "block";
}
	

</script>
</html>