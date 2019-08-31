package com.example.isa_projekat.DTO;

import com.example.isa_projekat.model.Korisnik;

public class KorisnikDTO {
	private Long id;
	private String email;
	private String pass;
	private String ime;
	private String prezime;
	private String grad;
	private String telefon;
	private Long idAvio;
	
	public KorisnikDTO() {}
	
	public KorisnikDTO(Korisnik korisnik) {
		super();
		this.id = korisnik.getId();
		this.email = korisnik.getEmail();
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.grad = korisnik.getGrad();
		this.telefon = korisnik.getTelefon();
	}
	
	public Long getIdAvio() {
		return idAvio;
	}
	
	public void setIdAvio(Long idAvio) {
		this.idAvio = idAvio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
}
