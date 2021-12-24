package com.example.newproj;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

class TestCases {

	@Test
	void test1ProductClass() 
	{
		Product obj1=new Product();
		obj1.setName("ProductCheck");
		assertEquals("ProductCheck",obj1.getName());
		obj1.setPrice(100.9);
		assertEquals(100.9,obj1.getPrice());
	}

	
	@Test
	void test1IngredientsClass() 
	{
		Ingredients obj1=new Ingredients();
		obj1.setQuantity(10);
		assertEquals(10,obj1.getQuantity());	
	}
	
	@Test
	void test1OrderClass() 
	{
		Order obj1= new Order();
		obj1.setTbaleNo(10);
		assertEquals(10,obj1.getTbaleNo());
	}
	
	@Test
	void test2OrderClass() 
	{
		ArrayList<OrderProduct> obj1 = new ArrayList<OrderProduct>();
		assertTrue(obj1.isEmpty());
	}
	
	@Test
	void test3OrderClass() 
	{
		ArrayList<OrderProduct> obj1 = new ArrayList<OrderProduct>();
		assertTrue(obj1.size()==0);
	}

	@Test
	void test4Orderlass() 
	{
		try 
		{
			ArrayList<OrderProduct> obj1 = new ArrayList<OrderProduct>();
			obj1.remove(0);
		}
		catch(Exception e)
		{
			System.out.println("Exception");
		}
	}
	@Test
	void test1TaskClass() 
	{
		Task obj1= new Task();
		assertNull(obj1.getEmployee());
		assertNull(obj1.getTaskDate());
	}
	
	@Test
	void test1InventoryClass() 
	{
		ArrayList<Ingredients> obj1 = new ArrayList<Ingredients>();
		assertTrue(obj1.isEmpty());
	}
	
	@Test
	void test2InventoryClass() 
	{
		ArrayList<Ingredients> obj1 = new ArrayList<Ingredients>();
		assertTrue(obj1.size()==0);
	}
	
	@Test
	void test3InventoryClass() 
	{
		try 
		{
			ArrayList<Ingredients> obj1 = new ArrayList<Ingredients>();
			obj1.remove(0);
		}
		catch(Exception e)
		{
			System.out.println("Exception");
		}
	}
	
	@Test
	void test1CartProductClass() 
	{
		CartProduct obj1= new CartProduct();
		obj1.setName("Testing1");
		obj1.setPrice(100.9);
		obj1.setQuantity(10);
		
		assertEquals("Testing1",obj1.getName());
		assertEquals(100.9,obj1.getPrice());
		assertEquals(10,obj1.getQuantity());
	}
	
	@Test
	void test1EmployeeClass() 
	{
		Employee obj1= new Employee();
		obj1.setFirstName("Asad");
		obj1.setLastName("khan");
		
		assertEquals("Asad khan",obj1.getFullName());
	}
	
	@Test
	void test1OrderProductClass() 
	{
		OrderProduct obj1=new OrderProduct();
		obj1.setProductName("ProductCheck");
		assertEquals("ProductCheck",obj1.getProductName());
		obj1.setQuantity(10);
		assertEquals(10,obj1.getQuantity());
	}
	
	@Test
	void test1ProductCatalogClass() 
	{
		ArrayList<ProductDescription> obj1 = new ArrayList<ProductDescription>();
		assertTrue(obj1.isEmpty());
	}
	
	@Test
	void test2ProductCatalogClass() 
	{
		ArrayList<ProductDescription> obj1 = new ArrayList<ProductDescription>();
		assertTrue(obj1.size()==0);
	}
	
	@Test
	void test3ProductCatalogClass() 
	{
		try 
		{
			ArrayList<ProductDescription> obj1 = new ArrayList<ProductDescription>();
			obj1.remove(0);
		}
		catch(Exception e)
		{
			System.out.println("Exception");
		}
	}
	
	@Test
	void test1ProductDescriptionClass() 
	{
		ProductDescription obj1=new ProductDescription();
		obj1.setName("P1");
		obj1.setRecipe("Testing recipe");
		obj1.setDescription("Testing Description");
		assertEquals("P1",obj1.getName());	
		assertEquals("Testing recipe",obj1.getRecipe());	
		assertEquals("Testing Description",obj1.getDescription());	
	}
	

}
