<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

<section class="padding-y">
    <div class="container">
        <div class="row">
            <aside class="col-lg-3 col-xl-3">
                <div class="card p-3 h-100">
                    <nav class="nav flex-lg-column nav-pills mb-4">
                        <a class="nav-link <c:if test="${page_menu.equals('account')or page_menu.equals('track')}">active</c:if>" href="${pageContext.request.contextPath}/profile">Account</a>
                        <a class="nav-link <c:if test="${page_menu.equals('orders-history')}">active</c:if>" href="${pageContext.request.contextPath}/profile/orders-history">Orders history</a>
                        <a class="nav-link <c:if test="${page_menu.equals('wishlist')}">active</c:if>" href="${pageContext.request.contextPath}/profile/wishlist">My wishlist</a>
                        <a class="nav-link <c:if test="${page_menu.equals('transactions')}">active</c:if>" href="${pageContext.request.contextPath}/profile/transactions">Transactions</a>
                        <a class="nav-link <c:if test="${page_menu.equals('settings')}">active</c:if>" href="${pageContext.request.contextPath}/profile/settings">Profile setting</a>
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Log out</a>
                    </nav>
                </div>
            </aside>
            <main class="col-lg-9  col-xl-9">
                <c:if test="${not empty page_menu}">
                    <c:import url="profile-pages/${page_menu}.jsp"/>
                </c:if>
            </main>
        </div>
        <br><br>
    </div>
</section>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
