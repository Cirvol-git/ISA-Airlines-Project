package com.example.isa_projekat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.OcenaAvio;
import com.example.isa_projekat.repository.OcenaARepository;

@Service
public class OcenaAService {
	
	@Autowired
	private OcenaARepository ocenaARepository;
	
	public OcenaAService() {}
	
	public Optional<OcenaAvio> findOne(Long id) {
		return ocenaARepository.findById(id);
	}

	public List<OcenaAvio> findAll() {
		return ocenaARepository.findAll();
	}
	
	public OcenaAvio save(OcenaAvio ocena) {
		return ocenaARepository.save(ocena);
	}
	
	public List<OcenaAvio> exists(Aviokompanija avio, Korisnik korisnik) {
		return ocenaARepository.findByOcenaAvioAndOdKorisnika(avio, korisnik);
	}
	
	public List<OcenaAvio> findByAvio(Aviokompanija avio) {
		return ocenaARepository.findByOcenaAvio(avio);
	}
}
