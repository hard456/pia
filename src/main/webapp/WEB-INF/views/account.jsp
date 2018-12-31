<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">

                <div class="card card-style">
                    <h6 class="card-header card-header-style">Detail účtu</h6>
                    <div class="card-body">

                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Číslo účtu
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                ${account.number}/${bankCode}
                            </div>
                        </div>
                        <hr>

                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Aktuální hodnota účtu
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${account.balance}" />
                            </div>
                        </div>

                    </div>
                </div>

                <div class="card card-style mt-3">
                    <h6 class="card-header card-header-style">Kreditní karta</h6>
                    <div class="card-body">

                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Číslo kreditní karty
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                    ${fn:substring(account.cardNumber,0,4)}
                                    ${fn:substring(account.cardNumber,4,6)}** ****
                                    ${fn:substring(account.cardNumber,12,16)}
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </jsp:body>
</t:template>