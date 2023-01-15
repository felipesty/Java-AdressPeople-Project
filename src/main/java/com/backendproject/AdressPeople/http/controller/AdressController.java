package com.backendproject.AdressPeople.http.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.AdressPeople.entities.Adress;
import com.backendproject.AdressPeople.service.AdressService;

@RestController
@RequestMapping(value = "people/{id}/adress")
public class AdressController {

	@Autowired
	private AdressService adressService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Adress save(@PathVariable Long id, @RequestBody Adress adress) {
		return adressService.save(id, adress);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Adress> findAll(@PathVariable Long id) {
		return adressService.findAll(id);
	}
	
	@PutMapping("/{idAdress}/main")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Adress setMainAdress(@PathVariable Long id, @PathVariable Long idAdress) {
		return adressService.setMainAdress(id, idAdress);
	}
}
