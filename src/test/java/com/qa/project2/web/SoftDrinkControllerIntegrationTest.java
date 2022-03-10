package com.qa.project2.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.project2.domain.SoftDrink;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:softdrink-schema.sql", "classpath:softdrink-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class SoftDrinkControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		SoftDrink testDrink = new SoftDrink(null, "Keurig Dr Pepper", "Dr Pepper Original", "Sparkling Fruit Flavour Soft Drink with Sugar and Sweeteners.");
		String testDrinkAsJSON = this.mapper.writeValueAsString(testDrink);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDrinkAsJSON);
		
		SoftDrink testCreatedDrink = new SoftDrink(8, "Keurig Dr Pepper", "Dr Pepper Original", "Sparkling Fruit Flavour Soft Drink with Sugar and Sweeteners.");
		String testCreatedDrinkAsJSON = this.mapper.writeValueAsString(testCreatedDrink);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedDrinkAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception {
		RequestBuilder req = get("/getAll");
		List<SoftDrink> testDrinks = List.of(new SoftDrink(1, "The Coca-Cola Company", "Coca-Cola Original", "The worlds most popular soft drink!"),
				new SoftDrink(2, "The Coca-Cola Company", "Coca-Cola Zero", "The worlds most popular soft drink, but sugar-free!"),
				new SoftDrink(3, "PepsiCo", "Pepsi", "The other popular soft drink!"),
				new SoftDrink(4, "PepsiCo", "Pepsi Max", "The other popular soft drink, but sugar free!"),
				new SoftDrink(5, "The Coca-Cola Company", "Fanta Orange", "Bright, bubbly, instantly refreshing and great tasting!"),
				new SoftDrink(6, "The Coca-Cola Company", "Fanta Fruit Twist", "A juicy blast of fruity flavours and fruit juice!"),
				new SoftDrink(7, "Keurig Dr Pepper", "7 Up", "The original lemon-lime soda."));
		
		String testDrinksAsJSON = this.mapper.writeValueAsString(testDrinks);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDrinksAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGet() throws Exception {
		RequestBuilder req = get("/get/1");
		SoftDrink testDrink = new SoftDrink(1, "The Coca-Cola Company", "Coca-Cola Original", "The worlds most popular soft drink!");
		String testDrinkAsJSON = this.mapper.writeValueAsString(testDrink);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDrinkAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testReplace() throws Exception {
		SoftDrink testDrink = new SoftDrink(null, "Cola-Pepsi", "Pepsi-Cola", "A sinful mix.");
		String testDrinkAsJSON = this.mapper.writeValueAsString(testDrink);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testDrinkAsJSON);
		
		SoftDrink testCreatedDrink = new SoftDrink(1, "Cola-Pepsi", "Pepsi-Cola", "A sinful mix.");
		String testCreatedDrinkAsJSON = this.mapper.writeValueAsString(testCreatedDrink);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testCreatedDrinkAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		RequestBuilder req = delete("/delete/1");
		ResultMatcher checkStatus = status().isNoContent();
		
		this.mvc.perform(req).andExpect(checkStatus);
	}

}
