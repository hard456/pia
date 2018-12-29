<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style">
                    <h6 class="card-header card-header-style">Uživatelský profil</h6>
                    <div class="card-body">

                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Jméno
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" value="${user.firstname}" disabled>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Příjmení
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" value="${user.lastname}" disabled>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Rodné číslo
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" value="${user.pid}" disabled>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Adresa
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" value="${user.address}" disabled>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Číslo popisné
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" value="${user.addressNumber}" disabled>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                PSČ
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" value="${user.zipCode}" disabled>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Stát
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" value="${user.state.name}" disabled>
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Email
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" value="${user.email}">
                            </div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-12 col-lg-12 text-right">
                                <button type="button" class="btn btn-primary btn-sm button_primary_new">Uložit</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>