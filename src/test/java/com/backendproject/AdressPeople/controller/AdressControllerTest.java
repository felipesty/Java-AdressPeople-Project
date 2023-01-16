package com.backendproject.AdressPeople.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.backendproject.AdressPeople.entities.Adress;
import com.backendproject.AdressPeople.entities.People;
import com.backendproject.AdressPeople.http.controller.AdressController;
import com.backendproject.AdressPeople.service.AdressService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdressController.class)
public class AdressControllerTest {

	@Autowired
    MockMvc mockMvc;
	
	@Autowired
    ObjectMapper mapper;
	
	@MockBean
	private AdressService adressService;
	
	People PEOPLE  = new People(1L, "Felipe", new Date());
	Adress ADRESS = new Adress(1L, "Bairro", "6530000", "600", "Santa Inês", false, PEOPLE);
	Adress ADRESS2 = new Adress(2L, "Santo", "6530000", "480", "Santa Inês", true, PEOPLE);
	
	@Test
	public void testSave() throws Exception {
		Mockito.when(adressService.save(1L, ADRESS)).thenReturn(ADRESS);
		
		MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.post("/people/1/adress")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(ADRESS));
		
		mockMvc.perform(mock)
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.district", Matchers.is("Bairro")));
	}
	
	@Test
	public void testFindAll() throws Exception {
		
		List<Adress> listAdress = new ArrayList<>(Arrays.asList(ADRESS, ADRESS2));
		
		Mockito.when(adressService.findAll(1L)).thenReturn(listAdress);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/people/1/adress")
			.contentType(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk())
        	.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        	.andExpect(MockMvcResultMatchers.jsonPath("$[1].district", Matchers.is("Santo")));
	}
	
	@Test
	public void testSetMainAdress() throws Exception {
		Adress ADRESS_UPDATED = new Adress(1L, "Bairro", "6530000", "600", "Santa Inês", true, PEOPLE);

		Mockito.when(adressService.setMainAdress(1L, 1L)).thenReturn(ADRESS_UPDATED);
		
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders
				.put("/people/1/adress/1/main")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(ADRESS_UPDATED));

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$.main", Matchers.is(true)));
	}
}
