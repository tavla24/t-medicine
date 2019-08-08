<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recipe List</title>
</head>

<body>
	<div>
		<div>
			<span class="lead">List of Recipes, doctor: ${loggedinuser}</span><br>
			<span class="lead">patientId: ${logginsuranceId}
		</div>
		<table>
			<thead>
				<tr>
					<th>Healing</th>
					<th>dateFrom</th>
					<th>dateTo</th>

					<th>todo: link details</th>
					<sec:authorize access="hasRole('DOCTOR')">
						<th width="100"></th>
						<th width="100"></th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dto}" var="recipe">
					<tr>
						<th>${recipe.healing.type}:${recipe.healing.name}</th>
						<th>${recipe.dateFrom}</th>
						<th>${recipe.dateTo}</th>

						<th></th>
						<sec:authorize access="hasRole('DOCTOR')">
							<td><a href="<c:url value='/recipe/edit/pattern/${recipe.patient.insuranceId}' />">details</a></td>
							<td><a href="<c:url value='/recipe/delete/${recipe.patient.insuranceId}' />">delete</a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="../zfooter.jsp"%>
</body>

</html>