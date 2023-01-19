<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<article class="card">
  <div class="content-body">
    <h5 class="card-title"> Track Order </h5>
    <br>
    <ul class="steps-vertical border-0 pb-3">
      <li class="step active">
        <b class="icon"></b>
        <h6 class="title mb-0">Order received</h6>
        <p class="text-muted"> Something happened on this period </p>
      </li>
      <li class="step <c:if test="${order.order.trackStatus>=1}">active</c:if>">
        <b class="icon"></b>
        <h6 class="title mb-0">Confirmation</h6>
        <p class="text-muted"> That is confirmed by us </p>
      </li>
      <li class="step <c:if test="${order.order.trackStatus>=2}">active</c:if>">
        <b class="icon"></b>
        <h6 class="title mb-0">Out for delivery</h6>
        <p class="text-muted"> Dummy info like we are an good </p>
      </li>
      <li class="step <c:if test="${order.order.trackStatus>=3}">active</c:if>">
        <b class="icon"></b>
        <h6 class="title mb-0">Delivered</h6>
        <p class="text-muted"> Lorem ipsum is not good info </p>
      </li>
    </ul>
    <hr>
    <h5 class="card-title"> Details </h5>
    <div class="row">
      <div class="col-lg-6">
        <p class="mb-0 text-muted">Shipping address</p>
        <p class="m-0"> ${order.order.address.city} <br>
          ${order.order.address.address} ${order.order.address.postCode}
        </p>
      </div>
      <div class="col-lg-6 border-start">
        <p class="mb-0 text-muted">Contact</p>
        <p class="m-0">
          ${order.order.name} <br>  Phone: ${order.order.phone} <br> Email: ${order.order.email}
        </p>
      </div>
    </div>
    <hr>
    <ul class="row">
      <c:forEach var="product" items="${order.orderContent}">
        <li class="col-xl-4  col-lg-6">
          <figure class="itemside mb-3">
            <div class="aside">
              <img width="72" height="72" src="${pageContext.request.contextPath}/img/products/${product.product.images[0].url}" class="img-sm rounded border">
            </div>
            <figcaption class="info">
              <a href="/product/${product.product.id}" class="title cutoff-text-2">${product.product.title}</a>
              <strong> ${product.quantity}x = $${product.pricePer*product.quantity} </strong>
            </figcaption>
          </figure>
        </li>
      </c:forEach>
    </ul>
  </div>
</article>