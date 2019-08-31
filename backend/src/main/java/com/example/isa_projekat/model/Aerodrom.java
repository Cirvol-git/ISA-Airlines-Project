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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Aerodrom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "ime", length = 50)
	private String ime;
	
	@Column(name = "adresa", length = 50)
	private String adresa;
	
	@Column(name = "grad", length = 50)
	private String grad;
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "destinacije")
    private Set<Aviokompanija> kompanije = new HashSet<>();
	
	@OneToMany(mappedBy = "odDest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Let> odd = new HashSet<Let>();
	
	@OneToMany(mappedBy = "doDest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Let> doo = new HashSet<Let>();
	
	public Aerodrom() {}

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
	
	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
	
	public Set<Aviokompanija> getKompanije() {
		return kompanije;
	}

	public void setKompanije(Set<Aviokompanija> kompanije) {
		this.kompanije = kompanije;
	}

	public Set<Let> getOdd() {
		return odd;
	}

	public void setOdd(Set<Let> odd) {
		this.odd = odd;
	}

	public Set<Let> getDoo() {
		return doo;
	}

	public void setDoo(Set<Let> doo) {
		this.doo = doo;
	}
	
}
