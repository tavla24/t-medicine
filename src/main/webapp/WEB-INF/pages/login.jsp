<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>

<body>
	<div>
	<c:url var="loginUrl" value="/logincmd" />
		<form action="${loginUrl}" method="POST">
			<c:if test="${param.error != null}">
				<div>
					<p>Invalid username and password.</p>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div>
					<p>You have been logged out successfully.</p>
				</div>
			</c:if>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<div>
				<input type="text" id="username" name="login"
					placeholder="Enter Username" required>
			</div>
			<div>
				<input type="password" id="password" name="password"
					placeholder="Enter Password" required>
			</div>
			<div>
				<label><input type="checkbox" id="rememberme"
					name="remember-me">Remember Me</label>
			</div>
			<div>
				<button type="submit">Log in...</button>
			</div>
		</form>
	</div>
	<%@include file="zfooter.jsp" %>
</body>

</html>