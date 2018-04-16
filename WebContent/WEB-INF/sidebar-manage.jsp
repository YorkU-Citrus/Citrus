<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="width-one-fourth width-md-full">
	<div class="special-outline sidebar">
		<h3>My Account</h3>
		<ul>
			<li class="${billing-active}"><a href="?type=billing">Billing Information</a></li>
			<li class="${shipping-active}"><a href="?type=shipping">Shipping Information</a></li>
			<li class="${history-active}"><a href="?type=history">Order History</a></li>
		</ul>
	</div>
	<div class="row spacing"></div>
	<div class="special-outline sidebar">
		<h3>Webmaster Tools</h3>
		<ul>
			<li class="${analytics-active}"><a href="?type=analytics">Analytics Page</a></li>
			<li class="${products-active}"><a href="?type=products">Products</a></li>
			<li class="${orders-active}"><a href="?type=orders">Orders</a></li>
			<li class="${comments-active}"><a href="?type=comments">Comments</a></li>
		</ul>
	</div>
	<div class="row spacing"></div>
</div>