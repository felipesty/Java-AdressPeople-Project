package com.backendproject.AdressPeople.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.backendproject.AdressPeople.http.controller.AdressController;
import com.backendproject.AdressPeople.service.AdressService;


@WebMvcTest
public class AdressControllerTest {

	@Autowired
	private AdressController adressController;
	
	@MockBean
	private AdressService adressService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.adressController);
	}
	
	@Test
	public void testSave() {
	}
}
