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
									
										<table style="width:100%" class="analytics-table">
										  <tr>
										    <th>Year</th>
										    <th>Month</th> 
										    <th>Sales Volume</th>
										  </tr>
											<c:forEach items="${monthly_list}" var="item">
												<tr>
													<td class="info-span">${item.getYear()}</td>
													<td class="info-span">${item.getMonth()}</td>
													<td class="info-span">${item.getSalesVolumn()}</td>
												</tr>											
											</c:forEach>
										</table>
									</div>
								</div>
								
								<div class="row spacing"></div>
								
								<div class="special-outline side-content">
									<h2 class="title-left form-title">Analytics: Top 10 Seller</h2>
									<div class="description-content">

										<table style="width:100%" class="analytics-table">
										  <tr>
										    <th>Rank</th>
										    <th>Title</th> 
										    <th>Sales Volume</th>
										    <th>Price</th>
										  </tr>
											<c:forEach items="${top_list}" var="item">
												<tr>
													<td class="info-span">#${item.getRank()}</td>
													<td class="info-span"><a href="item?id=${item.getBookId()}">${item.getTitle()}</a></td>
													<td class="info-span">${item.getOrderAmount()}</td>
													<td class="info-span">${item.getPrice() /100} CAD</td>
												</tr>											
											</c:forEach>
										</table>
					
									</div>
								</div>
								
								
								<div class="row spacing"></div>
								
								<div class="special-outline side-content">
									<h2 class="title-left form-title">Analytics: Buyer Report</h2>
									<div class="description-content">
									<table style="width:100%" class="analytics-table">
									  <tr>
									    <th>User</th>
									    <th>Postal Code</th> 
									    <th>Number of Orders</th>
									    <th>Total Consumption</th>
									  </tr>
										<c:forEach items="${buyer_list}" var="item">
											<tr>
												<td class="info-span">${item.getUserAnnomizedName()}</td>
												<td class="info-span">${item.getPostalCode()}</td>
												<td class="info-span">${item.getOrderAmount()}</td>
												<td class="info-span">${item.getTotalConsumption()/ 100} CAD</td>
											</tr>											
										</c:forEach>
									</table>
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