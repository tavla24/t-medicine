<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

        <div>
            <form:form method="POST" modelAttribute="account">
                <div class="form-group">
                    <div class="row">
                        <div class="col-md-6 offset-md-3">

                            <label for="login">Login</label>
                            <form:input type="text" path="login" id="login" class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="login"/>
                        </div>
                    </div>
                      <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <label for="password">Password</label>
                            <form:input type="password" path="password" id="password"
                                        class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="password"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 offset-md-3">
                            <label for="role">Select role</label>
                            <form:select path="role.type" items="${roles}" multiple="false" id="role"
                                         class="form-control form-control-sm mb-2 mr-2"/>
                            <form:errors path="role.type"/>
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