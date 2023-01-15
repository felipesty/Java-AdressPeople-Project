package com.backendproject.AdressPeople.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.backendproject.AdressPeople.entities.People;
import com.backendproject.AdressPeople.service.PeopleService;

@RestController
@RequestMapping(value = "/people")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public People save(@RequestBody People people) {
		return peopleService.save(people);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<People> listPeople() {
		return peopleService.listPeople();
	}
	
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public People findById(@PathVariable Long id) {
		return peopleService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa não encontrada"));
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public People update(@PathVariable Long id, @RequestBody People people) {
		return peopleService.update(id, people);
	}
}
