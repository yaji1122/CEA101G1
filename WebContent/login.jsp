<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0" />
		<meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>會員登入</title>
        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/img/loading.png" />
 	    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
        <script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>  
    </head>
    <style>
    	h5 {
    font-family: sans-serif;
    padding-top: 20px;
}
.swal-footer {
    background-color: rgb(245, 248, 250);
    margin-top: 32px;
    border-top: 1px solid #e9eef1;
    overflow: hidden;
    text-align: center;
}
.bg {
    position: fixed;
    right: 0;
    top: 0;
    z-index: -1;
}
.bg img {
    height: 100vh;
}
.login-window {
    width: 70vw;
    height: 90vh;
    display: flex;
    justify-content: center;
    flex-direction: row;
    align-items: center;
    transition: all 0.5s linear;
}
.login-box {
    display: flex;
    padding: 30px;
    background: none;
    border-radius: 20px;
    justify-content: space;
    vertical-align: center;
    align-items: center;
    flex-direction: column;
    transition: all 0.5s linear;
}

.login-box input {
    width: 300px;
    text-align-last: center;
}
p.forget {
    font-size: 0.7rem;
}
.login-box img {
    height: 60px;
    margin-bottom: 40px;
}
.login-input-form {
    display: flex;
    flex-direction: row;
    justify-content: center;
    width: 100%;
    margin: 10px;
}
/* input */
.group {
    position: relative;
    margin-bottom: 15px;
    margin-top: 10px;
}
input {
    font-size: 18px;
    padding: 20px 10px 10px 5px;
    display: block;
    width: 300px;
    border: none;
    border-bottom: 1px solid #8f8f8f;
    background: none;
    color: black;
}
input:focus {
    outline: none;
}

/* LABEL  */
label {
    color: #999;
    font-size: 16px;
    font-weight: normal;
    position: absolute;
    pointer-events: none;
    left: 34%;
    top: 20px;
    transition: 0.2s ease all;
    -moz-transition: 0.2s ease all;
    -webkit-transition: 0.2s ease all;
}

/* active state */
input:focus ~ label,
input:valid ~ label {
    top: -10px;
    font-size: 14px;
    color: black;
}

/* BOTTOM BARS */
.bar {
    position: relative;
    display: block;
    width: 300px;
}
.bar:before,
.bar:after {
    content: "";
    height: 2px;
    width: 0;
    bottom: 0px;
    position: absolute;
    background: black;
    transition: 0.2s ease all;
    -moz-transition: 0.2s ease all;
    -webkit-transition: 0.2s ease all;
}
.bar:before {
    left: 50%;
}
.bar:after {
    right: 50%;
}

/* active state */
input:focus ~ .bar:before,
input:focus ~ .bar:after {
    width: 50%;
}

/* HIGHLIGHTER  */
.highlight {
    position: absolute;
    height: 60%;
    width: 100px;
    top: 25%;
    left: 0;
    pointer-events: none;
    opacity: 0.5;
}

/* active state */
input:focus ~ .highlight {
    -webkit-animation: inputHighlighter 0.3s ease;
    -moz-animation: inputHighlighter 0.3s ease;
    animation: inputHighlighter 0.3s ease;
}

/* ANIMATIONS ================ */
@-webkit-keyframes inputHighlighter {
    from {
        background: black;
    }
    to {
        width: 0;
        background: transparent;
    }
}
@-moz-keyframes inputHighlighter {
    from {
        background: black;
    }
    to {
        width: 0;
        background: transparent;
    }
}
@keyframes inputHighlighter {
    from {
        background: black;
    }
    to {
        width: 0;
        background: transparent;
    }
}

/* button */
button {
    background: none;
    border: 0;
    box-sizing: border-box;
    margin: 1em;
    margin-top: 2em;
    padding: 0.5em 1em;
    width: 200px;
    box-shadow: inset 0 0 0 2px #757575;
    color: #757575;
    font-size: 18px;
    font-weight: 700;
    position: relative;
    vertical-align: middle;
    letter-spacing: 5px;
}
button::before,
button::after {
    box-sizing: inherit;
    content: "";
    position: absolute;
    width: 100%;
    height: 100%;
}
.draw {
    -webkit-transition: color 0.25s;
    transition: color 0.25s;
}
.draw::before,
.draw::after {
    border: 5px solid transparent;
    width: 0;
    height: 0;
}
.draw::before {
    top: 0;
    left: 0;
}
.draw::after {
    bottom: 0;
    right: 0;
}
.draw:hover {
    color: #000000;
}
.draw:hover::before,
.draw:hover::after {
    width: 100%;
    height: 100%;
}
.draw:hover::before {
    border-top-color: #000000;
    border-right-color: #000000;
    -webkit-transition: width 0.25s ease-out, height 0.25s ease-out 0.25s;
    transition: width 0.25s ease-out, height 0.25s ease-out 0.25s;
}
.draw:hover::after {
    border-bottom-color: #000000;
    border-left-color: #000000;
    -webkit-transition: border-color 0s ease-out 0.5s, width 0.25s ease-out 0.5s, height 0.25s ease-out 0.75s;
    transition: border-color 0s ease-out 0.5s, width 0.25s ease-out 0.5s, height 0.25s ease-out 0.75s;
}

/* RWD */
@media screen and (max-width: 840px) {
    .login-box {
        background-color: #222736b4;
    }
    .login-window {
        width: 100vw;
    }
    input {
        border-bottom: 1px solid rgb(131, 131, 131);
        color: white;
    }
    .draw:hover {
        color: white;
    }
    .draw:hover::before {
        border-top-color: #ffffff;
        border-right-color: #ffffff;
    }
    .draw:hover::after {
        border-bottom-color: #ffffff;
        border-left-color: #ffffff;
    }
    button {
        color: whitesmoke;
        box-shadow: rgb(221, 221, 221);
        border-bottom: #ffffff;
    }
    label {
        color: white;
        text-shadow: 0px 0px 1px black;
    }
    input:focus ~ label,
    input:valid ~ label {
        color: white;
    }
    .bar:before,
    .bar:after {
        background-color: white;
    }
}
    </style>
    <body>
        <div class="bg"><img src="<%=request.getContextPath()%>/img/bgimg.png" alt="" /></div>

        <div class="wrapper">
            <div class="login-window">
                <form method="POST" id="login-form" action="<%=request.getContextPath()%>/LoginHandler">
                    <div class="login-box">
                        <div>
                            <img src="<%=request.getContextPath()%>/img/logo.png" alt="" />
                        </div>
                        <div class="group">
                            <input type="text" name="mb_email" id="mb_email" autocomplete="off" required />
                            <span class="bar"></span>
                            <label>&nbsp&nbspE-MAIL</label>
                        </div>
                        <div class="group">
                            <input type="password" name="mb_pwd" id="mb_pwd" required />
                            <span class="bar"></span>
                            <label>PASSWORD</label>
                        </div>
                        <input type="text" name="pass" value="pass" style="display: none;" />
						<input type="text" name="action" value="member-login" style="display: none;" />
						<input type="text" name="location" value="loginPage" style="display: none;" />
                        <div>
                            <button type="submit" class="draw" id="login">LOGIN</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script>
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
		xhr.open("post", "<%=request.getContextPath()%>/LoginHandler");
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
        </script>
    </body>
</html>
