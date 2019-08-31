package com.example.isa_projekat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa_projekat.DTO.RezervacijaDTO;
import com.example.isa_projekat.service.RezervacijaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/rezervacija")
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@RequestMapping(
			value = "/all/{id}",
			method  = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> findAll(@PathVariable Long id) {
		
		System.out.println("findAllFromUser("+id+")");
		
		List<RezervacijaDTO> ret;
		
		return (ret = rezervacijaService.findByKorisnik(id)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/rezervacije/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> getRezervacije(@PathVariable Long id) {
		
		System.out.println("findAllConfermed("+id+")");
		
		List<RezervacijaDTO> ret;
		
		return (ret = rezervacijaService.findByKorisnikAndPotvrdjeno(id, true)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/invites/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> findAllInvites(@PathVariable Long id) {
		
		System.out.println("findAllInvites("+id+")");
		
		List<RezervacijaDTO> ret;
		
		return (ret = rezervacijaService.findByKorisnikAndPotvrdjeno(id, false)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/potvrdi",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> potvrdi(@RequestBody RezervacijaDTO rezDTO) {
		
		System.out.println("potvrdi("+rezDTO.getId()+")");
		
		RezervacijaDTO ret;
		
		return (ret = rezervacijaService.accept(rezDTO)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(ret, HttpStatus.OK);
				
	}
	
	@RequestMapping(
			value = "/create",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> create(@RequestBody List<RezervacijaDTO> novi) {
		
		System.out.println("createRezervacije()");
		
		List<RezervacijaDTO> retList = null;
		
		try {
			
			retList = rezervacijaService.createRezervacije(novi);
		
		}catch(NullPointerException e) {
		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		}catch(IllegalArgumentException e) {
		
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		}catch(ObjectOptimisticLockingFailureException e) {
			
			System.out.println("ObjectOptimisticLockingFailureException");
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(retList,HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/createFast",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> createFast(@RequestBody RezervacijaDTO rezDTO) {
		
		System.out.println("createFast()");
		
		RezervacijaDTO ret;
		
		try {
			
		return (ret = rezervacijaService.createBrzaRez(rezDTO)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				
				new ResponseEntity<>(ret,HttpStatus.CREATED);
		
		}catch(ObjectOptimisticLockingFailureException e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(
			value = "/otkazi",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> otkazi(@RequestBody RezervacijaDTO rezDTO){
		
		System.out.println("otkzi("+rezDTO.getId()+")");
		
		RezervacijaDTO ret;
		
		return (ret = rezervacijaService.decline(rezDTO)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
} 
