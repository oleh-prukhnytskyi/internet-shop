<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<article class="card">
  <div class="content-body">
    <figure class="itemside align-items-center">
      <div class="aside">
        <span class="bg-gray icon-md rounded-circle">
          <img src="images/avatars/avatar.webp" class="icon-md rounded-circle">
        </span>
      </div>
      <figcaption class="info">
        <h6 class="title">Mr. Jackson Mike</h6>
        <p>Email: myusername@gmail.com, Phone: +1234567890988
          <a href="/profile/settings" class="px-2"><i class="fa fa-pen" aria-hidden="true"></i></a>
        </p>
      </figcaption>
    </figure>
    <hr>
    <div class="row g-2 mb-3">
      <div class="col-md-6">
        <article class="box bg-light">
          <b class="mx-2 text-muted"><i class="fa fa-map-marker-alt" aria-hidden="true"></i></b>
          United States, 3601 Old Capitol Trail, Unit A-7, Suite
        </article>
      </div>
      <div class="col-md-6">
        <article class="box  bg-light">
          <b class="mx-2 text-muted"><i class="fa fa-map-marker-alt" aria-hidden="true"></i></b>
          Moscow city, Street name, Building lenin, House 77
        </article>
      </div>
    </div>
    <a href="#" class="btn btn-light"> <i class="me-2 fa fa-plus" aria-hidden="true"></i> Add new address </a>
    <hr class="my-4">
    <h5 class="card-title"> Your orders </h5>
    <c:forEach var="order" items="${orders}">
      <article class="card border-primary mb-4">
        <div class="card-body">
          <header class="d-lg-flex">
            <div class="flex-grow-1">
              <h6 class="mb-0">Order ID: ${order.order.id} <i class="dot"></i>
                <c:choose>
                  <c:when test="${order.order.trackStatus==3}">
                    <span class="text-success"> Delivered</span>
                  </c:when>
                  <c:otherwise><span class="text-danger"> Pending</span></c:otherwise>
                </c:choose>
              </h6>
              <span class="text-muted">Date: ${order.order.orderDate}</span>
            </div>
            <div>
              <a href="/profile/order/cancel/${order.order.id}" class="btn btn-sm btn-outline-danger">Cancel order</a>
              <a href="/profile/order/track/${order.order.id}" class="btn btn-sm btn-primary">Track order</a>
            </div>
          </header>
          <hr>
          <div class="row">
            <div class="col-lg-4">
              <p class="mb-0 text-muted">Contact</p>
              <p class="m-0">
                ${order.order.name} <br>  Phone: ${order.order.phone} <br> Email: ${order.order.email}
              </p>
            </div>
            <div class="col-lg-4 border-start">
              <p class="mb-0 text-muted">Shipping address</p>
              <p class="m-0"> ${order.order.address.city} <br>
                ${order.order.address.address} ${order.order.address.postCode}
              </p>
            </div>
            <div class="col-lg-4 border-start">
              <p class="mb-0 text-muted">Payment</p>
              <p class="m-0">
                <span class="text-success"> ${order.transaction.method}  **** ${order.transaction.cardNumber}   </span> <br>
                Shipping fee:  $${order.order.shipping} <br>
                Total paid:  $${order.transaction.amount}
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
    </c:forEach>
  </div>
</article>