$(document).ready(function () {
    $(".show-basic-update").click(function () {
        buttonShow($(this));
    });
    $(".show-account-update").click(function () {
        buttonShow($(this));
        $(".password_reset").css("display", "flex");
    });
    //按鈕出現與消失
    function buttonShow(item) {
		item.css("display", "none")
        let form = item.parent().siblings("form");
        let divs = form.children("div");
        let inputs = divs.children("input");
        inputs.prop("disabled", false);
        form.find(".cancel-update").css("display", "block");
		form.find(".cancel-update").next().css("display", "block");
        form.find(".cancel-update").click(function () {
            $(this).css("display", "none");
 			$(this).next().css("display", "none");
		    item.css("display", "block")
            inputs.prop("disabled", true);
            $(".password_reset").css("display", "none");
        });
    }
});
