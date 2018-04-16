<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
  <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
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
					<h1 id="title"><img src="${pageContext.request.contextPath}/res/logo.png" id="logo-img" alt="Citrus"><span class="hidden-xs hidden-sm" id="sub-title">Your Online Manga Book Store</span></h1>
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
<jsp:include page="menu.jsp" />