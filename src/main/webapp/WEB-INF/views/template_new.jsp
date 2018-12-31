<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style">
                    <h6 class="card-header card-header-style">Nový vzor platby</h6>
                    <div class="card-body">
                        <form:form modelAttribute="template" action="${pageContext.request.contextPath}/template/new/add"
                                   method="post">
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                    Název
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="name" type="text" class="form-control" maxlength="50"/>
                                    <form:errors class="text-danger" path="name"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                    Na účet
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6 col">
                                    <div class="row">
                                        <div class="col-sm-7 col-md-7 col-lg-7">
                                            <form:input path="number" type="text" class="form-control" maxlength="17"/>
                                            <form:errors class="text-danger" path="number"/>
                                        </div>
                                        <div class="col-sm-1 col-md-1 col-lg-1 align-self-center text-center size-25">
                                            /
                                        </div>
                                        <div class="col-sm-4 col-md-4 col-lg-4">
                                            <form:input path="code" type="text" class="form-control" maxlength="4"/>
                                            <form:errors class="text-danger" path="code"/>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                    Částka
                                </div>
                                <div class="col-sm-12 col-md-5 col-lg-5">
                                    <form:input path="value" type="text" class="form-control" maxlength="15"/>
                                    <form:errors class="text-danger" path="value"/>
                                </div>
                                <div class="col-sm-12 col-md-1 col-lg-1 align-self-center text-right">CZK</div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                    Variabilní symbol
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="variableSymbol" type="text" class="form-control" maxlength="10"/>
                                    <form:errors class="text-danger" path="variableSymbol"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                    Konstantní symbol
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="constantSymbol" type="text" class="form-control" maxlength="4"/>
                                    <form:errors class="text-danger" path="constantSymbol"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                    Specifický symbol
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="specificSymbol" type="text" class="form-control" maxlength="10"/>
                                    <form:errors class="text-danger" path="specificSymbol"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-2">
                                <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                    Zpráva
                                </div>
                                <div class="col-sm-12 col-md-6 col-lg-6">
                                    <form:input path="message" type="text" class="form-control" maxlength="100"/>
                                    <form:errors class="text-danger" path="message"/>
                                </div>
                            </div>
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