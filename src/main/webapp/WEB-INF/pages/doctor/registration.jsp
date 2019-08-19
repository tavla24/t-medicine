<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Doctors">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title="Doctor registration"/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <br>

        <div>
            <form:form method="POST" modelAttribute="dto">
                <sec:authorize access="hasRole('ADMIN')">
                    <c:if test="${!dto.edit}">
                        <div class="row">
                            <div class="col-md-6 offset-md-3">
                                <div class="form-group">
                                    <label for="login">Login</label>
                                    <form:input type="text" path="login" id="login"
                                                class="form-control form-control-sm mb-2 mr-2"/>
                                    <form:errors path="login" class="text-danger"/>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </sec:authorize>

                <sec:authorize access="hasRole('DOCTOR')">
                    <form:input type="hidden" path="login"/>
                </sec:authorize>

                <div class="form-group">
                    <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <label for="name">First Name</label>
                            <form:input type="text" path="name" id="name"
                                        class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="name" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <label for="surname">Surname</label>
                            <form:input type="text" path="surname" id="surname"
                                        class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="surname" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <label for="patronymic">Patronymic</label>
                            <form:input type="text" path="patronymic" id="patronymic"
                                        class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="patronymic" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <label for="specialization">Specialization</label>
                            <form:input type="text" path="specialization" id="specialization"
                                        class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="specialization" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <label for="email">email</label>
                            <form:input type="text" path="email" id="email"
                                        class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="email" class="text-danger"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <label for="phone">phone</label>
                            <form:input type="text" path="phone" id="phone" placeholder="X-XXX-XXXXXXX"
                                        class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="phone" class="text-danger"/>
                        </div>
                    </div>
                </div>

                <br>
                <div class="text-center">
                    <input type="submit" value="Register" class="btn btn-success mr-2 btn-sm"/>
                </div>
            </form:form>
        </div>

    </jsp:body>
</t:content>