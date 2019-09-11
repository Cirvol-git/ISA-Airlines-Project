package com.example.isa_projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {
	List<Rezervacija> findByPripadaKorisniku(Korisnik pripadaKorisniku);
	List<Rezervacija> findByPripadaKorisnikuAndPotvrdjeno(Korisnik pripadaKorisniku, boolean potvrdjeno);
	List<Rezervacija> findByPripadaKorisnikuAndPripadaLetu(Korisnik korisnik, Let let);
}
