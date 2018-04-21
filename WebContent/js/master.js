function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function sameAsBillingAddress(){
	document.getElementsByName("firstname-ship").innerHTML = document.getElementsByName("firstname").innerHTML;
	document.getElementsByName("lastname-ship").innerHTML = document.getElementsByName("lastname").innerHTML;
	document.getElementsByName("addr1-ship").innerHTML = document.getElementsByName("addr1").innerHTML;
	document.getElementsByName("province-ship").innerHTML = document.getElementsByName("province").innerHTML;
	document.getElementsByName("country-ship").innerHTML = document.getElementsByName("country").innerHTML;
	document.getElementsByName("pcode-ship").innerHTML = document.getElementsByName("pcode").innerHTML;

}

(function() {
  document.addEventListener("DOMContentLoaded",
      function(event) {

        // Navigation Bar
        function check_position() {
          var body = document.getElementsByTagName("BODY")[0];
          var nav = document.getElementById("menu");
          if (window.scrollY > 200) {
            body.classList.add("floating-nav");
            nav.style = "top:0";
          } else {
            body.classList.remove("floating-nav");
            nav.style = "top:-50px";
          }
        }

        let origin_method = window.onscroll;
        window.onscroll = function() {
          check_position();
          if (origin_method != null){
        	  origin_method(); 
          }          
        };
        check_position();

        // Push to bottom
        function check_height() {
          var main = document.getElementById("main-body");
          if (main == null) {
            return;
          }

          var footer = document.getElementById("footer");
          if (footer == null) {
            return;
          }

          var delta = window.innerHeight - main.clientHeight
              - footer.clientHeight - 2;
          if (delta > 0) {
            main.style = "min-height:"
                + (delta + main.clientHeight) + "px";
          }
        }
        
        window.onresize = function() {
          check_height();
        }
        
        function updateCartRedDot(){
          try{
            var dot = document.getElementById("cart-dot");
            var cart_cookie = getCookie('cart');
            if (cart_cookie == ""){
              dot.hidden = true;
            }else{
              var total = 0
              var cart_obj = JSON.parse(cart_cookie);
              for (var i in cart_obj){
                total += cart_obj[i];
              }
              dot.innerHTML = total;
              dot.hidden = false;
            }
          }catch(error){
            setCookie("cart", "", 100);
            var dot = document.getElementById("cart-dot");
            dot.hidden = true;
          }
        }

        function addBookToCart(bookId){
          var cart_cookie = getCookie('cart');
          var cart_obj;
          if (cart_cookie == ""){
            cart_obj = new Object();
          }else{
            cart_obj = JSON.parse(cart_cookie);
          }
          if (cart_obj[bookId] == undefined){
            cart_obj[bookId] = 1;
          }else{
            cart_obj[bookId] ++ ;
          }
          setCookie("cart",JSON.stringify(cart_obj), 100);
          updateCartRedDot();
        }

        function bindAddCartEvent(){
          let add_button = document.getElementsByClassName("add_to_cart");
          for (let i = add_button.length - 1; i >= 0; i--) {
            add_button[i].onclick = function(node){
              addBookToCart(add_button[i].getAttribute("bid"));
            }
          };
        }
        
        updateCartRedDot();
        bindAddCartEvent();
        check_height();
      });
})();
