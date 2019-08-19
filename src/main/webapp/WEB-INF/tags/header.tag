<%@tag description="Header" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--bg-success btn-success--%>
<nav class="navbar navbar-expand-md navbar-light shadow-sm">

    <a class="navbar-brand" href="<c:url value="/" />"><%--Bolni<i class="rletter">&bull;<b style="font-size: 120%">T</b>&bull;</i>chka--%>
        <i class="fa fa-home" aria-hidden="true" style="font-size: 110%">Bolni<i class="rletter">T</i>chka</i>
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <jsp:doBody/>
    </div>

</nav>