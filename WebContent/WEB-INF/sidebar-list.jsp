<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


						<div class="width-one-fourth width-md-full">
							<div class="special-outline sidebar">
								<h3>Category</h3>
								<ul>
									<c:forEach items="${category_list}" var="item">
										<c:choose>
											<c:when test="${js_cat_id eq  item.getCid()}">
												<li class="active"><a href="?category=${item.getCid()}">${item.getCtitle()}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="?category=${item.getCid()}">${item.getCtitle()}</a></li>	
											</c:otherwise>
										</c:choose>
										
									</c:forEach>
								</ul>
							</div>
						</div>