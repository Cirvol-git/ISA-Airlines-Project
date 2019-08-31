package com.example.isa_projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Prijatelji;

public interface PrijateljiRepository extends JpaRepository<Prijatelji, Long> {
	//List<Prijatelji> removeByJeAndPrijateljOd(Korisnik je, Korisnik prijateljOd);
	//List<Prijatelji> findByJeOrPrijateljAndPotvrdio(Korisnik je, Korisnik prijateljOd, boolean potvrdio);
	
	List<Prijatelji> findByPotvrdioAndPrijateljOd(boolean potvrdio, Korisnik prijateljOd);
	
	List<Prijatelji> findByPotvrdioAndJe(boolean potvrdio, Korisnik je);
	
	@Query("select s from Prijatelji s where (s.je = ?1 and s.potvrdio = ?2) or (s.prijateljOd = ?1 and s.potvrdio = ?2)")
	List<Prijatelji> pronadjiPrijatelje(Korisnik korisnik, boolean potvrdio);
}
