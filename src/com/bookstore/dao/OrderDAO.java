package com.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookstore.entity.BookOrder;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder> {

	@Override
	public BookOrder create(BookOrder order) {
		order.setOrderDate(new Date());		
		order.setStatus("Processing");
		
		return super.create(order);
	}

	@Override
	public BookOrder update(BookOrder order) {
		return super.update(order);
	}

	@Override
	public BookOrder get(Object orderId) {
		return super.find(BookOrder.class, orderId);
	}

	public BookOrder get(Integer orderId, Integer customerId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("orderId", orderId);
		parameters.put("customerId", customerId);
		
		List<BookOrder> result = super.findWithNamedQuery("BookOrder.findByIdAndCustomer", parameters );
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public void delete(Object orderId) {
		super.delete(BookOrder.class, orderId);		
	}

	@Override
	public List<BookOrder> listAll() {
		return super.findWithNamedQuery("BookOrder.findAll");
	}

	@Override
	public long count() {	
		return super.countWithNamedQuery("BookOrder.countAll");
	}
	
	public List<BookOrder> listByCustomer(Integer customerId) {
		return super.findWithNamedQuery("BookOrder.findByCustomer", 
				"customerId", customerId);
	}
	
	public List<BookOrder> listMostRecentSales() {
		return super.findWithNamedQuery("BookOrder.findAll", 0, 3);
	}

	public long countOrderDetailByBook(int bookId) {
		return super.countWithNamedQuery("OrderDetail.countByBook", "bookId", bookId);
	}
	
	public long countByCustomer(int customerId) {
		return super.countWithNamedQuery("BookOrder.countByCustomer", "customerId", customerId);
	}
}
