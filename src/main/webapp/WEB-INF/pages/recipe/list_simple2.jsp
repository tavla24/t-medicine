<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <form:form method="post" modelAttribute="container">
            <c:forEach items="${container.dtos}" var="recipe" varStatus="rLoop">
                <tr>
                    <th>
                        <form:input type="text" path="dtos[${rLoop.index}].healingType" value="${recipe.healingType}" readonly="true"/>
                            <%--<form:label type="text" path="dto[${rLoop.index}].healingName" />--%>
                                <%--${recipe.healingType}:${recipe.healingName}--%>
                    </th>
                    <th>
                            <%--<form:label type="text" path="dto[${rLoop.index}].dateFrom" />--%>
                                <%--${recipe.dateFrom}--%>
                    </th>
                    <th>${recipe.dateTo}</th>

                    <th></th>
                    <sec:authorize access="hasRole('DOCTOR')">
                        <td><a href="<c:url value='/recipe/edit/${recipe.id}' />">details</a></td>
                        <td><a href="<c:url value='/recipe/delete/${insuranceId}/${recipe.id}' />">delete</a>
                            <input type="submit" value="DELETE" /></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </form:form>
        </tbody>
    </table>

</div>
<%@include file="../zfooter.jsp"%>
</body>

</html>