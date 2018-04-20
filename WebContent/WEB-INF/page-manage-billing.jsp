<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />

<!--Content begin-->
<div class="container">
	<div class="row spacing"></div>
	<div class="row">
		<div class="width-full">
			<div class="row">
				<jsp:include page="sidebar-manage.jsp" />
				<div class="width-three-fourth">
					<div class="special-outline side-content">

						<div class="special-outline side-content">
							<h2 class="title-left form-title">Billing Information</h2>
							<c:choose>
								<c:when test="${not empty error }">
									<div class="payment-form message-box">
										<span class="error"><i class="fas fa-exclamation-circle"></i>
											${error}</span>
									</div>
								</c:when>
								<c:when test="${not empty success }">
									<div class="payment-form message-box">
										<span class="success"><i class="far fa-check-circle"></i>
											${success}</span>
									</div>
								</c:when>
							</c:choose>
							<form class="information-form payment-form" method="POST">
								<label for="firstname">First Name</label> <input type="text"
									name="firstname" class="normal-textfield" value="${sessionScope.billing.getFirstName()}" /> <label
									for="lastname">Last Name</label> <input type="text"
									name="lastname" class="normal-textfield" value="${sessionScope.billing.getLastName()}"/>
								 <label
									for="address">Address</label> <input type="text" name="address"
									class="normal-textfield"  value="${sessionScope.billing.getStreet()}"/> <label for="province">Province</label>
								<input type="text" name="province" class="normal-textfield"  value="${sessionScope.billing.getProvince()}" />
								<label for="country">Country</label> <input type="text"
									name="country" class="normal-textfield"  value="${sessionScope.billing.getCountry()}"/> <label for="pcode">Postal
									Code</label> <input type="text" name="pcode" class="normal-textfield"  value="${sessionScope.billing.getZip()}"/>
								<input type="hidden" name="formtype" value="manage" />


								<button class="normal-button">
									<i class="far fa-check-circle"></i> Update
								</button>
							</form>
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