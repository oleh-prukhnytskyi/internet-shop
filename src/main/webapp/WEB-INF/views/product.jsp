<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />
<c:set var="isAuthenticated" value="${user.isAuthenticated()&&!user.getPrincipal().equals('anonymousUser')}"/>
<fmt:formatNumber var="rating" value="${averageRating * 2}" maxFractionDigits="0"/>

<section class="padding-y bg-white">
    <div class="container">
        <div class="row">
            <aside class="col-lg-6">
                <article class="gallery-wrap ${product.images.size() > 1 ? 'gallery-vertical' : ''}">
                    <div class="img-big-wrap img-thumbnail">
                        <a href="/img/products/${product.images[0].url}" data-fslightbox="gallery" data-type="image" data-type="image">
                            <img height="520" src="/img/products/${product.images[0].url}">
                        </a>
                    </div>
                    <div class="thumbs-wrap mb-3">
                        <c:forEach items="${product.images}" var="img" begin="1">
                            <a href="/img/products/${img.url}" data-fslightbox="gallery" data-type="image" class="item-thumb">
                                <img width="60" height="60" src="/img/products/${img.url}">
                            </a>
                        </c:forEach>
                    </div>
                </article>
            </aside>
            <div class="col-lg-6">
                <article class="ps-lg-3">
                    <h4 class="title text-dark">${product.title}</h4>
                    <c:if test="${reviews.size()!=0}">
                        <div class="rating-wrap my-3">
                            <ul class="rating-stars">
                                <li style="width:${rating*10}%" class="stars-active">
                                    <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg" alt="">
                                </li>
                                <li>
                                    <img height="520" src="${pageContext.request.contextPath}/img/assets/starts-disable.svg" alt="">
                                </li>
                            </ul>
                            <b class="label-rating text-warning me-0"> ${averageRating}</b>
                            <span class="text-muted align-text-top">(${reviews.size()})</span>
    <%--                        <i class="dot"></i>--%>
    <%--                        <span class="label-rating text-muted">--%>
    <%--                            <i class="fa fa-shopping-basket">--%>

    <%--                            </i> 154 orders </span>--%>
    <%--                        <i class="dot"></i>--%>
    <%--                        <span class="label-rating text-success">Verified</span>--%>
                        </div>
                    </c:if>
                    <div class="mb-3">
                        <var class="price h5">$ ${product.price}</var>
                    </div>
                    <p>${product.description}</p>
                    <hr>
                    <a href="/cart/add/${product.id}" class="btn btn-primary">
                        <i class="me-2 fa fa-shopping-basket"></i> Add to cart
                    </a>
                    <button class="btn <c:choose><c:when test="${wishlist_products.contains(product.id)}">btn-outline-primary</c:when><c:otherwise>btn-light</c:otherwise></c:choose>"
                            data-enabled="<c:choose><c:when test="${wishlist_products.contains(product.id)}">true</c:when><c:otherwise>false</c:otherwise></c:choose>"
                            id="wishBtn${product.id}"
                            onclick="wishList(${product.id})">
                        <i class="me-2 fa fa-heart"></i> Save
                    </button>
                </article>
            </div>
        </div>
    </div>
</section>

<section class="padding-y bg-light border-top">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Reviews </h5>
                        <hr>
                        <c:forEach var="review" items="${reviews}">
                            <blockquote class="border-bottom">
                                <c:if test="${user_reviews.contains(review.id)}">
                                    <div class="float-lg-end d-flex mb-3">
                                        <div class="d-inline-flex align-middle dropdown">
                                            <button class="btn btn-light dropdown-toggle btn-sm float-end remove-icon btn-dropdown" type="button" id="dropdownMenu2" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></button>
                                            <nav class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenu2" style="">
                                                <a class="dropdown-item" href="/product/${product.id}/delete/${review.id}">Delete</a>
                                            </nav>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="icontext">
                                    <img src="${pageContext.request.contextPath}/img/assets/avatar.webp" class="img-xs icon rounded-circle">
                                    <div class="text">
                                        <h6 class="mb-0">${review.name}</h6>
                                        <div class="rating-wrap">
                                            <ul class="rating-stars">
                                                <li style="width:${review.rating * 20}%" class="stars-active">
                                                    <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg" alt="">
                                                </li>
                                                <li>
                                                    <img src="${pageContext.request.contextPath}/assetsstarts-disable.svg" alt="">
                                                </li>
                                            </ul>
                                            <b class="dot"></b>
                                            <span class="label-rating text-muted">Not purchased</span>
