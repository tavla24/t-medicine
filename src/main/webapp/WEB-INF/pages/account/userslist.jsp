<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
</head>

<body>
	<div>
		<div>
			<span class="lead">List of Users, ${loggedinuser}</span>
		</div>
		<table>
			<thead>
				<tr>
					<th>Login</th>
					<th>Password</th>
					<th>Role</th>
					<th>todo: link details</th>
					<sec:authorize access="hasRole('ADMIN') or hasRole('ROOT')">
						<th width="100"></th>
					</sec:authorize>
					<sec:authorize access="hasRole('ADMIN')">
						<th width="100"></th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<th>${user.login}</th>
						<th>${user.password}</th>
						<th>${user.role}</th>
						<th></th>
						<sec:authorize access="hasRole('ADMIN') or hasRole('ROOT')">
							<td><a href="<c:url value='/account/edit-user-${user.login}' />">edit</a></td>
						</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/account/delete-user-${user.login}' />">delete</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="../zfooter.jsp"%>
</body>

</html>