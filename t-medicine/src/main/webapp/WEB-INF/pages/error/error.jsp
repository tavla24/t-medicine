<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<script type='text/javascript'>
$(function(){
$("#my-div").removeClass('d-none');
});
</script>

<t:content title="Exception">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <div class="row d-flex justify-content-center">
            <div class="col-md-6 container-fluid">
                <p class="text-center" style="margin-top: 50px"> ${errorMessage} <br></p>
                <%--<a href="#my-div">show</a>--%>
                <div id="my-div" class="d-none">${exception}</div>
            </div>
        </div>
    </jsp:body>
</t:content>