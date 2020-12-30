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
	/*------------------
		   確認Email沒有重複
		--------------------*/
	$("#registration-form").submit(function(event) {
		event.preventDefault();
		let form = $(this);
		let email = $("#temp-email").val();
		let password = $("#temp-password").val();
		let confirmPassword = $("#temp-confirm-password").val();
		if (password !== confirmPassword) {
			swalfire("密碼確認錯誤", "請確認兩次密碼輸入是否相同")
			return;
		}
		let data = new FormData();
		data.append("email", email);
		data.append("action", "email_confirm");
		let xhr = new XMLHttpRequest();
		xhr.open("post", "http://localhost:8080/CEA101G1/MembersServlet");
		xhr.onload = function() {
			if (xhr.readyState === xhr.DONE) {
				if (xhr.status === 200) {
					console.log("Status 200")
					if (xhr.responseText === "used") {
						swalfire("您輸入的EMAIL已註冊", "請從會員登入進入")
					} else {
						form.unbind('submit').submit();
					}
				}
			}
		};
		xhr.send(data);
	});
	/*------------------
           登入確認
    --------------------*/
	$("#login-form").submit(function(event) {
		event.preventDefault();
		let form = $(this);
		let mb_email = $("#mb_email").val();
		let mb_pwd = $("#mb_pwd").val();
		let data = new FormData();
		data.append("mb_email", mb_email);
		data.append("mb_pwd", mb_pwd);
		data.append("action", "member-login");
		let xhr = new XMLHttpRequest();
		xhr.open("post", "http://localhost:8080/CEA101G1/LoginHandler");
		xhr.onload = function() {
			if (xhr.readyState === xhr.DONE) {
				if (xhr.status === 200) {
					if (xhr.responseText === "email_not_found") {
						swalfire("此EMAIL尚未註冊會員", "請重新註冊會員")
					} else if (xhr.responseText === "pwd_incorrect") {
						swalfire("密碼錯誤", "超過三次將封鎖帳號")
					} else {
						form.unbind('submit').submit();
					}
				}
			}
		};
		xhr.send(data);
	})
	/*------------------
       通用錯誤訊息發射～
    --------------------*/
	function swalfire(msgTitle, msg) {
		Swal.fire({
			position: "center",
			icon: "error",
			title: msgTitle,
			text: msg,
			showConfirmButton: false,
			timer: 1500,
		});
	}
});
