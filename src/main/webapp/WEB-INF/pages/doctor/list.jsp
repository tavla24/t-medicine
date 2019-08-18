<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Doctors">
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

                <th>Specialization</th>
                <th>email</th>
                <th>Phone</th>
                <th>Login</th>
                <sec:authorize access="hasRole('ADMIN')">
                    <th class="col-1"></th>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <th class="col-1"></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dto}" var="doctor">
                <tr>
                    <th>${doctor.name}</th>
                    <th>${doctor.surname}</th>
                    <th>${doctor.patronymic}</th>

                    <th>${doctor.specialization}</th>
                    <th>${doctor.email}</th>
                    <th>${doctor.phone}</th>
                    <th>${doctor.login}</th>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/admin/doctor/edit/${doctor.login}' />">edit</a></td>
                        <td><a href="<c:url value='/admin/doctor/delete/${doctor.login}' />">delete</a></td>
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