<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

<section class="padding-y bg-light">
  <div class="container">
    <div class="row">
      <main class="col-xl-8 col-lg-8">
        <form method="post" id="checkout_form" action="/checkout">
          <article class="card">
            <div class="content-body">
              <h5 class="card-title"> Checkout </h5>
              <div class="row">
                <div class="col-6 mb-3">
                  <label class="form-label">First name</label>
                  <input type="text" class="form-control" name="firstName" value="${customer.firstName}" placeholder="Type here">
                  <small class="invalid-feedback"></small>
                </div>
                <!-- col end.// -->
                <div class="col-6">
                  <label class="form-label">Last name</label>
                  <input type="text" class="form-control" name="lastName" value="${customer.lastName}" placeholder="Type here">
                  <small class="invalid-feedback"></small>
                </div>
                <!-- col end.// -->
                <div class="col-6 mb-3">
                  <label class="form-label">Phone</label>
                  <input type="text" maxlength="11" class="form-control" <c:choose><c:when test="${not empty customer.phone}">value="${customer.phone}"</c:when><c:otherwise>value="+44"</c:otherwise></c:choose> name="phone" placeholder="">
                  <small class="invalid-feedback"></small>
                </div>
                <!-- col end.// -->
                <div class="col-6 mb-3">
                  <label class="form-label">Email</label>
                  <input type="text" class="form-control" name="email" value="${customer.email}" placeholder="example@gmail.com">
                  <small class="invalid-feedback"></small>
                </div>
                <!-- col end.// -->
              </div>
              <!-- row.// -->
              <hr class="my-4">
              <h5 class="card-title"> Shipping info </h5>

              <div class="row mb-3">
                <c:forEach var="delivery" items="${delivery_options}" varStatus="loop">
                  <div class="col-lg-4 mb-3">
                    <div class="box box-check">
                      <label class="form-check">
                        <input class="form-check-input" data-price="${delivery.price}" <c:if test="${not empty customer.deliveryOption and customer.deliveryOption.id == delivery.id or loop.index == 0 and empty customer.deliveryOption}">checked</c:if> type="radio" value="${delivery.id}" name="shipping">
                        <b class="border-oncheck"></b>
                        <span class="form-check-label">
                           ${delivery.title}<br>
                           <small class="text-muted">$ ${delivery.price} - ${delivery.description}</small>
                    </span>
                      </label>
                    </div>
                  </div>
                </c:forEach>
              </div>

              <c:if test="${not empty addresses or not empty addresses and empty customer.address.user}">
                <div class="row g-2 mb-3">
                  <c:forEach var="address" items="${addresses}" varStatus="loop">
                    <div class="col-md-6">
                      <article class="box box-check bg-light">
                        <label>
                          <input class="form-check-input d-none" <c:if test="${not empty customer.address.user and customer.address.id == address.id or loop.index == 0 and not empty customer.address.user}">checked</c:if> type="radio" value="${address.id}" name="addressOption">
                          <b class="border-oncheck"></b>
                          <b class="mx-2 text-muted"><i class="fa fa-map-marker-alt" aria-hidden="true"></i></b>
                          ${address.address}
                        </label>
                      </article>
                    </div>
                  </c:forEach>
                </div>
                  <div class="w-100 mb-4">
                    <button type="button" class="btn btn-light" id="new_address_btn" <c:if test="${empty customer.address.user}">hidden</c:if>>
                      <i class="me-2 fa fa-plus" aria-hidden="true"></i> Add new address
                    </button>
                  </div>
              </c:if>
              <input type="radio" name="addressOption" id="new_address_radio" value="new" class="d-none" <c:if test="${empty addresses or empty customer.address.user}">checked</c:if>>

              <div id="add_address" <c:if test="${not empty addresses and not empty customer.address.user}">hidden</c:if>>
                <c:if test="${not empty addresses and not empty customer.address.user or not empty addresses and empty customer.address}">
                  <hr class="my-4">
                  <h5 class="card-title"> New address </h5>
                </c:if>
                <div class="row">
                  <div class="col-sm-8 mb-3">
                    <label class="form-label">Address</label>
                    <input type="text" class="form-control" name="address" <c:if test="${empty customer.address.user}">value="${customer.address.address}"</c:if> placeholder="Type here">
                    <small class="invalid-feedback"></small>
                  </div>
                  <div class="col-sm-4 mb-3">
                    <label class="form-label">City</label>
                    <select class="form-select" id="city*"  name="city" aria-label="City*">
                      <option value="1">New York</option>
                      <option value="2">Moscow</option>
                      <option value="3">Samarqand</option>
                    </select>
                  </div>
                  <div class="col-6 mb-3">
                    <label class="form-label">House</label>
                    <input type="text" class="form-control" name="house" <c:if test="${empty customer.address.user}">value="${customer.address.house}"</c:if> placeholder="Type here">
                    <small class="invalid-feedback"></small>
                  </div>
                  <div class="col-6 mb-3">
                    <label class="form-label">Postal code</label>
                    <input type="text" class="form-control" name="postcode" <c:if test="${empty customer.address.user}">value="${customer.address.postCode}"</c:if> placeholder="">
                    <small class="invalid-feedback"></small>
                  </div>
                </div>
                <label class="form-check mb-4">
                  <input class="form-check-input" type="checkbox" name="saveAddress">
                  <span class="form-check-label"> Save this address </span>
                </label>
              </div>

              <!-- col end.// -->
              <div class="float-end">
                <a href="/cart" class="btn btn-light">Cancel</a>
                <input type="submit" class="btn btn-success" value="Continue">
              </div>
            </div>
            <!-- card-body end.// -->
          </article>
        </form>
      </main>
      <!-- col.// -->
      <aside class="col-xl-4 col-lg-4">
        <!-- ============== COMPONENT SUMMARY =============== -->
        <article class="ms-lg-4 mt-4 mt-lg-0" style="max-width: 320px">
          <h6 class="card-title">Summary</h6>
          <dl class="dlist-align">
            <dt>Total price:</dt>
            <dd class="text-end" id="checkout_total"> $ ${total_price}</dd>
          </dl>
          <dl class="dlist-align">
            <dt>Shipping cost:</dt>
            <dd class="text-end" id="shipping_cost"> + $ ${not empty customer.deliveryOption.price ? customer.deliveryOption.price : delivery_options.get(0).price} </dd>
          </dl>
          <hr>
          <dl class="dlist-align">
            <dt> Total: </dt>
            <dd class="text-end"> <strong class="text-dark" id="checkout_total_price">$ ${total_price + customer.deliveryOption.price}</strong> </dd>
          </dl>
          <div class="input-group my-4">
            <input type="text" class="form-control" name="" placeholder="Promo code">
            <button class="btn btn-light text-primary">Apply</button>
          </div>
          <hr>
          <h6 class="mb-4">Items in cart</h6>
          <c:forEach var="product" items="${products}">
            <figure class="itemside align-items-center mb-4">
              <div class="aside">
                <b class="badge bg-secondary rounded-pill">${product.quantity}</b>
                <img src="/img/products/${product.product.images[0].url}" class="img-sm rounded border">
              </div>
              <figcaption class="info">
                <a href="/product/${product.product.id}" class="title cutoff-text-2">${product.product.title}</a>
                <div class="price text-muted">Total: $ ${product.totalPrice}</div>
              </figcaption>
            </figure>
          </c:forEach>
        </article>
        <!-- ============== COMPONENT SUMMARY .// =============== -->
      </aside>
      <!-- col.// -->
    </div>
    <!-- row.// -->
    <br>
    <br>
  </div>
  <!-- container .//  -->
</section>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
