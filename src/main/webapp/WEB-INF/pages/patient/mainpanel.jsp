<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Patient page</title>
</head>

<body>
	<div>
		<span>Doctor: ${loggedinuser}</span> <br> 
		<span><a href="<c:url value="/patient/add" />">Add patient profile</a></span> <br>
		<span><a href="<c:url value="/patient/list" />">Patients list</a></span> <br>
	</div>
    <%@include file="../zfooter.jsp" %>
</body>

</html>