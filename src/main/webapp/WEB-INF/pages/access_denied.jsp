<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Access Denied page</title>
</head>

<body>
	<div>
		<div>
			<span><strong>${loggedinuser}</strong> access denied </span> <br>
			<span class="floatRight"><a href="<c:url value="/" />">Main
					page</a></span>
		</div>
</body>

</html>