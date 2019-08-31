package com.example.isa_projekat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Prijatelji;
import com.example.isa_projekat.repository.PrijateljiRepository;

@Service
public class PrijateljiService {
	
	@Autowired
	private PrijateljiRepository prijateljiRepository;
	
	public PrijateljiService() {}
	
	public Optional<Prijatelji> findOne(Long id) {
		return prijateljiRepository.findById(id);
	}
	
	public List<Prijatelji> findAll() {
		return prijateljiRepository.findAll();
	}
	
	public Prijatelji save(Prijatelji avio) {
		return prijateljiRepository.save(avio);
	}
	
	public void remove(Long id) {
		prijateljiRepository.deleteById(id);
	}
	
	public List<Prijatelji> findZahtevi(boolean p, Korisnik od) {
		return prijateljiRepository.findByPotvrdioAndPrijateljOd(p, od);
	}
	
	public List<Prijatelji> pronadjiPrijatelje(Korisnik korisnik, boolean potvrdio) {
		return prijateljiRepository.pronadjiPrijatelje(korisnik, potvrdio);
	}

	public List<Prijatelji> findPoslatiZahtevi(boolean b, Korisnik korisnik) {
		return prijateljiRepository.findByPotvrdioAndJe(b, korisnik);
	}
}
