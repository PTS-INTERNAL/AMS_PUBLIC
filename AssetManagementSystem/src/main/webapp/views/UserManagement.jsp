<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QUẢN LÝ NGƯỜI DÙNG</title>
<jsp:include page="viewcommon/library.jsp"></jsp:include>
<link rel="stylesheet" href="./resources/css/tabledata.css">

</head>
<body>
	<jsp:include page="viewcommon/header.jsp"></jsp:include>
	<jsp:include page="viewcommon/subHeaderEmpty.jsp"></jsp:include>
	<div style="margin-top: 10px; padding: 0px; width: 100%; margin: auto">
		<div style="margin: 10px">
			<form action="UserManagement" method="POST">
				<table class="table   table-search">
					<thead>
						<tr>
							<td class="title">CÔNG TY</td>
							<td>
								<div class="input-group">
									<input type="text" class="form-control" name="cmpn_cd"
										id="cmpn_cd" value="${user.getCompany_cd()}"
										style="display: contents"> <input type="text"
										class="form-control" name="cmpn_na" readonly="readonly"
										value="${user.getCompany_name()}" id="cmpn_na">
									<button type="button" class="select btn-table-search"
										onclick="return openDialogue('PopupCompanyInput?param1=cmpn_cd&param2=cmpn_na')">
										<i class="fa fa-th-list"></i>
									</button>
									<button type="button" class="select btn-table-search"
										onclick="return clearText('cmpn_na','cmpn_cd' )">
										<i class="fa fa-eraser" aria-hidden="true"></i>
									</button>
								</div>


							</td>
							<td class="title">HỌ VÀ TÊN</td>
							<td><input name="employee_name" id=""
								value="${user.getName()}"></td>
							<td class="title">MÃ NHÂN VIÊN</td>
							<td><input name="employee_code" id=""
								value="${user.getEmployee_cd()}"></td>
							<td class="title">MẬT KHẨU</td>
							<td><input name="employee_pass" id=""
								value="${user.getPasword()}"></td>
						</tr>
						<tr>
							<td class="title">PHONE</td>
							<td><input name="employee_phone" id=""
								value="${user.getPhone()}"></td>
							<td class="title">NGÀY HẾT HẠN</td>
							<td><input type="date" name="date_expire" id=""
								value="${user.getPhone()}"></td>
							<td class="title">GIỜ HẾT HẠN</td>
							<td><input type="time" name="time_expire" id=""
								value="${user.getPhone()}"></td>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="title-feature">
					<div class="text-right">
						<button type="submit" style="border-radius: 0"
							class="btn btn-primary" name="search">
							<i style="font-size: 24px" class="fa">&#xf067;</i>TÌM KIẾM
						</button>
						<button type="submit" style="border-radius: 0"
							class="btn btn-primary" name="register">
							<i style="font-size: 24px" class="fa">&#xf067;</i>ĐĂNG KÝ
						</button>
						<button type="submit" style="border-radius: 0" name="back"
							class="btn btn-primary">
							<i class="fa fa-arrow-circle-right" style="font-size: 24px"></i></i>QUAY
							LẠI
						</button>
					</div>
				</div>

				<!-- 				<div class="title-feature"> -->
				<!-- 					<div class="title-feature-sub"> -->
				<!-- 						<button type="submit" name="create" class="btn btn-primary"> -->
				<!-- 							<i style="font-size: 24px" class="fa">&#xf067;</i>THÊM MỚI -->
				<!-- 						</button> -->
				<!-- 					</div> -->
				<!-- 					<div class="text-right"> -->
				<!-- 						<button type="submit" class="btn btn-primary text-right" -->
				<!-- 							name="back"> -->
				<!-- 							<i class="fas fa-undo"></i> QUAY TRỞ LẠI -->
				<!-- 						</button> -->
				<!-- 					</div> -->
				<!-- 				</div> -->
			</form>
		</div>
		<jsp:include page="viewcommon/message.jsp"></jsp:include>
		<c:if test="${lst.size() > 0}">
			<div class="row">
				<div class="col-sm-12 general-setting shadow-sm p-3 mb-5 bg-gray">
					<table id="table.data" class="table   table-data"
						style="margin-top: 10px">
						<thead>
							<tr>
								<th>STT</th>
								<th>CÔNG TY</th>
								<th>MÃ NHÂN VIÊN</th>
								<th>HỌ TÊN</th>
								<th>NGÀY HẾT HẠN</th>
								<th>GIỜ HẾT HẠN</th>
								<th>CẬP NHẬT</th>
								<th>QUYỀN</th>
								<th>XÓA</th>
							</tr>
						</thead>
						<tbody>
							<%
								int stt = 0;
							%>
							<c:forEach var="item" items="${lst}" varStatus="count">
								<tr>
									<td><%=stt + 1%></td>
<%-- 									<td>${lstNae.get(count.index)}</td> --%>
<td></td>
									<td>${item.getEmployee_cd()}</td>
									<td>${item.getName()}</td>
									<td>${item.getDate_expire()}</td>
									<td>${item.getTime_expire()}</td>
									<td style="width: 95px;">
										<button style="width: 98px;" class="view">CẬP NHẬT</button>
									</td>
									<td style="width: 120px;">

										<form action="UserRightManagement" method="post"
											style="width: 120px;">
											<input style="display: none;" name="employee_cd"
												value="${item.getEmployee_cd()}">
											<button type="submit" style="width: 120px;" class="view"
												name="SetUserRight">PHÂN QUYỀN</button>
										</form>
									</td>
									<td style="width: 120px;">

										<form action="UserManagement" method="post"
											style="width: 100px;">
											<input style="display: none;" name="employee_cd"
												value="${item.getEmployee_cd()}">
											<button type="submit" onclick="return myFunction()" style="width: 100px;" class="view"
												name="delete">XÓA</button>
										</form>
									</td>
								</tr>
								<%
									stt++;
								%>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</c:if>
	</div>
</body>
<script type="text/javascript">
function myFunction() {
	  if(!confirm("Bạn có chắc muốn xóa người dùng!"))
	  return false;
	  else
	return true; 
	  
	}
</script>
</html>