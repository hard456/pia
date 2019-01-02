<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<t:template>
    <jsp:body>

        <div class="card card-style">

            <h6 class="card-header-style card-header">Vzory plateb</h6>

            <div class="card-body">

                <div class="row mb-4">
                    <a href="${pageContext.request.contextPath}/template/new">
                        <input type="button" class="btn btn-primary button_primary_new" value="Přidat vzor">
                    </a>
                </div>

                <div class="row">
                    <div class="col-sm-12 col-md-3 col-lg-3 font-weight-bold">Název</div>
                    <div class="col-sm-12 col-md-3 col-lg-3 font-weight-bold">Účet</div>
                    <div class="col-sm-12 col-md-2 col-lg-2 font-weight-bold">Částka</div>
                    <div class="col-sm-12 col-md-4 col-lg-4 text-right">
                    </div>
                </div>
                <hr>

                <c:choose>
                    <c:when test="${empty templates}">
                        <div class="row">
                            <div class="col-sm-12 col-md-12 col-lg-12">
                                Nejsou k dispozici žádné šablony.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${templates}" var="item" varStatus="i">
                            <div class="row">
                                <div class="col-sm-12 col-md-3 col-lg-3 align-self-center">${item.name}</div>
                                <div class="col-sm-12 col-md-3 col-lg-3 align-self-center">${item.number}/${item.code}</div>
                                <div class="col-sm-12 col-md-3 col-lg-3 align-self-center">
                                    <c:if test="${not empty item.value}">
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                                          value="${item.value}"/> CZK
                                    </c:if>
                                </div>
                                <div class="col-sm-12 col-md-3 col-lg-3 text-right">
                                    <a href="${pageContext.request.contextPath}/transaction/new/${item.id}"
                                       class="text-decoration-none">
                                        <input type="button" class="btn btn-primary btn-sm button_primary_new"
                                               value="Použít">
                                    </a>
                                    <a href="${pageContext.request.contextPath}/template/${item.id}"
                                       class="text-decoration-none">
                                        <input type="button" class="btn btn-sm btn-info" value="Upravit">
                                    </a>
                                    <a href="${pageContext.request.contextPath}/template/${item.id}/delete"
                                       class="text-decoration-none">
                                        <input type="button" class="btn btn-sm btn-danger" value="Smazat">
                                    </a>
                                </div>
                            </div>
                            <c:if test="${!i.last}">
                                <hr>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <br>

            </div>
        </div>

    </jsp:body>
</t:template>
