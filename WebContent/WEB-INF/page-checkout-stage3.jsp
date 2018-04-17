<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />

		<!--Content begin-->
		<div class="container">
			<div class="row spacing"></div>	
			<div class="row">
				<div class="width-full side-content">
					<div class="special-outline">
					<c:choose>
						<c:when test="${not empty error}">
							<h2 class="title-left"><i class="fa-times-circle" style="color:red"></i> ${error}!</h2>
							<div class="description-content">
								<p>Please try again or contact the manager.</p>
								<p><br><br><br><br><br></p>
							</div>
						</c:when>
						<c:otherwise>
							<h2 class="title-left"><i class="far fa-check-circle" style="color:green"></i> Order Successfully Completed!</h2>
							<div class="description-content">
								<p>Your order has be proceed! Your shipment information will be updated immediately.</p>
								<p>Check other products if you love them.</p>
								<p><br><br><br><br><br></p>
							</div>
						</c:otherwise>
					</c:choose>
					</div>
				</div>
			</div>

			<div class="row spacing" id="push-bottom"></div>
			<div class="row spacing"></div>
			<div class="row spacing"></div>
		</div>
		<!--Content end-->
<jsp:include page="footer.jsp" />
