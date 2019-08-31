package com.example.isa_projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa_projekat.model.Aerodrom;

public interface AeroRepository extends JpaRepository<Aerodrom, Long>{
	//List<Aerodrom> findByPripada(Aviokompanija pripada);
}
