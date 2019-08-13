<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" description="Page Title" %>
<%@attribute name="header" fragment="true" description="Page Header" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />

<!DOCTYPE HTML>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">
    <link rel="stylesheet" href="/res/css/jquery-ui.min.css">
    <link rel="stylesheet" href="/res/css/fa-all.css">
    <link rel="stylesheet" href="/res/css/bootstrap.min.css">
    <script src="/res/js/jquery-3.4.1.min.js"></script>
    <script src="/res/js/jquery-ui.min.js"></script>
    <title>${title}</title>
</head>

<body>
<jsp:invoke fragment="header"/>
<div class="container">
    <jsp:doBody/>
</div>
</body>

</html>