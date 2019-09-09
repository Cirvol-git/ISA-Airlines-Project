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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.isa_projekat.TestUtil;
import com.example.isa_projekat.DTO.LetDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetControllerTest {

	private static final String URL_PREFIX = "/api/let";
	
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
		.andExpect(content().contentType(contentType));
		
	}
	
	@Test
	public void testFindOne() throws Exception {
		
		mockMvc.perform(get(URL_PREFIX + "/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
		
	}
	
	@Test
	public void testFinByAvio() throws Exception {
		
		mockMvc.perform(get(URL_PREFIX + "/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
		
	}
	
	@Test
	public void testPretraga() throws Exception {
		
		LetDTO dto = new LetDTO();
		dto.setOd(1l);
		dto.setDoo(2l);
		dto.setTipLeta(0);
		dto.setKlasa(0);
		dto.setPrtljag(0);
		
		String json = TestUtil.json(dto);
		
		mockMvc.perform(post(URL_PREFIX + "/pretraga").contentType(contentType).content(json)).andExpect(status().isOk());
	}
}
