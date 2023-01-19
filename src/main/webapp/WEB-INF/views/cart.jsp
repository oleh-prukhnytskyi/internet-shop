<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

<section class="padding-y bg-light">
  <div class="container">
    <div class="row bg-light">
      <div class="col-lg-9">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title mb-4">Shopping cart</h5>
            <c:forEach var="product" items="${products}">
              <article class="row gy-3 mb-4">
                <div class="col-lg-6">
                  <div class="itemside me-lg-5">
                    <div class="aside">
                      <img src="/img/products/${product.product.images[0].url}" class="img-sm border rounded">
                    </div>
                    <div class="info">
                      <a href="/product/${product.product.id}" class="title mb-1 cutoff-text-2">${product.product.title}</a>
                    </div>
                  </div>
                </div>
                <div class="col-auto">
                  <div class="input-group input-spinner">
                    <button class="btn btn-light" <c:if test="${product.quantity <= 1}">disabled</c:if> type="button" id="m${product.product.id}" onclick="qty(${product.product.id}, 'remove')">
                      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="#999" viewBox="0 0 24 24">
                        <path d="M19 13H5v-2h14v2z"></path>
                      </svg>
                    </button>
                    <input type="number" min="1" class="form-control inp" disabled id="inp${product.product.id}" value="${product.quantity}">
                    <button class="btn btn-light" type="button" id="p${product.product.id}" onclick="qty(${product.product.id}, 'add')">
                      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="#999" viewBox="0 0 24 24">
                        <path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"></path>
                      </svg>
                    </button>
                  </div>
                </div>
                <div class="col-lg-2 col-sm-4 col-6">
                  <div class="price-wrap lh-sm">
                    <var class="price h6" data-price="${product.product.price}" id="price${product.product.id}">$ ${product.product.price * product.quantity}</var>
                    <br>
                    <small class="text-muted"> $ ${product.pricePer} / per item </small>
                  </div>
                </div>
                <div class="col-lg col-sm-4">
                  <div class="float-lg-end">
                    <button class="btn <c:choose><c:when test="${wishlist_products.contains(product.id)}">btn-outline-primary</c:when><c:otherwise>btn-light</c:otherwise></c:choose> btn-icon over_link"
                            data-enabled="<c:choose><c:when test="${wishlist_products.contains(product.id)}">true</c:when><c:otherwise>false</c:otherwise></c:choose>"
                            id="wishBtn${product.id}"
                            onclick="wishList(${product.id})">
                      <i class="fa fa-heart"></i>
                    </button>
                    <a href="/cart/remove-all/${product.product.id}" class="btn btn-light text-danger"> Remove</a>
                  </div>
                </div>
              </article>
            </c:forEach>
          </div>
          <div class="content-body border-top">
            <p><i class="me-2 text-muted fa-lg fa fa-truck"></i> Free Delivery within 1-2 weeks</p>
            <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
              tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
              quis nostrud exercitation ullamco laboris nisi ut aliquip</p>
          </div>
        </div>
      </div>
      <aside class="col-lg-3">
        <div class="card">
          <div class="card-body">
            <dl class="dlist-align">
              <dt>Total:</dt>
              <dd class="text-end text-dark h5" id="total"> $ ${products.stream().map(cart -> cart.getTotalPrice()).sum()} </dd>
            </dl>
            <div class="d-grid gap-2 my-3">
              <a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary w-100"> Make Purchase </a>
              <a href="${pageContext.request.contextPath}/" class="btn btn-light w-100"> Back to shop </a>
            </div>
          </div>
        </div>
      </aside>
    </div>
  </div>
</section>
<jsp:include page="/WEB-INF/includes/footer.jsp" />