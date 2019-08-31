package com.example.isa_projekat.DTO;

import java.util.Set;

import com.example.isa_projekat.model.Aerodrom;

public class AerodromDTO{
	private Long id;
	private String ime;
	private String adresa;
	private String grad;
	private Long idAvio;
	private Set<AviokompanijaDTO> kompanije;
	
	public AerodromDTO() {}

	public AerodromDTO(Aerodrom a) {
		super();
		this.id = a.getId();
		this.ime = a.getIme();
		this.adresa = a.getAdresa();
		this.grad = a.getGrad();
	}
	
	public Set<AviokompanijaDTO> getKompanije() {
		return kompanije;
	}

	public void setKompanije(Set<AviokompanijaDTO> kompanije) {
		this.kompanije = kompanije;
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
	
}
