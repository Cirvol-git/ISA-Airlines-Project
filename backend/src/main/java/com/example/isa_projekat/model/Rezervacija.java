package com.example.isa_projekat.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class Rezervacija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Version
	private Long version;
	
	@Column(name = "red")
	private int red;
	
	@Column(name = "kolona")
	private int kolona;

	@Column(name = "odobreno")
	private boolean odobreno;
	
	@Column(name = "tip")
	private int tip;
	
	@Column
	private String ime;
	
	@Column
	private String prezime;
	
	@Column
	private String pasos;
	
	@Column(name = "rezervisano")
	@Temporal(TemporalType.TIMESTAMP)
	private Date rezervisano;
	
	@Column(name = "potvrdjeno")
	private boolean potvrdjeno;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	private Let pripadaLetu;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	private Korisnik pripadaKorisniku;
	
	@ManyToOne(optional = true ,fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	private Korisnik invOd;
	
	public Rezervacija() {}
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getKolona() {
		return kolona;
	}

	public void setKolona(int kolona) {
		this.kolona = kolona;
	}

	public boolean isOdobreno() {
		return odobreno;
	}

	public void setOdobreno(boolean odobreno) {
		this.odobreno = odobreno;
	}

	public int getTip() {
		return tip;
	}

	public void setTip(int tip) {
		this.tip = tip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPasos() {
		return pasos;
	}

	public void setPasos(String pasos) {
		this.pasos = pasos;
	}

	public Korisnik getInvOd() {
		return invOd;
	}

	public void setInvOd(Korisnik invOd) {
		this.invOd = invOd;
	}

	public Date getRezervisano() {
		return rezervisano;
	}
	
	public boolean isPotvrdjeno() {
		return potvrdjeno;
	}

	public void setPotvrdjeno(boolean potvrdjeno) {
		this.potvrdjeno = potvrdjeno;
	}

	public void setRezervisano(Date rezervisano) {
		this.rezervisano = rezervisano;
	}

	public Let getPripadaLetu() {
		return pripadaLetu;
	}

	public void setPripadaLetu(Let pripadaLetu) {
		this.pripadaLetu = pripadaLetu;
	}

	public Korisnik getPripadaKorisniku() {
		return pripadaKorisniku;
	}

	public void setPripadaKorisniku(Korisnik pripadaKorisniku) {
		this.pripadaKorisniku = pripadaKorisniku;
	}
}