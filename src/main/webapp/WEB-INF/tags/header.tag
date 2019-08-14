<%@tag description="Header" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--bg-success --%>
<nav class="navbar navbar-expand-md navbar-dark bg-success shadow-sm">

    <a class="navbar-brand" href="<c:url value="/" />">
        <i class="fa fa-home" aria-hidden="true" style="font-size: 150%"></i>
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <jsp:doBody/>
    </div>

</nav>