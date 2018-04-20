<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
<!--Content begin-->
<div class="container">
	<div class="row spacing"></div>
	<div class="row" id="mainpage">
	
		<c:forEach items="${top_list}" var="item">
			<div class="width-one-fourth">
				<div class="thumb">
					<a href="item?id=${item.getBookId()}">
						<div class="img-box thumb-img">
							<img src="${item.getImage()}" />
						</div>
						<div class="thumb-title">
							<h4>${item.getTitle()}</h4>
							<span class="info-span"><i class="far fa-heart"></i> ${item.getRating()}/5</span> 
							<!-- <span class="info-span"><i class="far fa-comment"></i> ${item.getNumberOfComment()}</span> -->
						</div>
					</a>
				</div>
			</div>
		</c:forEach>
	
		
		
		
	</div>
	<div class="row spacing"></div>
	<div class="row">
		<div class="width-full">
			<div class="special-outline">
				<div class="row">
					<div class="width-one-fourth hide-on-mobile">
						<div class="thumb">
							<div class="img-box">
								<a href="list?category=2" ><img src="res/1.jpg" /></a>
							</div>
						</div>
					</div>
					<div class="width-three-fourth width-md-two-half">
						<div class="description-content">
							<h3>Read Me</h3>
							<p>This is just a web page for demo.</p>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 
	<div class="row spacing"></div>
	<div class="row">
		<div class="width-full">
			<div class="special-outline">
				<div class="row">
					<div class="width-three-fourth width-md-two-half">
						<div class="description-content">
							<h3>Engineer</h3>
						</div>

					</div>
					<div class="width-one-fourth hide-on-mobile">
						<div class="thumb">
							<div class="img-box">
								<img src="res/2.jpg" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row spacing"></div>
	<div class="row">
		<div class="width-full">
			<div class="special-outline">
				<div class="row">
					<div class="width-one-fourth hide-on-mobile">
						<div class="thumb">
							<div class="img-box">
								<img src="res/3.jpg" />
							</div>
						</div>
					</div>
					<div class="width-three-fourth width-md-two-half">
						<div class="description-content">
							<h3>Fiction</h3>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	 -->

	<div class="row spacing" id="push-bottom"></div>
	<div class="row spacing"></div>
	<div class="row spacing"></div>
</div>
<!--Content end-->
<jsp:include page="footer.jsp" />
