<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<style type="text/css">
    .texcon {
        padding-top: 4px;
        font-size: 12px;
    }

    .texblock {
        margin-top: 20px;
    }

    .imgcon {
        margin: 10px 20px 10px 20px;
        width: 200px;
        height: 100px;
    }
</style>

<t:content title="Main Page">
    <jsp:attribute name="header">
        <t:header>
        <jsp:body>
            <t:navmenu title=""/>
        </jsp:body>
        </t:header>
    </jsp:attribute>
    <jsp:body>
        <div class="text-center texblock"><h3>Recent Medical News</h3></div>
        <c:forEach items="${dto}" var="article">
        <div class="container texblock">
            <img src="${article.imgpath}" class="rounded float-left imgcon" alt="Cinque Terre">
            <div>
                <h5>${article.title}</h5>
                    ${article.text}
            </div>
        </div>
        </c:forEach>
    </jsp:body>
</t:content>
