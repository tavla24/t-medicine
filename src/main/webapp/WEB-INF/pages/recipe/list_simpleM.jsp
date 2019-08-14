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
        <span class="lead">patientId: ${insuranceId}
        <span class="lead">patient name: ${dto[0].patient.name} ${dto[0].patient.surname} ${dto[0].patient.patronymic}
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
                <th>${recipe.healingType}:${recipe.healingName}</th>
                <th>${recipe.dateFrom}</th>
                <th>${recipe.dateTo}</th>

                <th></th>
                <sec:authorize access="hasRole('DOCTOR')">
                    <td><a href="<c:url value='/recipe/edit/${insuranceId}/${recipe.id}' />">details</a></td>
                    <td><a href="<c:url value='/recipe/delete/${insuranceId}/${recipe.id}' />">delete</a></td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <span><a href="<c:url value="/recipe/edit/${insuranceId}/0" />">New recipe</a></span> <br>
</div>
<%@include file="../zfooter.jsp"%>
</body>

</html>