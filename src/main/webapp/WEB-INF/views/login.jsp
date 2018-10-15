<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <h6 class="card-header" style="background-color: #535353; color: #ffffff;">Přihlášení uživatele</h6>
                    <div class="card-body">
                        <form>
                            <div class="form-group" style="max-width: 450px; margin: 0 auto;">
                                <div class="row" style="margin-bottom: 7px;">
                                    <input type="text" name="user" class="form-control" placeholder="Username">
                                </div>
                                <div class="row" style="margin-bottom: 7px;">
                                    <input type="password" name="pass" class="form-control" placeholder="Password">
                                </div>
                                <div class="row" style="margin-bottom: 7px;">
                                    <input type="submit" name="login" class="form-control" value="Login">
                                </div>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>