<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<security:authorize access="hasRole('ROLE_USER')" var="isUser" />
<security:authorize access="hasRole('ROLE_SELLER')" var="isSeller" />
<c:set var="isAuthenticated" value="${!user.isAuthenticated()||user.getPrincipal().equals('anonymousUser')}"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${page_title}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/responsive.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://js.stripe.com/v3/"></script>
</head>
<body>
<div class="wrapper">
    <header class="section-header">
        <section class="header-main border-bottom">
            <div class="container">
                <div class="row gy-3 align-items-center">
                    <div class="col-lg-2 col-sm-4 col-4">
                        <a href="${pageContext.request.contextPath}/" class="navbar-brand">
                            <img class="logo" height="40" src="${pageContext.request.contextPath}/img/assets/logo.svg" alt="">
                        </a>
                    </div>
                    <div class="order-lg-last col-lg-5 col-sm-8 col-8">
                        <div class="float-end">
                            <c:if test="${isUser or isAuthenticated}">
                                <a href="${pageContext.request.contextPath}/profile/wishlist" class="btn btn-light">
                                    <i class="fa fa-heart"></i>  <span class="ms-1 d-none d-sm-inline-block">Wishlist</span>
                                </a>
                                <a href="${pageContext.request.contextPath}/cart" class="btn btn-light">
                                    <i class="fa fa-shopping-cart"></i> <span class="ms-1">My cart </span>
                                </a>
                            </c:if>
                            <c:if test="${isSeller}">
                                <a href="/profile/seller/add" class="btn btn-primary"> <i class="me-1 fa fa-plus-circle"></i> Add item </a>
                            </c:if>
                            <c:choose>
                                <c:when test="${isAuthenticated}">
                                    <a href="${pageContext.request.contextPath}/signin" class="btn btn-outline-primary">
                                        <i class="fa fa-user"></i>  <span class="ms-1 d-none d-sm-inline-block">Sign in</span>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <div class="d-inline-flex align-middle dropdown">
                                        <button class="btn btn-outline-primary btn-icon" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false">
                                            <i class="fa fa-user"></i>
                                        </button>
                                        <nav class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenu2" style="">
                                            <a class="dropdown-item" href="/profile<c:if test='${!user.getAuthorities().toArray()[0].toString().equals(\'ROLE_USER\')}'>/${user.getAuthorities().toArray()[0].toString().toLowerCase().substring(5)}</c:if>">Profile</a>
                                            <c:if test='${user.getAuthorities().toArray()[0].toString().equals(\'ROLE_USER\')}'>
                                                <a class="dropdown-item" href="/profile/orders-history">Orders History</a>
                                                <a class="dropdown-item" href="/profile/wishlist">Wishlist</a>
                                                <a class="dropdown-item" href="/profile/transactions">Transactions</a>
                                            </c:if>
                                            <a class="dropdown-item" href="/profile/settings">Settings</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
                                        </nav>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="col-lg-5 col-md-12 col-12">
                        <form id="search-form" class="m-0">
                            <div class="input-group">
                                <input type="search" name="query" value="${search_query}" class="form-control" style="width:55%" placeholder="Search">
                                <button class="btn btn-primary">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </header>

    <div class="main bg-light">