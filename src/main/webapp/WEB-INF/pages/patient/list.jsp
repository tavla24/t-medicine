<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Patients List</title>
</head>

<body>
    <div>
        <div>
            <span class="lead">List of Patients, doctor ${loggedinuser}</span>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Patronymic</th>

                    <th>InsuranceId</th>
                    <th>Diagnosis</th>
                    <th>Status</th>
                    
                    <th>Doctor</th>
                    <th>recipe</th>

                    <th>email</th>
                    <th>Phone</th>

                    <th>todo: link details</th>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('ROOT')">
                        <th width="100"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th width="100"></th>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th width="100"></th>
                    </sec:authorize>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${patients}" var="patient">
                    <tr>
                        <th>${patient.name}</th>
                        <th>${patient.surname}</th>
                        <th>${patient.patronymic}</th>

                        <th>${patient.insuranceId}</th>
                        <th>${patient.diagnosis}</th>
                        <th>${patient.status}</th>

                        <th>${patient.doctor.name}</th>
                        <th>recipe</th>

                        <th>${patient.email}</th>
                        <th>${patient.phone}</th>

                        <th></th>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DOCTOR')">
                            <td><a href="<c:url value='/patient/edit/${patient.insuranceId}' />">edit</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DOCTOR')">
                            <td><a href="<c:url value='/patient/delete/${patient.insuranceId}' />">delete</a></td>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DOCTOR')">
                            <td><a href="<c:url value='/patient/recipe/${patient.insuranceId}' />">recipe</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../zfooter.jsp"%>
</body>

</html>