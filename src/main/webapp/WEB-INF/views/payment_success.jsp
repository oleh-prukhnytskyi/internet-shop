<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

<section class="padding-y bg-light">
    <div class="container">
        <!-- row.// --> <!-- row.// -->
        <div class="row">
            <div class="col-lg-8">
                <!-- ============== COMPONENT FINAL =============== -->
                <article class="card">
                    <div class="card-body">
                        <div class="mt-4 mx-auto text-center" style="max-width:600px">
                            <svg width="96px" height="96px" viewBox="0 0 96 96" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                                    <g id="round-check">
                                        <circle id="Oval" fill="#D3FFD9" cx="48" cy="48" r="48"></circle>
                                        <circle id="Oval-Copy" fill="#87FF96" cx="48" cy="48" r="36"></circle>
                                        <polyline id="Line" stroke="#04B800" stroke-width="4" stroke-linecap="round" points="34.188562 49.6867496 44 59.3734993 63.1968462 40.3594229"></polyline>
                                    </g>
                                </g>
                            </svg>
                            <div class="my-3">
                                <h4>Thank you for order</h4>
                                <p>Some information will be written here, bla bla lorem ipsum you enter into any new area of science, you almost always find yourself</p>
                            </div>
                        </div>
                        <ul class="steps-wrap mx-auto" style="max-width: 600px">
                            <li class="step active"> <span class="icon">1</span> <span class="text">Order received</span> </li>
                            <!-- step.// -->
                            <li class="step "> <span class="icon">2</span> <span class="text">Confirmation</span> </li>
                            <!-- step.// -->
                            <li class="step "> <span class="icon">3</span> <span class="text">Delivery</span> </li>
                            <!-- step.// -->
                        </ul>
                        <!-- tracking-wrap.// --> <br>
                    </div>
                </article>
                <!-- ============== COMPONENT FINAL .// =============== -->
            </div>
            <!-- col.// -->
            <aside class="col-lg-4">
                <!-- ============== COMPONENT RECEIPE =============== -->
                <article class="card">
                    <div class="card-body">
                        <h5 class="card-title"> Receipe </h5>
                        <div class="itemside mb-3">
                            <div class="aside"> <span class="icon-sm text-primary bg-primary-light rounded"><i class="fab fa-lg fa-paypal"></i></span> </div>
                            <div class="info lh-sm">
                                <strong>Order ID: ${order.id}</strong> <br>
                                <span class="text-muted">${order.orderDate}</span>
                            </div>
                        </div>
                        <dl class="dlist-align">
                            <dt>Method:</dt>
                            <dd>${transaction.method} - - - - ${transaction.cardNumber}</dd>
                        </dl>
                        <dl class="dlist-align">
                            <dt>Billed to:</dt>
                            <dd>${transaction.customerName}</dd>
                        </dl>
                        <dl class="dlist-align">
                            <dt>Fee:</dt>
                            <dd>$${transaction.fee}</dd>
                        </dl>
                        <dl class="dlist-align">
                            <dt>Paid:</dt>
                            <dd>$${transaction.amount}</dd>
                        </dl>
                        <hr>
                        <a href="#" class="btn btn-light">Download invoice</a>
                    </div>
                </article>
                <!-- ============== COMPONENT RECEIPE .// =============== -->
            </aside>
            <!-- col.// -->
        </div>
        <!-- row.// -->
    </div>
    <!-- container end.// -->
</section>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
