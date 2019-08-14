<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Event Registration Form</title>
</head>

<body>
<div>
    <span><strong>${loggedinuser}</strong>, welcome </span>

    <div>Event Registration Form</div>
    <div>
        <form:form method="POST" modelAttribute="dto">
            <form:input type="hidden" path="recipe.id"/>

            <label for="name">Пациент:</label>
            <form:input type="text" path="recipe.patient.surname" id="name" readonly="true"/>
            <form:input type="text" path="recipe.patient.name" id="name"/>
            <form:input type="text" path="recipe.patient.patronymic" id="name"/>
            <br>
            <label for="healing">Healing:</label>
            <form:input type="text" path="recipe.healingType" id="healing"/>
            <form:input type="text" path="recipe.healingName" id="healing"/>
            <form:input type="text" path="recipe.doze" id="healing"/>
            <br>
            <label for="datetime">DateTime:</label>
            <fmt:formatDate value="${dto.datestamp}" var="dateString" pattern="yyyy-MM-dd"/>
            <form:input type="date" path="datestamp" id="datetime" value="${dateString}"/>
            <br>
            <label for="status">DateTime:</label>
            <form:select path="status" multiple="false" id="status" items="${statuses}"/>
            <label for="info">Information:</label>
            <form:input type="text" path="info" id="info"/>
            <br>
            <input type="submit" value="Register"/>
        </form:form>
    </div>
</div>
<%@include file="../zfooter.jsp" %>
</body>

</html>