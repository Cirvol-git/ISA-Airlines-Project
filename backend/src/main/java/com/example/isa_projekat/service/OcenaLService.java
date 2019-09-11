package com.example.isa_projekat.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.DTO.OcenaDTO;
import com.example.isa_projekat.DTO.OcenaProsekDTO;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.OcenaLeta;
import com.example.isa_projekat.repository.KorisnikRepository;
import com.example.isa_projekat.repository.LetRepository;
import com.example.isa_projekat.repository.OcenaLRepository;
import com.example.isa_projekat.repository.RezervacijaRepository;

@Service
public class OcenaLService {
	
	@Autowired
	private OcenaLRepository ocenaLRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private LetRepository letRepository;
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	public OcenaLService() {}
	
	public List<OcenaDTO> getFromKorisnik(Long id) {
			
		Optional<Korisnik> k = korisnikRepository.findById(id);
		
		if(!k.isPresent()) {
			
			return null;
		}
		
		
		return ocenaLRepository.findByOdKorisnika(k.get()).stream().map(x-> new OcenaDTO(x)).collect(Collectors.toList());
	}

	public OcenaDTO oceni(OcenaDTO dto) {


		if (dto.getIdAorL() == null || dto.getIdKorisnika() == null) {
			
			return null;
		}
		
		Optional<Korisnik> korisnik = korisnikRepository.findById(dto.getIdKorisnika());
		Optional<Let> let = letRepository.findById(dto.getIdAorL());
		
		if (!korisnik.isPresent() || !let.isPresent()) {
			
			return null;
		}
		
		OcenaLeta postoji = ocenaLRepository.findByLetAndOdKorisnika(let.get(), korisnik.get());
		
		OcenaLeta ocena = new OcenaLeta();
	
		ocena.setLet(let.get());
		ocena.setOdKorisnika(korisnik.get());
		ocena.setVrednost(dto.getVrednost());
		
		if(postoji != null) {
			
			ocena.setId(postoji.getId());
		}
		
		return new OcenaDTO(ocenaLRepository.save(ocena));
	}

	public boolean cantVote(OcenaDTO dto) {

		
		if (dto.getIdAorL() == null || dto.getIdKorisnika() == null) {
			
			return true;
		}
		
		Optional<Korisnik> korisnik = korisnikRepository.findById(dto.getIdKorisnika());
		Optional<Let> let = letRepository.findById(dto.getIdAorL());
		
		if (!korisnik.isPresent() || !let.isPresent()) {
			
			return true;
		}
		
		
		//letovi za koje ima rezervaciju
		List<Let> letovi =rezervacijaRepository.findByPripadaKorisnikuAndPripadaLetu(korisnik.get(), let.get())
				.stream().map(x-> x.getPripadaLetu()).collect(Collectors.toList());
		
		
		
		for (Let x : letovi) {
			if(new Date(x.getSleceDate().getTime() +x.getSleceTime().getTime()).compareTo(new Date()) < 0) {
				
				return true;
			}
		}		
		
		
		return false;
		
	}

	public List<OcenaDTO> prosek(Long id) {

		Optional<Let> let = letRepository.findById(id);
		
		if(!let.isPresent()) {
		
			return null;
		}
		
		return ocenaLRepository.findByLet(let.get()).stream().map(x -> new OcenaDTO(x)).collect(Collectors.toList());
	}
	
	public List<OcenaProsekDTO> allProsek() {
		
		List<Let> avios = letRepository.findAll();
		
		return avios.stream().map(x-> {
			
			List<OcenaLeta> ocene = ocenaLRepository.findByLet(x);
			OcenaProsekDTO ret = new OcenaProsekDTO();
			
			ret.setIdAorL(x.getId());
			ret.setCount(ocene.size());
			ret.setSum((ocene.stream().mapToInt(y-> y.getVrednost())).sum());
		
			return ret;
			
		}).collect(Collectors.toList());
		
	}
}
