<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Doctors">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title="Doctors list"/>
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

                <th>Specialization</th>
                <th>email</th>
                <th>Phone</th>
                <th>Login</th>
                <sec:authorize access="hasRole('ADMIN')">
                    <%--<th class="col-1"></th>--%>
                    <th class="col-1"></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dto}" var="doctor">
                <c:url var="link" value='/admin/doctor/edit/${doctor.login}' />
                <tr onclick="window.location='${link}';">
                    <td>${doctor.name}</td>
                    <td>${doctor.surname}</td>
                    <td>${doctor.patronymic}</td>

                    <td>${doctor.specialization}</td>
                    <td>${doctor.email}</td>
                    <td>${doctor.phone}</td>
                    <td>${doctor.login}</td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <%--<td><a href="<c:url value='/admin/doctor/edit/${doctor.login}' />">edit</a></td>--%>
                        <td>
                            <div class="text-center">
                                <a href="<c:url value='/admin/doctor/delete/${doctor.login}' />"><img src="res/img/remove.png" style="width: 16px; height: 16px;" alt="Textual description of bar.png"/></a>
                            </div></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
            <sec:authorize access="hasRole('ADMIN')">
                <br>
                <a class="col-12 btn btn-success" href="<c:url value="/admin/doctor/new" />">Create new doctor profile</a>
            </sec:authorize>
        </div>

    </jsp:body>
</t:content>