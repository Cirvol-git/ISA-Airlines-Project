package com.example.isa_projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.isa_projekat.model.Aviokompanija;

public interface AvioRepository extends JpaRepository<Aviokompanija, Long> {

}
