<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Patient Registration Form</title>
</head>

<body>
<div>
    <span><strong>${loggedinuser}</strong>, welcome </span>

    <div>Patient Registration Form</div>
    <form:form method="POST" modelAttribute="recipes">
        <form:input type="hidden" path="id" id="id" />

        <div>
            <label>Patient:</label> ${recipes.patient.name} ${recipes.patient.surname}
        </div>

        <div>
            <label for="name">Healing:</label>
            <div>
                <form:input type="text" path="healing.name" id="name" />
                <form:errors path="healing.name" />
                <label for="status">Type:</label>
                <form:select tabindex="0" path="healing.type" items="${statuses}" multiple="false" id="status"/>
                <form:errors path="healing.type"/>
            </div>
         </div>

        <div>
            <label for="name">Periodic:</label>from
            <form:input type="text" path="dateFrom" id="name" />
            <div>
                <form:errors path="dateFrom" />
            </div>to
            <form:input type="text" path="dateTo" id="name" />
            <div>
                <form:errors path="dateTo" />
            </div>
        </div>


        <table>
            <thead>
            <tr>
                <th>Day name</th>
                <th>Day parts</th>
                <th>links</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${recipes.dayNames}" var="dayName">
                <tr>
                    <th>${dayName.name}</th>
                    <th>
                        <c:forEach items="${dayName.dayParts}" var="dayPart">
                            ${dayPart.part}:${dayPart.doze}<br>
                        </c:forEach>
                    </th>
                    <sec:authorize access="hasRole('DOCTOR')">
                        <td><a href="<c:url value='/recipe/edit/${recipes.patient.insuranceId}' />" method="POST">edit</a></td>
                        <td><a href="<c:url value='/recipe/delete/${recipes.patient.insuranceId}' />" method="POST">delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <div>
            <br>
            <input type="submit" value="Register" />
        </div>
    </form:form>
</div>
<%@include file="../zfooter.jsp" %>
</body>

</html>