package com.example.isa_projekat.DTO;

import com.example.isa_projekat.model.Korisnik;

public class PrijavaDTO {
	
	private Long id;
	private String email;
	private String pass;
	private String ime;
	private String prezime;
	private String grad;
	private String telefon;
	private boolean admin;
	private Long adminOd;
	
	public PrijavaDTO() {}
	
	public PrijavaDTO(Korisnik k) {
		super();
		this.id = k.getId();
		this.email = k.getEmail();
		this.pass = k.getPass();
		this.ime = k.getIme();
		this.prezime = k.getPrezime();
		this.grad = k.getGrad();
		this.telefon = k.getTelefon();
		this.admin = k.isAdmin();
		//this.adminOd = k.getAdminOd();
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
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Long getAdminOd() {
		return adminOd;
	}
	public void setAdminOd(Long adminOd) {
		this.adminOd = adminOd;
	}
	
}
