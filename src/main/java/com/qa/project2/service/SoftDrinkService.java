package com.qa.project2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.project2.domain.SoftDrink;
import com.qa.project2.repo.SoftDrinkRepo;

@Service
public class SoftDrinkService implements ServiceInterface<SoftDrink>{

	private SoftDrinkRepo repo;
	
	@Autowired
	public SoftDrinkService(SoftDrinkRepo repo) {
		this.repo = repo;
	}
	
	public SoftDrink create(SoftDrink s) {
		SoftDrink created = this.repo.save(s);
		return created;
	}

	public List<SoftDrink> getAll() {
		return this.repo.findAll();
	}

	public SoftDrink get(Integer id) {
		Optional<SoftDrink> found = this.repo.findById(id);
		return found.get();
	}

	public SoftDrink replace(Integer id, SoftDrink s) {
		SoftDrink existing = this.repo.findById(id).get();
		existing.setBrand(s.getBrand());
		existing.setName(s.getName());
		existing.setDescription(s.getDescription());
		SoftDrink updated = this.repo.save(existing);
		return updated;
	}

	public void remove(Integer id) {
		this.repo.deleteById(id);
	}

}
