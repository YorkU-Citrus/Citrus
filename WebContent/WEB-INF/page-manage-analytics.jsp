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
						<jsp:include page="sidebar-manage.jsp" />
						<div class="width-three-fourth">

								<div class="special-outline side-content">
									<h2 class="title-left form-title">Analytics: Books sold each month</h2>
									<div class="description-content">
										<c:forEach items="${monthly_list}" var="item">
											<hr>
											<p>
												<strong>Year:</strong> ${item.getYear()}
												<strong>Month:</strong> ${item.getMonth()}
												<strong>Sold:</strong> ${item.getAmount()}
											</p>											
										</c:forEach>
									</div>
								</div>
								
								<div class="row spacing"></div>
								
								<div class="special-outline side-content">
									<h2 class="title-left form-title">Analytics: Top 10 Seller</h2>
									<div class="description-content">
										<p style="font-weight:100;">${message}</p>
										<c:forEach items="${top_list}" var="item">
											<hr>
											<p>
												<strong>Rank #${item.getRank()}</strong>
												<br/>
												<strong>Title</strong>: <a href="item?id=${item.getBookId()}">${item.getTitle()}</a>
												<br/>
												<strong>Sold</strong>: ${item.getOrderAmount()}
												<br/>
												<strong>Price</strong>: ${item.getPrice() /100} CAD
											</p>											
										</c:forEach>
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
<jsp:include page="footer.jsp" />