package com.example.isa_projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.OcenaLeta;

public interface OcenaLRepository extends JpaRepository<OcenaLeta, Long> {
	
	List<OcenaLeta> findByLet(Let let);
	
	List<OcenaLeta> findByOdKorisnika(Korisnik korisnik);
	
	OcenaLeta findByLetAndOdKorisnika(Let let, Korisnik korisnik);
}
