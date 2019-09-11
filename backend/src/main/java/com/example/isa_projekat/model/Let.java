package com.example.isa_projekat.model;


import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "let")
public class Let {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date poleceDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date sleceDate;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date poleceTime;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date sleceTime;
	
	@Column(name = "vreme", nullable = false)
	private int vreme;
	
	@Column(name = "duzina", nullable = false)
	private int duzina;
	
	@Column(name = "tipLeta", nullable = false)
	private int tipLeta;
	
	@Column(name = "cenaE", nullable = false)
	private int cEconomy;
	
	@Column
	private int freeE;
	
	@Column(name = "cenaB", nullable = false)
	private int cBusiness;
	
	@Column
	private int freeB;
	
	@Column(name = "cenaF", nullable = false)
	private int cFirst;
	
	@Column
	private int freeF;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aviokompanija avio;
	
	@Column(name = "presedanja")
	private int brojPresedanja;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom odDest;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Aerodrom doDest;
	
	@Column(name = "redova")
	private int brojRedova;
	
	@OneToMany(mappedBy = "pripadaLetu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Rezervacija> rezervacije = new HashSet<Rezervacija>();
	
	@OneToMany(mappedBy = "let", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaLeta> ocene = new HashSet<OcenaLeta>();
	
	@Column(name = "prtljag")
	private int prtljag;
	
	public Let() {}

	public int getTipLeta() {
		return tipLeta;
	}

	public void setTipLeta(int tipLeta) {
		this.tipLeta = tipLeta;
	}

	public int getBrojRedova() {
		return brojRedova;
	}

	public void setBrojRedova(int brojRedova) {
		this.brojRedova = brojRedova;
	}

	public Aviokompanija getAvio() {
		return avio;
	}

	public void setAvio(Aviokompanija avio) {
		this.avio = avio;
	}

	public Aviokompanija getKompanija() {
		return avio;
	}

	public void setKompanija(Aviokompanija kompanija) {
		this.avio = kompanija;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getPoleceDate() {
		return poleceDate;
	}

	public void setPoleceDate(Date poleceDate) {
		this.poleceDate = poleceDate;
	}

	public Date getSleceDate() {
		return sleceDate;
	}

	public void setSleceDate(Date sleceDate) {
		this.sleceDate = sleceDate;
	}

	public Date getPoleceTime() {
		return poleceTime;
	}

	public void setPoleceTime(Date poleceTime) {
		this.poleceTime = poleceTime;
	}

	public Date getSleceTime() {
		return sleceTime;
	}

	public void setSleceTime(Date sleceTime) {
		this.sleceTime = sleceTime;
	}

	public int getVreme() {
		return vreme;
	}

	public void setVreme(int vreme) {
		this.vreme = vreme;
	}

	public int getDuzina() {
		return duzina;
	}

	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}
	
	public int getcEconomy() {
		return cEconomy;
	}

	public void setcEconomy(int cEconomy) {
		this.cEconomy = cEconomy;
	}

	public int getcBusiness() {
		return cBusiness;
	}

	public void setcBusiness(int cBusiness) {
		this.cBusiness = cBusiness;
	}

	public int getcFirst() {
		return cFirst;
	}

	public void setcFirst(int cFirst) {
		this.cFirst = cFirst;
	}

	public Set<OcenaLeta> getOcene() {
		return ocene;
	}

	public void setOcene(Set<OcenaLeta> ocene) {
		this.ocene = ocene;
	}
	
	public int getBrojPresedanja() {
		return brojPresedanja;
	}

	public void setBrojPresedanja(int brojPresedanja) {
		this.brojPresedanja = brojPresedanja;
	}

	public Aerodrom getOdDest() {
		return odDest;
	}

	public void setOdDest(Aerodrom odDest) {
		this.odDest = odDest;
	}

	public Aerodrom getDoDest() {
		return doDest;
	}

	public void setDoDest(Aerodrom doDest) {
		this.doDest = doDest;
	}

	public Set<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(Set<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	public int getFreeE() {
		return freeE;
	}

	public void setFreeE(int freeE) {
		this.freeE = freeE;
	}

	public int getFreeB() {
		return freeB;
	}

	public void setFreeB(int freeB) {
		this.freeB = freeB;
	}

	public int getFreeF() {
		return freeF;
	}

	public void setFreeF(int freeF) {
		this.freeF = freeF;
	}

	public int getPrtljag() {
		return prtljag;
	}

	public void setPrtljag(int prtljag) {
		this.prtljag = prtljag;
	}
	
}
