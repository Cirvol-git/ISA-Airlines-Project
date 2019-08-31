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

import com.example.isa_projekat.DTO.OcenaDTO;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.OcenaAvio;
import com.example.isa_projekat.service.AvioService;
import com.example.isa_projekat.service.KorisnikService;
import com.example.isa_projekat.service.OcenaAService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/ocenaa")
public class OcenaAController {

	@Autowired
	private OcenaAService ocenaAService;
	
	@Autowired
	private AvioService avioService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping(value = "/oceni",
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OcenaDTO> createExam(@RequestBody OcenaDTO ocenaDTO) {
		
		if (ocenaDTO.getIdAorL() == null || ocenaDTO.getIdKorisnika() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Korisnik korisnik = korisnikService.findOne(ocenaDTO.getIdKorisnika()).get();
		Aviokompanija avio = avioService.findOne(ocenaDTO.getIdAorL()).get();
		
		if (korisnik == null || avio == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		OcenaAvio ocena = new OcenaAvio();
		ocena.setOcenaAvio(avio);
		ocena.setOdKorisnika(korisnik);
		ocena.setVrednost(ocenaDTO.getVrednost());
		List<OcenaAvio> postoji = ocenaAService.exists(avio, korisnik);
		if(!postoji.isEmpty()) {
			ocena.setId(postoji.get(0).getId());
			ocenaAService.save(ocena);
			return new ResponseEntity<>(ocenaDTO, HttpStatus.OK);
		}

		ocenaAService.save(ocena);
		return new ResponseEntity<>(ocenaDTO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}",
					method = RequestMethod.GET)
	public ResponseEntity<Integer> prosek(@PathVariable Long id) {
		Optional<Aviokompanija> avio = avioService.findOne(id);
		
		if(!avio.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<OcenaAvio> lista = ocenaAService.findByAvio(avio.get());
		
		if(lista.isEmpty()) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
		int ret = 0;
		for (OcenaAvio o : lista) {
			ret += o.getVrednost();
		}
		ret = ret/ lista.size();
		return new ResponseEntity<Integer>(ret, HttpStatus.OK);
	}
}
