package com.backendproject.AdressPeople.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendproject.AdressPeople.entities.Adress;
import com.backendproject.AdressPeople.entities.People;
import com.backendproject.AdressPeople.repository.AdressRepository;
import com.backendproject.AdressPeople.repository.PeopleRepository;

@Service
public class AdressService {

	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	public Adress save(Long id, Adress adress) {
		try {
			People people = peopleRepository.findById(id).orElse(null);
	        if (people == null) {
	            throw new IllegalArgumentException("Pessoa não encontrada");
	        }
	        adress.setPeople(people);
	        return adressRepository.save(adress);
		} catch (Exception e) {
			throw new RuntimeException("erro ao criar endereço " + e.getMessage());
		}
        
    }

    public List<Adress> findAll(Long id) {
    	try {
    		People people = peopleRepository.findById(id).orElse(null);
            if (people == null) {
                throw new IllegalArgumentException("Pessoa não encontrada");
            }
            return adressRepository.findByPeople(people);
		} catch (Exception e) {
			throw new RuntimeException("erro ao listar endereços " + e.getMessage());
		}
    	
    }

    public Adress setMainAdress(Long id, Long idAdress) {
    	try {
    		People people = peopleRepository.findById(id).orElse(null);
            if (people == null) {
                throw new IllegalArgumentException("Pessoa não encontrada");
            }
            List<Adress> listAdress = adressRepository.findByPeople(people);
            for (Adress adress : listAdress) {
                if (adress.getId().equals(idAdress)) {
                	adress.setMain(true);
                } else {
                	adress.setMain(false);
                }
                adressRepository.save(adress);
            }
            return adressRepository.findById(idAdress).orElse(null);
		} catch (Exception e) {
			throw new RuntimeException("erro ao atualizar endereço principal " + e.getMessage());
		}
    }
}
