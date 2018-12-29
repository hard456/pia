<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card card-style">
                    <h6 class="card-header card-header-style">Nová platba</h6>
                    <div class="card-body">

                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Použít vzor
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <select class="form-control">
                                    <option>Nevyplňovat podle vzoru</option>
                                    <option>Ubytování</option>
                                    <option>Menza</option>
                                    <option>Test11</option>
                                    <option>Test Ads</option>
                                </select>
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Na účet
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6 col">
                                <div class="row">
                                    <div class="col-sm-7 col-md-7 col-lg-7">
                                        <input type="text" class="form-control" maxlength="17">
                                    </div>
                                    <div class="col-sm-1 col-md-1 col-lg-1 align-self-center text-center size-25">
                                        /
                                    </div>
                                    <div class="col-sm-4 col-md-4 col-lg-4">
                                        <input type="text" class="form-control" maxlength="4">
                                    </div>

                                </div>
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Částka
                            </div>
                            <div class="col-sm-12 col-md-5 col-lg-5">
                                <input type="text" class="form-control" maxlength="15">
                            </div>
                            <div class="col-sm-12 col-md-1 col-lg-1 align-self-center text-right">CZK</div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Splatnost
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="date" class="form-control" maxlength="10">
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Variabilní symbol
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" maxlength="10">
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Konstantní symbol
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" maxlength="4">
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 align-self-center font-weight-bold">
                                Specifický symbol
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <input type="text" class="form-control" maxlength="10">
                            </div>
                        </div>
                        <hr>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-6 col-lg-6 font-weight-bold">
                                Zpráva
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <textarea class="form-control" rows="5" id="comment"></textarea>
                            </div>
                        </div>
                        <div class="row mb-2">
                            <div class="col-sm-12 col-md-12 col-lg-12 text-right">
                                <button type="button" class="btn btn-primary btn-sm button_primary_new">Odeslat</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>