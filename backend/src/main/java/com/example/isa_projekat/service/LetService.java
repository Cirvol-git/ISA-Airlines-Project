package com.example.isa_projekat.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.DTO.LetDTO;
import com.example.isa_projekat.DTO.RezervacijaDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.repository.AeroRepository;
import com.example.isa_projekat.repository.AvioRepository;
import com.example.isa_projekat.repository.LetRepository;

@Service
public class LetService {
	
	@Autowired
	private LetRepository letRepository;
	
	@Autowired
	private AeroRepository aeroRepository;
	
	@Autowired
	private AvioRepository avioRepository;
	
	public LetService() {}
	
	public LetDTO findOne(Long id) {
		
		Optional<Let> let = letRepository.findById(id);
		
		if(!let.isPresent()) {
			
			return null;
		}
		
		List<RezervacijaDTO> rezercacije = let.get().getRezervacije().stream().map(x -> new RezervacijaDTO(x)).collect(Collectors.toList());

		LetDTO ret = new LetDTO(let.get());
		
		ret.setRows(rezercacije);
		
		return ret; 
	}
	
	public List<LetDTO> findByAvio(Long id) {
		
		Optional<Aviokompanija> avio = avioRepository.findById(id);
		
		if(!avio.isPresent()) {
			
			return null;
		}
		
		return avio.get().getLetovi().stream().map(x->{LetDTO l = new LetDTO(x); l.setRows(x.getRezervacije().stream().map(y-> new RezervacijaDTO(y)).collect(Collectors.toList())); return l;} ).collect(Collectors.toList());
		
	}

	public List<Let> findAll() {
		return letRepository.findAll();
	}
	
	public Let save(Let let) {
		return letRepository.save(let);
	}
	
	public List<LetDTO> pretraga(LetDTO pretraga) {
		
		Aerodrom d1 = aeroRepository.findById(pretraga.getOd()).orElse(null);
		Aerodrom d2 = aeroRepository.findById(pretraga.getDoo()).orElse(null);
		
		if(d1 == null || d2 == null) {
		
			return null;
		}
		
		List<Let> ret = letRepository.findAllByPoleceDateAndSleceDateAndOdDestAndDoDest(pretraga.getPolece(), pretraga.getSlece(), d1, d2);
		
		if(pretraga.getTipLeta() != 0) {
			
			ret.removeIf(x -> x.getTipLeta() != pretraga.getTipLeta());
		}
		
		if(pretraga.getKlasa() != 0) {
			
			if(pretraga.getKlasa() == 1) {
			
				ret.removeIf(x -> x.getFreeF() < pretraga.getBrojOsoba());
			
			}else if(pretraga.getKlasa() == 2) {
			
				ret.removeIf(x -> x.getFreeB() < pretraga.getBrojOsoba());
			
			}else {
			
				ret.removeIf(x -> x.getFreeE() < pretraga.getBrojOsoba());
			}
		}
		
		if(pretraga.getPrtljag() != 0) {
			
			ret.removeIf(x -> x.getPrtljag() < pretraga.getPrtljag());
		}
		
		return ret.stream().map(x -> new LetDTO(x)).collect(Collectors.toList());
		
	}

}
