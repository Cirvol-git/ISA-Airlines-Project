package com.example.isa_projekat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.DTO.OcenaDTO;
import com.example.isa_projekat.DTO.OcenaProsekDTO;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.OcenaAvio;
import com.example.isa_projekat.model.Rezervacija;
import com.example.isa_projekat.repository.AvioRepository;
import com.example.isa_projekat.repository.KorisnikRepository;
import com.example.isa_projekat.repository.OcenaARepository;
import com.example.isa_projekat.repository.RezervacijaRepository;

@Service
public class OcenaAService {
	
	@Autowired
	private OcenaARepository ocenaARepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private AvioRepository avioRepository;
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public OcenaAService() {}
	
	public List<OcenaDTO> getFromKorisnik(Long id) {
			
		Optional<Korisnik> k = korisnikRepository.findById(id);
		
		if(!k.isPresent()) {
			
			return null;
		}
		
		
		return ocenaARepository.findByOdKorisnika(k.get()).stream().map(x-> new OcenaDTO(x)).collect(Collectors.toList());
	}

	public OcenaDTO oceni(OcenaDTO dto) {


		if (dto.getIdAorL() == null || dto.getIdKorisnika() == null) {
			
			return null;
		}
		
		Optional<Korisnik> korisnik = korisnikRepository.findById(dto.getIdKorisnika());
		Optional<Aviokompanija> avio = avioRepository.findById(dto.getIdAorL());
		
		if (!korisnik.isPresent() || !avio.isPresent()) {
			
			return null;
		}
		
		OcenaAvio postoji = ocenaARepository.findByAvioAndOdKorisnika(avio.get(), korisnik.get());
		
		OcenaAvio ocena = new OcenaAvio();
	
		ocena.setAvio(avio.get());
		ocena.setOdKorisnika(korisnik.get());
		ocena.setVrednost(dto.getVrednost());
		
		if(postoji != null) {
			
			ocena.setId(postoji.getId());
		}
		
		return new OcenaDTO(ocenaARepository.save(ocena));
	}

	public boolean cantVote(OcenaDTO dto) {

		
		if (dto.getIdAorL() == null || dto.getIdKorisnika() == null) {
			
			return true;
		}
		
		Optional<Korisnik> korisnik = korisnikRepository.findById(dto.getIdKorisnika());
		Optional<Aviokompanija> avio = avioRepository.findById(dto.getIdAorL());
		
		if (!korisnik.isPresent() || !avio.isPresent()) {
			
			return true;
		}
		
		List<Rezervacija> rezervacije =rezervacijaRepository.findByPripadaKorisniku(korisnik.get());
		
		List<Let> letovi = rezervacije.stream().filter(x -> x.getPripadaLetu().getAvio().getId() == avio.get().getId()).map(x -> x.getPripadaLetu()).collect(Collectors.toList());
		
		
		for (Let x : letovi) {
			if(new Date(x.getSleceDate().getTime() +x.getSleceTime().getTime()).compareTo(new Date()) < 0) {
				
				return false;
			}
		}		
		
		
		return true;
		
	}

	public List<OcenaDTO> prosek(Long id) {

		Optional<Aviokompanija> avio = avioRepository.findById(id);
		
		if(!avio.isPresent()) {
		
			return null;
		}
		
		return ocenaARepository.findByAvio(avio.get()).stream().map(x -> new OcenaDTO(x)).collect(Collectors.toList());
	}

	public List<OcenaProsekDTO> allProsek() {
		
		List<Aviokompanija> avios = avioRepository.findAll();
		
		return avios.stream().map(x-> {
			
			List<OcenaAvio> ocene = ocenaARepository.findByAvio(x);
			OcenaProsekDTO ret = new OcenaProsekDTO();
			
			ret.setIdAorL(x.getId());
			ret.setCount(ocene.size());
			ret.setSum((ocene.stream().mapToInt(y-> y.getVrednost())).sum());
		
			return ret;
			
		}).collect(Collectors.toList());
		
	}

}
