package com.example.isa_projekat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Korisnik {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email", unique = true, nullable = false, length = 35)
	private String email;
	
	@Column(name = "pass", nullable = false, length = 20)
	private String pass;
	
	@Column(name = "ime", nullable = false, length = 20)
	private String ime;
	
	@Column(name = "prezime", nullable = false, length = 20)
	private String prezime;
	
	@Column(name = "grad", length = 20)
	private String grad;
	
	@Column(name = "broj_telefona", length = 20)
	private String telefon;
	
	@Column(name = "admin_leta", nullable = false)
	private boolean admin;
	
	@OneToMany(mappedBy = "odKorisnika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaAvio> oceneAvio = new HashSet<OcenaAvio>();
	
	@OneToMany(mappedBy = "odKorisnika", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaLeta> oceneLetaova = new HashSet<OcenaLeta>();
	
	@OneToMany(mappedBy = "je", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Prijatelji> prijatelji = new HashSet<Prijatelji>();
	
	@OneToMany(mappedBy = "prijateljOd", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Prijatelji> prijateljOd = new HashSet<Prijatelji>();
	/*
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "prijatelji", joinColumns = {
	@JoinColumn(name = "korisnik", referencedColumnName = "id")}, inverseJoinColumns = {
	@JoinColumn(name = "prijatelj", referencedColumnName = "id")})
	private Set<Korisnik> prijatelji = new HashSet<Korisnik>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "zahtev_za_p", joinColumns = {
	@JoinColumn(name = "korisnik", referencedColumnName = "id")}, inverseJoinColumns = {
	@JoinColumn(name = "prijatelj", referencedColumnName = "id")})
	private Set<Korisnik> zahtevZaP = new HashSet<Korisnik>();
	*/
	@OneToMany(mappedBy = "pripadaKorisniku", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Aviokompanija adminOd;
	
	public Korisnik() {}

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
	
	public Set<OcenaAvio> getOceneAvio() {
		return oceneAvio;
	}

	public void setOceneAvio(Set<OcenaAvio> oceneAvio) {
		this.oceneAvio = oceneAvio;
	}
	
	public Set<OcenaLeta> getOceneLetaova() {
		return oceneLetaova;
	}

	public void setOceneLetaova(Set<OcenaLeta> oceneLetaova) {
		this.oceneLetaova = oceneLetaova;
	}

	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public Set<Prijatelji> getPrijatelji() {
		return prijatelji;
	}

	public void setPrijatelji(Set<Prijatelji> prijatelji) {
		this.prijatelji = prijatelji;
	}

	public Set<Prijatelji> getPrijateljOd() {
		return prijateljOd;
	}

	public void setPrijateljOd(Set<Prijatelji> prijateljOd) {
		this.prijateljOd = prijateljOd;
	}

	public Aviokompanija getAdminOd() {
		return adminOd;
	}

	public void setAdminOd(Aviokompanija adminOd) {
		this.adminOd = adminOd;
	}
	
}