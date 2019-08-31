package com.example.isa_projekat.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Let;

public interface LetRepository extends JpaRepository<Let, Long> {
	List<Let> findByAvio(Aviokompanija avio);
	
	List<Let> findAllByPoleceDateAndSleceDateAndOdDestAndDoDest(Date poleceDate, Date sleceDate, Aerodrom odDest, Aerodrom doDest);
	/*
	@Query("select s from Let s where (s.odDest = ?1 and s.doDest = ?2 and s.polece = ?3 and s.slece  = ?4)")
	List<Let> pretraga(Aerodrom od, Aerodrom doo, Date polece, Date slece);
	
	@Query("select s from Let s where (s.odDest = ?1 and s.doDest = ?2 and s.polece = ?3 and s.slece = ?4 and s.tipLeta = ?5 and s.freeF >= ?6 and s.prtljag >= ?7)")
	List<Let> pretragaLetaF(Aerodrom od, Aerodrom doo, Date polece, Date slece, int tip, int broj, int prtljag);
	
	@Query("select s from Let s where (s.odDest = ?1 and s.doDest = ?2 and s.polece = ?3 and s.slece = ?4 and s.tipLeta = ?5 and s.freeB >= ?6 and s.prtljag >= ?7)")
	List<Let> pretragaLetaB(Aerodrom od, Aerodrom doo, Date polece, Date slece, int tip, int broj, int prtljag);
	
	@Query("select s from Let s where (s.odDest = ?1 and s.doDest = ?2 and s.polece = ?3 and s.slece = ?4 and s.tipLeta = ?5 and s.freeE >= ?6 and s.prtljag >= ?7)")
	List<Let> pretragaLetaE(Aerodrom od, Aerodrom doo, Date polece, Date slece, int tip, int broj, int prtljag);
	*/
}