<%--                                            <span class="label-rating text-success">Verified purchase</span>--%>
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-3">
                                    <p>${review.text}</p>
                                </div>
                            </blockquote>
                        </c:forEach>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${isAuthenticated}">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Write review</h5>
                                <hr>
                                <form method="post" action="/product/${product.id}" id="review-form">
                                    <div class="row mb-3">
                                        <div class="rating-group col-12 col-sm-8 col-md-6 justify-content-between">
                                            <input disabled checked class="rating__input rating__input--none" name="rating" id="rating3-none" value="0" type="radio">
                                            <label aria-label="1 star" class="rating__label d-grid text-center" for="rating3-1"><i class="rating__icon rating__icon--star fa fa-star"></i><div>Awful</div></label>
                                            <input class="rating__input" name="rating" id="rating3-1" value="1" type="radio">
                                            <label aria-label="2 stars" class="rating__label d-grid text-center" for="rating3-2"><i class="rating__icon rating__icon--star fa fa-star"></i><div>Poor</div></label>
                                            <input class="rating__input" name="rating" id="rating3-2" value="2" type="radio">
                                            <label aria-label="3 stars" class="rating__label d-grid text-center" for="rating3-3"><i class="rating__icon rating__icon--star fa fa-star"></i><div>Fair</div></label>
                                            <input class="rating__input" name="rating" id="rating3-3" value="3" type="radio">
                                            <label aria-label="4 stars" class="rating__label d-grid text-center" for="rating3-4"><i class="rating__icon rating__icon--star fa fa-star"></i><div>Good</div></label>
                                            <input class="rating__input" name="rating" id="rating3-4" value="4" type="radio">
                                            <label aria-label="5 stars" class="rating__label d-grid text-center" for="rating3-5"><i class="rating__icon rating__icon--star fa fa-star"></i><div>Excellent</div></label>
                                            <input class="rating__input" name="rating" id="rating3-5" value="5" type="radio">
                                        </div>
                                        <small class="invalid-feedback"></small>
                                    </div>
                                    <div class="row gx-3">
                                        <div class="col-12 col-sm-6 mb-4">
                                            <label class="form-label">Name</label>
                                            <input type="text" name="name" class="form-control" value="<c:if test="${not empty customer}">${customer.firstName} ${customer.lastName}</c:if>" placeholder="Type here">
                                            <small class="invalid-feedback"></small>
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Message</label>
                                        <textarea class="form-control" name="text" maxlength="2000" placeholder="Type here"></textarea>
                                        <small class="invalid-feedback"></small>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Submit request</button>
                                </form>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <article class="card mb-4">
                            <div class="content-body">
                                <div class="float-end">
                                    <a href="${pageContext.request.contextPath}/signup" class="btn btn-outline-primary">Register</a>
                                    <a href="${pageContext.request.contextPath}/signin" class="btn btn-primary">Sign in</a>
                                </div>
                                <h5>Have an account?</h5>
                                <p class="mb-0">Sign in or create an account to write reviews</p>
                            </div>
                        </article>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="col-lg-4">
                <article class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">About seller</h5>
                        <div class="itemside mb-4">
                            <div class="aside"> <img src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/brands/logo.webp" width="60" height="60" class="img-sm img-thumbnail"> </div>
                            <div class="info">
                                <a href="#" class="h6 title">Texnoshop LLC</a>
                                <div class="rating-wrap">
                                    <ul class="rating-stars">
                                        <li class="stars-active" style="width: 70%;"> <img src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/misc/stars-active.svg" alt=""> </li>
                                        <li> <img src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/misc/starts-disable.svg" alt=""> </li>
                                    </ul>
                                    <span class="label-rating text-muted">120 feedback</span>
                                </div>
                                <!-- rating-wrap.// -->
                                <p> <img height="20" src="https://bootstrap-ecommerce.com/bootstrap5-ecommerce/images/flags/flag-usa.webp"> United States, 2 years</p>
                            </div>
                        </div>
                        <p> Established in 1980, lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor ut labore et dolore ipsum </p>
                        <a href="#" class="btn w-100 btn-outline-primary">Visit store</a>
                    </div>
                </article>
                <article class="card">
                    <div class="card-body">
                        <div class="rating-wrap mt-3">
                            <ul class="rating-stars stars-lg">
                                <li style="width:${rating*10}%" class="stars-active">
                                    <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg" alt="">
                                </li>
                                <li>
                                    <img height="520" src="${pageContext.request.contextPath}/img/assets/starts-disable.svg" alt="">
                                </li>
                            </ul>
                            <b class="label-rating text-lg"> ${averageRating} out of 5 </b>
                        </div>
                        <p class="mt-1 text-muted"> ${reviews.size()} reviews </p>
                        <table class="table table-borderless table-sm">
                            <tbody>
                            <c:forEach begin="1" end="5" varStatus="loop">
                                <c:set var="percentage" value="${reviewStats.values().toArray()[loop.end - loop.index]/reviews.size()*100}"/>
                                <tr valign="middle">
                                    <td width="50"> ${reviewStats.keySet().toArray()[loop.end - loop.index]} star </td>
                                    <td>
                                        <div class="progress" style="height: 10px;">
                                            <div class="progress-bar bg-warning" role="progressbar" style="width: <fmt:parseNumber type='number' value='${percentage}'/>%;" aria-valuenow="25" aria-valuemax="100"></div>
                                        </div>
                                    </td>
                                    <td width="50" class="text-nowrap"> <fmt:parseNumber type='number' value='${percentage == "NaN" ? 0 : percentage}'/> % </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </article>
            </div>
        </div>
    </div>
</section>




<jsp:include page="/WEB-INF/includes/footer.jsp" />
