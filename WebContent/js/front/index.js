$(document).ready(function() {
	//頂端nav section 漸變
	window.addEventListener("scroll", function() {
		let opac = "rgba(34, 39, 54," + (window.scrollY / 620).toString() + ")";
		$(".menu-item").css("background-color", opac);
		let book = $(".booking").eq(0);
		if (window.pageYOffset + book.innerHeight()*2 + 10 > window.innerHeight) {
			book.css("background-color", "rgb(34, 39, 54)");
			let top = window.innerHeight - book.innerHeight();
			book.css("position","sticky");
			book.css("top", top + "px");
		} else {
			$(".booking").css("background-color", "rgba(0, 0, 0, 0)");
			book.css("position","static");
		}
	});
	//滑鼠到頂層導覽變色
	$(".menu-item").mouseover(() => $(".menu-item").css("background-color", "#222736"));
	$(".menu-item").mouseout(() =>
		$(".menu-item").css("background-color", "rgba(34, 39, 54," + (window.scrollY / 620).toString() + ")")
	);
    /*------------------
		Navigation
	--------------------*/
	$(".testimonial-slider").slick({
		speed: 1000,
		slidesToScroll: 1,
		slidesToShow: 1,
		autoplay: true,
		fade: true,
		autoplaySpeed: 5000,
		infinite: true,
	});

	 /*------------------
        Navigation
    --------------------*/
	$(".mobile-menu").slicknav({
		prependTo: "#mobile-menu-wrap",
		allowParentLinks: true,
	});
    /*------------------
		Date Picker 日期
    --------------------*/
	jQuery.datetimepicker.setLocale('zh-TW');
	$("#date-in").datetimepicker({
		minDate: 0,
		timepicker:false,
		format: "Y-m-d",
		numberOfMonths: 2,
		onSelectDate: function(selectedDate) {
			$("#date-out").datetimepicker("option", "minDate", selectedDate);
		},
	});


	/*$.datepicker.regional["zh-TW"] = {
		dayNamesMin: ["日", "一", "二", "三", "四", "五", "六"],
		monthNames: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
		monthNamesShort: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
		prevText: "上月",
		nextText: "次月",
		weekHeader: "週",
	};
	//將預設語系設定為中文
	$.datepicker.setDefaults($.datepicker.regional["zh-TW"]);
	*/
	
	
	 /*------------------
        Nice Select
    -------------------*/
	$("select").niceSelect();

    /*------------------
        Background Set
    --------------------*/
	$(".set-bg").each(function() {
		var bg = $(this).data("setbg");
		$(this).css("background-image", "url(" + bg + ")");
	});
	
	
});
