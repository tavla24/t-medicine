<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Main Page Title">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:title_login title="Main Page Title 2"/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <div class="card-deck">
            <div>
                <span><a href="<c:url value="/login" />">Login</a></span> <br>
                <span><a href="<c:url value="/logout" />">Logout</a></span> <br>
                <span>Only for users with ADMIN role  (for example: login [admin] password [2222])</span> <br>
                <span><a href="<c:url value="/admin/account/" />">Admin panel</a></span> <br>
                <span>Only for users with DOCTOR role (for example: login [doctor] password [4444])</span> <br>
                <span><a href="<c:url value="/doctor/" />">Doctor panel</a></span> <br>
                <span><a href="<c:url value="/test/test" />">test</a></span> <br>
                <span><a href="<c:url value="/test/error" />">error</a></span> <br>
                <span><a href="<c:url value="/event/list" />">events list</a></span> <br>
            </div>
        </div>
    </jsp:body>
</t:content>