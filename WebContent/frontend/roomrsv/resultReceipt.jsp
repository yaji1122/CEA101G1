<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html lang="en" xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:o="urn:schemas-microsoft-com:office:office">
<head>
<meta charset="utf8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta name="x-apple-disable-message-reformatting">
<title>Your reservation is now confirmed</title>
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/slick-theme.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/slick.css" rel="stylesheet">
<style>
.buttons:hover {
		box-shadow: 1px 0px 1px grey;
		transform: scale(1.01);
	}
.slick-prev {
	position: absolute;
    left: 20%;
    top: 50%;
    z-index: 99;
    transform: translateX(-50%);;
}
.slick-next {
	position: absolute;
    right: 20%;
    top: 50%;
}
.slick-prev:before, .slick-next:before {
	font-size:40px;
}
@media screen {
	img {
		max-width: 100%;
	}
	td, th {
		box-sizing: border-box;
	}
	u ~div .wrapper {
		min-width: 100vw;
	}
	a[x-apple-data-detectors] {
		color: inherit;
		text-decoration: none;
	}
	.all-font-roboto {
		font-family: Roboto, -apple-system, "Segoe UI", sans-serif !important;
	}
	.all-font-sans {
		font-family: -apple-system, "Segoe UI", sans-serif !important;
	}
}
	
