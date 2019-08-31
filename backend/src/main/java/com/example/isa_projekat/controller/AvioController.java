package com.example.isa_projekat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.isa_projekat.DTO.AerodromDTO;
import com.example.isa_projekat.DTO.AviokompanijaDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.service.AvioService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/avio")
public class AvioController {
	
	@Autowired
	private AvioService avioService;
	
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AviokompanijaDTO>> findAll() {
		List<Aviokompanija> avios = avioService.findAll();
		List<AviokompanijaDTO> ret = new ArrayList<AviokompanijaDTO>();
		for (Aviokompanija a : avios) {
			ret.add(new AviokompanijaDTO(a));
		}
		return new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AviokompanijaDTO> findOne(@PathVariable Long id) {
		Optional<Aviokompanija> k = avioService.findOne(id);
	
		if(!k.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Set<Aerodrom> dest = k.get().getDestinacije();
		List<AerodromDTO> ret_dest = new ArrayList<AerodromDTO>();
		for (Aerodrom aerodrom : dest) {
			ret_dest.add(new AerodromDTO(aerodrom));
		}
		AviokompanijaDTO ret = new AviokompanijaDTO(k.get());
		ret.setDestinacije(ret_dest);
		
		return new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AviokompanijaDTO> update(@RequestBody AviokompanijaDTO novi) {
		
		System.out.println("avioUpdate()");
		
		Optional<Aviokompanija> k = avioService.findOne(novi.getId());
	
		if(!k.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Aviokompanija ret = k.get();
		
		ret.setIme(novi.getIme());
		
		if(ret.getVersion() != novi.getVersion()) {
			
			System.out.println("OptimisticLockingFailureException");
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}else {
		
			ret.setVersion(novi.getVersion());
		}
		
		ret.setAdresa(novi.getAdresa());
		ret.setOpis(novi.getOpis());
		
		try {
			
			ret = avioService.save(ret);
			
		}catch(OptimisticLockingFailureException e) {
			
			System.out.println("OptimisticLockingFailureException");
			
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(new AviokompanijaDTO(ret),HttpStatus.OK);
	}
}