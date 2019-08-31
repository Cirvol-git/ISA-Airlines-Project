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

import com.example.isa_projekat.DTO.LetDTO;
import com.example.isa_projekat.DTO.OcenaDTO;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.OcenaLeta;
import com.example.isa_projekat.service.KorisnikService;
import com.example.isa_projekat.service.LetService;
import com.example.isa_projekat.service.OcenaLService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/ocenal")
public class OcenaLController {
	
	@Autowired
	private OcenaLService ocenaLService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private LetService letService;
	/*
	@RequestMapping(value = "/oceni",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OcenaDTO> createExam(@RequestBody OcenaDTO ocenaDTO) {

		if (ocenaDTO.getIdAorL() == null || ocenaDTO.getIdKorisnika() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Korisnik korisnik = korisnikService.findOne(ocenaDTO.getIdKorisnika()).get();
		LetDTO let = letService.findOne(ocenaDTO.getIdAorL());

		if (korisnik == null || let == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		OcenaLeta ocena = new OcenaLeta();
		//ocena.setOcenaLeta(let);
		ocena.setOdKLet(korisnik);
		ocena.setVrednost(ocenaDTO.getVrednost());
		List<OcenaLeta> postoji = ocenaLService.exists(let, korisnik);
		if(!postoji.isEmpty()) {
		ocena.setId(postoji.get(0).getId());
			ocenaLService.save(ocena);
			return new ResponseEntity<>(ocenaDTO, HttpStatus.OK);
		}	

		ocenaLService.save(ocena);
		return new ResponseEntity<>(ocenaDTO, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}",
					method = RequestMethod.GET)
	public ResponseEntity<Integer> prosek(@PathVariable Long id) {
		Optional<Let> let = letService.findOne(id);
		
		if(!let.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<OcenaLeta> lista = ocenaLService.findByLet(let.get());

		if(lista.isEmpty()) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
		int ret = 0;
		for (OcenaLeta o : lista) {
			ret += o.getVrednost();
		}
		ret = ret/ lista.size();
		return new ResponseEntity<Integer>(ret, HttpStatus.OK);
	}
	*/
}
