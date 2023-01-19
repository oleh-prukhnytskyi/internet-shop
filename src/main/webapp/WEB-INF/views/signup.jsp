<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

<aside class="col-lg-6 m-auto">
    <div class="card mb-4">
        <article class="card-body">
            <h4 class="mb-4">Create account</h4>
            <form method="post" action="/signup">
                <div class="row gx-3">
                    <div class="col mb-3"> <label class="form-label">Email</label> <input type="email" name="email" class="form-control" placeholder=""> <small class="form-text">We'll never share your email</small> </div>
                </div>
                <div class="row gx-3">
                    <div class="col mb-3"> <label class="form-label">Create password</label> <input class="form-control" name="password" type="password"> </div>
                    <div class="col mb-3"> <label class="form-label">Repeat password</label> <input class="form-control" name="repeatPassword" type="password"> </div>
                </div>
                <div class="row mt-3 mb-4 align-items-center">
                    <div class="col-auto">
                        <button class="btn btn-primary" type="submit">Register now</button>
                    </div>
                </div>
            </form>
            <hr>
            <p class="mb-0">Already have an account? <a href="${pageContext.request.contextPath}/signin">Sign in</a></p>
        </article>
    </div>
</aside>

<jsp:include page="/WEB-INF/includes/footer.jsp" />