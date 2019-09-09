<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@tag description="Page template" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" description="Page Title" %>
<%@attribute name="header" fragment="true" description="Page Header" %>
<%@attribute name="footer" fragment="true" description="Page Footer" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}"/>

<!DOCTYPE HTML>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}/">

    <%--<link rel="stylesheet" type="text/css" href="res/css/font-awesome.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="res/css/bootstrap.min.css">--%>
    <%--<script src="res/css/jquery-3.4.1.js"></script>--%>
    <%--<script src="res/css/bootstrap.min.js"></script>--%>

    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="res/css/style.css">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic&display=swap" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>

    <title>${title}</title>
</head>
<body>
<header>
    <jsp:invoke fragment="header"/>
</header>
<div class="container container_bg">
    <jsp:doBody/>
</div>

<footer>
    <%--<div class="navbar-fixed-bottom row-fluid">--%>
        <%--<div class="navbar-inner">--%>
            <div class="container main-content">
                <div class="d-flex justify-content-between">
                    <div class="footer-left">
                        <i>SPb, 2019</i>
                    </div>
                    <div class="footer-right">
                        <a href="http://vk.com"><i class="fa fa-vk" aria-hidden="true"></i></a>
                        <a href="http://facebook.com"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                        <a href="http://twitter.com"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                    </div>
                </div>
            </div>
        <%--</div>--%>
    <%--</div>--%>
</footer>

</body>
</html>