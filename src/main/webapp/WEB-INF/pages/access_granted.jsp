<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Access Granted page</title>
</head>

<body>
	<div>
		<span><strong>${loggedinuser}</strong> access granted </span> <br>
	</div>
	<%@include file="zfooter.jsp" %>
</body>

</html>