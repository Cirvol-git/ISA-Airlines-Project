package com.example.isa_projekat.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Date;

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
import com.example.isa_projekat.DTO.RezervacijaDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RezervacijaControllerTest {
	
	private static final String URL_PREFIX = "/api/rezervacija";
	
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
	public void testFindAllOfKorisnika() throws Exception {
		
		mockMvc.perform(get(URL_PREFIX + "/all/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
	}
	
	@Test
	public void testAllOdKorisnikIPotvrdjeno() throws Exception {
		
		mockMvc.perform(get(URL_PREFIX + "/rezervacije/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
	}
	
	@Test
	public void testFindAllOfLet() throws Exception {
		
		mockMvc.perform(get(URL_PREFIX + "/rezervacije/1")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType));
	}
	
	@Test
	public void testDecline() throws Exception {
		
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setId(1l);
		
		String json = TestUtil.json(dto);
		
		mockMvc.perform(put(URL_PREFIX + "/otkazi").contentType(contentType).content(json)).andExpect(status().isOk());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCreateFastReservation() throws Exception {
		
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setId(1l);
		dto.setId_korisnik(2l);
		dto.setVersion(55l);
		dto.setRezervisano(new Date());
		dto.setPotvrdjeno(true);
		dto.setPasos("1111");
		
		String json = TestUtil.json(dto);
		
		mockMvc.perform(post(URL_PREFIX + "/createFast").contentType(contentType).content(json)).andExpect(status().isCreated());
	}
}
