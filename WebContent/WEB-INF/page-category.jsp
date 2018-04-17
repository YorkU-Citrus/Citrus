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
									<li><a href="#">Science</a></li>
									<li><a href="#">Engineer</a></li>
									<li><a href="#">Fiction</a></li>
								</ul>
							</div>
						</div>
						<div class="width-three-fourth">
							<div class="special-outline side-content">
								<form id="search-ctn">
									<input type="text" class="search-text text-box" placeholder="Find your book..." />
									<button type="submit" value="Search" class="search-button hide-on-mobile"><i class="fas fa-search"></i> Search</button>
								</form>
								<div class="item-list-info">
									<p>888 Result using the keyword "<strong>Funny</strong>"</p>
								</div>
								<div class="row spacing"></div>	
								<div class="item-list">
									<div class="item-list-thumb">
										<img src="res/1.jpg" />
									</div>
									<div class="item-list-content">
										<h2>Ryuuou no Oshigoto! 7</h2>
										<p>
											<span class="info-span"><i class="far fa-heart"></i> 7</span> <span class="info-span"><i class="far fa-comment"></i> 6</span>
											<br />
											<span>
											The story is about a teenage boy who happens to be a shogi master. One day, a nine-year-old girl turns up at his house, requesting to be taken as his disciple. From there, all kinds of wacky hijinks ensue.
											</span>
										</p>
										<p>
											<span class="info-span info-price">$ 9.99CAD</span> <button class="normal-button add_to_cart" bid="1" ><i class="fas fa-cart-arrow-down"></i> Add to Cart</button>
										</p>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="item-list">
									<div class="item-list-thumb">
										<img src="res/1.jpg" />
									</div>
									<div class="item-list-content">
										<h2>Ryuuou no Oshigoto! 7</h2>
										<p>
											<span class="info-span"><i class="far fa-heart"></i> 7</span> <span class="info-span"><i class="far fa-comment"></i> 6</span>
											<br />
											<span>
											The story is about a teenage boy who happens to be a shogi master. One day, a nine-year-old girl turns up at his house, requesting to be taken as his disciple. From there, all kinds of wacky hijinks ensue.
											</span>
										</p>
										<p>
											<span class="info-span info-price">$ 9.99CAD</span> <button class="normal-button add_to_cart" bid="2" ><i class="fas fa-cart-arrow-down"></i> Add to Cart</button>
										</p>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="item-list">
									<div class="item-list-thumb">
										<img src="res/1.jpg" />
									</div>
									<div class="item-list-content">
										<h2>Ryuuou no Oshigoto! 7</h2>
										<p>
											<span class="info-span"><i class="far fa-heart"></i> 7</span> <span class="info-span"><i class="far fa-comment"></i> 6</span>
											<br />
											<span>
											The story is about a teenage boy who happens to be a shogi master. One day, a nine-year-old girl turns up at his house, requesting to be taken as his disciple. From there, all kinds of wacky hijinks ensue.
											</span>
										</p>
										<p>
											<span class="info-span info-price">$ 9.99CAD</span> <button class="normal-button add_to_cart" bid="3" ><i class="fas fa-cart-arrow-down"></i> Add to Cart</button>
										</p>
									</div>
								</div>
								<div class="clearfix"></div>

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
<jsp:include page="footer.jsp" />
