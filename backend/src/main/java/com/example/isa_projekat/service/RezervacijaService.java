package com.example.isa_projekat.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.example.isa_projekat.DTO.LetDTO;
import com.example.isa_projekat.DTO.RezervacijaDTO;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.Rezervacija;
import com.example.isa_projekat.repository.KorisnikRepository;
import com.example.isa_projekat.repository.LetRepository;
import com.example.isa_projekat.repository.RezervacijaRepository;

@Service
public class RezervacijaService {
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private LetRepository letRepository;
	
	public RezervacijaService() {}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public Optional<Rezervacija> findOne(Long id) {
		
		return rezervacijaRepository.findById(id);
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<Rezervacija> findAll() {
		
		return rezervacijaRepository.findAll();
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED,
			rollbackFor = {IllegalArgumentException.class, NullPointerException.class, ObjectOptimisticLockingFailureException.class})
	public Rezervacija createBrzaRez(RezervacijaDTO rezDTO) throws ObjectOptimisticLockingFailureException {
		
		Optional<Rezervacija> r = rezervacijaRepository.findById(rezDTO.getId());
		Optional<Korisnik> k = korisnikRepository.findById(rezDTO.getId_korisnik());
		
		if(!r.isPresent() || !k.isPresent()) {
			
			return null;
		}
		
		Rezervacija rez = r.get();
		
		rez.setVersion(rezDTO.getVersion());
		rez.setRezervisano(rezDTO.getRezervisano());
		rez.setPripadaKorisniku(k.get());
		rez.setPotvrdjeno(true);
		rez.setIme(k.get().getIme());
		rez.setPrezime(k.get().getPrezime());
		rez.setPasos(rezDTO.getPasos());
		
		return rezervacijaRepository.save(rez);
	}
	
	public void remove(Long id) {
		
		rezervacijaRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RezervacijaDTO> findByKorisnik(Long id) {
		
		Optional<Korisnik> k = korisnikRepository.findById(id);
		
		if(!k.isPresent()) {
		
			return null;
		}
		
		return rezervacijaRepository.findByPripadaKorisniku(k.get())
				.stream().map(x-> new RezervacijaDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true, isolation=Isolation.READ_COMMITTED)
	public List<RezervacijaDTO> findByKorisnikAndPotvrdjeno(Long id, boolean p) {
		
		Optional<Korisnik> k = korisnikRepository.findById(id);
		
		if(!k.isPresent()) {
		
			return null;
		}
		
		return rezervacijaRepository.findByPripadaKorisnikuAndPotvrdjeno(k.get(), p)
				.stream().map(x-> {RezervacijaDTO r = new RezervacijaDTO(x); r.setLet(new LetDTO(x.getPripadaLetu())); return r;}).collect(Collectors.toList());
	}
	/*
	public void saveAll(List<Rezervacija> rezList) {
		
		rezervacijaRepository.saveAll(rezList);
	}
	*/
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED,
			rollbackFor = {IllegalArgumentException.class, NullPointerException.class, ObjectOptimisticLockingFailureException.class})
	public List<RezervacijaDTO> createRezervacije(List<RezervacijaDTO> novi) throws IllegalArgumentException, NullPointerException {
		
		Optional<Korisnik> invOd = korisnikRepository.findById(novi.get(0).getInv());		
		Optional<Rezervacija> rToFindLet  = rezervacijaRepository.findById(novi.get(0).getId());
		
		if(!invOd.isPresent() || !rToFindLet.isPresent()) { throw new NullPointerException();}
		
		Let let = letRepository.findById(rToFindLet.get().getPripadaLetu().getId()).orElse(null);
		
		if(let == null) { throw new NullPointerException();}
		
		List<Rezervacija> rezList = new ArrayList<>();
		
		int f=0,b=0,e=0;
		
		Korisnik k;
		
		for (RezervacijaDTO rDTO : novi) {
			
			if(rDTO.getId_korisnik() != null) {
				
				k = korisnikRepository.findById(rDTO.getId_korisnik()).orElse(null);
				
				if(k == null) { throw new NullPointerException();}
			
			}else {
			
				k = null;
			
			}
			
			Rezervacija ret = rezervacijaRepository.findById(rDTO.getId()).orElse(null);
			
			if(ret == null) { throw new NullPointerException();}
			
			ret.setVersion(rDTO.getVersion());
			ret.setIme(rDTO.getIme());
			ret.setPrezime(rDTO.getPrezime());
			ret.setPasos(rDTO.getPasos());
			ret.setRezervisano(rDTO.getRezervisano());
			ret.setPripadaKorisniku(k);
			
			if(ret.getTip() == 1) {
			
				f++;
			
			}else if(ret.getTip() == 2) {
			
				b++;
			
			}else {
			
				e++;
			
			}
			
			//ret = sedisteRepository.save(s);
			if(k != null) {
				
				if(rDTO.getInv() == k.getId()) {
				
					ret.setPotvrdjeno(true);
					ret.setInvOd(null);
				
				}else {
				
					ret.setPotvrdjeno(false);
					ret.setInvOd(invOd.get());
				
				}
			
			}else {
				
				ret.setPotvrdjeno(true);
				ret.setInvOd(invOd.get());
			}
			
			rezList.add(ret);

		}
		
		let.setFreeF(let.getFreeF()-f);
		let.setFreeB(let.getFreeB()-b);
		let.setFreeE(let.getFreeE()-e);
		
		let.setRezervacije(new HashSet<Rezervacija>(rezList));
		
		rezList.forEach(x->x.setPripadaLetu(let));
		
		rezList = rezervacijaRepository.saveAll(rezList);
		
		//letRepository.save(let);
		
		return rezList.stream().map(x-> new RezervacijaDTO(x)).collect(Collectors.toList());
	}

	public List<RezervacijaDTO> findAllRezervacije(Long id) {
		
		Optional<Let> l = letRepository.findById(id);
		
		if(!l.isPresent()) {
		
			return null;
		}
		
		Set<Rezervacija> rez = l.get().getRezervacije();
		
		//List<RezervacijaDTO> ret = new ArrayList<RezervacijaDTO>();
		
		return rez.stream().map(x-> new RezervacijaDTO(x)).collect(Collectors.toList());
	}

	public RezervacijaDTO decline(RezervacijaDTO rezDTO) {

		Optional<Rezervacija> r = rezervacijaRepository.findById(rezDTO.getId());
		
		if(!r.isPresent()) {
		
			return null;
		}
		
		Rezervacija ret = r.get();
		
		ret.setIme(null);
		ret.setPrezime(null);
		ret.setPasos(null);
		ret.setInvOd(null);
		ret.setPripadaKorisniku(null);
		ret.setPotvrdjeno(false);
		ret.setRezervisano(null);

		return new RezervacijaDTO(rezervacijaRepository.save(ret));
		
	}

	public RezervacijaDTO accept(RezervacijaDTO rezDTO) {
		
		Optional<Rezervacija> r = rezervacijaRepository.findById(rezDTO.getId());
		
		if(!r.isPresent()) {
		
			return null;
		}
		
		Rezervacija rez = r.get();
		
		rez.setPotvrdjeno(true);
		
		RezervacijaDTO ret = new RezervacijaDTO(rezervacijaRepository.save(rez));
		
		ret.setLet(new LetDTO(rez.getPripadaLetu()));
		
		return ret;
	}
}
