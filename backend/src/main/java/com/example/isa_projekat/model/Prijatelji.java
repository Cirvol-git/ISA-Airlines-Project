package com.example.isa_projekat.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Prijatelji {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "potvrdio", nullable = false)
	private boolean potvrdio;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH} )
	private Korisnik je;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH} )
	private Korisnik prijateljOd;
	
	public Prijatelji() {}
	
	public Prijatelji(boolean potvrdio, Korisnik je, Korisnik prijateljOd) {
		super();
		this.potvrdio = potvrdio;
		this.je = je;
		this.prijateljOd = prijateljOd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getJe() {
		return je;
	}

	public void setJe(Korisnik je) {
		this.je = je;
	}

	public Korisnik getPrijateljOd() {
		return prijateljOd;
	}

	public void setPrijateljOd(Korisnik prijateljOd) {
		this.prijateljOd = prijateljOd;
	}

	public boolean isPotvrdio() {
		return potvrdio;
	}

	public void setPotvrdio(boolean potvrdio) {
		this.potvrdio = potvrdio;
	}
}
