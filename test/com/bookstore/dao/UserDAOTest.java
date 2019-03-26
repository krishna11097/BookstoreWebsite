package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		userDAO = new UserDAO();		
	}
	
	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("john3@gmail.com");
		user1.setFullName("John Smith 3");
		user1.setPassword("johnny3");
		
		user1 = userDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();		
		user1 = userDAO.create(user1);		
	}
	
	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(1);
		user.setEmail("nam@codejava.net");
		user.setFullName("Nam Ha Minh");
		user.setPassword("mysecret");
		
		user = userDAO.update(user);
		String expected = "mysecret";
		String actual = user.getPassword();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetUsersFound() {
		Integer userId = 1;
		Users user = userDAO.get(userId);
		
		if (user != null) {
			System.out.println(user.getEmail());
		}
		
		assertNotNull(user);
	}

	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test
	public void testDeleteUsers() {
		Integer userId = 5;
		userDAO.delete(userId);
		
		Users user = userDAO.get(userId);
		
		assertNull(user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		Integer userId = 55;
		userDAO.delete(userId);
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		
		for (Users user : listUsers) {
			System.out.println(user.getEmail());
		}
		
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalUsers = userDAO.count();
		
		assertTrue(totalUsers > 0);
		
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "nam@codejava.net";
		String password = "mysecret";
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertTrue(loginResult);
	}

	@Test
	public void testCheckLoginFailed() {
		String email = "nam123";
		String password = "mysecret1";
		boolean loginResult = userDAO.checkLogin(email, password);
		
		assertFalse(loginResult);
	}
	
	@Test
	public void testFindByEmail() {
		String email = "nam@codejava.net";
		Users user = userDAO.findByEmail(email);
		
		assertNotNull(user);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.close();
	}
}
