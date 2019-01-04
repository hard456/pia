<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--URL--%>
<c:set var="userEditIdtUrl" value="${s:mvcUrl('userController#edit-id').build()}" scope="page"/>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style mb-5">
                    <h6 class="card-header card-header-style">Detail uživatele</h6>
                    <div class="card-body">

                        <form:form modelAttribute="userForm"
                                   action="${userEditIdtUrl}${userForm.id}" method="post">
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Účet
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                        ${account.number}/${bankCode}
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Mezinárodní platba kartou
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <c:choose>
                                        <c:when test="${account.internationalPayment == true}">
                                            Povolena
                                        </c:when>
                                        <c:otherwise>
                                            Zakázána
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Povolená částka do mínusu
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <fmt:formatNumber type="number" minFractionDigits="2"
                                                      maxFractionDigits="2"
                                                      value="${account.limitBelow}"/> CZK
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Jméno
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="firstname" type="text" class="form-control" maxlength="50"/>
                                    <form:errors class="text-danger" path="firstname"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Příjmení
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="lastname" type="text" class="form-control" maxlength="50"/>
                                    <form:errors class="text-danger" path="lastname"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Rodné číslo
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="pid" type="text" class="form-control" maxlength="10"/>
                                    <form:errors class="text-danger" path="pid"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Adresa
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="address" type="text" class="form-control" maxlength="50"/>
                                    <form:errors class="text-danger" path="address"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Číslo popisné
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="addressNumber" type="text" class="form-control" maxlength="8"/>
                                    <form:errors class="text-danger" path="addressNumber"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    PSČ
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="zipCode" type="text" class="form-control" maxlength="10"/>
                                    <form:errors class="text-danger" path="zipCode"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Stát
                                </div>

                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:select path="state" cssClass="form-control">
                                        <form:options items="${states}" itemValue="id" itemLabel="name"/>
                                    </form:select>
                                    <form:errors class="text-danger" path="state"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                    Email
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="email" type="email" class="form-control" maxlength="50"/>
                                    <form:errors class="text-danger" path="email"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-12 col-lg-12 text-right">
                                    <button type="submit" class="btn btn-primary btn-sm button_primary_new">Uložit
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