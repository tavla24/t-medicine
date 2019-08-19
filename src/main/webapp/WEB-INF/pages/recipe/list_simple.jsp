<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Recipes">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title="Recipes list"/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <br>
        <div class="table-responsive-sm">
            <table class="table table-striped table-hover table-bordered table-sm">
                <thead class="thead-light">
                <tr>
                    <th>Healing</th>
                    <th>dateFrom</th>
                    <th>dateTo</th>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DOCTOR')">
                        <th class="col-1"></th>
                        <th class="col-1"></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dto}" var="recipe">
                    <tr>
                        <th>${recipe.healingType}:${recipe.healingName}</th>
                        <th>${recipe.dateFrom}</th>
                        <th>${recipe.dateTo}</th>
                        <sec:authorize access="hasRole('ADMIN') or hasRole('DOCTOR')">
                            <td><a href="<c:url value='/recipe/edit/${insuranceId}/${recipe.id}' />">details</a></td>
                            <td><a href="<c:url value='/recipe/delete/${insuranceId}/${recipe.id}' />">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <a  class="col-12 btn btn-success" href="<c:url value="/recipe/new/${insuranceId}" />">New recipe</a>
        </div>
    </jsp:body>
</t:content>