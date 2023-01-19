<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

<div class="container">
    <div class="row padding-y">
        <aside class="col-lg-8">
            <!-- ============================ COMPONENT REVIEW CART ================================= -->
            <div class="card">
                <article class="card-body">
                    <h4 class="card-title">Review items</h4>
                    <div class="row">
                        <c:forEach var="product" items="${products}">
                            <div class="col-md-6">
                                <div class="itemside mb-4">
                                    <div class="aside">
                                        <img src="${pageContext.request.contextPath}/img/${product.product.images[0].url}" class="border img-sm rounded">
                                    </div>
                                    <div class="info">
                                        <p class="cutoff-text-2"> ${product.product.title} </p>
                                        <span class="text-muted">${product.quantity} x $ ${product.pricePer} </span> <br>
                                        <strong class="price"> $ ${product.totalPrice} </strong>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </article>
                <article class="card-body border-top">
                    <a class="btn btn-light" href="/checkout"> <i class="fa fa-arrow-left me-2"></i> Edit order</a>
                    <table style="max-width:360px" class="table table-sm float-lg-end">
                        <tbody>
                        <tr>
                            <td> Subtotal (${products.size()} items): </td>
                            <td> $ ${total_price} </td>
                        </tr>
                        <tr>
                            <td> Delivery charge: </td>
                            <td> $ ${customer.deliveryOption.price} </td>
                        </tr>
                        <tr>
                            <td> Total: </td>
                            <td> <strong class="h5 price">$ ${total_price + customer.deliveryOption.price}</strong> </td>
                        </tr>
                        </tbody>
                    </table>
                </article>
                <!-- card-body.// -->
            </div>
            <!-- card.// --><!-- =================== COMPONENT REVIEW CART .// =================== -->
        </aside>
        <!-- col.// -->
        <aside class="col-lg-4">
            <!-- ============== COMPONENT PAYMENT MINI =============== -->
<%--            <article class="card mb-3">--%>
<%--                <div class="card-body">--%>
<%--                    <h5 class="card-title">Your credit cards</h5>--%>
<%--                    <div class="row g-2">--%>
<%--                        <div class="col-sm-12 col-md-6 col-lg-12">--%>
<%--                            <article class="box box-check bg-light">--%>
<%--                                <label>--%>
<%--                                    <input class="form-check-input d-none" <c:if test="${not empty customer.address.user and customer.address.id == address.id or loop.index == 0 and not empty customer.address.user}">checked</c:if> type="radio" value="${address.id}" name="addressOption">--%>
<%--                                    <b class="border-oncheck"></b>--%>
<%--                                    <b class="mx-2 text-muted"><i class="fa fa-credit-card"></i></b>--%>
<%--                                    ${address.address}--%>
<%--                                </label>--%>
<%--                            </article>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </article>--%>

            <article class="card">
                <div class="card-body">
                    <h5 class="card-title">Payment form</h5>







                    <form id="payment-form" method="post">
                        <input id="api-key" type="hidden" value="${stripePublicKey}">
                        <div class="form-group mb-3">
                            <div class="w-100" id="card-element">
                                <!-- A Stripe Element will be inserted here. -->
                            </div>
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <input class="form-control" id="email" name="email"--%>
<%--                                   placeholder="Email Address" type="email" required>--%>
<%--                        </div>--%>

<%--                        <div class="col mb-3">--%>
<%--                            <label class="form-label">Name on card</label>--%>
<%--                            <input type="text" class="form-control" name="username" placeholder="Type here" required="">--%>
<%--                        </div>--%>
<%--                        <!-- col end.// -->--%>
<%--                        <div class="col mb-3">--%>
<%--                            <label class="form-label">Card number</label>--%>
<%--                            <div class="input-group">--%>
<%--                                <input type="text" class="form-control" id="cardNumber" placeholder="0000-0000-0000-0000" name="cardNumber">--%>
<%--                                <span class="input-group-text">--%>
<%--                                    <i class="fab fa-cc-visa"></i>--%>
<%--                                    &nbsp; <i class="fab fa-cc-amex"></i>--%>
<%--                                    &nbsp; <i class="fab fa-cc-mastercard"></i>--%>
<%--                                </span>--%>
<%--                            </div>--%>
<%--                            <!-- input-group end.// -->--%>
<%--                        </div>--%>
<%--                        <!-- col end.// -->--%>
<%--                        <div class="row">--%>
<%--                            <div class="col-auto mb-3">--%>
<%--                                <label class="form-label"> Expiration </label>--%>
<%--                                <div class="input-group">--%>
<%--                                    <select class="form-select" style="width: 120px;">--%>
<%--                                        <option value="0">MM</option>--%>
<%--                                        <option value="1">01 - Janiary</option>--%>
<%--                                        <option value="2">02 - February</option>--%>
<%--                                        <option value="3">03 - February</option>--%>
<%--                                    </select>--%>
<%--                                    <select class="form-select" style="width: 120px;">--%>
<%--                                        <option value="1">YY</option>--%>
<%--                                        <option value="2">2018</option>--%>
<%--                                        <option value="3">2019</option>--%>
<%--                                    </select>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <!-- col end.// -->--%>
<%--                            <div class="col-3"> <label class="form-label" data-bs-toggle="tooltip" title="3 digits on back side of the card"> CVV <i class="fa fa-question-circle"></i> </label> <input class="form-control" id="cvv" required="" type="text" style="width: 110px;"> </div>--%>
<%--                            <!-- col end.// -->--%>
<%--                        </div>--%>
<%--                        <label class="form-check mb-4">--%>
<%--                            <input class="form-check-input" type="checkbox" name="saveAddress">--%>
<%--                            <span class="form-check-label"> Save this card </span>--%>
<%--                        </label>--%>
                        <button class="btn w-100 btn-success mt-4" type="submit" id="submitButton">Pay $230</button>
                    </form>
                </div>
            </article>
            <!-- ============== COMPONENT PAYMENT MINI .// =============== -->
        </aside>
        <!-- col.// -->
    </div>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script>
    const stripe = Stripe($('#api-key').val());
    const elements = stripe.elements({clientSecret:'${client_secret}'});
    const payment = elements.create('payment',{type:'payment'});
    payment.mount('#card-element');

    payment.addEventListener('change', function (event) {
        $('#card-errors').text(event.error ? event.error.message : '');
    });

    $('#payment-form').on('submit', async function (e) {
        <%--const { paymentMethod, error } = await stripe.createPaymentMethod(--%>
        <%--    'payment',--%>
        <%--    payment,--%>
        <%--    { billing_details: { email: '${customer.email}' } }--%>
        <%--);--%>
        <%--if (error) {--%>
        <%--    alert(error);--%>
        <%--} else {--%>
        <%--    const token = paymentMethod.id;--%>
        <%--    const email = '${customer.email}';--%>
        <%--    $.post(--%>
        <%--        "/payment/create-charge",--%>
        <%--        {email: email, token: token},--%>
        <%--        function (data) {--%>
        <%--            alert(data.details);--%>
        <%--        }, 'json');--%>
        <%--}--%>
        stripe.confirmPayment({
            elements,
            redirect: 'if_required'
        })
            .then(function(result) {
                if (result.error) {
                    console.log(result.error);
                } else {
                    $.ajax({
                        type: "POST",
                        url: "/payment/data",
                        data: {id: result.paymentIntent.id},success: (id) =>
                            window.location.href = window.location.origin + '/payment/'+id+'/success/'});
                }
            });
        e.preventDefault();
    });
</script>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
