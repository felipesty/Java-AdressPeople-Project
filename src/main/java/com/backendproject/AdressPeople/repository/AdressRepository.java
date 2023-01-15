package com.backendproject.AdressPeople.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.AdressPeople.entities.Adress;
import com.backendproject.AdressPeople.entities.People;

public interface AdressRepository extends JpaRepository<Adress, Long> {
	
	List<Adress> findByPeople(People people);
	
}
