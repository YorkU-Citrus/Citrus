<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!--Menu begin-->
<div class="container header-panel">
	<div class="row">
		<div class="width-full">
			<div id="menu">
				<ul class="nav-menu">
					<li><img src="${pageContext.request.contextPath}/res/logo-sm.png" id="logo-small" /></li>
				</ul>
				<ul class="nav-menu hide-on-mobile">
					<li><a href="index.html"><i class="fas fa-home"></i> Home</a></li>
					<li><a href="category.html"><i class="fas fa-bars"></i>
							Category</a></li>
				</ul>
				<ul class="nav-menu right-menu">
					<li><a href="${pageContext.request.contextPath}/login"><i class="fas fa-user"></i> Login</a></li>
					<li><a href="${pageContext.request.contextPath}/cart"><i class="fas fa-shopping-cart"></i>
							Cart</a>
						<div class="red-dot" style="top: -43px; left: 21px;">2</div></li>
				</ul>
			</div>
		</div>
	</div>
	<c:choose>
		<c:when test="${slide eq 'display'}">
			<div class="row">
				<div class="width-full">
					<div id="header-slide">
						<div class="img-box">
							<img src="${pageContext.request.contextPath}/res/20180316235046.png" />
						</div>
					</div>
				</div>
			</div>
		</c:when>
	</c:choose>
</div>
<!--Menu end-->