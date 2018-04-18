<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="width-one-fourth width-md-full">
	<div class="special-outline sidebar">
		<h3>My Account</h3>
		<ul>
			<li class="${billingactive}"><a href="?type=billing">Billing Information</a></li>
			<li class="${shippingactive}"><a href="?type=shipping">Shipping Information</a></li>
			<li class="${historyactive}"><a href="?type=history">Order History</a></li>
			<li><hr></li>
			<li class=""><a href="?type=signout"><strong>Sign Out</strong></a></li>
		</ul>
	</div>
	<div class="row spacing"></div>
	<c:if test="${not empty manager}">
		<div class="special-outline sidebar">
			<h3>Webmaster Tools</h3>
			<ul>
				<li class="${analyticsactive}"><a href="?type=analytics">Analytics Page</a></li>
				<li class="${productsactive}"><a href="?type=products">Products</a></li>
				<li class="${ordersactive}"><a href="?type=orders">Orders</a></li>
				<li class="${commentsactive}"><a href="?type=comments">Comments</a></li>
			</ul>
		</div>
		<div class="row spacing"></div>
	</c:if>
</div>