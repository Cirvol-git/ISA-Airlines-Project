package com.example.isa_projekat.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.example.isa_projekat.DTO.RezervacijaDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.Rezervacija;
import com.example.isa_projekat.service.AeroService;
import com.example.isa_projekat.service.AvioService;
import com.example.isa_projekat.service.LetService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "api/let")
public class LetController {
	
	@Autowired
	private LetService letService;
	
	@Autowired
	private AeroService aeroService;
	
	@Autowired
	private AvioService avioService;
	
	@RequestMapping(
			value = "/all",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LetDTO>> findAll() {
		List<Let> letovi = letService.findAll();
		List<LetDTO> ret = new ArrayList<LetDTO>();
		LetDTO za;
		for (Let l : letovi) {
			za = new LetDTO(l);
			//for (Aerodrom a : l.getDestinacije()) {
				//za.getDestinacijeTrue().add(new AerodromDTO(a));
			//}
			ret.add(za);
		}
		return new ResponseEntity<>(ret,HttpStatus.OK);
	}
	@RequestMapping(
			value = "/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LetDTO> findOne(@PathVariable Long id) {
		
		System.out.println("getLet("+ id+")");
		
		LetDTO ret;
		
		return (ret = letService.findOne(id)) == null ?
		
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				new ResponseEntity<>(ret,HttpStatus.OK);
	
	}
	
	@RequestMapping(
			value = "/lets/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LetDTO>> findLetove(@PathVariable Long id) {
		
		System.out.println("findLetoveOdAvio("+id+")");
		
		List<LetDTO> ret;
		
		return (ret = letService.findByAvio(id)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				
				new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "/pretraga",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LetDTO>> pretrazi(@RequestBody LetDTO pretraga) {
		
		System.out.println("pretraga()");
		
		List<LetDTO> ret;
		
		return (ret = letService.pretraga(pretraga)) == null ?
				
				new ResponseEntity<>(HttpStatus.NOT_FOUND)
			:
				
				new ResponseEntity<>(ret, HttpStatus.OK);
		
	}
	
	@RequestMapping(
			value = "/update",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Let> update(@RequestBody Let novi) {
		LetDTO k = letService.findOne(novi.getId());
	
		if(k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Let ret = letService.save(novi);
		return new ResponseEntity<>(ret,HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/create",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LetDTO> create(@RequestBody LetDTO novi) {
		
		Optional<Aerodrom> od = aeroService.findSingle(novi.getOd());
		Optional<Aerodrom> doo = aeroService.findSingle(novi.getDoo());
		Optional<Aviokompanija> a = avioService.findOne(novi.getIdAvio());
		
		if(od == null || doo == null || !a.isPresent()) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Let ret = new Let(); 


		//NEEEDTOFIXXITT!!
		
		ret.setOdDest(od.get());
		ret.setDoDest(doo.get());
		///DODATI PRESEDANJA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		ret.setBrojPresedanja(novi.getBrojPresedanja());
		ret.setTipLeta(novi.getTipLeta());
		ret.setPoleceDate(novi.getPolece());
		ret.setPoleceTime(novi.getPolece());
		ret.setSleceDate(novi.getSlece());
		ret.setSleceTime(novi.getSlece());
		ret.setVreme(novi.getVreme());
		ret.setDuzina(novi.getDuzina());
		ret.setcEconomy(novi.getcEconomy());
		ret.setcBusiness(novi.getcBusiness());
		ret.setcFirst(novi.getcFirst());
		ret.setBrojRedova(novi.getRedova());
		ret.setAvio(a.get());
		ret.setPrtljag(novi.getPrtljag());
		
		/*
		 * Optional<Aerodrom> a;
		for (Long id : novi.getDestinacije()) {
			a = aeroService.findOne(id);
			if(!a.isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
//			ret.getDestinacije().add(a.get());
		}
		*/
		
		int f=0,b=0,e=0;
		
		Set<Rezervacija> rezList = new HashSet<>();
		
		Rezervacija s;
		
		for (RezervacijaDTO sed : novi.getRows()) {
			if(sed.getTip() == 1) {
				f++;
			}else if(sed.getTip() == 2) {
				b++;
			}else if(sed.getTip() == 3) {
				e++;
			}
			s = new Rezervacija();
			s.setRed(sed.getRed());
			s.setKolona(sed.getKolona());
			s.setPotvrdjeno(false);
			s.setRezervisano(null);
			s.setIme(null);
			s.setPrezime(null);
			s.setPasos(null);
			s.setTip(sed.getTip());
			s.setOdobreno(sed.isOdobreno());
			s.setPripadaKorisniku(null);
			s.setInvOd(null);
			//s.setPripadaLetu(ret);
			//s = sedisteService.save(s);
			rezList.add(s);
		}
		ret.setFreeF(f);
		ret.setFreeB(b);
		ret.setFreeE(e);
		
		for (Rezervacija rezervacija : rezList) {
			
			rezervacija.setPripadaLetu(ret);
		}
		
		ret.setRezervacije(rezList);
		
		ret = letService.save(ret);
		
		return new ResponseEntity<>(new LetDTO(ret),HttpStatus.OK);
		
	}
}