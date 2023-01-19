<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

</div>
<section class="padding-y bg-light">
    <div class="container">
        <div class="row">
            <aside class="col-lg-3">
                <button class="btn btn-outline-secondary mb-3 w-100  d-lg-none" data-bs-toggle="collapse" data-bs-target="#aside_filter">Show filter</button>
                <!-- ===== Card for sidebar filter ===== -->
                <div id="aside_filter" class="collapse card d-lg-block mb-5">
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" class="title" data-bs-toggle="collapse" data-bs-target="#collapse_aside1">
                                <i class="icon-control fa fa-chevron-down"></i>
                                Related items
                            </a>
                        </header>
                        <div class="collapse show" id="collapse_aside1">
                            <div class="card-body">
                                <ul class="list-menu">
                                    <li><a href="#">Electronics </a></li>
                                    <li><a href="#">Home items  </a></li>
                                    <li><a href="#">Books, Magazines </a></li>
                                    <li><a href="#">Men's clothing </a></li>
                                    <li><a href="#">Interiors items </a></li>
                                    <li><a href="#">Underwears </a></li>
                                    <li><a href="#">Shoes for men </a></li>
                                    <li><a href="#">Accessories </a></li>
                                </ul>
                            </div>
                            <!-- card-body.// -->
                        </div>
                    </article>
                    <!-- filter-group // -->
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" class="title" data-bs-toggle="collapse" data-bs-target="#collapse_aside_brands">
                                <i class="icon-control fa fa-chevron-down"></i>
                                Brands
                            </a>
                        </header>
                        <div class="collapse show" id="collapse_aside_brands">
                            <div class="card-body">
                                <label class="form-check mb-2">
                                    <input class="form-check-input" type="checkbox" value="" checked="">
                                    <span class="form-check-label"> Mercedes </span>
                                    <b class="badge rounded-pill bg-gray-dark float-end">120</b>
                                </label> <!-- form-check end.// -->
                                <label class="form-check mb-2">
                                    <input class="form-check-input" type="checkbox" value="" checked="">
                                    <span class="form-check-label"> Toyota </span>
                                    <b class="badge rounded-pill bg-gray-dark float-end">15</b>
                                </label> <!-- form-check end.// -->
                                <label class="form-check mb-2">
                                    <input class="form-check-input" type="checkbox" value="" checked="">
                                    <span class="form-check-label"> Mitsubishi </span>
                                    <b class="badge rounded-pill bg-gray-dark float-end">35</b>
                                </label> <!-- form-check end.// -->
                                <label class="form-check mb-2">
                                    <input class="form-check-input" type="checkbox" value="" checked="">
                                    <span class="form-check-label"> Nissan </span>
                                    <b class="badge rounded-pill bg-gray-dark float-end">89</b>
                                </label> <!-- form-check end.// -->
                                <label class="form-check mb-2">
                                    <input class="form-check-input" type="checkbox" value="">
                                    <span class="form-check-label"> Honda </span>
                                    <b class="badge rounded-pill bg-gray-dark float-end">30</b>
                                </label>
                                <label class="form-check mb-2">
                                    <input class="form-check-input" type="checkbox" value="">
                                    <span class="form-check-label"> Honda accord </span>
                                    <b class="badge rounded-pill bg-gray-dark float-end">30</b>
                                </label>
                            </div>
                        </div>
                    </article>
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" class="title" data-bs-toggle="collapse" data-bs-target="#collapse_aside2">
                                <i class="icon-control fa fa-chevron-down"></i>
                                Price
                            </a>
                        </header>
                        <div class="collapse show" id="collapse_aside2">
                            <div class="card-body">
                                <input type="range" class="form-range" min="0" max="100">
                                <div class="row mb-3">
                                    <div class="col-6">
                                        <label for="min" class="form-label">Min</label>
                                        <input class="form-control" id="min" placeholder="$0" type="number">
                                    </div>
                                    <div class="col-6">
                                        <label for="max" class="form-label">Max</label>
                                        <input class="form-control" id="max" placeholder="$1,0000" type="number">
                                    </div>
                                </div>
                                <button class="btn btn-light w-100" type="button">Apply</button>
                            </div>
                        </div>
                    </article>
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" class="title" data-bs-toggle="collapse" data-bs-target="#collapse_aside3">
                                <i class="icon-control fa fa-chevron-down"></i>
                                Size
                            </a>
                        </header>
                        <div class="collapse show" id="collapse_aside3">
                            <div class="card-body">
                                <label class="checkbox-btn">
                                    <input type="checkbox">
                                    <span class="btn btn-light"> XS </span>
                                </label>
                                <label class="checkbox-btn">
                                    <input type="checkbox">
                                    <span class="btn btn-light"> SM </span>
                                </label>
                                <label class="checkbox-btn">
                                    <input type="checkbox">
                                    <span class="btn btn-light"> LG </span>
                                </label>
                                <label class="checkbox-btn">
                                    <input type="checkbox">
                                    <span class="btn btn-light"> XXL </span>
                                </label>
                            </div>
                        </div>
                    </article>
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" class="title" data-bs-toggle="collapse" data-bs-target="#collapse_aside4">
                                <i class="icon-control fa fa-chevron-down"></i> Ratings
                            </a>
                        </header>
                        <div class="collapse show" id="collapse_aside4">
                            <div class="card-body">
                                <label class="form-check mb-2">
                                    <input class="form-check-input" type="checkbox" value="" checked="">
                                    <span class="form-check-label">
                                        <ul class="rating-stars">
                                            <li class="stars-active" style="width: 100%;">
                                                <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg">
                                            </li>
                                            <li><img src="${pageContext.request.contextPath}/img/assets/starts-disable.svg"></li>
                                        </ul>
                                    </span>
                                    </label>
                                    <label class="form-check mb-2">
                                        <input class="form-check-input" type="checkbox" value="" checked="">
                                        <span class="form-check-label">
                                  <ul class="rating-stars">
                                     <li class="stars-active" style="width: 80%;">
                                        <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg">
                                     </li>
                                     <li> <img src="${pageContext.request.contextPath}/img/assets/starts-disable.svg">  </li>
                                  </ul>
                               </span>
                                    </label>
                                    <label class="form-check mb-2">
                                        <input class="form-check-input" type="checkbox" value="" checked="">
                                        <span class="form-check-label">
                                  <ul class="rating-stars">
                                     <li class="stars-active" style="width: 60%;">
                                        <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg">
                                     </li>
                                     <li> <img src="${pageContext.request.contextPath}/img/assets/starts-disable.svg">  </li>
                                  </ul>
                               </span>
                                    </label>
                                    <label class="form-check mb-2">
                                        <input class="form-check-input" type="checkbox" value="" checked="">
                                        <span class="form-check-label">
                                  <ul class="rating-stars">
                                     <li class="stars-active" style="width: 40%;">
                                        <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg">
                                     </li>
                                     <li> <img src="${pageContext.request.contextPath}/img/assets/starts-disable.svg">  </li>
                                  </ul>
                               </span>
                                    </label>
                            </div>
                        </div>
                    </article>
                </div>
            </aside>
            <main class="col-lg-9">
                <header class="d-sm-flex align-items-center border-bottom mb-4 pb-3">
                    <strong class="d-block py-2">${products.totalElements} Items found </strong>
                    <form action="${pageContext.request.contextPath}/" method="post" class="ms-auto m-0">
                        <select class="form-select d-inline-block w-auto">
                            <option value="0">Best match</option>
                            <option value="1">Recommended</option>
                            <option value="2">High rated</option>
                            <option value="3">Randomly</option>
                        </select>
                        <div class="btn-group">
                            <button value="grid" name="listLayout" class="btn btn-light <c:if test="${userSettings.listLayout=='grid' or empty userSettings.listLayout}">active</c:if>" data-bs-toggle="tooltip" title="" data-bs-original-title="Grid view">
                                <i class="fa fa-th"></i>
                            </button>
                            <button value="list" name="listLayout" class="btn btn-light <c:if test="${userSettings.listLayout=='list'}">active</c:if>" data-bs-toggle="tooltip" title="" data-bs-original-title="List view">
                                <i class="fa fa-bars"></i>
                            </button>
                        </div>
                    </form>
                </header>
                <c:choose>
                <c:when test="${userSettings.listLayout == 'list'}"><div class="row m-0"></c:when>
                <c:otherwise><div class="row ms-n1 me-n1"></c:otherwise>
                </c:choose>
                    <c:forEach items="${products.content}" var="product">
                        <fmt:formatNumber var="rating" value="${reviewsAverage.get(product.id) * 2}" maxFractionDigits="0"/>
                        <c:choose>
                            <c:when test="${userSettings.listLayout == 'list'}">
                                <article class="card card-product-list">
                                    <div class="row g-0">
                                        <aside class="col-xl-3 col-md-4">
                                            <a href="#" class="img-wrap">
                                                <img src="/img/products/${product.images[0].url}">
                                            </a>
                                        </aside>
                                        <div class="col-xl-6 col-md-5 col-sm-7">
                                            <div class="card-body">
                                                <a href="/product/${product.id}" class="stretched-link"></a>
                                                <a href="#" class="title h5 cutoff-text-2"> ${product.title} </a>
                                                <c:if test="${reviewsCount.get(product.id)!=0}">
                                                    <div class="rating-wrap mb-2">
                                                        <ul class="rating-stars">
                                                            <li class="stars-active" style="width: ${rating*10}%;">
                                                                <img src="${pageContext.request.contextPath}/img/assets/stars-active.svg" alt="">
                                                            </li>
                                                            <li> <img src="${pageContext.request.contextPath}/img/assets/starts-disable.svg" alt=""> </li>
                                                        </ul>
                                                        <span class="label-rating text-warning me-0">${reviewsAverage.get(product.id)}</span>
                                                        <span class="text-muted align-text-top">(${reviewsCount.get(product.id)})</span>
                                                            <%--                                                    <i class="dot"></i>--%>
                                                            <%--                                                    <span class="label-rating text-muted">74 orders</span>--%>
                                                    </div>
                                                </c:if>
                                                <p class="over_link ${reviewsCount.get(product.id)!=0?'cutoff-text-4':'cutoff-text-5'}">${product.description}</p>
                                            </div>
                                        </div>
                                        <aside class="col-xl-3 col-md-3 col-sm-5">
                                            <div class="info-aside">
                                                <div class="price-wrap">
                                                    <span class="price h5 over_link"> $ ${product.price} </span>
                                                </div>
                                                <br>
                                                <div class="mb-3">
                                                    <a href="/cart/add/${product.id}" class="btn btn-primary over_link"> Add to cart </a>
                                                    <button class="btn ${wishlist_products.contains(product.id) ? 'btn-outline-primary' : 'btn-light'} btn-icon over_link"
                                                            data-enabled="${wishlist_products.contains(product.id) ? 'true' : 'false'}"
                                                            id="wishBtn${product.id}"
                                                            onclick="wishList(${product.id})">
                                                        <i class="fa fa-heart"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </aside>
                                    </div>
                                </article>
                            </c:when>
                            <c:otherwise>
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
                                                <a href="/cart/add/${product.id}" class="btn btn-primary over_link">Add to cart</a>
                                                <button class="btn ${wishlist_products.contains(product.id) ? 'btn-outline-primary' : 'btn-light'} btn-icon over_link"
                                                        data-enabled="${wishlist_products.contains(product.id) ? 'true' : 'false'}"
                                                        id="wishBtn${product.id}"
                                                        onclick="wishList(${product.id})">
                                                    <i class="fa fa-heart"></i>
                                                </button>
                                            </div>
                                        </figcaption>
                                    </figure>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
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
            </main>
        </div>
    </div>
</section>



<jsp:include page="/WEB-INF/includes/footer.jsp" />
