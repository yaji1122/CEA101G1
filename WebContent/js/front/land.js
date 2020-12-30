$(document).ready(function(){
        $('.pictrue-wall').slick({
          slidesToShow: 1,
          slidesToScroll: 1,
          arrows: false,
          fade: true,
          autoplaySpeed: 3000,
          autoplay: true
        });
       
     });
$(function(){
      $(".input-date").datepicker({
         showOn : "button",
         dateFormat:'yy/mm/dd',
         buttonImage:'https://ps9103.s3.us-east-2.amazonaws.com/public/field_date+(1).png',
         buttonImageOnly : false,
         buttonText:'Date',
      });
  
  });
$('#slick').slick({
    autoplay: true,
    arrows: false,
    slidesToShow: 1,
    infinite: true,
    autoplaySpeed: 1000,
  });

