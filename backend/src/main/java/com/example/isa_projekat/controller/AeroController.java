package com.example.isa_projekat.controller;

import java.util.ArrayList;
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

import com.example.isa_projekat.DTO.AddAeroDepDTO;
import com.example.isa_projekat.DTO.AerodromDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.service.AeroService;
import com.example.isa_projekat.service.AvioService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/aero")
public class AeroController {
	
	@Autowired
	private AeroService aeroService;
	
	@Autowired
	private AvioService avioService;
	
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AerodromDTO>> findAll() {
		List<Aerodrom> aero = aeroService.findAll();
		List<AerodromDTO> ret = new ArrayList<AerodromDTO>();
		for (Aerodrom a : aero) {
			ret.add(new AerodromDTO(a));
		}
		return new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/allexceptfor/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AerodromDTO>> findAll(@PathVariable Long id) {
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
		Optional<Aerodrom> k = aeroService.findOne(id);
	
		if(!k.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new AerodromDTO(k.get()),HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AerodromDTO> update(@RequestBody AerodromDTO novi) {
		Optional<Aerodrom> k = aeroService.findOne(novi.getId());
	
		if(!k.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Aerodrom ret = k.get();
		ret.setIme(novi.getIme());
		ret.setAdresa(novi.getAdresa());
		ret.setGrad(novi.getGrad());
		ret = aeroService.save(ret);
		return new ResponseEntity<>(new AerodromDTO(ret),HttpStatus.OK);
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
		Aerodrom ret = new Aerodrom();
		
		Optional<Aviokompanija> a = avioService.findOne(novi.getIdAvio());
		if(!a.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
		ret.setIme(novi.getIme());
		ret.setGrad(novi.getGrad());
		ret.setAdresa(novi.getAdresa());
		
		ret.getKompanije().add(a.get());
		
		a.get().getDestinacije().add(ret);
		
		ret= aeroService.save(ret);
		
		
		return new ResponseEntity<>(new AerodromDTO(ret),HttpStatus.CREATED);
	}
}
