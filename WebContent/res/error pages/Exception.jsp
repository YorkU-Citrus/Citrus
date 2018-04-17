<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>\
<%@ page  isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="/WEB-INF/header.jsp" />
<!--Content begin-->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>
</head>
<body>
	<div class="container">
	<div class="row spacing"></div>
	<div class="row">
		<div class="width-full">
			<div class="special-outline side-content">
				<h2 class="title-center form-title">Oops...</h2>
				<div style="margin:0 auto;">
					<table border="1">
						<tr>
							<td width="40%"><b>Error:</b></td>
							<td>${pageContext.exception}</td>
						</tr>
							
						<tr>
							<td><b>URI:</b></td>
							<td>${pageContext.errorData.requestURI}</td>
						</tr>
						
						<tr>
							<td><b>Status code:</b></td>
							<td>${pageContext.errorData.statusCode}</td>
						</tr>
						
						<tr>
							<td><b>Stack trace:</b></td>
							<td>
								<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
									<p>${trace}</p>
								</c:forEach>
							</td>
						</tr>
					</table>
				</div>


			</div>
		</div>
	</div>
	<div class="row spacing"></div>

	<div class="row spacing" id="push-bottom"></div>
	<div class="row spacing"></div>
	<div class="row spacing"></div>
</div>
</body>
</html>
<!--Content end-->
<jsp:include page="/WEB-INF/footer.jsp" />