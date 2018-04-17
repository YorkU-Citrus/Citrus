<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
		<!--Content begin-->
		<div class="container">
			<div class="row spacing"></div>	
			<div class="row">
				<div class="width-full" >
					<div class="row">
						<div class="width-three-fourth">
							<div class="special-outline side-content">
								<form id="search-ctn">
									<input type="text" class="search-text text-box" placeholder="Find your book..." />
									<button type="submit" value="Search" class="search-button hide-on-mobile"><i class="fas fa-search"></i> Search</button>
								</form>
								<div class="item-list-info">
									<p><strong id="cart-cout-label">_</strong> books in your cart.</p>
								</div>
								<div class="row spacing"></div>	
								<div id="warpper">

								</div>
							</div>
							<div class="row spacing"></div>
						</div>

						<div class="width-one-fourth width-md-full">
							<div class="special-outline sidebar">
								<h3>Shopping Cart</h3>
								<ul>
									<li><span class="list-item">Subtotal: <span class="list-price"><strong>CAD <span id="p-subtotal">66.68</span></strong></span></span></li>
									<li><span class="list-item">HST: <span class="list-price">+ CAD <span id="p-tax">8.67</span></span></span></li>
									<li><hr /></li>
									<li><span class="list-item">Total: <span class="list-price"><strong>CAD <span id="p-total">75.35</span></strong></span></span></li>
								</ul>
							</div>
							<div class="row spacing"></div>	

							<div class="special-outline sidebar">
								<button class="normal-button checkout-btn"><i class="far fa-check-circle"></i> CHECK OUT</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row spacing"></div>	

			<div class="row spacing" id="push-bottom"></div>
			<div class="row spacing"></div>
			<div class="row spacing"></div>
		</div>
		<!--Content end-->
<script type="text/javascript">
(function() {
  document.addEventListener("DOMContentLoaded",
      function(event) {

		var cart_obj;
		var price_obj = new Object;

		function loadCartFromCookie(){
            var cart_cookie = getCookie('cart');
            if (cart_cookie == ""){
              cart_obj = new Object;
            }else{
              cart_obj = JSON.parse(cart_cookie);
            }
		}


		function saveCartToCookie(){
          	setCookie("cart",JSON.stringify(cart_obj), 100);
		}

        function updateCountLabel(){
			var label = document.getElementById("cart-cout-label");
			var count = 0;
            for (var item in cart_obj)   {
            	count ++;
            }
            label.innerHTML = count;
        }

        function updateTotalPrice(){
        	var sum = 0;
            for (var item in cart_obj)   {
            	sum += cart_obj[item] * price_obj[item];
            }
            document.getElementById("p-subtotal").innerHTML = Math.round(sum) / 100;
            document.getElementById("p-tax").innerHTML = Math.round(sum * 0.13) / 100;
            document.getElementById("p-total").innerHTML = Math.round(sum * 1.13) / 100;
        }

		function cartPage_renderBook(data){
			//Build the element
			var holder = document.createElement('div');
			holder.innerHTML = `
				<div class="item-list">
					<div class="item-list-thumb">
						<img src="`+data.image+`" />
					</div>
					<div class="item-list-content">
						<h2>`+data.title+`</h2>
						<p>
							<span>`+data.description+`</span>
						</p>
						<p>
							<span class="info-span info-price">$ `+(data.price / 100)+`CAD</span> <button class="normal-button minus-btn" bid="`+data.id+`"><i class="fas fa-minus"></i></button><input type="text" class="normal-textfield list-amount" value="`+cart_obj[data.id]+`"/><button class="normal-button add-btn"><i class="fas fa-plus"></i></button>
						</p>	
					</div>
				</div>
				<div class="clearfix"></div>
			`;
			
			let minusBtn = holder.getElementsByClassName("minus-btn")[0]; 
			let display = holder.getElementsByClassName("list-amount")[0];
			let plusBtn = holder.getElementsByClassName("add-btn")[0]; 
			let id = data.id;
			price_obj[id] = data.price;
			minusBtn.onclick = function(){
				if (cart_obj[id] === 0){
					return;
				}
				cart_obj[id] --;
				display.value = cart_obj[id];
				updateTotalPrice();
				saveCartToCookie();
			}
			plusBtn.onclick = function(){
				cart_obj[id] ++;
				display.value = cart_obj[id];
				updateTotalPrice();
				saveCartToCookie();
			}
			display.onchange = function(){
				if (display.value < 0){
					display.value = 0;
				}
				cart_obj[id] = display.value;
				updateTotalPrice();
				saveCartToCookie();
			}

			//Add to page
			var warp = document.getElementById("warpper");
			warp.appendChild(holder);
			updateTotalPrice();
		}


		let request_url = "http://localhost:8080/Citrus/REST/book/";

		function fetchBookInfoById(bookId, cb){
			var request = new XMLHttpRequest();
			request.onreadystatechange = function() {
			    if (request.readyState === 4 && request.status === 200) {
			    	var book_info = JSON.parse(request.responseText);
			    	if (book_info.id == undefined){
			    		return;
			    	}
			    	cb(book_info);
			    }
			};
			request.open("GET", request_url+ "id/" + bookId, true);
			request.send();
		}

		function loadShoppingCart(){
			loadCartFromCookie();
			updateCountLabel();
            for (var item in cart_obj) {

            	if (cart_obj[item] === 0){
            		delete cart_obj[item];
            		updateCountLabel();
            		saveCartToCookie();
            	}else{
            		fetchBookInfoById(item, cartPage_renderBook);	
            	}
            }
            updateTotalPrice();


		}

		loadShoppingCart();

      });
})();
</script>
<jsp:include page="footer.jsp" />
