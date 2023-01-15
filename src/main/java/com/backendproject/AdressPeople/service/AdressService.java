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
	
	public Adress save(Long id, Adress endereco) {
        People people = peopleRepository.findById(id).orElse(null);
        if (people == null) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
        endereco.setPeople(people);
        return adressRepository.save(endereco);
    }

    public Adress update(Long id, Long idAdress, Adress adress) {
    	People people = peopleRepository.findById(id).orElse(null);
        if (people == null) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
        adress.setId(idAdress);
        adress.setPeople(people);
        return adressRepository.save(adress);
    }

    public Adress findById(Long id, Long idAdress) {
    	People people = peopleRepository.findById(id).orElse(null);
        if (people == null) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
        return adressRepository.findById(idAdress).orElse(null);
    }

    public List<Adress> findAll(Long id) {
    	People people = peopleRepository.findById(id).orElse(null);
        if (people == null) {
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
        return adressRepository.findByPeople(people);
    }

    public Adress setMainAdress(Long id, Long idAdress) {
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
    }
}
