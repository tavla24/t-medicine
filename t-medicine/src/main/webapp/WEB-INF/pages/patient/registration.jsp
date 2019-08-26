<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Patients">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title="Patient registration"/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <br>

        <div>
            <form:form method="POST" modelAttribute="dto">
                <form:input type="hidden" path="oldInsuranceId" id="oldInsuranceId"/>
                <sec:authorize access="hasRole('ADMIN')">
                        <div class="row">
                            <div class="col-md-6 offset-md-3">
                                <div class="form-group">
                                    <label for="doctor.login">Doctor's login</label>
                                    <form:input type="text" path="doctor.login" id="doctor.login"
                                                class="form-control form-control-sm"/>
                                    <form:errors path="doctor.login" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                </sec:authorize>

                <sec:authorize access="hasRole('DOCTOR')">
                    <form:input type="hidden" path="doctor.login"/>
                </sec:authorize>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="name">First Name</label>
                            <form:input type="text" path="name" id="name"
                                        class="form-control form-control-sm "/>
                            <form:errors path="name" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="surname">Surname</label>
                            <form:input type="text" path="surname" id="surname"
                                        class="form-control form-control-sm"/>
                            <form:errors path="surname" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="patronymic">Patronymic</label>
                            <form:input type="text" path="patronymic" id="patronymic"
                                        class="form-control form-control-sm"/>
                            <form:errors path="patronymic" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="insuranceId">Insurance Id</label>
                            <form:input type="text" path="insuranceId" id="insuranceId"
                                        class="form-control form-control-sm"/>
                            <form:errors path="insuranceId" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="diagnosis">Diagnosis</label>
                            <form:input type="text" path="diagnosis" id="diagnosis"
                                        class="form-control form-control-sm"/>
                            <form:errors path="diagnosis" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="status">Status</label>
                            <form:select tabindex="1" path="status" items="${dto.statuses}" multiple="false" id="status"
                                         class="form-control form-control-sm"/>
                            <form:errors path="status" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mb-2 mr-2">
                            <label for="email">email</label>
                            <form:input type="text" path="email" id="email"
                                        class="form-control form-control-sm"/>
                            <form:errors path="email" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3  mb-2 mr-2">
                            <label for="phone">phone</label>
                            <form:input type="text" path="phone" id="phone"
                                        class="form-control form-control-sm"/>
                            <form:errors path="phone" class="text-danger"/>
                        </div>
                    </div>
                </div>

                <br>
                <div class="text-center">
                    <input type="submit" value="Register" class="btn btn-success  mb-2 mr-2 btn-sm"/>
                </div>
            </form:form>
        </div>

    </jsp:body>
</t:content>