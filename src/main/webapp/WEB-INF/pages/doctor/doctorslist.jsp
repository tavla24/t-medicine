<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Doctors List</title>
</head>

<body>
    <div>
        <div>
            <span class="lead">List of Doctors, ${loggedinuser}</span>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Patronymic</th>

                    <th>Specialization</th>
                    <th>email</th>
                    <th>Phone</th>
                    <th>Login</th>

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
                        <sec:authorize access="hasRole('ADMIN') or hasRole('ROOT')">
                            <td><a href="<c:url value='/person/edit-user-${doctor.login}' />">edit</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td><a href="<c:url value='/person/delete-user-${doctor.login}' />">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../zfooter.jsp"%>
</body>

</html>