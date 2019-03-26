package com.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.OrderDAO;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.dao.UserDAO;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Review;

@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminHomeServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserDAO userDao = new UserDAO();
		OrderDAO orderDao = new OrderDAO();
		ReviewDAO reviewDao = new ReviewDAO();
		BookDAO bookDao = new BookDAO();
		CustomerDAO customerDao = new CustomerDAO();
		
		List<BookOrder> listMostRecentSales = orderDao.listMostRecentSales();
		List<Review> listMostRecentReviews = reviewDao.listMostRecent();
		
		long totalUsers = userDao.count();
		long totalBooks = bookDao.count();
		long totalCustomers = customerDao.count();
		long totalReviews = reviewDao.count();
		long totalOrders = orderDao.count();
		
		request.setAttribute("listMostRecentSales", listMostRecentSales);
		request.setAttribute("listMostRecentReviews", listMostRecentReviews);
		
		request.setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalBooks", totalBooks);
		request.setAttribute("totalCustomers", totalCustomers);
		request.setAttribute("totalReviews", totalReviews);
		request.setAttribute("totalOrders", totalOrders);
		
		String homepage = "index.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(homepage);
		dispatcher.forward(request, response);
	}

}
