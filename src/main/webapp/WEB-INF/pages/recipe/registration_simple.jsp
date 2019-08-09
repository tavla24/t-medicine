<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Patient Registration Form</title>
</head>

<body>
<div>
    <span><strong>${loggedinuser}</strong>, welcome </span>

    <div>Patient Registration Form</div>
    <form:form method="POST" modelAttribute="dto">
        <form:input type="hidden" path="id" id="id" />
        <form:input type="hidden" path="patient.insuranceId" id="patient.insuranceId" />

        <div>
            <label>Patient:</label> ${dto.patient.name} ${dto.patient.surname} ${dto.patient.patronymic}
        </div>

        <div>
            <label for="name">Healing</label>
            <div>
                <label for="status">Name:</label>
                <form:input type="text" path="healingName" id="name" />
                <form:errors path="healingName" />
                <br><label for="status">Type:</label>
                <form:select tabindex="0" path="healingType" items="${sourceHealingTypes}" multiple="false" id="status"/>
                <form:errors path="healingType"/>
                <br><label for="status">Doze:</label>
                <form:input type="text" path="doze" id="doze" />
            </div>
        </div>

        <div>
            <label for="name">Periodic:</label>from
            <fmt:formatDate value="${dto.dateFrom}" var="dateFromString" pattern="yyyy-MM-dd" />
            <form:input type="date" path="dateFrom" id="name" value="${dateFromString}" />
            <div>
                <form:errors path="dateFrom" />
            </div>to
            <fmt:formatDate value="${dto.dateTo}" var="dateToString" pattern="yyyy-MM-dd" />
            <form:input type="date" path="dateTo" id="name" value="${dateToString}" />
            <%--<form:input type="date" path="dateTo" id="name" />--%>
            <div>
                <form:errors path="dateTo" />
            </div>
        </div>

        <div>
            <form:checkbox path="healthful" />
        </div>

        <div>
            <form:checkboxes path="dayPartsList" items="${sourceDayParts}" />
        </div>

        <div>
            <form:select path="dayNamesList" items="${sourceDayNames}" multiple="true" />
        </div>

        <div>
            <br>
            <input type="submit" value="Register" />
        </div>
    </form:form>
</div>
<%@include file="../zfooter.jsp" %>
</body>

</html>