package com.example.isa_projekat.DTO;

import java.util.List;

import com.example.isa_projekat.model.Aviokompanija;

public class AviokompanijaDTO {
	private Long id;
	private Long version;
	private String ime;
	private String adresa;
	private String opis;
	private List<AerodromDTO> destinacije;
	
	public AviokompanijaDTO() {}
	
	public AviokompanijaDTO(Aviokompanija a) {
		super();
		this.id = a.getId();
		this.version = a.getVersion();
		this.ime = a.getIme();
		this.adresa = a.getAdresa();
		this.opis = a.getOpis();
	}
	
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

	public List<AerodromDTO> getDestinacije() {
		return destinacije;
	}

	public void setDestinacije(List<AerodromDTO> destinacije) {
		this.destinacije = destinacije;
	}
	
}
