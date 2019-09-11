package com.example.isa_projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.OcenaAvio;

public interface OcenaARepository extends JpaRepository<OcenaAvio, Long>{
	
	OcenaAvio findByAvioAndOdKorisnika(Aviokompanija avio, Korisnik korisnik);
	
	List<OcenaAvio> findByAvio(Aviokompanija avio);
	
	List<OcenaAvio> findByOdKorisnika(Korisnik korisnik);
}
