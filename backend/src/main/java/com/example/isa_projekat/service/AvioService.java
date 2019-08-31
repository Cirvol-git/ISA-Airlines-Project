package com.example.isa_projekat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.repository.AvioRepository;

@Service
public class AvioService {
	
	@Autowired
	private AvioRepository avioRepository;
	
	public AvioService () {}
	
	public Optional<Aviokompanija> findOne(Long id) {
		return avioRepository.findById(id);
	}
	
	public List<Aviokompanija> findAll() {
		return avioRepository.findAll();
	}
	
	public Aviokompanija save(Aviokompanija avio) throws OptimisticLockingFailureException {
		return avioRepository.save(avio);
	}
}
