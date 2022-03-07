package com.qa.project2.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.project2.domain.SoftDrink;
import com.qa.project2.service.SoftDrinkService;

@RestController
public class SoftDrinkController {

	private SoftDrinkService service;
	
	@Autowired
	public SoftDrinkController(SoftDrinkService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<SoftDrink> createProduct(@RequestBody SoftDrink s){
		SoftDrink created = this.service.create(s);
		ResponseEntity<SoftDrink> response = new ResponseEntity<SoftDrink>(created, HttpStatus.CREATED);
		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<SoftDrink>> getAllSoftDrinks(){
		return ResponseEntity.ok(this.service.getAll());
	}
	
	@GetMapping("/get/{id}")
	public SoftDrink getSoftDrink(@PathVariable Integer id) {
		return this.service.get(id);
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<SoftDrink> replaceSoftDrink(@PathVariable Integer id, @RequestBody SoftDrink s){
		SoftDrink replaced = this.service.replace(id, s);
		ResponseEntity<SoftDrink> response = new ResponseEntity<SoftDrink>(replaced, HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
