<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Accounts">
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
                <th>Login</th>
                <th>Password</th>
                <th>Role</th>
                <sec:authorize access="hasRole('ADMIN') or hasRole('ROOT')">
                    <th class="col-1"></th>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <th class="col-1"></th>
                </sec:authorize>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dto}" var="account">
                <tr>
                    <th>${account.login}</th>
                    <th>${account.password}</th>
                    <th>${account.role.type}</th>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('ROOT')">
                        <td><a href="<c:url value='/account/edit/${account.login}' />">edit</a></td>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/account/delete/${account.login}' />">delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
            <br>
            <a class="col-12 btn btn-success" href="<c:url value="/account/new" />">Create new account</a>
        </div>

    </jsp:body>
</t:content>