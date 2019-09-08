package com.example.isa_projekat.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa_projekat.DTO.KorisnikDTO;
import com.example.isa_projekat.DTO.PrijavaDTO;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.service.KorisnikService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/korisnik")
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KorisnikDTO>> findAll() {
		
		System.out.println("Korisnik finaAll()");
		
		return new ResponseEntity<>(korisnikService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/pretraga",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KorisnikDTO>> pretraga(@RequestBody KorisnikDTO pretraga) {
		
		System.out.println("pretraga("+pretraga.getIme()+","+pretraga.getPrezime()+")");
		
		return new ResponseEntity<>(korisnikService.pretraga(pretraga), HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> findOne(@PathVariable Long id) {
		
		System.out.println("findOne("+id+")");
		
		KorisnikDTO ret; 
		
		return (ret = korisnikService.findOne(id)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(ret,HttpStatus.OK);
	
	}
	
	@RequestMapping(
			value = "/login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrijavaDTO> login(@RequestBody PrijavaDTO log) {
		
		Optional<Korisnik> k = korisnikService.login(log.getEmail().trim());
	
		if(!k.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Korisnik ret = k.get();
		if(!ret.getPass().equals(log.getPass())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		PrijavaDTO r = new PrijavaDTO(ret);
		if(ret.isAdmin() && ret.getAdminOd() != null) {
			r.setAdminOd(ret.getAdminOd().getId());
		}
		return new ResponseEntity<>(r,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> update(@RequestBody KorisnikDTO novi) {
		
		System.out.println("/update("+ novi.getId() + ")");
		
		KorisnikDTO k;
		
		return (k = korisnikService.update(novi)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(k, HttpStatus.OK);
		
	}
}