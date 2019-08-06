<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Doctor Registration Form</title>
</head>

<body>
    <div>
        <span><strong>${loggedinuser}</strong>, welcome </span>

        <div>Doctor Registration Form</div>
        <form:form method="POST" modelAttribute="dto">


            <div>
                <label for="name">First Name</label>
                <div>
                    <form:input type="text" path="doctor.name" id="name" />
                    <div>
                        <form:errors path="doctor.name" />
                    </div>
                </div>
            </div>
            <div>
                <label for="surname">Surname</label>
                <div>
                    <form:input type="text" path="doctor.surname" id="surname" />
                    <div>
                        <form:errors path="doctor.surname" />
                    </div>
                </div>
            </div>
            <div>
                <label for="patronymic">Patronymic</label>
                <div>
                    <form:input type="text" path="doctor.patronymic" id="patronymic" />
                    <div>
                        <form:errors path="doctor.patronymic" />
                    </div>
                </div>
            </div>


            <div>
                <label for="specialization">Specialization</label>
                <div>
                    <form:input type="text" path="doctor.specialization" id="specialization" />
                    <div>
                        <form:errors path="doctor.specialization" />
                    </div>
                </div>
            </div>

            <div>
                <label for="email">email</label>
                <div>
                    <form:input type="text" path="doctor.email" id="email" />
                    <div>
                        <form:errors path="doctor.email" />
                    </div>
                </div>
            </div>
            <div>
                <label for="phone">phone</label>
                <div>
                    <form:input type="text" path="doctor.phone" id="phone" />
                    <div>
                        <form:errors path="doctor.phone" />
                    </div>
                </div>
            </div>

            <sec:authorize access="hasRole('ADMIN') or hasRole('ROOT')">
            <div>
                <label for="login">login</label>
                <div>
                    <form:input type="text" path="account.login" id="login" />
                    <div>
                        <form:errors path="account.login" />
                    </div>
                </div>
            </div>
            </sec:authorize>

            <div>
                <br>
                <input type="submit" value="Register" />
            </div>
        </form:form>
    </div>
    <%@include file="../zfooter.jsp" %>
</body>

</html>