package com.example.isa_projekat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.DTO.AddAeroDepDTO;
import com.example.isa_projekat.DTO.AerodromDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.repository.AeroRepository;
import com.example.isa_projekat.repository.AvioRepository;

@Service
public class AeroService {
	
	@Autowired
	private AeroRepository aeroRepository;
	
	@Autowired
	private AvioRepository avioReposiroty;
	
	public AeroService() {}
	
	public Optional<Aerodrom> findOne(Long id) {
		return aeroRepository.findById(id);
	}
	
	public List<Aerodrom> findAll() {
		return aeroRepository.findAll();
	}
	/*
	public List<Aerodrom> findByAvio(Aviokompanija a) {
		return aeroRepository.findByPripada(a);
	}
	*/
	public Aerodrom save(Aerodrom avio) {
		return aeroRepository.save(avio);
	}

	public List<AerodromDTO> allExceptFor(Long id) {
		Aviokompanija avio = avioReposiroty.findById(id).orElse(null);
		if(avio == null) {
			return null;
		}
		
		List<Aerodrom> aeros = aeroRepository.findAll();
		List<AerodromDTO> ret = new ArrayList<AerodromDTO>();
		
		avio.getDestinacije().forEach(x -> aeros.removeIf(y -> x.getId() == y.getId()));
		System.out.println(aeros);
		aeros.forEach(x -> ret.add(new AerodromDTO(x)));
		System.out.println(ret);

		return ret;
	}

	public Aerodrom addDependecy(AddAeroDepDTO dep) {
		Aviokompanija a =avioReposiroty.findById(dep.getIdAvio()).orElse(null);
		Aerodrom b =aeroRepository.findById(dep.getIdAero()).orElse(null);
		if(a == null || b == null) {
			return null;
		}
		a.getDestinacije().add(b);
		b.getKompanije().add(a);
		return aeroRepository.save(b);
	}
}
