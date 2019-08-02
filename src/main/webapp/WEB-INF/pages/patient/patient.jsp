<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Patient Registration Form</title>
</head>

<body>
    <div>
        <span><strong>${loggedinuser}</strong>, welcome </span>

        <div>Patient Registration Form</div>
        <form:form method="POST" modelAttribute="account">

            <div>
                <label for="name">First Name</label>
                <div>
                    <form:input type="text" path="name" id="name" />
                    <div>
                        <form:errors path="name" />
                    </div>
                </div>
            </div>
            <div>
                <label for="surname">Surname</label>
                <div>
                    <form:input type="text" path="surname" id="surname" />
                    <div>
                        <form:errors path="surname" />
                    </div>
                </div>
            </div>
            <div>
                <label for="patronymic">Patronymic</label>
                <div>
                    <form:input type="text" path="patronymic" id="patronymic" />
                    <div>
                        <form:errors path="patronymic" />
                    </div>
                </div>
            </div>


            <div>
                <label for="insuranceId">Insurance Id</label>
                <div>
                    <form:input type="text" path="insuranceId" id="insuranceId" />
                    <div>
                        <form:errors path="insuranceId" />
                    </div>
                </div>
            </div>
            <div>
                <label for="diagnosis">Diagnosis</label>
                <div>
                    <form:input type="text" path="diagnosis" id="diagnosis" />
                    <div>
                        <form:errors path="diagnosis" />
                    </div>
                </div>
            </div>
            <div>
                <label for="status">Status</label>
                <div>
                    <form:input type="text" path="status" id="status" />
                    <div>
                        <form:errors path="status" />
                    </div>
                </div>
            </div>

            <div>
                <label for="email">email</label>
                <div>
                    <form:input type="text" path="email" id="email" />
                    <div>
                        <form:errors path="email" />
                    </div>
                </div>
            </div>
            <div>
                <label for="phone">phone</label>
                <div>
                    <form:input type="text" path="phone" id="phone" />
                    <div>
                        <form:errors path="phone" />
                    </div>
                </div>
            </div>
            <div>
                <label for="login">login</label>
                <div>
                    <form:input type="text" path="login" id="login" />
                    <div>
                        <form:errors path="login" />
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