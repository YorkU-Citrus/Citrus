<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page  isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error page</title>
</head>
<body>
			
				
				
					<h2 class="title-center form-title">Oops... Caught an exception!</h2>
					<table border="1">
						<tr>
							<td width="20%"><b>Error:</b></td>
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
							<td style="vertical-align:top;"><b>Stack trace:</b></td>
							<td>
								
								<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
									<p><code>${trace}</code></p>
								</c:forEach>
							</td>
						</tr>
					</table>
				

</body>
</html>