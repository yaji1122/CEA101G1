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

$(".pageslider").slick({
    autoplay: true,
    autoplaySpeed: 3500,
    fade: true,
    speed: 1500,
    arrows: false,
});
$(".itemslider").slick({
    dots: true,
    infinite: true,
    speed: 500,
    cssEase: "linear",
});
$(".icon").click(function () {
    
    if ($(".shopping-cart").hasClass("shopping-cart-show")) {
        $(".shopping-cart").removeClass("shopping-cart-show");
    } else {
        $(".shopping-cart").addClass("shopping-cart-show");
        $(".back").css("display", "block");
    }
});
$(".close").click(function () {
    $(".shopping-cart").removeClass("shopping-cart-show");
    $(".back").css("display", "none");
});

// var cot = 0;
// function nex() {
//     if (cot <= 2) {
//         $(".wrap img").eq(cot).animate({ "margin-left": "-100%" }, 500);
//         cot++;
//     }
// }
// function pre() {
//     if (cot > 0) {
//         cot--;
//         $(".wrap img").eq(cot).animate({ "margin-left": "0" }, 500);
//     }
// }
