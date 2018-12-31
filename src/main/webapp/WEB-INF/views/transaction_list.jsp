<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style">

                    <h6 class="card-header card-header-style">Pohyby</h6>

                    <div class="card-body">

                        <div class="row">
                            <div class="col-sm-12 col-md-2 col-lg-2 font-weight-bold">Datum</div>
                            <div class="col-sm-12 col-md-8 col-lg-8 font-weight-bold">Platba</div>
                            <div class="col-sm-12 col-md-2 col-lg-2 font-weight-bold text-right">Částka</div>
                        </div>
                        <hr>
                        <c:forEach items="${transactions}" var="item" varStatus="i">
                        <div class="row">
                            <div class="col-sm-12 col-md-2 col-lg-2 size-14 align-center">
                                <fmt:formatDate type="date" pattern="dd.MM.yyyy" value="${item.maturity}" />
                                <br>
                                <a href="${pageContext.request.contextPath}/transaction/${item.id}/detail">[Více]</a>
                            </div>
                            <div class="col-sm-12 col-md-8 col-lg-8">
                                <div class="row">
                                    <div class="col-sm-12 col-md-12 col-lg-12">
                                        <c:if test="${item.income eq true}">
                                            Příchozí úhrada (${item.number}/${item.code})
                                        </c:if>
                                        <c:if test="${item.income eq false}">
                                            Odchozí úhrada (${item.number}/${item.code})
                                        </c:if>
                                    </div>
                                </div>
                                <div class="row size-14">
                                    <div class="col-sm-12 col-md-12 col-lg-4">
                                        Variabilní: ${item.variableSymbol}
                                    </div>
                                    <div class="col-sm-12 col-md-12 col-lg-4">
                                        Specifický: ${item.specificSymbol}
                                    </div>
                                    <div class="col-sm-12 col-md-12 col-lg-4">
                                        Konstantní: ${item.constantSymbol}
                                    </div>
                                </div>
                            </div>
                            <c:if test="${item.income eq true}">
                                <div class="col-sm-12 col-md-2 col-lg-2 align-self-center text-success text-right">
                                    <span>
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.value}" /> CZK
                                    </span>
                                </div>
                            </c:if>
                            <c:if test="${item.income eq false}">
                                <div class="col-sm-12 col-md-2 col-lg-2 align-self-center text-danger text-right">
                                    <span>
                                        -<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.value}" /> CZK
                                    </span>
                                </div>
                            </c:if>

                        </div>
                            <hr>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>