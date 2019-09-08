package com.example.isa_projekat.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.DTO.KorisnikDTO;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.repository.KorisnikRepository;



@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public KorisnikService() {}
	
	public List<KorisnikDTO> findAll() {
		
		return korisnikRepository.findAll().stream().map(x-> new KorisnikDTO(x)).collect(Collectors.toList());
	}
	
	public Optional<Korisnik> findSingle(Long id) {
		return korisnikRepository.findById(id);
	}
	
	public KorisnikDTO findOne(Long id) {
		
		Optional<Korisnik> k = korisnikRepository.findById(id);
	
		if(!k.isPresent()) {
			
			return null;
	}
		
		return new KorisnikDTO(k.get());
	}

	public Optional<Korisnik> login(String email) {
		return korisnikRepository.findByEmail(email);
	}
	
	public List<KorisnikDTO> pretraga(KorisnikDTO k) {
		
		return korisnikRepository.findByImeIgnoreCaseContainingAndPrezimeIgnoreCaseContaining(k.getIme(), k.getPrezime())
				.stream().map(x-> new KorisnikDTO(x)).collect(Collectors.toList());
	}
	
	
	
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

	public KorisnikDTO update(KorisnikDTO novi) {
		
		Optional<Korisnik> k = korisnikRepository.findById(novi.getId());
		
		if(!k.isPresent()) {
			
			return null;
		}
		
		Korisnik ret = k.get();
		
		if(!ret.getEmail().equals(novi.getEmail())) {
			
			Optional<Korisnik> kor = korisnikRepository.findByEmail(novi.getEmail());
			
			if(kor.isPresent()) {
			
				return null;
			}
		}
		
		ret.setEmail(novi.getEmail());
		ret.setPass(novi.getPass());
		ret.setIme(novi.getIme());
		ret.setPrezime(novi.getPrezime());
		ret.setGrad(novi.getGrad());
		ret.setTelefon(novi.getTelefon());
		
		return new KorisnikDTO(korisnikRepository.save(ret));
	}
}
