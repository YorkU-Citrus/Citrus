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
						<div class="width-one-fourth width-md-full">
							<div class="special-outline sidebar">
								<h3>Category</h3>
								<ul>
									<c:forEach items="${category_list}" var="item">
										<c:choose>
											<c:when test="${js_cat_id eq  item.getCid()}">
												<li class="active"><a href="?category=${item.getCid()}">${item.getCtitle()}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="?category=${item.getCid()}">${item.getCtitle()}</a></li>	
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</ul>
							</div>
						</div>
						<div class="width-three-fourth">
							<div class="special-outline side-content">
								<form id="search-ctn" method="GET">
									<input type="text" name="search" class="search-text text-box" placeholder="Find your book..." value="${js_search}"/>
									<button type="submit" value="Search" class="search-button hide-on-mobile"><i class="fas fa-search"></i> Search</button>
								</form>
								<div class="item-list-info">
									${note}
								</div>
								<div class="row spacing"></div>	
								<div id="warpper">
								</div>

								<div class="item-list-info">
									<h4 id="loading-info"><i class="fas fa-sync fa-spin"></i> Please Hold On.... Loading...</h4>
								</div>
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

		function cartPage_renderBook(data){
			//Build the element
			let holder = document.createElement('div');
			holder.innerHTML = `
				<div class="item-list">
					<div class="item-list-thumb">
						<a href="item?id=`+data.id+`" target="_blank"><img src="`+data.image+`" /></a>
					</div>
					<div class="item-list-content">
						<a href="item?id=`+data.id+`" target="_blank"><h2>`+data.title+`</h2></a>
						<p>
							<span class="info-span"><i class="far fa-heart"></i> `+data.rating+`/5 </span> <span class="info-span"><i class="far fa-comment"></i> `+data.numberOfComment+` &nbsp;&nbsp;<i class="far fa-folder-open"></i> `+data.category+`</span>
							<br />
							<span>`+data.description+`</span>
						</p>
						<p>
							<span class="info-span info-price">$ `+(data.price / 100)+`CAD</span> <button class="normal-button add_to_cart" bid="`+data.id+`" ><i class="fas fa-cart-arrow-down"></i> Add to Cart</button>
						</p>
					</div>
				</div>
				<div class="clearfix"></div>
			`;
			
			let addToCartButton = holder.getElementsByClassName("add_to_cart")[0]; 
			let id = data.id;
			addToCartButton.onclick = function(){
				addBookToCart(id);
			}

			//Add to page
			var warp = document.getElementById("warpper");
			warp.appendChild(holder);
		}

		function displayLoadingError(){
			document.getElementById("loading-info").innerHTML = `<i class="fas fa-exclamation-triangle"></i> Something went wrong, please refresh the page.`
		}

		function loadingFinished(){
			document.getElementById("loading-info").innerHTML = `<i class="fas fa-check"></i> All books have been loaded.`
		}

		let onloadingbooks = false;
		let offset = 0;
		let request_url = "http://localhost:8080/Citrus/REST/book/";

		let search_keyword = "${js_search}"==="" ? null : "${js_search}";
		let category_id = "${js_cat_id}"==="" ? null : "${js_cat_id}";

		function fetchBook(cb){
			if (onloadingbooks == true){
				return;
			}
			onloadingbooks = true;
			var request = new XMLHttpRequest();
			request.onreadystatechange = function() {
			    if (request.readyState === 4){
					if (request.status === 200) {
				    	var list_info = JSON.parse(request.responseText);
				    	if (list_info.success === undefined){
				    		displayLoadingError();
				    		return;
				    	}
				    	offset += list_info.offset + list_info.count;
				    	for (var i in list_info.list){
				    		//console.log(list_info.list[i]);
				    		cb(list_info.list[i]);
				    	}
				    	if (list_info.count < 5){
				    		loadingFinished();
				    	}
				    }else{
				    	displayLoadingError();
				    }
			    	onloadingbooks = false;
			    } 
			};
			if (search_keyword != null){
				request.open("GET", request_url+ "search/" + search_keyword + "/" + offset, true);
			}else if (category_id != null){
				request.open("GET", request_url+ "category/" + category_id + "/" + offset, true);
			}else{
				request.open("GET", request_url+ "all/" + offset, true);
			}
			
			request.send();
		}

		window.onscroll = function(ev) {
		    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
		        //console.log("bottom");
				fetchBook(cartPage_renderBook);
		    };
		};
		fetchBook(cartPage_renderBook);

      });
})();
</script>
<jsp:include page="footer.jsp" />
