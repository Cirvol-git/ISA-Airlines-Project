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
public class OcenaAvio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Aviokompanija avio;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Korisnik odKorisnika;
	
	@Column(name = "vrednost")
	private int vrednost;
	
	public OcenaAvio() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Aviokompanija getAvio() {
		return avio;
	}

	public void setAvio(Aviokompanija avio) {
		this.avio = avio;
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
