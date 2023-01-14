package com.backendproject.AdressPeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.AdressPeople.entities.Adress;

public interface AdressPeople extends JpaRepository<Adress, Long> {
	
}
