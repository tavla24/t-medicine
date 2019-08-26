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
<div class="d-flex dropdown">
    <button class="btn btn-outline-light dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true"
            aria-expanded="false">${v_login}</button>
    <div class="dropdown-menu dropdown-menu-right">

        <form class="form m-1" method="POST" action="${post_login}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <c:if test="${loggedinuser == null}">
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="aria-login" for="login"><i class="fa fa-user"></i></span>
                    </div>
                    <input type="text" required class="form-control" id="login" name="login" placeholder="Login"
                           aria-describedby="aria-login">
                </div>
                <br>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="aria-password" for="password"><i
                                class="fa fa-lock"></i></span>
                    </div>
                    <input type="password" required class="form-control" id="password" name="password"
                           placeholder="Password" aria-describedby="aria-password">
                </div>

                <div class="dropdown-divider"></div>
            </c:if>
            <button type="submit" class="btn btn-success btn-block">Log in...</button>
        </form>
    </div>
</div>