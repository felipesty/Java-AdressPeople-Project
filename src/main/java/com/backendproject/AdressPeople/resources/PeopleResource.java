package com.backendproject.AdressPeople.resources;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.AdressPeople.entities.People;

@RestController
@RequestMapping(value = "/people")
public class PeopleResource {
	
	@GetMapping
	public ResponseEntity<People> findAll() {
		People ana = new People(1L, "Ana", new Date());
		return ResponseEntity.ok(ana);
	}
}
