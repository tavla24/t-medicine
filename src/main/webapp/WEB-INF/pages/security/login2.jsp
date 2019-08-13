<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="LogIn">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:title_login title=""/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <div class="row d-flex justify-content-center">
            <div class="col-md-6 container-fluid">
                    <c:url var="loginUrl" value="/logincmd" />
                    <form action="${loginUrl}" method="POST" style="margin-top: 50px">
                        <c:if test="${param.error != null}">
                            <div>
                                <p>Invalid username or password.</p>
                            </div>
                        </c:if>
                        <input type="hidden" name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                        <div class="input-group">
                            <input class="form-control"  type="text" id="username" name="login" placeholder="Enter Username" required>
                        </div>
                        <br>
                        <div class="input-group">
                            <input class="form-control"  type="password" id="password" name="password" placeholder="Enter Password" required>
                        </div>
                        <br>
                        <div class="text-right">
                            <button type="submit" class="btn btn-success mr-2 btn-sm">Log in...</button>
                        </div>
                    </form>

                </div>
            </div>
    </jsp:body>
</t:content>