</style>
</head>
<body style="box-sizing: border-box; margin: 0; padding: 0; width: 100%; word-break: break-word; -webkit-font-smoothing: antialiased;">
	<div class="receipts">
	<c:forEach var="bkod" items="${bkodList}">
	<div>
		<table class="wrapper all-font-sans" width="100%" height="100%"
			cellpadding="0" cellspacing="0" role="presentation">
			<tr>
				<td align="center" style="padding: 24px;" width="100%">
					<table class="sm-w-full" width="600" cellpadding="0"
						cellspacing="0" role="presentation">
						<tr>
							<td colspan="2" class="sm-inline-block" style="display: none;">
								<img
								src="https://images.unsplash.com/photo-1505577058444-a3dab90d4253?ixlib=rb-0.3.5&s=fed02ccbe457c9b8fc1f2cf76f30d755&w=600&h=400&q=80&fit=crop"
								alt="Double Room"
								style="border: 0; line-height: 100%; vertical-align: middle; border-top-left-radius: 4px; border-top-right-radius: 4px; box-shadow: 0 10px 15px -3px rgba(0, 0, 0, .1), 0 4px 6px -2px rgba(0, 0, 0, .05);">
							</td>
						</tr>
						<tr>
							<td class="sm-hidden"
								style="padding-top: 40px; padding-bottom: 40px;" width="160">
								<img
								src="https://images.unsplash.com/photo-1505577058444-a3dab90d4253?ixlib=rb-0.3.5&s=fed02ccbe457c9b8fc1f2cf76f30d755&w=320&h=800&q=80&fit=crop"
								alt="Double room"
								style="border: 0; line-height: 100%; vertical-align: middle; border-top-left-radius: 4px; border-bottom-left-radius: 4px; box-shadow: 0 10px 15px -3px rgba(0, 0, 0, .1), 0 4px 6px -2px rgba(0, 0, 0, .05);"
								width="160">
							</td>
							<td align="left" class="sm-p-20 sm-dui17-b-t"
								style="border-radius: 2px; padding: 40px; position: relative; box-shadow: 0 10px 15px -3px rgba(0, 0, 0, .1), 0 4px 6px -2px rgba(0, 0, 0, .05); vertical-align: top; z-index: 50;"
								bgcolor="#ffffff" valign="top">
								<table width="100%" cellpadding="0" cellspacing="0"
									role="presentation">
									<tr>
										<td width="80%">
											<h1 class="sm-text-lg all-font-roboto"
												style="font-weight: 700; line-height: 100%; margin: 0; margin-bottom: 4px; font-size: 24px;">Customer
												Invoice</h1>
											<p class="sm-text-xs"
												style="margin: 0; color: #a0aec0; font-size: 14px;">Your
												reservation is now confirmed</p>
										</td>
										<td style="text-align: right;" width="20%" align="right">
											<a
											href="<%=request.getContextPath()%>/receipt.jsp?bk_no=${bkod.bk_no}"
											target="_blank" style="text-decoration: none;"> <img
												src="https://image0.flaticon.com/icons/png/128/872/872220.png"
												alt="Download PDF"
												style="border: 0; line-height: 100%; vertical-align: middle; font-size: 12px;"
												width="24">
										</a>
										</td>
									</tr>
								</table>
								<div style="line-height: 32px;">&zwnj;</div>
								<table class="sm-leading-32"
									style="line-height: 28px; font-size: 14px;" width="100%"
									cellpadding="0" cellspacing="0" role="presentation">
									<tr>
										<td class="sm-inline-block" style="color: #718096;"
											width="50%">Invoice No#</td>
										<td class="sm-inline-block"
											style="font-weight: 600; text-align: right;" width="50%"
											align="right">${bkod.bk_no}</td>
									</tr>
									<tr>
										<td class="sm-inline-block" style="color: #718096;"
											width="50%">Customer</td>
										<td class="sm-inline-block"
											style="font-weight: 600; text-align: right;" width="50%"
											align="right">${member.mb_name}</td>
									</tr>
									<tr>
										<td class="sm-inline-block" style="color: #718096;"
											width="50%">Guests</td>
										<jsp:useBean id="detailSvc" scope="page"
											class="com.bookingdetail.model.BookingDetailService" />
										<td class="sm-inline-block"
											style="font-weight: 600; text-align: right;" width="50%"
											align="right">${detailSvc.getAllByBkNo(bkod.bk_no).stream().map(e -> e.rm_guest).sum()}
											Adult</td>
									</tr>
									<tr>
										<td class="sm-w-1-4 sm-inline-block" style="color: #718096;"
											width="50%">Stay</td>
										<td class="sm-w-3-4 sm-inline-block"
											style="font-weight: 600; text-align: right;" width="50%"
											align="right">${bkod.dateOut.compareTo(bkod.dateIn)}
											Night</td>
									</tr>
								</table>
								<table width="100%" cellpadding="0" cellspacing="0"
									role="presentation">
									<tr>
										<td style="padding-top: 24px; padding-bottom: 24px;">
											<div
												style="background-color: #edf2f7; height: 2px; line-height: 2px;">&zwnj;</div>
										</td>
									</tr>
								</table>
								<table style="font-size: 14px;" width="100%" cellpadding="0"
									cellspacing="0" role="presentation">
									<tr>
										<td class="sm-w-full sm-inline-block sm-text-center"
											width="40%">
											<p class="all-font-roboto"
												style="margin: 0; margin-bottom: 4px; color: #a0aec0; font-size: 10px; text-transform: uppercase; letter-spacing: 1px;">Check-in</p>
											<p class="all-font-roboto"
												style="font-weight: 600; margin: 0; color: #000000; letter-spacing: 1px">${bkod.dateIn.format(DateTimeFormatter.ofPattern("yyyy MMM dd"))}</p>
										</td>
										<td class="sm-w-full sm-inline-block sm-py-12"
											style="font-family: Menlo, Consolas, monospace; font-weight: 600; text-align: center; color: #cbd5e0; font-size: 18px; letter-spacing: -1px;"
											width="20%" align="center">&gt;&gt;&gt;</td>
										<td class="sm-w-full sm-inline-block sm-text-center"
											style="text-align: right;" width="40%" align="right">
											<p class="all-font-roboto"
												style="margin: 0; margin-bottom: 4px; color: #a0aec0; font-size: 10px; text-transform: uppercase; letter-spacing: 1px;">Check-out</p>
											<p class="all-font-roboto"
												style="font-weight: 600; margin: 0; color: #000000; letter-spacing: 1px">${bkod.dateOut.format(DateTimeFormatter.ofPattern("yyyy MMM dd"))}</p>
										</td>
									</tr>
								</table>
								<table width="100%" cellpadding="0" cellspacing="0"
									role="presentation">
									<tr>
										<td style="padding-top: 24px; padding-bottom: 24px;">
											<div
												style="background-color: #edf2f7; height: 2px; line-height: 2px;">&zwnj;</div>
										</td>
									</tr>
								</table>
								<table style="line-height: 28px; font-size: 14px;" width="100%"
									cellpadding="0" cellspacing="0" role="presentation">
									<tr>
										<td style="color: #718096;" width="50%">Price per
											person(tax include)</td>
										<td style="font-weight: 600; text-align: right;" width="50%"
											align="right">\$${bkod.total_price /
											detailSvc.getAllByBkNo(bkod.bk_no).stream().map(e ->
											e.rm_guest).sum()}0</td>
									</tr>
									<tr>
										<td
											style="font-weight: 600; padding-top: 32px; color: #000000; font-size: 20px;"
											width="50%">Total</td>
										<td
											style="font-weight: 600; padding-top: 32px; text-align: right; color: #68d391; font-size: 20px;"
											width="50%" align="right">\$${bkod.total_price}</td>
									</tr>
									<tr>
										<td
											style="font-weight: 600; padding-top: 32px; color: #000000; font-size: 20px;text-align: center;padding:0px 10px"
											width="50%"><button id="pkup" class="buttons"
												style="border: 1px solid black; background-color: transparent; border-radius: 2px; font-size: 14px; cursor: pointer; outline: none;width: 100%;">預約接送</button></td>
										<td
											style="font-weight: 600; padding-top: 32px; text-align: right; color: #68d391; font-size: 20px;text-align: center;padding:20px 10px"
											width="50%" align="right"><button class="buttons" 
												style="border: 1px solid black; background-color: transparent; border-radius: 2px; font-size: 14px; cursor: pointer; outline: none;width: 100%;" onclick="window.location.href = '<%=request.getContextPath()%>/frontend/index.jsp'">回首頁</button></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	</c:forEach>
	</div>
	<script src="<%=request.getContextPath()%>/js/slick.min.js"></script>
	<script>
		$(document).ready(function(){
			$(".receipts").slick({
				arrows:true,
			});
		})
	</script>
</body>
</html>