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
public class OcenaLeta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Aviokompanija pripadaAvio;
	*/
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Let let;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Korisnik odKorisnika;
	
	@Column(name = "vrednost")
	private int vrednost;
	
	public OcenaLeta() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Let getLet() {
		return let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public Korisnik getOdKorisnika() {
		return odKorisnika;
	}

	public void setOdKorisnika(Korisnik odKorisnika) {
		this.odKorisnika = odKorisnika;
	}

	public int getVrednost() {
		return vrednost;
	}

	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}
	
}
