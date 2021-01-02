$(document).ready(function(){
$(".shopping-cart").click(function() {
    $(".shopping-cart-box").addClass("display-box-show");
    $(".black").css("display", "block");
    $(".black").click(function() {
        $(".shopping-cart-box").removeClass("display-box-show");
        $(".black").css("display", "none");
    })
});    

$(".close-display-box").click(function() {
    $(".display-box").removeClass("display-box-show");
    $(".shopping-cart-box").removeClass("display-box-show");
    $(".black").css("display", "none");
});
})

$(".display-close").click(function(){
    $(".shopping-cart-box").removeClass("display-box-show");
    $(".black").css("display", "none");
})

$(".display-qty").each(function(index){
$("#display-icon-plus" + index + "").click(function(e) {
	e.preventDefault();
	fieldName = $(this).attr('field');
	hiddenQty = $("#item_quantity" + index + "").attr('field');
    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
    var hiddenVal = parseInt($('input[field=' + hiddenQty + ']').val());
    price = parseInt($("#price" + index + "").val());
    showprice = parseInt($("#showprice" + index + "").html());
    if (!isNaN(currentVal)) {
      $('input[name=' + fieldName + ']').val(currentVal + 1); 
      $('input[field=' + hiddenQty + ']').val(hiddenVal + 1);
    } else {
      $('input[name=' + fieldName + ']').val(1);
      $('input[field=' + hiddenQty + ']').val(1);
    }   
    $("#showprice" + index + "").html(showprice + price);
    $("#item_price" + index + "").val(showprice + price);
    });
})

$(".display-qty").each(function(index){
$("#display-icon-minus" + index + "").click(function(e) {
	e.preventDefault();
    fieldName = $(this).attr('field');
    hiddenQty = $("#item_quantity" + index + "").attr('field');
    var currentVal = parseInt($('input[name=' + fieldName + ']').val());
    var hiddenVal = parseInt($('input[field=' + hiddenQty + ']').val());
    price = parseInt($("#price" + index + "").val());
    showprice = parseInt($("#showprice" + index + "").html());
    if (!isNaN(currentVal) && currentVal > 1) {
      $('input[name=' + fieldName + ']').val(currentVal - 1);
      $('input[field=' + hiddenQty + ']').val(hiddenVal - 1);
    } else {
      $('input[name=' + fieldName + ']').val(1);
      $('input[field=' + hiddenQty + ']').val(1);
    }
    if ($("#display-qty" + index + "").val() > 1) {
    	$("#showprice" + index + "").html(showprice - price);
    	$("#item_price" + index + "").val(showprice - price);
    }
    else if($("#display-qty" + index + "").val() == 1){
    	$("#showprice" + index + "").html(price);    
    	$("#item_price" + index + "").val(price);
    }
  });
})

$(function(){
	var totalprice = 0;
	$(".cart-name").each(function(index){
		var price = parseInt($("#cart-price" + index + "").html());	
		totalprice = totalprice + price;
	})
	$("#totalprice").html(totalprice);
})

  $(".hover").mouseleave(
    function () {
      $(this).removeClass("hover");
    }
  );


	
