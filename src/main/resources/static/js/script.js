const search_form = document.getElementById("search-form");
search_form.addEventListener("submit", function (event) {
    event.preventDefault();
    window.location.href = '/search/' + search_form.elements[0].value;
});
function qty(id, i) {
    const inp = $("#inp" + id);
    const m = $("#m" + id);
    const price = $("#price" + id);
    const total = $("#total");
    const prices = $("[id^=price]");
    if (i === "remove" && inp.val() <= 1) {
        m.prop('disabled', true);
    } else {
        m.prop('disabled', false);
        $.ajax({type: "GET", url: "/cart/" + i + "/" + id});
        inp.val(parseInt(inp.val() === ""
            ? 1 : inp.val()) + (i === "add" ? 1 : -1));
        if (inp.val() <= 1) m.prop('disabled', true);
        price.text("$ " + (parseFloat(inp.val()) *
            parseFloat(price.attr("data-price"))).toFixed(2));
        let totalf = 0;
        prices.each(function (index) {totalf += parseFloat(prices[index]
            .innerText.replace("$",""))});
        total.text("$ " + totalf.toFixed(2));
    }
}
function wishList(id) {
    const btn = $("#wishBtn" + id)[0];
    if (btn.getAttribute("data-enabled") === "true") {
        $.ajax({type: "GET", url: "/profile/wishlist/remove/" + id});
        btn.classList.replace("btn-outline-primary", "btn-light");
        btn.setAttribute("data-enabled", "false");
    } else {
        $.ajax({type: "GET", url: "/profile/wishlist/add/" + id});
        btn.classList.replace("btn-light", "btn-outline-primary");
        btn.setAttribute("data-enabled", "true");
    }
}
const new_address_btn = $("#new_address_btn");
new_address_btn.click(function() {
    new_address_btn.prop('hidden', true);
    $("#new_address_radio").prop("checked", true);
    $("#add_address").prop('hidden', false);
});
$('input[type=radio][name=addressOption]').change(function() {
    if (this.value !== 'new')
        new_address_btn.prop('hidden', false);
    $("#add_address").prop('hidden', this.value !== 'new');
});
$("#checkout_form").on("submit", function (e) {
    $.ajax({
        type: "POST",
        url: "/checkout/validate" + ($(this).serializeArray()
            .find(field => field.name === 'addressOption').value === 'new'
            ? "/new" : ""),
        data: $(this).serialize(),
        success: function (data) {
            if (data.length === 0)
                $("#checkout_form").unbind('submit').submit()
            $('.is-invalid').map(function() {
                this.classList.remove('is-invalid');
                this.nextElementSibling.innerText = '';
            });
            for (const invalid of data) {
                const elem = $('input[name='+invalid.field+']')[0];
                if (!elem.classList.contains('is-invalid'))
                    elem.classList.add('is-invalid');
                elem.nextElementSibling.innerText = invalid.defaultMessage;
            }
        }
    });
    e.preventDefault();
});
$('input[type=radio][name=shipping]').change(function (){
    $('#shipping_cost').text('+ $ ' + $(this).attr('data-price'));
    $('#checkout_total_price').text('$ ' + (parseFloat($('#checkout_total').text()
        .replace('$', '')) + parseFloat($(this).attr('data-price'))).toFixed(2));
});

$("#review-form").on( 'submit', function (e) {
    $.ajaxSetup({headers: {'X-CSRF-TOKEN':$('meta[name="csrf-token"]').attr('content')}});
    $.ajax({
        type: 'POST',
        url: '/product/validate',
        data: $(this).serialize(),
        success: function (data) {
            if (data.length === 0)
                $("#review-form").unbind('submit').submit()
            $('.is-invalid').map(function() {
                this.classList.remove('is-invalid');
                this.nextElementSibling.innerText = '';
            });
            for (const invalid of data) {
                const elem = $('[name='+invalid.field+']')[0];
                if (!elem.classList.contains('is-invalid'))
                    if (elem.name === 'rating')
                        elem.parentElement.classList.add('is-invalid');
                    else
                        elem.classList.add('is-invalid');
                if (elem.name === 'rating')
                    $(elem).parent().parent().find('.invalid-feedback:first').text(invalid.defaultMessage);
                else
                    $(elem).parent().find('.invalid-feedback:first').text(invalid.defaultMessage);
            }
        }
    });
    e.preventDefault();
});

$('.upload_images').on('change', function() {
    if ($('.uploader-img').length < 6) {
        let files = $(this)[0].files;
        for (let i = 0; i < files.length; i++) {
            if (files[i].size <= 25000000) {
                let reader = new FileReader();
                reader.onload = function(e) {
                    if ($('.uploader-img').length > 5)
                        return;
                    $('<div style="width: 80px; height: 80px" class="uploader-img"><img src="'+e.target.result+'"/>' +
                        '<button type="button" class="btn-close bg-white position-absolute top-0 end-0">' +
                        '</button></div>').insertBefore('.uploader-img:last');
                    imgLength();
                }
                reader.readAsDataURL(files[i]);
            }
        }
    }
    $('body').on('click', ".btn-close", () => {
        $(this).parent().remove();
        if ($('.uploader-img').length < 6) {
            $('.uploader-img:last').show();
        }
    });
    imgLength();
});
function imgLength() {
    if ($('.uploader-img').length >= 6)
        $('.uploader-img:last').hide()
}

$('#form-add-item').on('submit', function(e) {
    let formData = new FormData();
    formData.append("title", $("#title").val());
    formData.append("description", $("#desc").val());
    formData.append("price", $("#price").val());
    let files = Array.from($("#images")[0].files).splice(0,5);
    console.log(files)
    for (let i = 0; i < files.length; i++) {
        formData.append("images", files[i]);
    }
    $.ajax({
        url: "/profile/seller/add/validate",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: function (data) {
            if (data.length === 0) {
                $.ajax({
                    url: "/profile/seller/add",
                    type: "POST",
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: () => window.location.href = '/profile/seller'
                });
            }
            $('.is-invalid').map(function() {
                this.classList.remove('is-invalid');
                this.nextElementSibling.innerText = '';
            });
            for (const invalid of data) {
                const elem = $('[name='+invalid.field+']')[0];
                if (!elem.classList.contains('is-invalid'))
                    if (elem.name === 'images')
                        elem.parentElement.parentElement.classList.add('is-invalid');
                    else
                        elem.classList.add('is-invalid');
                if (elem.name === 'images')
                    $(elem).parent().parent().parent().find('.invalid-feedback:first').text(invalid.defaultMessage);
                else
                    $(elem).parent().find('.invalid-feedback:first').text(invalid.defaultMessage);
            }
        }
    });
    e.preventDefault();
});