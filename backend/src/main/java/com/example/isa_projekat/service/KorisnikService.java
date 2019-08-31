package com.example.isa_projekat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.repository.KorisnikRepository;



@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public KorisnikService() {}
	
	public Optional<Korisnik> findOne(Long id) {
		return korisnikRepository.findById(id);
	}

	public Optional<Korisnik> login(String email) {
		return korisnikRepository.findByEmail(email);
	}
	
	public List<Korisnik> pretraga(String ime, String prezime) {
		return korisnikRepository.findByImeIgnoreCaseContainingAndPrezimeIgnoreCaseContaining(ime, prezime);
	}
	
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}
	
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}
}
