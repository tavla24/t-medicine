<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
</head>

<body>
    <div>
        <span><strong>${loggedinuser}</strong>, welcome </span>

        <div >User Registration Form</div>
        <form:form method="POST" modelAttribute="account">

            <div>
                <label for="login">Login</label>
                <div>
                    <form:input type="text" path="login" id="login" />
                        <div>
                            <form:errors path="login"/>
                        </div>
                </div>
            </div>

            <div>
                <label for="password">Password</label>
                <div>
                    <form:input type="password" path="password" id="password" />
                        <div>
                            <form:errors path="password"/>
                        </div>
                </div>
            </div>

			<div>
				<div>
					<label for="role">Roles</label>
					<div>
						<form:select path="role" items="${roles}" multiple="false" />
						<div class="has-error">
							<form:errors path="role"/>
						</div>
					</div>
				</div>
			</div>

            <div>
                <br>
                <input type="submit" value="Register" />
            </div>
        </form:form>
    </div>
    <%@include file="../zfooter.jsp" %>
</body>

</html>