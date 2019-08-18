<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Events">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title=""/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <div>
            <form:form method="POST" modelAttribute="filter">
                <div class="table-responsive-sm">
                    <table class="table table-striped table-hover table-bordered table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th class="col-5">Name</th>
                            <th class="col-7">Surname</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <div class="form-group">
                                <th>
                                    <form:input type="text" path="name" id="name"
                                                class="form-control form-control-sm"/>
                                </th>
                                <th>
                                    <form:input type="text" path="surname" id="surname"
                                                class="form-control form-control-sm"/>
                                </th>
                            </div>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="table-responsive-sm">
                    <table class="table table-striped table-hover table-bordered table-sm">
                        <thead class="thead-dark">
                        <tr>
                            <th class="col-2">Status</th>
                            <th class="col-2">Healing type</th>
                            <th class="col-2">From date</th>
                            <th class="col-2">To date</th>
                            <th class="col-2">Sort by time</th>
                            <th class="col-2">In next hours</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <div class="form-group">
                                <th>
                                    <form:select path="status" multiple="false" id="status"
                                                 class="form-control form-control-sm">
                                        <form:option value="" label="--- All ---"/>
                                        <form:options items="${filter.statuses}"/>
                                    </form:select>
                                </th>
                                <th>
                                    <form:select path="healingType" multiple="false" id="status"
                                                 class="form-control form-control-sm">
                                        <form:option value="" label="--- All ---"/>
                                        <form:options items="${filter.healingTypes}"/>
                                    </form:select>
                                </th>
                                <th>
                                    <fmt:formatDate value="${filter.dateFrom}" var="dateFromString"
                                                    pattern="yyyy-MM-dd"/>
                                    <form:input type="date" path="dateFrom" id="dateFrom"
                                                value="${dateFromString}" class="form-control form-control-sm"/>
                                </th>
                                <th>
                                    <fmt:formatDate value="${filter.dateTo}" var="dateToString"
                                                    pattern="yyyy-MM-dd"/>
                                    <form:input type="date" path="dateTo" id="dateTo" value="${dateToString}"
                                                class="form-control form-control-sm"/>
                                </th>
                                <th><form:checkbox min="0" path="sortByTime" id="sortByTime" name="sortByTime"
                                                class="form-control form-control-sm"/>
                                <th><form:input type="number" min="0" path="nextHours" id="nextHours"
                                                class="form-control form-control-sm"/></th>
                            </div>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="text-center">
                    <th><input type="submit" value="Filt" class="btn btn-success mr-2 btn-sm"/></th>
                </div>

            </form:form>
        </div>

        <br>
        <div class="table-responsive-sm">
            <table class="table table-striped table-hover table-bordered table-sm">
                <thead class="thead-dark">
                <tr>
                    <th>Patient</th>
                    <th>DateTime</th>
                    <th>Status</th>
                    <th>Healing</th>
                    <%--<th>info</th>--%>
                    <th class="col-1"></th>

                    <sec:authorize access="hasRole('DOCTOR')">
                        <th class="col-1"></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dto}" var="event">
                    <tr>
                        <th>${event.recipe.patient.surname} ${event.recipe.patient.name} ${event.recipe.patient.patronymic}</th>
                        <fmt:formatDate value="${event.datestamp}" var="dateString" pattern="yyyy-MM-dd HH:mm"/>
                        <th>${dateString}</th>
                        <th>${event.status}</th>

                        <th>${event.recipe.healingType}: ${event.recipe.healingName} [${event.recipe.doze}]</th>
                            <%--<th>${event.info}</th>--%>

                            <%--<sec:authorize access="hasRole('DOCTOR')">--%>
                        <td><a href="<c:url value='/event/edit/${event.id}' />">details</a></td>
                            <%--</sec:authorize>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </jsp:body>
</t:content>