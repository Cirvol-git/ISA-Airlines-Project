package com.example.isa_projekat.controller;

import java.util.List;

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

import com.example.isa_projekat.DTO.AddAeroDepDTO;
import com.example.isa_projekat.DTO.AerodromDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.service.AeroService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/aero")
public class AeroController {
	
	
	@Autowired
	private AeroService aeroService;
	
	
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AerodromDTO>> findAll() {
		
		List<AerodromDTO> ret = aeroService.findAll();
			
		return new ResponseEntity<>(ret,HttpStatus.OK);
	
	}
	
	@RequestMapping(
			value = "/allexceptfor/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AerodromDTO>> findAllEx(@PathVariable Long id) {
		
		System.out.println("findAllExceptFor("+id+")");
		
		List<AerodromDTO> ret = aeroService.allExceptFor(id); 
		
		if(ret != null) { 
		
			return new ResponseEntity<>(ret, HttpStatus.OK);
		
		}else {
		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AerodromDTO> findOne(@PathVariable Long id) {
		
		System.out.println("findOne("+id+")");
		
		AerodromDTO a;
		
		return (a = aeroService.findOne(id)) == null ?
		
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(a,HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AerodromDTO> update(@RequestBody AerodromDTO novi) {
		
		System.out.println("update("+novi.getId()+")");
		
		AerodromDTO a;
		
		return (a = aeroService.update(novi)) == null ?
			
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
	
				new ResponseEntity<>(a, HttpStatus.OK);	
	}
	
	@RequestMapping(
			value = "/adddep",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AerodromDTO> updatedep(@RequestBody AddAeroDepDTO dep) {
		
		System.out.println("addDep()");
		
		Aerodrom ret = aeroService.addDependecy(dep);
		
		if(ret != null) {
		
			return new ResponseEntity<>(new AerodromDTO(ret),HttpStatus.OK);
		
		}else {
		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(
			value = "/create",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AerodromDTO> create(@RequestBody AerodromDTO novi) {
		
		System.out.println("Aero create()");
		
		AerodromDTO ret;
		
		return (ret = new AerodromDTO(aeroService.create(novi))) == null ?
			
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(ret, HttpStatus.CREATED);
		
	}
}
