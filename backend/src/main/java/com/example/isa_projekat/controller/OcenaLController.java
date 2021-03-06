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

import com.example.isa_projekat.DTO.OcenaDTO;
import com.example.isa_projekat.DTO.OcenaProsekDTO;
import com.example.isa_projekat.service.OcenaLService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/ocenal")
public class OcenaLController {
	
	@Autowired
	private OcenaLService ocenaLService;
	
	@RequestMapping(value = "/allfrom/{id}",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OcenaDTO>> getRatingFromKorisnik(@PathVariable Long id) {
		
		System.out.println("getRatingsFrom("+id+")");
		
		List<OcenaDTO> ret;
		
		return (ret = ocenaLService.getFromKorisnik(id)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				
				new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/oceni",
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OcenaDTO> createOcena(@RequestBody OcenaDTO dto) {
		
		System.out.println("Rate let:"+dto.getIdAorL()+" , korisnik:"+dto.getIdKorisnika()+" ("+ dto.getVrednost()+")");
		
		if(ocenaLService.cantVote(dto)) {
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		OcenaDTO ret;
		
		return (ret = ocenaLService.oceni(dto)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				
				new ResponseEntity<>(ret,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/prosek/{id}",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OcenaDTO>> prosek(@PathVariable Long id) {
		
		System.out.println("prosekOcenaForAvio("+id+")");
		
		List<OcenaDTO> ret;
		
		return (ret = ocenaLService.prosek(id)) == null ?
			
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
		
				new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/allprosek",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OcenaProsekDTO>> getAllProsek() {
	
		System.out.println("prosekAll()");
		
		List<OcenaProsekDTO> ret;
		
		return (ret = ocenaLService.allProsek()) == null ?
		
			new ResponseEntity<>(HttpStatus.NOT_FOUND)
		:
	
			new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
