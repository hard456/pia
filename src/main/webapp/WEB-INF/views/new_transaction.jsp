<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style">
                    <h6 class="card-header card-header-style">Nová platba</h6>
                    <div class="card-body">
                        <form:form modelAttribute="transaction"
                                   action="${pageContext.request.contextPath}/transaction/new/add" method="post">
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Použít vzor
                                </div>

                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <select class="form-control" onchange="this.options[this.selectedIndex].value &&
                                        (window.location = this.options[this.selectedIndex].value);">
                                        <c:if test="${template != null}">
                                            <option value="${pageContext.request.contextPath}/transaction/new/${template.id}">${template.name}</option>
                                            <option value="${pageContext.request.contextPath}/transaction/new">
                                                Bez použití vzoru
                                            </option>
                                        </c:if>
                                        <c:if test="${template == null}">
                                            <option value="${pageContext.request.contextPath}/transaction/new">
                                                Bez použití vzoru
                                            </option>
                                        </c:if>
                                        <c:forEach items="${templates}" var="item" varStatus="i">
                                            <c:if test="${template != null}">
                                                <c:if test="${item.id != template.id}">
                                                    <option value="${pageContext.request.contextPath}/transaction/new/${item.id}">${item.name}</option>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${template == null}">
                                                <option value="${pageContext.request.contextPath}/transaction/new/${item.id}">${item.name}</option>
                                            </c:if>
                                        </c:forEach>>
                                    </select>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Na účet*
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6 col">
                                    <div class="row">
                                        <div class="col-sm-7 col-md-7 col-lg-7">
                                            <form:input path="number" type="text" class="form-control" maxlength="17"/>
                                        </div>
                                        <div class="col-sm-1 col-md-1 col-lg-1 align-self-center text-center size-25">
                                            /
                                        </div>
                                        <div class="col-sm-4 col-md-4 col-lg-4">
                                            <form:input path="code" type="text" class="form-control" maxlength="4"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-7 col-md-7 col-lg-7">
                                            <form:errors class="text-danger" path="number"/>
                                        </div>
                                        <div class="col-sm-1 col-md-1 col-lg-1 align-self-center text-center size-25">
                                        </div>
                                        <div class="col-sm-4 col-md-4 col-lg-4">
                                            <form:errors class="text-danger" path="code"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Částka*
                                </div>
                                <div class="col-sm-12 col-md-5 col-lg-5">
                                    <c:if test="${template == null}">
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${transaction.value}" var="newValue" groupingUsed="false"/>
                                    </c:if>
                                    <c:if test="${template != null}">
                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${template.value}" var="newValue" groupingUsed="false"/>
                                    </c:if>
                                    <form:input path="value" value="${newValue}" type="text" class="form-control" maxlength="15"/>
                                    <form:errors class="text-danger" path="value"/>
                                </div>
                                <div class="col-sm-12 col-md-1 col-lg-1 align-self-center text-right">CZK</div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Splatnost*
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">

                                    <%--Aktuální datum pro input date--%>
                                    <jsp:useBean id="dateNow" class="java.util.Date" />
                                    <fmt:formatDate var="actualDate" value="${dateNow}" pattern="yyyy-MM-dd" />

                                    <form:input path="maturity" min="${actualDate}" type="date" class="form-control" />
                                    <form:errors class="text-danger" path="maturity"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Variabilní symbol
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="variableSymbol" type="text" class="form-control" maxlength="10"/>
                                    <form:errors class="text-danger" path="variableSymbol"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Konstantní symbol
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="constantSymbol" type="text" class="form-control" maxlength="4"/>
                                    <form:errors class="text-danger" path="constantSymbol"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Specifický symbol
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="specificSymbol" type="text" class="form-control" maxlength="10"/>
                                    <form:errors class="text-danger" path="specificSymbol"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 font-weight-bold">
                                    Zpráva
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="message" type="text" class="form-control" maxlength="100"/>
                                    <form:errors class="text-danger" path="message"/>
                                </div>
                            </div>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-12 col-lg-12 text-right">
                                    <button type="submit" class="btn btn-primary btn-sm button_primary_new">Odeslat
                                    </button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>