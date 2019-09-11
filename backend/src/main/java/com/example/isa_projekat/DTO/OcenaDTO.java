package com.example.isa_projekat.DTO;

import com.example.isa_projekat.model.OcenaAvio;
import com.example.isa_projekat.model.OcenaLeta;

public class OcenaDTO {
	private Long id;
	private Long idAorL;
	private Long idKorisnika;
	private int vrednost;
	
	public OcenaDTO() {}
	
	public OcenaDTO(OcenaAvio o) {
		this.id = o.getId();
		this.idAorL = o.getAvio().getId();
		this.idKorisnika = o.getOdKorisnika().getId();
		this.vrednost = o.getVrednost();
	}
	
	public OcenaDTO(OcenaLeta o) {
		this.id = o.getId();
		this.idAorL = o.getLet().getId();
		this.idKorisnika = o.getOdKorisnika().getId();
		this.vrednost = o.getVrednost();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAorL() {
		return idAorL;
	}

	public void setIdAorL(Long idAorL) {
		this.idAorL = idAorL;
	}

	public Long getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(Long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public int getVrednost() {
		return vrednost;
	}

	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}
}