<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
welcome : Admin
<hr>
<%-- 	<c:if test="${not empty pageContext.request.userPrincipal}"> --%>
<!-- 		<p> is Log-In</p> -->
<%-- 	</c:if> --%>
<%-- 	<c:if test="${empty pageContext.request.userPrincipal}"> --%>
<!-- 		<p> is Log-Out</p> -->
<%-- 	</c:if> --%>
	<sec:authorize access="isAuthenticated()">
		<p>Log-In</p>
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<p>Log-Out</p>
	</sec:authorize>
<%-- 	USER ID : ${pageContext.request.userPrincipal.name} <br> --%>
	USER ID : <sec:authentication property="name"/> <br>
	
	<c:url value="/logout" var="logoutUrl"/>
	<a href="${logoutUrl}">Log-Out</a>
</body>
</html>