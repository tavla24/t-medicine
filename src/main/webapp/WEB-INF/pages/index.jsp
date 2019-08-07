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
		<span><a href="<c:url value="/login" />">Login</a></span> <br>
		<span><a href="<c:url value="/logout" />">Logout</a></span> <br>
		<span>Only for users with ADMIN role  (for example: login [admin] password [2222])</span> <br>
        <span><a href="<c:url value="/admin/account/" />">Admin panel</a></span> <br>
		<span>Only for users with DOCTOR role (for example: login [doctor] password [4444])</span> <br>
        <span><a href="<c:url value="/doctor/" />">Doctor panel</a></span> <br>
		<span><a href="<c:url value="/test/test" />">test</a></span> <br>
	</div>
</body>

</html>