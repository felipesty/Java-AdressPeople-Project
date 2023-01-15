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
		return peopleRepository.save(people);
	}
	
	public List<People> listPeople() {
		return peopleRepository.findAll();
	}
	
	public Optional<People> findById(Long id) {
		return peopleRepository.findById(id);
	}
	
	public People update(Long id, People people) {
		People oldPeople = peopleRepository.findById(id).orElse(null);
        if (oldPeople == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        people.setId(id);
        return peopleRepository.save(people);
    }
}
