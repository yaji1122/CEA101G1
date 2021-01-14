<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
      <%@ include file="/backend/files/backend_header.file" %> <!-- 加入基本 css -->
      <link rel="stylesheet" href="<%=request.getContextPath()%>/css/back/messenger.css" >
     <title>客服即時通</title>
    </head>
    <body  onload="connect();" onunload="disconnect();">
    <%@ include file="/backend/files/backend_sidebar.file" %>
        <div class="wrapper">
            <div class="container">
                <div class="left">
                    <div class="top" id="chatArea">
                        <span class="name"><i class="far fa-user"></i>客服專員：${empVO.emp_name} - ${empVO.emp_id}</span>
                    </div>
                    <div class="write">
                        <input type="text" id="sendMessage" onkeydown="if (event.keyCode == 13) sendMessage();" disabled />
                        <i class="fas fa-paper-plane"></i>
                    </div>
                </div>
                <div class="right">
                    <ul class="people" id="members">
                       
                    </ul>
                </div>
            </div>
        </div>
    </body>
    <%@ include file="/backend/files/backend_footer.file" %> <!-- 加入基本 js -->
    <script>
        //WebSocket
        var MyPoint = "/customerWS/${empVO.emp_id}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		var members;
		var chat;
		var webSocket;
	
		function connect() {
			// create a websocket
			webSocket = new WebSocket(endPointURL);
	
			webSocket.onopen = function(event) {
				console.log("Connect Success!");
				document.getElementById('sendMessage').disabled = false;
			}
	
			webSocket.onmessage = function(event) {
				var jsonObj = JSON.parse(event.data);
				if ("open" === jsonObj.type) { //會員上線
					refreshCustomerList(jsonObj);
				} else if ("openEmp" === jsonObj.type) { //客服上線
					refreshCustomerList(jsonObj);
					 
						members = {
				            list: document.querySelector("ul.people"),
				            all: document.querySelectorAll(".right .person"),
				            name: "",
				        },
				        chat = {
				            container: document.querySelector(".container .left"),
				            current: null,
				            person: null,
				            name: document.querySelector(".container .left .top .name"),
				        };
				
				    members.all.forEach((f) => {
				        f.addEventListener("mousedown", () => {
				            f.classList.contains("active") || setAciveChat(f);
				        });
				    });
					    
				} else if ("history" === jsonObj.type) {
					var messages = JSON.parse(jsonObj.message);
					for (var i = 0; i < messages.length; i++) {
						var msg = messages[i];
						var showMsg = msg.message;
						var time = msg.time;
						var sender = msg.sender;
						var receiver = msg.receiver;
						var div = document.createElement('div');
						div.classList.add("bubble");
						let memberID;
						let empID;
						if (sender.indexOf("EMP") >= 0){
							empID = sender.split("-")[0];
							memberID = receiver.split("-")[0];
							div.classList.add("me");
						} else {
							memberID = sender.split("-")[0];
							empID = receiver.split("-")[0];
							div.classList.add("you");
						}
						var chatArea = document.getElementById("chat-"+ memberID);
						// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
						div.innerText = showMsg;
						chatArea.append(div);
						chatArea.scrollTop = chatArea.scrollHeight;
					}
					messagesArea.scrollTop = messagesArea.scrollHeight;
				} else if ("chat" === jsonObj.type) {
					var div = document.createElement('div');
					div.classList.add("bubble");
					let empID;
					let memberID;
					if (jsonObj.sender.indexOf("EMP") >= 0){
						empID = jsonObj.sender.split("-")[0];
						memberID = jsonObj.receiver.split("-")[0];
						div.classList.add("me");
					} else {
						memberID = jsonObj.sender.split("-")[0];
						empID = jsonObj.receiver.split("-")[0];
						div.classList.add("you");
					}
					console.log(empID);
					console.log(memberID)
					div.innerText = jsonObj.message;
					let chatArea = document.getElementById("chat-"+ memberID);
					chatArea.append(div);
					chatArea.scrollTop = chatArea.scrollHeight;
				} else if ("close" === jsonObj.type) {
					refreshCustomerList(jsonObj);
					let closeMemberID = jsonObj.memberID;
					let chatArea = document.getElementById("chat-"+ closeMemberID);
					chatArea.remove();
				}
			};
	
			webSocket.onclose = function(event) {
				console.log("Disconnected!");
			};
		}
		
		function setAciveChat(f) {
			if (members.list.querySelector(".active") != null) {
				members.list.querySelector(".active").classList.remove("active");
			}
            f.classList.add("active");
            chat.current = chat.container.querySelector(".active-chat");
            chat.person = f.getAttribute("data-mbid");
            if (chat.current != null) chat.current.classList.remove("active-chat");
            chat.container.querySelector('[data-mbid="' + chat.person + '"]').classList.add("active-chat");
            members.name = f.querySelector(".name").innerText;
            chat.name.innerHTML = members.name;
        }
		
		function sendMessage() {
			var inputMessage = document.getElementById("sendMessage");
			var memberID = $(".person.active").attr("data-mbid");
			var memberName = $(".person.active").attr("data-mbname");
			var message = inputMessage.value.trim();
			var time = new Date();
			var timeStr = time.getFullYear() + "-" + (time.getMonth()+1) + "-" 
						+ time.getDate() + " " + time.getHours() + ":" + time.getMinutes();
			if (message === "") {
				inputMessage.focus();
				return;
			} else if (memberID === undefined) {
				return;
			} else {
				var jsonObj = {
					"type" : "chat",
					"sender" : "${empVO.emp_id}-${empVO.emp_name}",
					"receiver" : memberID + "-" + memberName,
					"message" : message,
					"time" : timeStr,
				};
				webSocket.send(JSON.stringify(jsonObj));
				inputMessage.value = "";
				inputMessage.focus();
			}
		}
		
		// 有新的客戶上線或離線就更新列表
		function refreshCustomerList(jsonObj) {
			var memberIDs = jsonObj.memberIDs;
			var memberList = document.getElementById("members");
			var chatArea = document.getElementById("chatArea");
			memberList.innerHTML = '';
			for (var i = 0; i < memberIDs.length; i++) {
				let memberID = memberIDs[i];
				var memberName = "";
				$.ajax({
					url: "<%=request.getContextPath()%>/MembersServlet?action=ajaxGetMemberName",
					data: {
						mb_id: memberID,
					},
					type: "POST",
					success: function(msg){
						memberName = msg;
						memberList.innerHTML += 
							`
							<li class="person" id="\${memberID}" data-mbid="\${memberID}" data-mbname="\${memberName}">
		                            <img src="<%=request.getContextPath()%>/MembersServlet?action=getMbPicForChat&mb_id=\${memberID}" alt="" />
		                            <span class="name">\${memberName}</span>
		                            <span class="time"></span>
		                            <span class="preview"></span>
		                    </li>
							`;
						var div = document.createElement("div");
						div.classList.add("chat");
						div.setAttribute("id", "chat-" + memberID);
						chatArea.after(div);
					}
				})
			}
		}
		// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
		$(document).on("click", ".person", function(e){
			$("#sendMessage").prop("disabled", false);
			var member = $(this).attr("data-mbid");
			var memberName = $(this).attr("data-mbname")
			$(".person").removeClass("active");
			$(this).addClass("active");
			$(".chat").removeClass("active-chat")
			$("#chat-" + member).addClass("active-chat");
			var aMember = member + "-" + memberName;
			var jsonObj = {
					"type" : "history",
					"sender" : "${empVO.emp_id}-${empVO.emp_name}",
					"receiver" : aMember,
					"message" : ""
				};
			webSocket.send(JSON.stringify(jsonObj));
		});
		
		function disconnect() {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
		}
    </script>
</html>