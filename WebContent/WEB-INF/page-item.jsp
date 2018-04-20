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
								<div class="img-box thumb-img">
									<img src="${book.getImage()}" />
								</div>
							</div>
						</div>
						<div class="width-three-fourth">
							<div class="special-outline side-content">
								<div class="description-content">
									<h1>${book.getTitle()}</h1>
									<p><span class="info-span"><i class="far fa-heart"></i> ${book.getRating()}/5</span> <span class="info-span"><i class="far fa-comment"></i> ${book.getNumberOfComment()}</span>
									</p>
									<hr />
									<br/>
									<p>${book.getDescription()}</p>
									<br/>
									<hr />
									<br />
									<p>
										<span class="info-span info-price">$ ${book.getPrice() / 100}CAD</span> <button class="normal-button add_to_cart" bid="${book.getBookId()}"><i class="fas fa-cart-arrow-down"></i> Add to Cart</button> <br />
										${book.getAmount()} item(s) in stock. Available in 24 hours <br /><br />
									</p>
								</div>
							</div>
						</div>
					</div>
					
				</div>
			</div>
			<div class="row spacing"></div>	

			<div class="row">
				<div class="width-full" >
					<div class="special-outline">
						<div class="description-content">
							<h3>Comments <span class="subtitle">(total ${book.getNumberOfComment()})</span></h3>
							<hr/>
							<c:choose>
								<c:when test="${book.getNumberOfComment() eq  0}">
									<p>No Comment.</p>
								</c:when>
								<c:otherwise>
								
								
										<table style="width:100%" class="comment-table">
											<c:forEach items="${comment_list}" var="item">
												<tr>
													<td class="info-span"><strong>${item.getUserName()}</strong></td>
													<td class="info-span"><i class="far fa-clock"></i> ${item.getTimetamp()}</td>
													<td class="info-span">
														<span class="info-span info-star">
															<c:choose>
																<c:when test="${item.getRating() eq 1}">
																<i class="fas fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i>
																</c:when>
																<c:when test="${item.getRating() eq 2}">
																<i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i>
																</c:when>
																<c:when test="${item.getRating() eq 3}">
																<i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i>
																</c:when>
																<c:when test="${item.getRating() eq 4}">
																<i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="far fa-heart"></i>
																</c:when>
																<c:when test="${item.getRating() eq 5}">
																<i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="fas fa-heart"></i><i class="fas fa-heart"></i>
																</c:when>
																<c:otherwise>
																<i class="far fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i><i class="far fa-heart"></i>
																</c:otherwise>
															</c:choose>
														</span>
													</td>
												</tr>
												<tr>
													<td class="info-span" colspan="3">${item.getContent()}</td>
												</tr>								
											</c:forEach>
										</table>					
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
			<div class="row spacing"></div>	
			<c:choose>
				<c:when test="${empty no_comment_permission}">

			<div class="row">
				<div class="width-full" >
					<div class="special-outline">
						<div class="description-content">
							<form method="post" >
								<hr/>
								<p>Give it a rate: 
									<span class="info-span info-star">
<i class="far fa-heart cmt-rate" nrate="1"></i><i class="far fa-heart cmt-rate" nrate="2"></i><i class="far fa-heart cmt-rate" nrate="3"></i><i class="far fa-heart cmt-rate" nrate="4"></i><i class="far fa-heart cmt-rate" nrate="5"></i>
									</span>
										(<span id="cmt-rate-display">0</span>/5)
								</p> 
								<hr/>
								<textarea id="cmt-area" name="cmt-textarea"></textarea>
								<hr/>
								<input type="hidden" name="rate" id="cmt-rate-field" value="0"/>
								<p><button type="submit" class="normal-button">Submit</button><span class="subtitle"></span></p> 
							</form>
						</div>
					</div>
				</div>
			</div>
<script type="text/javascript">
(function() {
  document.addEventListener("DOMContentLoaded",
  function(event) {
  	let hlist = document.getElementsByClassName("cmt-rate");
  	for (let i = 0; i < hlist.length; i++) {
  		hlist[i].onclick = function(){
  			//console.log(hlist[i].attributes["nrate"]);
  			for (var j = 0; j < hlist[i].attributes["nrate"].value; j++){
				hlist[j].className = hlist[j].className.replace( /(?:^|\s)far(?!\S)/g , 'fas' );
  			}
  			for (var j = hlist[i].attributes["nrate"].value; j < 5; j++){
				hlist[j].className = hlist[j].className.replace( /(?:^|\s)fas(?!\S)/g , 'far' );
  			}
  			document.getElementById("cmt-rate-field").value = i + 1;
  			document.getElementById("cmt-rate-display").innerHTML = i + 1;
  		}
  	};
  });
})();
</script>					
				</c:when>
				<c:otherwise>				
					<div class="row">
						<div class="width-full" >
							<div class="special-outline">
								<div class="description-content">
										<hr/>
										<p><span class="subtitle">${no_comment_permission}</span></p> 
										<hr/>
								</div>
							</div>
						</div>
					</div>
				</c:otherwise>
			
			</c:choose>
				
			<div class="row spacing"></div>	

			<div class="row spacing" id="push-bottom"></div>
			<div class="row spacing"></div>
			<div class="row spacing"></div>
		</div>
		<!--Content end-->
<jsp:include page="footer.jsp" />
