<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main page</title>
</head>
<body>
	<h2>Spring MVC main page...</h2>
	<span class="floatRight"><a href="<c:url value="/" />">Main
			page</a></span>
	<br>
	<span class="floatRight"><a href="<c:url value="/login" />">Login</a></span>
	<br>
	<span class="floatRight"><a href="<c:url value="/logout" />">Logout</a></span>
	<br>
</body>
</html>
