<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style">
                    <h6 class="card-header card-header-style">Nový uživatel</h6>
                    <div class="card-body">
                        <form:form modelAttribute="userForm" action="${pageContext.request.contextPath}/new-user/add" method="post">
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Jméno*
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <form:input path="firstname" type="text" class="form-control"/>
                                <form:errors class="text-danger" path="firstname"/>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Příjmení*
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <form:input path="lastname" type="text" class="form-control"/>
                                <form:errors class="text-danger" path="lastname"/>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Rodné číslo*
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <form:input path="pid" type="text" class="form-control"/>
                                <form:errors class="text-danger" path="pid"/>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Adresa
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <form:input path="address" type="text" class="form-control"/>
                                <form:errors class="text-danger" path="address"/>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Číslo popisné
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <form:input path="addressNumber" type="text" class="form-control"/>
                                <form:errors class="text-danger" path="addressNumber"/>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                PSČ
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <form:input path="zipCode" type="text" class="form-control"/>
                                <form:errors class="text-danger" path="zipCode"/>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Stát*
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
                                Email*
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <form:input path="email" type="text" class="form-control"/>
                                <form:errors class="text-danger" path="email"/>
                            </div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-12 col-lg-12 text-right">
                                <button type="submit" class="btn btn-primary btn-sm button_primary_new">Vytvořit
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