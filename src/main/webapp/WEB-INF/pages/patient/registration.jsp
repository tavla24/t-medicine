<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Patient registration">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:title_login title=""/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <br>

        <form:form method="POST" modelAttribute="patient">
            <div class="form-group">

                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="name">First Name</label>
                        <form:input type="text" path="name" id="name" class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="name"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="surname">Surname</label>
                        <form:input type="text" path="surname" id="surname" class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="surname"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="patronymic">Patronymic</label>
                        <form:input type="text" path="patronymic" id="patronymic" class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="patronymic"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="insuranceId">Insurance Id</label>
                        <form:input type="text" path="insuranceId" id="insuranceId"
                                    class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="insuranceId"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="diagnosis">Diagnosis</label>
                        <form:input type="text" path="diagnosis" id="diagnosis" class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="diagnosis"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="status">Status</label>
                        <form:select tabindex="1" path="status" items="${statuses}" multiple="false" id="status"
                                     class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="status"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="email">email</label>
                        <form:input type="text" path="email" id="email" class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="email"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <label for="phone">phone</label>
                        <form:input type="text" path="phone" id="phone" class="form-control form-control-sm mb-2 mr-2"/>
                        <form:errors path="phone"/>
                    </div>
                </div>
            </div>

            <br>
            <div class="text-center">
                <input type="submit" value="Register" class="btn btn-success  mb-2 mr-2 btn-sm"/>
            </div>
        </form:form>

    </jsp:body>
</t:content>