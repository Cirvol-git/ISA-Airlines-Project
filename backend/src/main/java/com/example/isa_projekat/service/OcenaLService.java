package com.example.isa_projekat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.OcenaLeta;
import com.example.isa_projekat.repository.OcenaLRepository;

@Service
public class OcenaLService {
	
	@Autowired
	private OcenaLRepository ocenaLRepository;
	
	public OcenaLService() {}
	
	public Optional<OcenaLeta> findOne(Long id) {
		return ocenaLRepository.findById(id);
	}

	public List<OcenaLeta> findAll() {
		return ocenaLRepository.findAll();
	}
	
	public OcenaLeta save(OcenaLeta ocena) {
		return ocenaLRepository.save(ocena);
	}
	
	public List<OcenaLeta> exists(Let let, Korisnik korisnik) {
		return ocenaLRepository.findByOcenaLetaAndOdKLet(let, korisnik);
	}
	
	public List<OcenaLeta> findByLet(Let let){
		return ocenaLRepository.findByOcenaLeta(let);
	}
}
