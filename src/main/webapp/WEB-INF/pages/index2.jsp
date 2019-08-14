<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:content title="Main Page">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:title_login title=""/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <div class="row d-flex justify-content-center">
            <div class="col-md-6 container-fluid">
                <p class="text-center" style="margin-top: 50px"> Enter insurance Id or login</p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 offset-md-3">
                <form action="/todo" method="POST" >
                    <div class="input-group">
                    <input type="text" id="insId" name="insId" class="form-control"
                           placeholder="Enter ensurance id" required>
                    </div>
                    <br>
                    <div class="text-center">
                        <button type="submit" class="btn btn-success mr-2 btn-sm">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </jsp:body>
</t:content>