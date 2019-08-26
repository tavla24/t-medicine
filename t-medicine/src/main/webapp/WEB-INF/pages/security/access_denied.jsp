<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Access">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title="Access Denied"/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <div class="row d-flex justify-content-center">

            <div class="col-md-6 container-fluid">
                <p class="text-center" style="margin-top: 50px"> <span>Sorry, access denied for <strong>${loggedinuser}</strong></span> <br></p>
            </div>
        </div>
    </jsp:body>
</t:content>