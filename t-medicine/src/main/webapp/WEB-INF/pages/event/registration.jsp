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
            <t:navmenu title="Event registration"/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <br>

        <div>
            <form:form method="POST" modelAttribute="dto">
                <form:input type="hidden" path="id"/>
                <form:input type="hidden" path="recipe.id"/>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="name">Patient</label>
                            <form:input type="text" path="recipe.patient.surname" id="name" readonly="true"
                                        class="form-control form-control-sm"/>
                            <form:input type="text" path="recipe.patient.name" id="name" readonly="true"
                                        class="form-control form-control-sm"/>
                            <form:input type="text" path="recipe.patient.patronymic" id="name" readonly="true"
                                        class="form-control form-control-sm"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="healing">Healing</label>
                            <form:input type="text" path="recipe.healingType" id="healing" readonly="true"
                                        class="form-control form-control-sm"/>
                            <form:input type="text" path="recipe.healingName" id="healing" readonly="true"
                                        class="form-control form-control-sm"/>
                            <form:input type="text" path="recipe.doze" id="healing" readonly="true"
                                        class="form-control form-control-sm"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="datetime">DateTime</label>
                            <fmt:formatDate value="${dto.datestamp}" var="dateString" pattern="yyyy-MM-dd HH:mm"/>
                            <form:input type="BOTH" path="datestamp" id="datetime" value="${dateString}" readonly="true"
                                        class="form-control form-control-sm"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="status">Status</label>
                            <form:select path="status" multiple="false" id="status" items="${dto.eventStatusList}"
                                         class="form-control form-control-sm"/>
                            <label for="info">Comment:</label>
                            <form:input type="text" path="info" id="info" class="form-control form-control-sm"/>
                            <form:errors path="info" class="text-danger"/>
                        </div>
                    </div>
                </div>
                <br>
                <div class="text-center">
                    <input type="submit" value="Save" class="btn btn-success mr-2 btn-sm mb-2 mr-2"/>
                </div>
            </form:form>
        </div>

    </jsp:body>
</t:content>