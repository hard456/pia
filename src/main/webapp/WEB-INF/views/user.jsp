<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <h6 class="card-header card-header-style">Uživatelský profil</h6>
                    <div class="card-body">

                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                Jméno
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                ${user.firstname}
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                Příjmení
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                ${user.lastname}
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
                                Rodné číslo
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                ${user.pid}
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center">
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