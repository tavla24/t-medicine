<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Patients">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title=""/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <br>
        <div class="table-responsive-sm">
            <table class="table table-striped table-hover table-bordered table-sm">
                <thead class="thead-dark">
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Patronymic</th>

                    <th>InsuranceId</th>
                    <th>Diagnosis</th>
                        <%--<th>Status</th>--%>

                    <th>Doctor</th>
                        <%--
                                        <th>email</th>
                                        <th>Phone</th>
                        --%>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DOCTOR')">
                        <th class="col-1"></th>
                        <th class="col-1"></th>
                        <th class="col-1"></th>
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
                            <%--<th>${patient.status}</th>--%>

                        <th>${patient.doctor.name}</th>

                            <%--<th>${patient.email}</th>
                           <th>${patient.phone}</th>--%>

                        <sec:authorize access="hasRole('ADMIN') or hasRole('DOCTOR')">
                            <td><a href="<c:url value='/patient/edit/${patient.insuranceId}' />">details</a></td>
                            <td><a href="<c:url value='/patient/delete/${patient.insuranceId}' />">delete</a></td>
                            <td><a href="<c:url value='/recipe/list/${patient.insuranceId}' />">recipes</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <sec:authorize access="hasRole('DOCTOR')">
                <br>
                <a class="col-12 btn btn-success" href="<c:url value="/patient/new" />">Register new patient</a>
            </sec:authorize>
        </div>
    </jsp:body>
</t:content>