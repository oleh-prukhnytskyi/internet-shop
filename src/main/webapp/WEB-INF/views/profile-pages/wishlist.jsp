<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h5 class="card-title"> Wishlist </h5>

<div class="row">
    <c:forEach var="product" items="${products.content}">
        <fmt:formatNumber var="rating" value="${reviewsAverage.get(product.id) * 2}" maxFractionDigits="0"/>
        <div class="col-xl-3 col-lg-4 col-md-6 col-sm-6 col-6 d-flex ps-1 pe-1">
            <figure class="card card-product-grid w-100">
                <div class="img-wrap">
                    <img src="/img/products/${product.images[0].url}">
                </div>
                <figcaption class="info-wrap border-top flex-column flex-fill d-flex">

                    <div class="price-wrap">
                        <strong class="price">$ ${product.price}</strong>
                    </div>
                    <c:if test="${reviewsCount.get(product.id)!=0}">
                        <div class="rating-wrap mb-1">
                            <ul class="rating-stars">
                                <li class="stars-active" style="width: ${rating*10}%;">
                                    <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg" alt="">
                                </li>
                                <li>
                                    <img src="${pageContext.request.contextPath}/img/assets/starts-disable.svg" alt="">
                                </li>
                            </ul>
                            <span class="label-rating text-warning me-0">${reviewsAverage.get(product.id)}</span>
                            <span class="text-muted align-text-top">(${reviewsCount.get(product.id)})</span>
                        </div>
                    </c:if>
                    <a href="/product/${product.id}" class="stretched-link"></a>
                    <p class="title mb-2 ${reviewsCount.get(product.id)!=0 ? 'cutoff-text-2' : 'cutoff-text-3'}">${product.title}</p>

                    <div class="mt-auto">
                        <a href="/cart/move/${product.id}" class="btn btn-primary over_link">Move to cart</a>
                        <button class="btn btn-outline-primary btn-icon over_link"
                                data-enabled="true"
                                id="wishBtn${product.id}"
                                onclick="wishList(${product.id})">
                            <i class="fa fa-heart"></i>
                        </button>
                    </div>
                </figcaption>
            </figure>
        </div>
    </c:forEach>
    <c:if test="${products.totalPages > 1}">
        <hr>
        <footer class="d-flex mt-4">
            <nav>
                <ul class="pagination">
                    <c:if test="${products.hasPrevious()}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${products.number}">Previous</a>
                        </li>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${products.totalPages}">
                        <c:choose>
                            <c:when test="${i-1 == products.number}">
                                <li class="page-item active">
                                    <span class="page-link">${i}</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${i-1<=products.number+2&&i-1>=products.number-2||
                                                    products.number+1<=2&&5>=i||products.number+1>=
                                                    products.totalPages-2&&products.totalPages-4<=i}">
                                    <li class="page-item">
                                        <a class="page-link" href="?page=${i}">${i}</a>
                                    </li>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${products.hasNext()}">
                        <li class="page-item">
                            <a class="page-link" href="?page=${products.number+2}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </footer>
    </c:if>
</div>
