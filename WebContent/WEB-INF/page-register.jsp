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
			<div class="special-outline side-content">
				<h2 class="title-center form-title">Register</h2>
				<c:choose>
					<c:when test="${not empty error}">
						<div class="error-message">
							<span class="error"><i class="fas fa-exclamation-triangle"></i>
								${error}</span>
						</div>
					</c:when>
				</c:choose>
				<form class="login-form information-form" method="POST">
					<label for="username">User Name</label> <input type="text"
						name="username" /> <label for="password">Password</label> <input
						type="password" name="password" /> <label for="repassword">Repeat
						Password</label> <input type="password" name="repassword" /><input type="hidden" name="formtype" value="register"/>
					<button type="submit" id="create-new-account">Create New
						Account</button>
				</form>
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