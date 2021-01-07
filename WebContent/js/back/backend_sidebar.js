$(".sidebar-dropdown > a").mouseenter(function () {
        $(this).next(".sidebar-submenu").slideDown(0);
        $(this).parent().addClass("active");
});
$(".sidebar-dropdown").mouseleave(function(){
	$(".sidebar-dropdown").removeClass("active");
        $(this).removeClass("active");
        $(this).children(".sidebar-submenu").slideUp(0);
})
$(window).on("load", function() {
	$(".loader").delay(400).fadeOut();
	$("#preloder").delay(600).fadeOut("slow");
});