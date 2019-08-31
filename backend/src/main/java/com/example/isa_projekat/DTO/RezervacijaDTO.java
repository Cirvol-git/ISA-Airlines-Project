package com.example.isa_projekat.DTO;

import java.util.Date;

import com.example.isa_projekat.model.Rezervacija;

public class RezervacijaDTO {
	private Long id;
	private Long version;
	private Date rezervisano;
	private boolean potvrdjeno;
	private Long id_let;
	private Long id_korisnik;
	private LetDTO let;
	private KorisnikDTO korisnik;
	private Long inv;
	private KorisnikDTO invOd;
	private String ime;
	private String prezime;
	private String pasos;
	private int red;
	private int kolona;
	private boolean odobreno;
	private int tip;
	
	public RezervacijaDTO() {}
	
	public RezervacijaDTO(Rezervacija r) {
		super();
		this.id = r.getId();
		this.version = r.getVersion();
		this.rezervisano = r.getRezervisano();
		this.potvrdjeno = r.isPotvrdjeno();
		//this.id_let = r.getPripadaLetu().getId();
		//this.id_korisnik = r.getPripadaKorisniku()!=null?r.getPripadaKorisniku().getId():null;
		//this.let = r.getPripadaLetu()!=null?new LetDTO(r.getPripadaLetu()):null;
		this.korisnik = r.getPripadaKorisniku()!=null?new KorisnikDTO(r.getPripadaKorisniku()):null;
		this.invOd = r.getInvOd()!=null?new KorisnikDTO(r.getInvOd()):null;
		this.pasos = r.getPasos();
		this.red = r.getRed();
		this.kolona = r.getKolona();
		this.odobreno = r.isOdobreno();
		this.tip = r.getTip();
	}
	/*
	public Rezervacija toRezervacija() {
		
		Rezervacija ret = new Rezervacija();
		
		ret.setId(id);
		ret.setVersion(version);		
		ret.setRed(red);
		ret.setKolona(kolona);
		ret.setTip(tip);
		ret.setIme(ime);
		ret.setPrezime(prezime);
		ret.setPasos(pasos);
		ret.setRezervisano(rezervisano);
		ret.setPotvrdjeno(potvrdjeno);
	}
	*/
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

	public KorisnikDTO getInvOd() {
		return invOd;
	}

	public void setInvOd(KorisnikDTO invOd) {
		this.invOd = invOd;
	}

	public Long getInv() {
		return inv;
	}

	public void setInv(Long inv) {
		this.inv = inv;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRezervisano() {
		return rezervisano;
	}

	public void setRezervisano(Date rezervisano) {
		this.rezervisano = rezervisano;
	}

	public boolean isPotvrdjeno() {
		return potvrdjeno;
	}

	public void setPotvrdjeno(boolean potvrdjeno) {
		this.potvrdjeno = potvrdjeno;
	}

	public Long getId_let() {
		return id_let;
	}

	public void setId_let(Long id_let) {
		this.id_let = id_let;
	}

	public Long getId_korisnik() {
		return id_korisnik;
	}

	public void setId_korisnik(Long id_korisnik) {
		this.id_korisnik = id_korisnik;
	}

	public LetDTO getLet() {
		return let;
	}

	public void setLet(LetDTO let) {
		this.let = let;
	}

	public KorisnikDTO getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikDTO korisnik) {
		this.korisnik = korisnik;
	}

}
