/*------------------
    Preloader
--------------------*/
$(window).on("load", function() {
	$(".loader").delay(400).fadeOut();
	$("#preloder").delay(600).fadeOut("slow");
});

$(document).ready(function() {
    /*------------------
            登入視窗
    --------------------*/
	$(".log-in").on("click", function() {
		$(".offcanvas-menu-overlay").removeClass("active");
		$(".login-window-overlay").addClass("active");
		$(".login-window").addClass("show-login-window");
		$(".offcanvas-menu-wrapper").removeClass("show-offcanvas-menu-wrapper");
	});
	$(".login-window-overlay").on("click", function(e) {
		$(".login-window-overlay").removeClass("active");
		$(".login-window").removeClass("show-login-window");
	});
	$(".img__btn").click(function() {
		document.querySelector(".cont").classList.toggle("s--signup");
	});
   
    /*------------------
        RWD用導覽側窗
    --------------------*/
	$(".canvas-open").on("click", function() {
		$(".offcanvas-menu-wrapper").addClass("show-offcanvas-menu-wrapper");
		$(".offcanvas-menu-overlay").addClass("active");
	});

	$(".canvas-close, .offcanvas-menu-overlay").on("click", function() {
		$(".offcanvas-menu-wrapper").removeClass("show-offcanvas-menu-wrapper");
		$(".offcanvas-menu-overlay").removeClass("active");
	});

});
