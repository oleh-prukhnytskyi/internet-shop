<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="/WEB-INF/includes/header.jsp" />

<section class="padding-y bg-light">
  <div class="container">
    <div class="row">
      <aside class="col-lg-6 m-auto">
        <div class="card mb-4">
          <article class="card-body">
            <h4 class="mb-4">Submit product</h4>
            <form id="form-add-item">
              <div class="row mb-4">
                <label class="col-3 col-form-label">Title</label>
                <div class="col-9">
                  <input type="text" id="title" name="title" class="form-control" placeholder="Type here">
                  <small class="invalid-feedback"></small>
                </div>
              </div>
              <div class="row mb-4">
                <label class="col-3 col-form-label">Price</label>
                <div class="col-9">
                  <input type="text" id="price" name="price" class="form-control" placeholder="$ 0.00">
                  <small class="invalid-feedback"></small>
                </div>
              </div>
              <div class="row mb-4">
                <label class="col-3 col-form-label"> Image <br> <small class="text-muted">(Max 25 mb)</small> </label>
                <div class="col-9">
                  <div class="gallery-uploader-wrap">

                    <label style="width: 80px; height: 80px" class="uploader-img">
                      <input type="file" id="images" name="images" multiple="" max data-max_length="6" class="upload_images">
                      <svg xmlns="http://www.w3.org/2000/svg" fill="#999" width="32" height="32" viewBox="0 0 24 24">
                        <circle cx="12" cy="12" r="3"></circle>
                        <path d="M16.83 4L15 2H9L7.17 4H2v16h20V4h-5.17zM12 17c-2.76 0-5-2.24-5-5s2.24-5 5-5 5 2.24 5 5-2.24 5-5 5z"></path>
                      </svg>
                    </label>

                  </div>
                  <small class="invalid-feedback"></small>
                </div>
              </div>
              <div class="row mb-4">
                <label class="col-3 col-form-label">Description</label>
                <div class="col-9">
                  <textarea class="form-control" id="desc" name="description" placeholder="Type here"></textarea>
                  <small class="invalid-feedback"></small>
                </div>
              </div>
              <div class="row mb-2">
                <div class="col-9 offset-3">
                  <button type="submit" class="btn btn-primary">Add product</button>
                  <a href="javascript:history.back()" class="btn btn-outline-danger">Cancel</a>
                </div>
              </div>
            </form>
          </article>
        </div>
      </aside>
    </div>
  </div>
</section>


<jsp:include page="/WEB-INF/includes/footer.jsp" />