package com.example.isa_projekat.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.isa_projekat.TestUtil;
import com.example.isa_projekat.DTO.AerodromDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AeroControllerTest {
	
	private static final String URL_PREFIX = "/api/aero";
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	
	private MockMvc mockMvc;
	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	
	@Before
	public void setup() {
	
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testFindAll() throws Exception {
		
		mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));//.andExpect(jsonPath("$", hasSize(1)))
		//.andExpect(jsonPath("$.[*].id").value(hasItem(1l))
		//.andExpect(jsonPath("$.[*].firstName").value(hasItem("ime")))
		//.andExpect(jsonPath("$.[*].lastName").value(hasItem("prezime")))
		//.andExpect(jsonPath("$.[*].index").value(hasItem("asfa")));
	}
	
	@Test
	public void testFindOne() throws Exception {
		
		mockMvc.perform(get(URL_PREFIX + "/2")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCreate() throws Exception {
		
		AerodromDTO dto = new AerodromDTO();
		dto.setIdAvio(1l);
		dto.setGrad("Novi Sad");
		dto.setAdresa("Svetozara Miletica 50");
		dto.setIme("NS Airlines");
		
		String json = TestUtil.json(dto);
		
		mockMvc.perform(post(URL_PREFIX + "/create").contentType(contentType).content(json)).andExpect(status().isCreated());
		//.andExpect(content().contentType(contentType));
		//this.mockMvc.perform(put(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isOk());
		
	}
}
