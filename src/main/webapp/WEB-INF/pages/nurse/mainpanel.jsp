<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Nurse page</title>
</head>

<body>
	<div>
		<span>Nurse: ${loggedinuser}</span> <br> 
		<span><a href="<c:url value="/doctor/edit" />">Edit profile</a></span> <br>
		<span><a href="<c:url value="/patient/" />">Patients</a></span> <br>
	</div>
    <%@include file="../zfooter.jsp" %>
</body>

</html>