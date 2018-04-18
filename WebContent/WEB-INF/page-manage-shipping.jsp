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

								<div class="special-outline side-content">
									<h2 class="title-left form-title">Order History</h2>
									<div class="description-content">
										<p style="font-weight:100;">${message}</p>
										
										<c:forEach items="${order_list}" var="item">											
											<hr>
											<p>
												<strong>Order ID</strong>: ${item.getId()} &nbsp;&nbsp;&nbsp;&nbsp;
												
												<c:choose>
													<c:when test="${item.getStatus() == 'COMPLETED'}">
														<span style="color: #009688;"><i class="far fa-check-circle"></i> Fulfilled</span>
													</c:when>
													<c:when test="${item.getStatus() == 'SHIPPED'}">
														<span style="color: #2196F3;"><i class="fas fa-shipping-fast"></i> Shipping</span>
													</c:when>
													<c:when test="${item.getStatus() == 'ORDERED'}">
														<span style="color: #2196F3;"><i class="far fa-calendar-check"></i> Ordered</span>
													</c:when>
													<c:when test="${item.getStatus() == 'DENIED'}">
														<span style="color: #FF5722;"><i class="far fa-times-circle"></i> Canceled</span>
													</c:when>
												</c:choose>
												
												<br/>
												<strong>Order Time</strong>: ${item.getTimestamp()}
												<br/>
												<strong>Price</strong>: ${item.getTotalPrice() / 100} CAD
											</p>
											
										</c:forEach>
									</div>
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