package com.backendproject.AdressPeople.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.backendproject.AdressPeople.entities.People;
import com.backendproject.AdressPeople.http.controller.PeopleController;
import com.backendproject.AdressPeople.service.PeopleService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	private PeopleService peopleService;
	
	
	People PEOPLE = new People(1L, "Felipe", new Date());
	People PEOPLE2 = new People(2L, "July", new Date());

	@Test
	public void testSave() throws Exception {		
		Mockito.when(peopleService.save(PEOPLE)).thenReturn(PEOPLE);
		
		MockHttpServletRequestBuilder mock = MockMvcRequestBuilders.post("/people")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(PEOPLE));
		
		mockMvc.perform(mock)
			.andExpect(status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
			.andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Felipe")));
	}
	
	@Test
	public void testFindAll() throws Exception {
		List<People> listPeople = new ArrayList<>(Arrays.asList(PEOPLE, PEOPLE2));
		
		Mockito.when(peopleService.findAll()).thenReturn(listPeople);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/people")
			.contentType(MediaType.APPLICATION_JSON))
        	.andExpect(status().isOk())
        	.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
        	.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("July")));
	}
	
	@Test
	public void testFindById() throws Exception {
        Mockito.when(peopleService.findById(PEOPLE.getId())).thenReturn(Optional.of(PEOPLE));
        
        mockMvc.perform(MockMvcRequestBuilders
                .get("/people/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Felipe")));
	}
	
	@Test
	public void testUpdate() throws Exception {
		
        Mockito.when(peopleService.save(PEOPLE)).thenReturn(PEOPLE);

		
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/people")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(PEOPLE));

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Felipe")));
		
	}
}
