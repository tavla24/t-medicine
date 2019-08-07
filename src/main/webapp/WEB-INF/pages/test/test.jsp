<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script src="http://code.jquery.com/jquery-2.2.4.js"
            type="text/javascript"></script>
    <script src="../../js/progress.js" type="text/javascript"></script>
    <title>Ajax Form</title>
</head>

<body>

<form:form method="POST" modelAttribute="source" >
    <%--
    <c:forEach items="${source}" var="item" varStatus="loop">
        <form:input type="text" path="item.name" /><br />
        <form:input type="text" path="item.login" /><br />
    </c:forEach>
    --%>
    <form:input type="text" path="name" /><br />
    <form:input type="text" path="login" /><br />
</form:form>

<input type="button" value="send" id="ajbutt">

<%@include file="../zfooter.jsp" %>
</body>

</html>