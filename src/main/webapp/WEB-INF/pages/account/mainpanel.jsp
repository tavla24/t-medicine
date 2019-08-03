<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Admin page</title>
</head>

<body>
	<div>
		<span><a href="<c:url value="/admin/account/list" />">Accounts list</a></span> <br>
		<span><a href="<c:url value="/admin/account/new" />">New account</a></span> <br>
        <span><a href="<c:url value="/admin/doctor/list" />">Doctors list</a></span> <br>
		<span><a href="<c:url value="/admin/doctor/new" />">New doctor</a></span> <br>
	</div>
    <%@include file="../zfooter.jsp" %>
</body>

</html>