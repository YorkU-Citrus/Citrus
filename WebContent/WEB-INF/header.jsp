<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>EECS 4413 Project</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/grid.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/master.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fa-regular.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fa-solid.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/fontawesome.min.css">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1">
</head>
<body>
	<div id="main-body">
		<!--Header begin-->
		<div class="container">
			<div class="row" id="header">
				<div class="width-three-fourth" id="logo">
					<h1 id="title"><img src="res/logo.png" id="logo-img" alt="Citrus"><span class="hidden-xs hidden-sm" id="sub-title">Your Online Manga Book Store</span></h1>
				</div>
				<div class="width-one-fourth width-md-full" id="logo">
					<form id="search">
						<input type="text" class="search-text text-box" placeholder="Find your book..." />
						<input type="submit" value="Search" class="search-button hide-on-mobile" /> 
					</form>
				</div>
			</div>
		</div>
		<!--Header end-->

		<!--Menu begin-->
		<div class="container header-panel">
			<div class="row">
				<div class="width-full">
					<div id="menu">
						<ul class="nav-menu">
							<li><img src="res/logo-sm.png" id="logo-small"/></li>
						</ul>
						<ul class="nav-menu hide-on-mobile">
							<li><a href="index.html"><i class="fas fa-home"></i> Home</a></li>
							<li><a href="category.html"><i class="fas fa-bars"></i> Category</a></li>
						</ul>
						<ul class="nav-menu right-menu">
							<li><a href="login.html"><i class="fas fa-user"></i> Login</a></li>
							<li><a href="cart.html"><i class="fas fa-shopping-cart"></i> Cart</a><div class="red-dot" style="top: -43px;left: 21px;">2</div></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="width-full">
					<div id="header-slide">
						<div class="img-box">
							<img src="res/20180316235046.png" />
						</div>
					</div>				
				</div>
			</div>
		</div>
		<!--Menu end-->