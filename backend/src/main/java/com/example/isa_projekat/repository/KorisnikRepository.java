package com.example.isa_projekat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa_projekat.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	Optional<Korisnik> findByEmail(String email);
	
	List<Korisnik> findByImeIgnoreCaseContainingAndPrezimeIgnoreCaseContaining(String ime, String prezime);
}