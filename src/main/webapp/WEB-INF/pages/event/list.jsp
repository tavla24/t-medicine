<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Doctors List</title>
</head>

<body>
    <div>
        <div>
            <span class="lead">List of Events, ${loggedinuser}</span>
        </div>
        <table>
            <thead>
                <tr>
                    <th>???????</th>
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
                <c:forEach items="${doctors}" var="doctor">
                    <tr>
                        <th>${doctor.name}</th>
                        <th>${doctor.surname}</th>
                        <th>${doctor.patronymic}</th>

                        <th>${doctor.specialization}</th>
                        <th>${doctor.email}</th>
                        <th>${doctor.phone}</th>
                        <th>${doctor.login}</th>

                        <th></th>
                        <sec:authorize access="hasRole('DOCTOR')">
                            <td><a href="<c:url value='/event/edit/${doctor.login}' />">edit</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('DOCTOR')">
                            <td><a href="<c:url value='/event/delete/${doctor.login}' />">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../zfooter.jsp"%>
</body>

</html>