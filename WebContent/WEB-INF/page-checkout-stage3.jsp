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
							<h2 class="title-left"><i class="far fa-times-circle" style="color:#FF5722;"></i> ${error}</h2>
							<div class="description-content">
								<p>Please try again or contact the manager.</p>
								<p><br><br><br><br><br></p>
							</div>
						</c:when>
						<c:otherwise>
							<h2 class="title-left"><i class="far fa-check-circle" style="color:#009688;"></i> Order Successfully Completed!</h2>
							<div class="description-content">
								<p>Your order has been proceed! Your shipment information will be updated immediately.</p>
								<p>Check other products if you love them.</p>
								<p><br><br><br><br><br></p>
							</div>
							
<script type="text/javascript">
(function() {
  document.addEventListener("DOMContentLoaded",
  function(event) {
      setCookie("cart", "", 100);
  });
  history.pushState(null, null, document.URL);
  window.addEventListener('popstate', function () {
      history.pushState(null, null, document.URL);
  });
})();
</script>
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
