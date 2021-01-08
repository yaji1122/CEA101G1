$(".show-memberdetail").click(function () {   //開啟詳情視窗
    $(".info-display").addClass("display-show");
    let tr = $(this).parents("tr");
    let children = tr.children();
    $("#detail-mbid").text(children.eq(0).text());
    $("#detail-mbname").text(children.eq(1).text());
    $("#detail-mbbd").text(tr.children(".mb_bd").text());
 	$("#detail-mbphone").text(tr.children(".mb_phone").text());
 	$("#detail-mbemail").text(tr.children(".mb_email").text());
	$("#detail-mbcity").text(tr.children(".mb_city").text());
	$("#detail-mbtown").text(tr.children(".mb_town").text());
	$("#detail-mbaddress").text(tr.children(".mb_address").text());
	$("#detail-createdate").text(tr.children(".create_date").text());
	$("#detail-mbpic").attr("src", "/CEA101G1/MembersServlet?mb_id=" + 
	children.eq(0).text() + "&action=getone_mbpic");
});

$(".show-memberupdate").click(function () {   //開啟修改視窗
    $(".update-display").addClass("display-show");
    let tr = $(this).parents("tr");
    let children = tr.children();
    $("#update-mbID").text(children.eq(0).text());
	$("#update-mbid").val(children.eq(0).text());
    $("#update-mbname").val(children.eq(1).text());
    $("#update-mbbd").val(tr.children(".mb_bd").text()).change();
 	$("#update-mbphone").val(tr.children(".mb_phone").text());
 	$("#update-mbemail").val(tr.children(".mb_email").text());
	$("#update-mbcity").val(tr.children(".mb_city").text());
	$("#update-mbtown").val(tr.children(".mb_town").text());
	$("#update-mbaddress").val(tr.children(".mb_address").text());
});

//關閉視窗
$(".icon").click(function () {
    let display = $(this).parents(".display-show");
    display.removeClass("display-show");
    $("#showroom").attr("src", "");
});

/* let roomTypeFilter = $("#room-type-select");
let roomStatusFilter = $("#room-status-select"); */
let currentTotal = parseInt($(".showmsg p b").text());
let allTr = $("tr");
/* 
roomTypeFilter.change(filter);
roomStatusFilter.change(filter); */

$("#mb_id").keyup(function () {
    let rmno = $("#mb_id").val().toUpperCase();
    let count = 0;
    for (let i = 1; i < allTr.length; i++) {
        if (allTr.eq(i).children().eq(0).text().indexOf(rmno) < 0) {
            allTr.eq(i).hide();
            count++;
        } else {
            allTr.eq(i).show();
        }
    }
    $(".showmsg p b").text(currentTotal - count);
});

$("#mb_name").keyup(function () {
    let name = $("#mb_name").val().toUpperCase();
    let count = 0;
    for (let i = 1; i < allTr.length; i++) {
        if (allTr.eq(i).children().eq(1).text().toUpperCase().indexOf(name) < 0) {
            allTr.eq(i).hide();
            count++;
        } else {
            allTr.eq(i).show();
        }
    }
    $(".showmsg p b").text(currentTotal - count);
});

$("#mb_email").keyup(function () {
    let name = $("#mb_email").val().toUpperCase();
    let count = 0;
    for (let i = 1; i < allTr.length; i++) {
        if (allTr.eq(i).children().eq(2).text().toUpperCase().indexOf(name) < 0) {
            allTr.eq(i).hide();
            count++;
        } else {
            allTr.eq(i).show();
        }
    }
    $(".showmsg p b").text(currentTotal - count);
});
