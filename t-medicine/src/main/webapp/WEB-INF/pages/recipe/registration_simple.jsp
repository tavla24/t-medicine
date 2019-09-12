<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Recipes">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title="Recipe registration"/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <br>

        <form:form method="POST" modelAttribute="dto">
            <form:input type="hidden" path="id" id="id"/>
            <form:input type="hidden" path="patient.insuranceId" id="patient.insuranceId"/>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label>Patient:</label> ${dto.patient.name} ${dto.patient.surname} ${dto.patient.patronymic}
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="healingName">Healing</label>
                        <form:input type="text" path="healingName" id="healingName"
                                    class="form-control form-control-sm mb-2 mr-2" required=""/>
                        <form:errors path="healingName" class="text-danger"/>

                        <form:select tabindex="0" path="healingType" items="${dto.sourceHealingTypes}" multiple="false"
                                     id="healingType" class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="healingType" class="text-danger"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="doze">Doze:</label>
                        <form:input type="text" path="doze" id="doze" class="form-control form-control-sm mb-2 mr-2" required=""/>
                        <form:errors path="doze" class="text-danger"/>
                    </div>
                </div>
            </div>

                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <div class="d-flex">
                        <label for="dateFrom" class="mb-2 mr-2 flex-fill p-2">Periodic:</label>

                        <label for="dateFrom" class="mb-2 mr-2 flex-fill p-2"> from </label>
                        <fmt:formatDate value="${dto.dateFrom}" var="dateFromString" pattern="yyyy-MM-dd"/>
                        <form:input type="date" path="dateFrom" id="dateFrom" value="${dateFromString}"
                                    class="form-control form-control-sm mb-2 mr-2 flex-fill p-2"/>


                        <label for="dateTo" class="mb-2 mr-2 flex-fill p-2"> to </label>
                        <fmt:formatDate value="${dto.dateTo}" var="dateToString" pattern="yyyy-MM-dd"/>
                        <form:input type="date" path="dateTo" id="dateTo" value="${dateToString}"
                                    class="form-control form-control-sm mb-2 mr-2 flex-fill p-2"/>

                        </div>
                        <div><form:errors path="dateFrom" class="text-danger"/></div>
                        <div><form:errors path="dateTo" class="text-danger"/></div>
                    </div>
                </div>

            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="d-flex">
                        <label for="dayPattern" class="mb-2 mr-2 flex-fill p-2">Day pattern:</label>
                        <div class="form-check form-check-inline">
                        <form:checkboxes path="dayPartsList" items="${dto.sourceDayParts}"
                                         class="form-check-input flex-fill mb-2 mr-2" id="dayPattern" style="margin-right:5px"/>
                        </div>
                    </div>
                    <form:errors path="dayPartsList" class="text-danger"/><br>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <form:select path="dayNamesList" items="${dto.sourceDayNames}" multiple="true"
                                 class="form-control form-control-sm"/>
                    <form:errors path="dayNamesList" class="text-danger"/>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="form-check text-center">

                    <label for="healthful" class="form-check-label" >Close recipe</label>
                    <form:checkbox path="healthful" id="healthful" class="form-check-input mb-2 mr-2" style="margin-left: -110px"/>
                    </div>
                    <form:errors path="healthful" class="text-danger"/>
                </div>
            </div>

            <br>
            <c:if test="${!dto.healthful}">
            <c:if test="${dto.patient.status == 'ILL'}">
            <div class="text-center">
                <input type="submit" value="Save" class="btn btn-success mr-2 btn-sm"/>
            </div>
            </c:if>
            </c:if>
        </form:form>

    </jsp:body>
</t:content>