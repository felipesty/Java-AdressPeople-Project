package com.backendproject.AdressPeople.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendproject.AdressPeople.entities.People;
import com.backendproject.AdressPeople.repository.PeopleRepository;

@Service
public class PeopleService {
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	public People save(People people) {
		try {
			return peopleRepository.save(people);
		} catch (Exception e) {
			throw new RuntimeException("erro ao criar pessoa " + e.getMessage());
		}
	}
	
	public List<People> listPeople() {
		try {
			return peopleRepository.findAll();
		} catch (Exception e) {
			throw new RuntimeException("erro ao listar pessoas " + e.getMessage());
		}
	}
	
	public Optional<People> findById(Long id) {
		try {
			return peopleRepository.findById(id);
		} catch (Exception e) {
			throw new RuntimeException("erro ao listar pessoas " + e.getMessage());
		}
	}
	
	public People update(Long id, People people) {
		try {
			People oldPeople = peopleRepository.findById(id).orElse(null);
	        if (oldPeople == null) {
	            throw new RuntimeException("Pessoa não encontrada");
	        }
	        people.setId(id);
	        return peopleRepository.save(people);
		} catch (Exception e) {
			throw new RuntimeException("erro ao atualizar pessoa " + e.getMessage());
		}
		
    }
}
