<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout page</title>
</head>

<body>
	<div>
		<c:url var="logoutUrl" value="/logoutcmd" />
		<form action="${logoutUrl}" method="POST">
			<!-- <form action="<%=request.getContextPath()%>/logoutcmd" method="POST"> -->
			<input type="submit" value="Logout..." /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
	<%@include file="zfooter.jsp" %>
</body>

</html>