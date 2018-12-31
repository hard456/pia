<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style">
                    <h6 class="card-header card-header-style">Seznam uživatelů</h6>
                    <div class="card-body">

                        <div class="row">
                            <div class="col-sm-12 col-md-3 col-lg-3 font-weight-bold">Jméno</div>
                            <div class="col-sm-12 col-md-3 col-lg-3 font-weight-bold">Příjmení</div>
                            <div class="col-sm-12 col-md-3 col-lg-3 font-weight-bold">Rodné číslo</div>
                            <div class="col-sm-12 col-md-3 col-lg-3 text-right">
                            </div>
                        </div>
                        <hr>
                        <c:forEach items="${users}" var="item" varStatus="i">
                        <div class="row">
                            <div class="col-sm-12 col-md-3 col-lg-3 align-self-center">${item.firstname}</div>
                            <div class="col-sm-12 col-md-3 col-lg-3 align-self-center">${item.lastname}</div>
                            <div class="col-sm-12 col-md-3 col-lg-3 align-self-center">${fn:substring(item.pid,0,6)}/${fn:substring(item.pid,6,11)}</div>
                            <div class="col-sm-12 col-md-3 col-lg-3 text-right">
                                <a href="${pageContext.request.contextPath}/user/${item.id}">
                                <button type="button" class="btn btn-primary btn-sm button_primary_new">Detail</button>
                                </a>
                            </div>
                        </div>
                            <c:if test="${!i.last}"><hr></c:if>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>