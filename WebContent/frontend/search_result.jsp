<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results for ${keyword} - Online Books Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<c:if test="${fn:length(result) == 0}">
			<h2>No Results for "${keyword}"</h2>
		</c:if>
		<c:if test="${fn:length(result) > 0}">
			<div class="book-group">
				<center><h2>Results for "${keyword}":</h2></center>
				<c:forEach items="${result}" var="book">
					<div>
						<div id="search-image">
							<div>
								<a href="view_book?id=${book.bookId}"> 
								<img class="book-small" src="data:image/jpg;base64,${book.base64Image}" />
								</a>
							</div>
						</div>
						<div id="search-description">
							<div>
								<h2><a href="view_book?id=${book.bookId}"> <b>${book.title}</b></a></h2>
							</div>
							<div><jsp:directive.include file="book_rating.jsp" /></div>
							<div>
								<i>by ${book.author}</i>
							</div>
							<div>
								<p>${fn:substring(book.description, 0, 100)}...</p>
							</div>					
						</div>
						<div id="search-price">
							<h3>$${book.price}</h3>
							<h3><a href="add_to_cart?book_id=${book.bookId}">Add To Cart</a></h3>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</div>

	<jsp:directive.include file="footer.jsp" />
</body>
</html>