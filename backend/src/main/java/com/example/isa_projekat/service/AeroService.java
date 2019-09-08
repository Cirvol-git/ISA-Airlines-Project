package com.example.isa_projekat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	private AvioRepository avioRepository;
	
	public AeroService() {}
	
	public AerodromDTO findOne(Long id) {
		
		Optional<Aerodrom> ret = aeroRepository.findById(id);
		
		return ret.isPresent() ? new AerodromDTO(ret.get()) : null;
		
	}
	
	public Optional<Aerodrom> findSingle(Long id) {
		
		return aeroRepository.findById(id);
	}
	
	public List<AerodromDTO> findAll() {
		
		return aeroRepository.findAll().stream().map(x -> new AerodromDTO(x)).collect(Collectors.toList());
	}
	/*
	public List<Aerodrom> findByAvio(Aviokompanija a) {
		return aeroRepository.findByPripada(a);
	}
	*/
	public AerodromDTO update(AerodromDTO update) {
		
		Optional<Aerodrom> a = aeroRepository.findById(update.getId());
		
		if(!a.isPresent()) {
			
			return null;
		}
		
		Aerodrom ret = a.get();
		ret.setIme(update.getIme());
		ret.setAdresa(update.getAdresa());
		ret.setGrad(update.getGrad());
		
		return new AerodromDTO(aeroRepository.save(ret));
	}
	
	public Aerodrom save(Aerodrom avio) {
		return aeroRepository.save(avio);
	}

	public List<AerodromDTO> allExceptFor(Long id) {
		Aviokompanija avio = avioRepository.findById(id).orElse(null);
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
		Aviokompanija a =avioRepository.findById(dep.getIdAvio()).orElse(null);
		Aerodrom b =aeroRepository.findById(dep.getIdAero()).orElse(null);
		if(a == null || b == null) {
			return null;
		}
		a.getDestinacije().add(b);
		b.getKompanije().add(a);
		return aeroRepository.save(b);
	}

	public Aerodrom create(AerodromDTO novi) {

		Optional<Aviokompanija>	avio = avioRepository.findById(novi.getIdAvio());
		
		if(!avio.isPresent()) {
			
			return null;
		}
		
		Aerodrom aero = new Aerodrom();
		
		aero.setIme(novi.getIme());
		aero.setAdresa(novi.getAdresa());
		aero.setGrad(novi.getGrad());
		
		aero.getKompanije().add(avio.get());
		avio.get().getDestinacije().add(aero);
		
		return aeroRepository.save(aero);
		
	}
}
