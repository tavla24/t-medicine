<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@tag description="Simple Title and Login button panel" pageEncoding="UTF-8" %>
<%@attribute name="title" required="true" %>


<c:url var="post_login" value="/logincmd"/>
<c:url var="v_login" value="LogIn"/>
<c:if test="${loggedinuser != null}">
    <c:url var="post_login" value="/logoutcmd"/>
    <c:url var="v_login" value="LogOut"/>
</c:if>

<div class="d-flex">
    <span class="navbar-text">${title}</span>
</div>

<c:if test="${loggedinuser == null}">
<div class="d-flex ">
    <div class="d-flex flex-row-reverse">
        <form class="form m-1 form-inline" method="POST" action="${post_login}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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
            <button type="submit" class="btn btn-success mr-2 btn-sm">Log in...</button>
        </form>
    </div>
</div>
</c:if>

<c:if test="${loggedinuser != null}">
    <div class="d-flex dropdown">
        <button class="btn dropdown-toggle btn-sm" type="button" data-toggle="dropdown" aria-haspopup="true"
                aria-expanded="false">${v_login}</button>
        <div class="dropdown-menu dropdown-menu-right">
            <form class="form" method="POST" action="${post_login}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-success btn-sm">Log out...</button>
            </form>
        </div>
    </div>
</c:if>