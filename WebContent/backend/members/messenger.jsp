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
		
		// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
		$(document).on("click", ".person", function(e){
			let member = $(this).attr("data-mbid");
			$("#sendMessage").prop("disabled", false);
			$(".person").removeClass("active");
			$(this).addClass("active");
			$(".chat").removeClass("active-chat")
			let chatBox = document.getElementById("chat-" + member);
			chatBox.classList.add("active-chat");
			chatBox.scrollTop = chatBox.scrollHeight;
			$(this).children(".unread").hide();
			$(this).children(".unread").text("0");
		});
		
		
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
					let chatBox;
					let memberID;
					let empID;
					for (var i = 0; i < messages.length; i++) {
						let msg = messages[i];
						let showMsg = msg.message;
						let sender = msg.sender;
						let receiver = msg.receiver;
						let time = msg.time;
						let div = document.createElement("div");
						let img = document.createElement("img");
						let span = document.createElement("span");
						
						span.append(time);
						div.classList.add("bubble");
						if (sender.indexOf("EMP") >= 0){
							empID = sender.split("-")[0];
							memberID = receiver.split("-")[0];
							div.classList.add("me");
							img.setAttribute("src", "<%=request.getContextPath()%>/emp/emp.do?action=getEmpPic&emp_id=${empVO.emp_id}");
							img.classList.add("emppic");
							span.classList.add("emptime");
							div.append(showMsg);
							div.append(img);
							div.append(span);
						} else {
							memberID = sender.split("-")[0];
							empID = receiver.split("-")[0];
							div.classList.add("you");
							img.setAttribute("src", "<%=request.getContextPath()%>/MembersServlet?action=getMbPicForChat&mb_id=" + memberID);
							img.classList.add("memberpic");
							span.classList.add("membertime");
							div.append(span);
							div.append(img);
							div.append(showMsg);
						}
						chatBox = document.getElementById("chat-"+ memberID);
						// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
						chatBox.append(div);
						chatBox.scrollTop = chatBox.scrollHeight;
					}
					showNewestMsg(memberID);
				} else if ("chat" === jsonObj.type) {
					let showMsg = jsonObj.message;
					let time = jsonObj.time;
					let div = document.createElement('div');
					let img = document.createElement("img");
					let span = document.createElement("span");
					span.append(time);
					div.classList.add("bubble");
					let empID;
					let memberID;
					if (jsonObj.sender.indexOf("EMP") >= 0){
						empID = jsonObj.sender.split("-")[0];
						memberID = jsonObj.receiver.split("-")[0];
						div.classList.add("me");
						img.setAttribute("src", "<%=request.getContextPath()%>/emp/emp.do?action=getEmpPic&emp_id=${empVO.emp_id}");
						img.classList.add("emppic");
						span.classList.add("emptime");
						div.append(showMsg);
						div.append(img);
						div.append(span);
					} else {
						memberID = jsonObj.sender.split("-")[0];
						empID = jsonObj.receiver.split("-")[0];
						div.classList.add("you");
						img.setAttribute("src", "<%=request.getContextPath()%>/MembersServlet?action=getMbPicForChat&mb_id=" + memberID);
						img.classList.add("memberpic");
						span.classList.add("membertime");
						div.append(span);
						div.append(img);
						div.append(showMsg);
					}
					let chatArea = document.getElementById("chat-"+ memberID);
					chatArea.append(div);
					chatArea.scrollTop = chatArea.scrollHeight;
					showNewestMsg(memberID);
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
			var timeStr = time.getFullYear() + "-" + (time.getMonth()+1).toString().padStart(2, "0") + "-" 
						+ time.getDate() + " " + time.getHours().toString().padStart(2, "0") + ":" + time.getMinutes().toString().padStart(2, "0");
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
		
		function showNewestMsg(memberID){
			let memberChats = $("#chat-"+memberID).children(".you").last();
			let memberBox = $("#"+memberID).children(".preview");
			let memberTime = $("#"+memberID).children(".time");
			let unreadMsg = $("#"+memberID).children(".unread");
			let time = memberChats.children("span").text();
			let msg = memberChats.html().split(">").slice(-1)[0];
			if (msg.length > 20) {
				msg  = msg.slice(0, 20) + "..."
			} 
			if (!$("#"+memberID).hasClass("active")){
				if (unreadMsg.text() == "-1"){
					let number = parseInt(unreadMsg.text());
					unreadMsg.text(number+1);
				} else {
					unreadMsg.show();
					let number = parseInt(unreadMsg.text());
					unreadMsg.text(number+1);
				}
			}
			memberBox.text(msg);
			memberTime.text(time);
		}
		
		// 有新的客戶上線或離線就更新列表
		function refreshCustomerList(jsonObj) {
			let memberIDs = jsonObj.memberIDs;
			let currentChatMember = $(".container .left .active-chat").eq(0).attr("id")
			let memberList = document.getElementById("members");
			let chatArea = document.getElementById("chatArea");
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
		                            <span class="unread">-1</span>
		                    </li>
							`;
						var div = document.createElement("div");
						div.classList.add("chat");
						div.setAttribute("id", "chat-" + memberID);
						chatArea.after(div);
						let aMember = memberID + "-" + memberName;
						$("#" + memberID).children(".unread").hide();
						let jsonObj = {
								"type" : "history",
								"sender" : "${empVO.emp_id}-${empVO.emp_name}",
								"receiver" : aMember,
								"message" : ""
							};
						webSocket.send(JSON.stringify(jsonObj));
						if (currentChatMember != null) {
							let id = currentChatMember.split("-")[1];
							$("#" + id).addClass("active");
						}
					}
				})
				
			}
		}
		
		function disconnect() {
			webSocket.close();
			document.getElementById('sendMessage').disabled = true;
		}
    </script>
</html>
