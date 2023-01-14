package com.backendproject.AdressPeople.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendproject.AdressPeople.entities.People;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
