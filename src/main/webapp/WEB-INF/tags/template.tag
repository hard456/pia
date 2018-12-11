<%@ tag description="Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>

<html lang="cs">
<head>

    <%-- meta tags --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Own CSS -->
    <spring:url value="/css/style.css" var="style"/>
    <link rel="stylesheet" href="${style}">

    <!-- Bootstrap 4.1.3 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <%--FONT AWESOME--%>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
          integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">

    <title>Bank</title>
</head>
<body class="body_bg">

<!-- DEFAULT MENU-->

<nav class="navbar navbar-expand-lg navbar-dark navbar-bg">

    <div class="container-fluid container-max-width">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">e-Banking</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link navbar-color" href="${pageContext.request.contextPath}/about-us">O nás</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link navbar-color" href="${pageContext.request.contextPath}/contact">Kontakt</a>
                </li>
            </ul>
            <ul class="navbar-nav navbar-right">

                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link navbar-color" href="${pageContext.request.contextPath}/user">
                            <i class="fas fa-user"></i>
                            <sec:authentication property="principal.firstName"/>
                            <sec:authentication property="principal.lastName"/>
                        </a>
                    </li>
                </sec:authorize>

                <li class="nav-item">
                    <sec:authorize access="!isAuthenticated()">
                        <a class="nav-link navbar-color" href="${pageContext.request.contextPath}/login">Přihlášení</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a class="nav-link navbar-color" href="${pageContext.request.contextPath}/logout">Odhlášení</a>
                    </sec:authorize>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%-- USER MENU --%>
<sec:authorize access="hasAuthority('USER')">
    <nav class="navbar navbar-expand-lg navbar-dark navbar-user-bg">

        <div class="container-fluid container-max-width">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link navbar-user-color" href="${pageContext.request.contextPath}/new-transaction">Účet</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link navbar-user-color" href="${pageContext.request.contextPath}/new-transaction">Nová
                        platba</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link navbar-user-color"
                       href="${pageContext.request.contextPath}/transaction-list">Pohyby</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link navbar-user-color" href="${pageContext.request.contextPath}/templates">Vzory plateb</a>
                </li>
            </ul>
        </div>
    </nav>
</sec:authorize>

<%-- ADMIN MENU --%>
<sec:authorize access="hasAuthority('ADMIN')">
    <nav class="navbar navbar-expand-lg navbar-dark navbar-user-bg">

        <div class="container-fluid container-max-width">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link navbar-user-color" href="${pageContext.request.contextPath}/about-us">Přidat
                        uživatele</a>
                </li>
            </ul>
        </div>
    </nav>
</sec:authorize>

<%-- ALERT --%>
<%--<div class="row" style="max-width: 1000px; margin: 0 auto;">--%>
    <%--<div class="col-md-12">--%>
        <%--<div class="alert alert-success alert-dismissible">--%>
            <%--<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>--%>
            <%--<strong>Success!</strong> Indicates a successful or positive action.--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<!-- BODY -->
<div id="body" class="mt-3">
    <div class="container-fluid container-max-width">
        <jsp:doBody/>
    </div>
</div>

</body>
</html>