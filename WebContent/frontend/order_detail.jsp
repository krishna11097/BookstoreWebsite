<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>My Order Details - Evergreen Bookstore</title>
	<link rel="stylesheet" href="css/style.css" >	
</head>
<body>
	<jsp:directive.include file="header.jsp" />
	
	<c:if test="${order == null}">
		<div align="center">
			<h2 class="pageheading">Sorry, you are not authorized to view this order</h2>
		<div align="center">
	</c:if>
	
	<c:if test="${order != null}">
	<div align="center">
		<h2 class="pageheading">Your Order ID: ${order.orderId}</h2>		
	</div>
	
	<div align="center">
		<table>
			<tr>
				<td><b>Order Status: </b></td>
				<td>${order.status}</td>
			</tr>		
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>			
			<tr>
				<td><b>Quantity: </b></td>
				<td>${order.bookCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount: </b></td>
				<td><fmt:formatNumber value="${order.total}" type="currency" /></td>
			</tr>			
			<tr>
				<td><b>Recipient Name: </b></td>
				<td>${order.recipientName}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td>${order.recipientPhone}</td>
			</tr>
			<tr>
				<td><b>Ship to: </b></td>
				<td>${order.shippingAddress}</td>
			</tr>			
			<tr>
				<td><b>Payment Method: </b></td>
				<td>${order.paymentMethod}</td>
			</tr>
												
		</table>
	</div>
	<div align="center">
		<h2>Ordered Books</h2>
		<table border="1">
			<tr>
				<th>No</th>
				<th>Book</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>
					<img style="vertical-align: middle;" src="data:image/jpg;base64,${orderDetail.book.base64Image}" width="48" height="64" />
					${orderDetail.book.title}
				</td>
				<td>${orderDetail.book.author}</td>
				<td><fmt:formatNumber value="${orderDetail.book.price}" type="currency" /></td>
				<td>${orderDetail.quantity}</td>
				<td><fmt:formatNumber value="${orderDetail.subtotal}" type="currency" /></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL:</i></b>
				</td>
				<td>
					<b>${order.bookCopies}</b>
				</td>
				<td>
					<b><fmt:formatNumber value="${order.total}" type="currency" /></b>
				</td>
			</tr>
		</table>
	</div>	
	</c:if>
	<jsp:directive.include file="footer.jsp" />
</body>
</html>