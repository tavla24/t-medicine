<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Patients">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title="Patients list"/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <br>
        <div class="table-responsive-sm">
            <table class="table table-striped table-hover table-bordered table-sm">
                <thead class="thead-light">
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
                        <%--<th class="col-1"></th>--%>
                        <th class="col-1"></th>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th class="col-1"></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dto}" var="patient">
                    <c:url var="link" value='/patient/edit/${patient.insuranceId}' />
                    <tr onclick="window.location='${link}';">
                        <td>${patient.name}</td>
                        <td>${patient.surname}</td>
                        <td>${patient.patronymic}</td>

                        <td>${patient.insuranceId}</td>
                        <td>${patient.diagnosis}</td>
                            <%--<th>${patient.status}</th>--%>

                        <td>${patient.doctor.surname}</td>

                            <%--<th>${patient.email}</th>
                           <th>${patient.phone}</th>--%>


                            <%--<td><a href="<c:url value='/patient/edit/${patient.insuranceId}' />">details</a></td>--%>
                            <td><a href="<c:url value='/recipe/list/${patient.insuranceId}' />">recipes</a></td>
                        <sec:authorize access="hasRole('ADMIN')">
                            <td>
                                <div class="text-center">
                                    <a href="<c:url value='/patient/delete/${patient.insuranceId}' />"><img src="res/img/remove.png" style="width: 16px; height: 16px;" alt="Textual description of bar.png"/></a>
                                </div></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <sec:authorize access="hasRole('ADMIN') or hasRole('DOCTOR')">
                <br>
                <a class="col-12 btn btn-success" href="<c:url value="/patient/new" />">Register new patient</a>
            </sec:authorize>

        </div>
    </jsp:body>
</t:content>