<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

<section class="padding-y bg-light">
    <div class="container">

        <div class="card">
            <header class="card-img-top overflow-hidden bg-cover" style="background-image: url('bootstrap5-ecommerce/images/banners/bg-cafe.webp');">
                <div class="card-body bg-dark-50">
                    <div class="d-lg-flex align-items-end">
                        <div class="itemside mt-lg-5 flex-auto">
                            <div class="aside"> <img src="${pageContext.request.contextPath}/img/assets/0.webp" class="img-md rounded-3" width="96" height="96"> </div>
                            <div class="info">
                                <p class="text-white">Moscow city</p>
                                <h3 class="text-white">McDonalds Moscow</h3>
                                <p class="text-white-50">National food of Uzbekistan and European foods, luxury interior</p>
                            </div>
                        </div>
                        <div class="flex-shrink-0 mt-3"> <span class="btn btn-sm btn-warning"> <i class="fa fa-star"></i> 4.7 </span> <a class="btn btn-sm btn-outline-light" href="#"> More info </a> </div>
                    </div>
                </div>
            </header>
            <div class="row g-0">
                <aside class="col-lg-3 border-end">
                    <nav class="nav flex-column nav-pills m-3"> <a href="#" class="nav-link active">Recommended</a> <a href="#" class="nav-link">Hamburgers</a> <a href="#" class="nav-link">Salads and Drinks</a> <a href="#" class="nav-link">Grilled meats</a> <a href="#" class="nav-link">Vegeterian foods</a> <a href="#" class="nav-link">Other meals</a> </nav>
                </aside>
                <div class="col-lg-9">
                    <div class="content-body">
                        <h4 class="card-title">Recommended</h4>
                        <article class="row mb-3">
                            <div class="col-xl-3 col-lg-4 col-md-4 col-sm-12"> <img class="rounded w-100 obj-cover mb-3" height="140" src="bootstrap5-ecommerce/images/items/food1.webp"> </div>
                            <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12">
                                <a href="#" class="btn btn-outline-primary float-end"> Add this</a>
                                <h6 class="title">Asian Beef Pilav</h6>
                                <div class="price-wrap mb-2"> <span class="price">$18.90</span> </div>
                                <p class="text-muted" style="max-width: 600px">Uzbek pilav cooked with chicken meat, carrot and other ingredients and some other descriptions Juicy, tender, succulent chicken</p>
                            </div>
                        </article>
                        <article class="row mb-3">
                            <div class="col-xl-3 col-lg-4 col-md-4 col-sm-12"> <img class="rounded w-100 obj-cover mb-3" height="140" src="bootstrap5-ecommerce/images/items/food2.webp"> </div>
                            <div class="col-xl-9 col-lg-8 col-md-8 col-sm-12">
                                <a href="#" class="btn btn-outline-primary float-end"> Add this</a>
                                <h6 class="title">Chicken Pizza </h6>
                                <div class="price-wrap mb-2"> <span class="price">$7.90</span> <del class="text-muted"> $12.90</del> <span class="ms-2 text-danger"> 15% off </span> </div>
                                <p class="text-muted" style="max-width: 600px">Juicy, tender, succulent chicken strips. Served with a choice of house made dips chicken meat and rice, carrot and other ingredients</p>
                            </div>
                        </article>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
