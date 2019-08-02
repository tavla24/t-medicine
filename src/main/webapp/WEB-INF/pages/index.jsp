<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Guests page</title>
</head>

<body>
	<div>
		<span>Page for everybody </span> <br> <span><a href="<c:url value="/" />">Main page</a></span><br>
		<span><a href="<c:url value="/login" />">Login</a></span> <br>
		<span><a href="<c:url value="/logout" />">Logout</a></span> <br>
		<span><a href="<c:url value="/account/list" />">List</a></span> <br>
		<span><a href="<c:url value="/account/newaccount" />">New account</a></span> <br>
		<span><a href="<c:url value="/person/list" />">List doctors</a></span> <br>
		<span><a href="<c:url value="/person/newdoctor" />">New doctor</a></span> <br>
	</div>
</body>

</html>