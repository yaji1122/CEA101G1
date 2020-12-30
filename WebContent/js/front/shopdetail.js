var bodyClass = document.body.classList,
    lastScrollY = 0;
window.addEventListener("scroll", function () {
    var st = this.scrollY;
    // 判斷是向上捲動，而且捲軸超過 200px
    if (st < lastScrollY) {
        bodyClass.remove("hideUp");
    } else {
        bodyClass.add("hideUp");
    }
    lastScrollY = st;
});
$(".sliderpics").slick({
    dots: true,
    infinite: true,
    speed: 500,
    cssEase: "linear",
});
//$(".icon").click(function () {
//	 
//    if ($(".shopping-cart").hasClass("shopping-cart-show")) {
//        $(".shopping-cart").removeClass("shopping-cart-show");
//    } else {
//        $(".shopping-cart").addClass("shopping-cart-show");
//        $(".back").css("display", "block");
//    }
//});
//$(".close").click(function () {
//    $(".shopping-cart").removeClass("shopping-cart-show");
//    $(".back").css("display", "none");
//});
