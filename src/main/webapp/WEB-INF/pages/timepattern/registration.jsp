<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="http://code.jquery.com/jquery-2.2.4.js"
            type="text/javascript"></script>
    <script src="../../js/progress.js" type="text/javascript"></script>
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

        <table>
            <thead>
            <tr>
                <th>Day name</th>
                <th>Day parts</th>
                <th>links</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${recipes.dayNames}" var="dayName" varStatus="cntNames">
                <tr>
                    <th>${dayName.name}</th>
                    <th>
                        <c:forEach items="${dayName.dayParts}" var="dayPart" varStatus="cntParts">
                            ${dayPart.part}
                            <form:input type="text" path="dayNames[cntNames.index].dayParts[cntParts.index].doze" />
                            <br>
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