<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Events List</title>
</head>

<body>
<div>
    <div>
        <span class="lead">List of Events, ${loggedinuser}</span>
    </div>

    <div>
        <form:form method="POST" modelAttribute="filter">
            <table>
                <thead>
                <tr>
                    <th>Пациент</th>
                    <th>DateTime</th>
                    <th>in next hours</th>
                    <th>Status</th>

                    <th>Healing type</th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th><form:input type="text" path="name" id="name"/>
                        <form:input type="text" path="surname" id="surname"/></th>

                    <th>
                        from
                        <fmt:formatDate value="${filter.dateFrom}" var="dateFromString" pattern="yyyy-MM-dd"/>
                        <form:input type="date" path="dateFrom" id="dateFrom" value="${dateFromString}"/>
                        to
                        <fmt:formatDate value="${filter.dateTo}" var="dateToString" pattern="yyyy-MM-dd"/>
                        <form:input type="date" path="dateTo" id="dateTo" value="${dateToString}"/>
                    </th>

                    <th><form:input type="number" min="0" path="nextHours" id="nextHours"/></th>

                    <th>
                        <form:select path="status" multiple="false" id="status">
                            <form:option value="" label="--- All ---"/>
                            <form:options items="${filter.statuses}"/>
                        </form:select>
                    </th>

                    <th>
                        <form:select path="healingType" multiple="false" id="status">
                            <form:option value="" label="--- All ---"/>
                            <form:options items="${filter.healingTypes}"/>
                        </form:select>
                    </th>
                    <th><input type="submit" value="Register"/></th>
                </tr>
                </tbody>
            </table>
        </form:form>
    </div>

    <div>
        <table>
            <thead>
            <tr>
                <th>Пациент</th>
                <th>DateTime</th>
                <th>Status</th>
                <th>Healing type</th>
                <th>info</th>

                <th>todo: link details</th>
                <sec:authorize access="hasRole('DOCTOR')">
                    <th width="100"></th>
                </sec:authorize>
                <sec:authorize access="hasRole('DOCTOR')">
                    <th width="100"></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dto}" var="event">
                <tr>
                    <th>${event.recipe.patient.surname} ${event.recipe.patient.name} ${event.recipe.patient.patronymic}</th>
                    <th>${event.datestamp}</th>
                    <th>${event.status}</th>

                    <th>${event.recipe.healingType}: ${event.recipe.healingName} [${event.recipe.doze}]</th>
                    <th>${event.info}</th>
                    <th></th>
                        <%--<sec:authorize access="hasRole('DOCTOR')">--%>
                        <td><a href="<c:url value='/event/edit/${event.id}' />">edit</a></td>
                    <%--</sec:authorize>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="../zfooter.jsp" %>
</body>

</html>