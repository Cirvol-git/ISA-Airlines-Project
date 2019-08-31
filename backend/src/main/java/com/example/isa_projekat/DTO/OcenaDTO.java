package com.example.isa_projekat.DTO;

public class OcenaDTO {
	private Long idAorL;
	private Long idKorisnika;
	private int vrednost;
	
	public OcenaDTO() {}
	
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