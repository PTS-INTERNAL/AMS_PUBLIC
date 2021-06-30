<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<c:if test="${message != null}">
		<div class="alert alert-danger message">
			<strong id="error">${message}</strong>
		</div>
	</c:if>
	<c:if test="${notification != null}">
		<div class="alert alert-success message">
			<strong>${notification}</strong>
		</div>
	</c:if>

</div>
