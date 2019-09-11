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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "aviokompanija")
public class Aviokompanija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private Long version;
	
	@Column(name = "ime", length = 20)
	private String ime;
	
	@Column(name = "adresa", length = 35)
	private String adresa;
	
	@Column(name = "opis", length = 55)
	private String opis;
	
	@OneToMany(mappedBy = "avio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaAvio> ocene = new HashSet<OcenaAvio>();
	/*
	@OneToMany(mappedBy = "pripadaAvio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OcenaLeta> oceneLetova = new HashSet<OcenaLeta>();
	*/
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinTable(name = "avio_aero",
            joinColumns = { @JoinColumn(name = "avio_id") },
            inverseJoinColumns = { @JoinColumn(name = "aero_id") })
    private Set<Aerodrom> destinacije = new HashSet<>();
	
	@OneToMany(mappedBy = "avio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Let> letovi = new HashSet<Let>();
	
	@OneToMany(mappedBy = "adminOd", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Korisnik> admini = new HashSet<Korisnik>();
	
	public Aviokompanija() {}
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public Set<Let> getLetovi() {
		return letovi;
	}

	public void setLetovi(Set<Let> letovi) {
		this.letovi = letovi;
	}

	public Set<OcenaAvio> getOcene() {
		return ocene;
	}

	public void setOcene(Set<OcenaAvio> ocene) {
		this.ocene = ocene;
	}
	
	public Set<Aerodrom> getDestinacije() {
		return destinacije;
	}

	public void setDestinacije(Set<Aerodrom> destinacije) {
		this.destinacije = destinacije;
	}

	public Set<Korisnik> getAdmini() {
		return admini;
	}

	public void setAdmini(Set<Korisnik> admini) {
		this.admini = admini;
	}
	
	/*
	public Set<OcenaLeta> getOceneLetova() {
		return oceneLetova;
	}

	public void setOceneLetova(Set<OcenaLeta> oceneLetova) {
		this.oceneLetova = oceneLetova;
	}
	*/
}