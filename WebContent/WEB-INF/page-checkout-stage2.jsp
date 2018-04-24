<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />
	
		<!--Content begin-->
		<div class="container">
			<div class="row spacing"></div>
			<form method="POST">	
			<div class="row">
				
				<div class="width-two-fourth">
					<div class="special-outline side-content">
						<h2 class="title-left form-title">Billing Information</h2>
						<c:choose>
							<c:when test="${not empty billing_error }">
								<div class="payment-form message-box">
									<span class="error"><i class="fas fa-exclamation-circle"></i>
										${billing_error}</span>
								</div>
							</c:when>
						</c:choose>
						<div class="information-form payment-form">
							<label for="firstname">First Name</label>
							<input type="text" name="firstname" class="normal-textfield" value="${sessionScope.b_firstname}" id="field1"/>
							<label for="lastname">Last Name</label>
							<input type="text" name="lastname" class="normal-textfield" value="${sessionScope.b_lastname}" id="field2"/>
							<label for="addr1">Address</label>
							<input type="text" name="addr1" class="normal-textfield" value="${sessionScope.b_address}" id="field3"/>
							<label for="province">Province</label>
							<input type="text" name="province" class="normal-textfield" value="${sessionScope.b_provience}" id="field4"/>
							<label for="country">Country</label>
							<input type="text" name="country" class="normal-textfield" value="${sessionScope.b_country}" id="field5"/>
							<label for="pcode">Postal Code</label>
							<input type="text" name="pcode" class="normal-textfield" value="${sessionScope.b_postal}" id="field6"/>
							<hr />
							<span><strong>Credit Card Information</strong></span>
							<label for="creditcard">Credit Car Number</label>
							<input type="text" name="creditcard" class="normal-textfield" value=""/>
							<label for="creditcard-password">CVV</label>
							<input type="password" name="creditcard-password" class="normal-textfield" value=""/>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
				<div class="width-two-fourth" >
					<div class="special-outline side-content">
						<h2 class="title-left form-title">Shipping Address</h2>
						<c:choose>
							<c:when test="${not empty shipping_error }">
								<div class="payment-form message-box">
									<span class="error"><i class="fas fa-exclamation-circle"></i>
										${shipping_error}</span>
								</div>
							</c:when>
						</c:choose>
						<div class="information-form payment-form">
							<button id="same_as_billing" type="button">Same As Billing Address</button>
							<label for="firstname-ship">First Name</label>
							<input type="text" name="firstname-ship" class="normal-textfield" value="${sessionScope.s_firstname}" id="fielda1"/>
							<label for="lastname-ship">Last Name</label>
							<input type="text" name="lastname-ship" class="normal-textfield" value="${sessionScope.s_lastname}" id="fielda2"/>
							<label for="addr1-ship">Address</label>
							<input type="text" name="addr1-ship" class="normal-textfield" value="${sessionScope.s_address}" id="fielda3"/>
							<label for="province-ship">Province</label>
							<input type="text" name="province-ship" class="normal-textfield" value="${sessionScope.s_provience}" id="fielda4"/>
							<label for="country-ship">Country</label>
							<input type="text" name="country-ship" class="normal-textfield" value="${sessionScope.s_country}" id="fielda5"/>
							<label for="pcode-ship">Postal Code</label>
							<input type="text" name="pcode-ship" class="normal-textfield" value="${sessionScope.s_postal}" id="fielda6"/>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="row spacing"></div>	
			<input type="hidden" name="checkoutform" />
			<div class="row">
				<div class="width-full">
					<div class="special-outline">
						<pre>
${bill}						
						</pre>
						<button class="normal-button checkout-btn"><i class="far fa-check-circle"></i> PROCESS - Total Amount: $${total} CAD</button>
						<br>
						<p><small>* We will never share your information with any others.</small></p>
					</div>
				</div>
			</div>
			</form>
			<div class="row spacing" id="push-bottom"></div>
			<div class="row spacing"></div>
			<div class="row spacing"></div>
		</div>
		<!--Content end-->
<script>
(function() {
	  document.addEventListener("DOMContentLoaded",
	      function(event) {

document.getElementById("same_as_billing").onclick = function(){
	document.getElementById("fielda1").value = document.getElementById("field1").value;
	document.getElementById("fielda2").value = document.getElementById("field2").value;
	document.getElementById("fielda3").value = document.getElementById("field3").value;
	document.getElementById("fielda4").value = document.getElementById("field4").value;
	document.getElementById("fielda5").value = document.getElementById("field5").value;
	document.getElementById("fielda6").value = document.getElementById("field6").value;	
	return false;
}


      });
})();

</script>
<jsp:include page="footer.jsp" />
