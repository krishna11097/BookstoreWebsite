package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;
import static com.bookstore.service.CommonUtitlity.*;

public class CategoryServices {
	private CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CategoryServices(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		
		categoryDAO = new CategoryDAO();
	}
	
	public void listCategory(String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDAO.listAll();
		
		request.setAttribute("listCategory", listCategory);
		
		forwardToPage("category_list.jsp", request, response);				
	}
	
	public void listCategory() throws ServletException, IOException {
		listCategory(null);
	}
	
	public void createCategory() throws ServletException, IOException {
		String name = request.getParameter("name");
		Category existCategory = categoryDAO.findByName(name);
		
		if (existCategory != null) {
			String message = "Could not create category."
					+ " A category with name " + name + " already exists.";
			showMessageBackend(message, request, response);
		} else {
			Category newCategory = new Category(name);
			categoryDAO.create(newCategory);
			String message = "New category created successfully.";
			listCategory(message);
		}
	}
	
	public void editCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		Category category = categoryDAO.get(categoryId);
		
		if (category != null) {
			request.setAttribute("category", category);
			forwardToPage("category_form.jsp", request, response);
		} else {
			String message = "Could not find category with ID " + categoryId;
			showMessageBackend(message, request, response);
		}
		
	}

	public void updateCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		String categoryName = request.getParameter("name");
		
		Category categoryById = categoryDAO.get(categoryId);
		Category categoryByName = categoryDAO.findByName(categoryName);
		
		if (categoryByName != null && categoryById.getCategoryId() != categoryByName.getCategoryId()) {
			String message = "Could not update category."
					+ " A category with name " + categoryName + " already exists.";
			
			showMessageBackend(message, request, response);			
		} else {
			categoryById.setName(categoryName);
			categoryDAO.update(categoryById);
			String message = "Category has been updated successfully";
			listCategory(message);
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		int categoryId = Integer.parseInt(request.getParameter("id"));
		String message;
		
		Category category = categoryDAO.get(categoryId);
		
		if (category == null) {
			message = "Could not find category with ID " + categoryId
					+ ", or it might have been deleted";
			showMessageBackend(message, request, response);
			return;
		}
		
		BookDAO bookDAO = new BookDAO();
		long numberOfBooks = bookDAO.countByCategory(categoryId);
				
		if (numberOfBooks > 0) {
			message = "Could not delete the category (ID: %d) because it currently contains some books.";
			message = String.format(message, numberOfBooks);
		} else {
			categoryDAO.delete(categoryId);		
			message = "The category with ID " + categoryId + " has been removed successfully.";
		}
		
		listCategory(message);
		
	}
	
}
