<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
	
		<!--Content begin-->
		<div class="container">
			<div class="row spacing"></div>	
			<div class="row">
				<div class="width-two-fourth">
					<div class="special-outline side-content">
						<h2 class="title-left form-title">Billing Information</h2>
						<form class="information-form payment-form">
							<label for="firstname">First Name</label>
							<input type="text" name="firstname" class="normal-textfield"/>
							<label for="lastname">First Name</label>
							<input type="text" name="lastname" class="normal-textfield"/>
							<hr />
							<span><strong>Credit Card Information</strong></span>
							<label for="creditcard">Credit Car Number</label>
							<input type="text" name="creditcard" class="normal-textfield"/>
							<label for="creditcard-password">CVV</label>
							<input type="text" name="creditcard-password" class="normal-textfield"/>
							<hr />
							<span><strong>Billing address</strong></span>
							<label for="addr1">Address</label>
							<input type="text" name="addr1" class="normal-textfield"/>
							<label for="province">Province</label>
							<input type="text" name="province" class="normal-textfield"/>
							<label for="country">Country</label>
							<input type="text" name="country" class="normal-textfield"/>
							<label for="pcode">Postal Code</label>
							<input type="text" name="pcode" class="normal-textfield"/>
						</form>
					</div>
				</div>
				<div class="width-two-fourth" >
					<div class="special-outline side-content">
						<h2 class="title-left form-title">Shipping Address</h2>
						<form class="information-form payment-form">
							<button>Same As Billing Address</button>
							<label for="firstname-ship">First Name</label>
							<input type="text" name="firstname-ship" class="normal-textfield"/>
							<label for="lastname-ship">Last Name</label>
							<input type="text" name="lastname-ship" class="normal-textfield"/>
							<label for="addr1-ship">Address</label>
							<input type="text" name="addr1-ship" class="normal-textfield"/>
							<label for="province-ship">Province</label>
							<input type="text" name="province-ship" class="normal-textfield"/>
							<label for="country-ship">Country</label>
							<input type="text" name="country-ship" class="normal-textfield"/>
							<label for="pcode-ship">Postal Code</label>
							<input type="text" name="pcode-ship" class="normal-textfield"/>
						</form>
					</div>
				</div>
			</div>
			<div class="row spacing"></div>	

			<div class="row">
				<div class="width-full">
					<div class="special-outline">
						<button class="normal-button checkout-btn"><i class="far fa-check-circle"></i> PROCESS - Total Amount: $999,999 CAD</button>
						<br>
						<p><small>* We will never share your information with any others.</small></p>
					</div>
				</div>
			</div>


			<div class="row spacing" id="push-bottom"></div>
			<div class="row spacing"></div>
			<div class="row spacing"></div>
		</div>
		<!--Content end-->
<jsp:include page="footer.jsp" />
