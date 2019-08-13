<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>

<%@tag description="Simple Title and Login button panel" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>


<c:url var="post_login" value="/logincmd"/>
<c:url var="v_login" value="Log in..."/>
<c:if test="${loggedinuser != null}">
    <c:url var="post_login" value="/logoutcmd"/>
    <c:url var="v_login" value="Log out..."/>
</c:if>

<div class="d-flex">
    <span class="navbar-text">${title}</span>
</div>

<c:if test="${loggedinuser != null}">
    <sec:authorize access="hasRole('ADMIN')">
        <ul class="nav navbar-nav navbar-custom">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/account/list" />">Accounts</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/doctor/list" />">Doctors</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/event/list" />">Events</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/patient/" />">Patients</a>
            </li>
        </ul>
    </sec:authorize>
    <sec:authorize access="hasRole('DOCTOR')">
        <ul class="nav navbar-nav navbar-custom">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/patient/" />">Patients</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/doctor/edit" />">Edit profile</a>
            </li>
        </ul>
    </sec:authorize>
    <sec:authorize access="hasRole('NURSE')">
        <ul class="nav navbar-nav navbar-custom">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/event/list" />">Events</a>
            </li>
        </ul>
    </sec:authorize>
</c:if>

<div class="d-flex ">
    <div class="d-flex flex-row-reverse">
        <form class="form m-1 form-inline" method="POST" action="${post_login}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <c:if test="${loggedinuser == null}">
                <div class="input-group input-group-sm mr-2 col-xs-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="aria-login" for="login"><i class="fa fa-user"></i></span>
                    </div>
                    <input type="text" required class="form-control col-xs-2" id="login" name="login" placeholder="Login"
                           aria-describedby="aria-login">
                </div>

                <div class="input-group input-group-sm mr-2 col-xs-2">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="aria-password" for="password"><i
                                class="fa fa-lock"></i></span>
                    </div>
                    <input type="password" required class="form-control col-xs-2" id="password" name="password"
                           placeholder="Password" aria-describedby="aria-password">
                </div>
            </c:if>
            <button type="submit" class="btn btn-success mr-2 btn-sm">${v_login}</button>
        </form>
    </div>
</div>