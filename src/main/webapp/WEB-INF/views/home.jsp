<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style">
                    <h6 class="card-header card-header-style">Domů</h6>
                    <div class="card-body">
                        <c:forEach items="${users}" var="c">
                            ${c.firstname}
                        </c:forEach>


                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>