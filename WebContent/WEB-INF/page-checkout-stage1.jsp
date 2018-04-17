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
						<h2 class="title-center form-title">Returned Customer</h2>
						<form class="login-form information-form" method="POST" action="${pageContext.request.contextPath}/login?checkout=1">
							<label for="username">User Name</label>
							<input type="text" name="username" class="normal-textfield"/>
							<label for="password">Password</label>
							<input type="password" name="password" class="normal-textfield"/>
							<input type="hidden" name="formtype" value="login"/>
							<button type="submit">GO</button>
						</form>
					</div>
				</div>
				<div class="width-two-fourth" >
					<div class="special-outline side-content">
						<h2 class="title-center form-title">New Customer</h2>
						<form class="login-form information-form" method="POST" action="${pageContext.request.contextPath}/register?checkout=1">
							<label for="username">User Name</label>
							<input type="text" name="username" class="normal-textfield"/>
							<label for="password">Password</label>
							<input type="password" name="password" class="normal-textfield"/>
							<label for="repassword">Repeat Password</label>
							<input type="password" name="repassword" class="normal-textfield"/>
							<input type="hidden" name="formtype" value="register"/>
							<button type="submit">Create New Account</button>
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
