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

import com.example.isa_projekat.DTO.KorisnikDTO;
import com.example.isa_projekat.DTO.PrijateljiDTO;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Prijatelji;
import com.example.isa_projekat.service.KorisnikService;
import com.example.isa_projekat.service.PrijateljiService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/prijatelji")
public class PrijateljiController {
	
	@Autowired
	private PrijateljiService prijateljiService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(
			value = "/allp{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PrijateljiDTO>> findAllF(@PathVariable Long id) {
		Optional<Korisnik> k = korisnikService.findSingle(id);
		
		if(!k.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Prijatelji> prijatelji = prijateljiService.pronadjiPrijatelje(k.get(), true);
		List<PrijateljiDTO> actualFriends = new ArrayList<PrijateljiDTO>();
		for (Prijatelji p : prijatelji) {
			if(p.getJe().getId() == id) {
				actualFriends.add(new PrijateljiDTO(p.getId(), new KorisnikDTO(p.getPrijateljOd())));
			}else {
				actualFriends.add(new PrijateljiDTO(p.getId(), new KorisnikDTO(p.getJe())));
			}
		}
		
		return new ResponseEntity<>(actualFriends,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/allz{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PrijateljiDTO>> findAllPF(@PathVariable Long id) {
		Optional<Korisnik> k = korisnikService.findSingle(id);
		
		if(!k.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Prijatelji> zahtevi = prijateljiService.findZahtevi(false, k.get());
		List<PrijateljiDTO> actualFriends = new ArrayList<PrijateljiDTO>();
		for (Prijatelji p : zahtevi) {
			actualFriends.add(new PrijateljiDTO(p.getId(), new KorisnikDTO(p.getJe())));	
		}
		
		return new ResponseEntity<>(actualFriends,HttpStatus.OK);
	}
	//poslati zahtevi
	@RequestMapping(
			value = "/allpz{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PrijateljiDTO>> findAllPZ(@PathVariable Long id) {
		System.out.println("Svi zahtevi poslati od ("+ id + ")");
		Optional<Korisnik> k = korisnikService.findSingle(id);
		
		if(!k.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Prijatelji> zahtevi = prijateljiService.findPoslatiZahtevi(false, k.get());
		List<PrijateljiDTO> actualFriends = new ArrayList<PrijateljiDTO>();
		for (Prijatelji p : zahtevi) {
			actualFriends.add(new PrijateljiDTO(p.getId(), new KorisnikDTO(p.getPrijateljOd())));	
		}
		
		return new ResponseEntity<>(actualFriends,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/create",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> create(@RequestBody PrijateljiDTO novi) {
		Optional<Korisnik> k1 = korisnikService.findSingle(novi.getJe());
		Optional<Korisnik> k2 = korisnikService.findSingle(novi.getOd());
		if(!k1.isPresent() || !k2.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		prijateljiService.save(new Prijatelji(false, k1.get(), k2.get()));
		return new ResponseEntity<>(new KorisnikDTO(k2.get()),HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/update/{id}",
			method = RequestMethod.PUT)
	public ResponseEntity<PrijateljiDTO> update(@PathVariable Long id) {
		
		System.out.println("Prijatelj update("+id+")");
		
		Optional<Prijatelji> p = prijateljiService.findOne(id);
		if(!p.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Prijatelji ret = p.get();
		ret.setPotvrdio(true);
		prijateljiService.save(ret);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/delete/{id}",
			method = RequestMethod.DELETE)
	public ResponseEntity<PrijateljiDTO> delete(@PathVariable Long id) {
		
		System.out.println("Prijatelj delete("+id+")");
		
		Optional<Prijatelji> p = prijateljiService.findOne(id);
		if(!p.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		prijateljiService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
