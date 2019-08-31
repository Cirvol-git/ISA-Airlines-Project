package com.example.isa_projekat.DTO;

public class PrijateljiDTO {
	private Long id;
	private Long je;
	private Long od;
	private KorisnikDTO ret;
	
	public PrijateljiDTO() {}
	
	public PrijateljiDTO(Long id, KorisnikDTO ret) {
		super();
		this.id = id;
		this.ret = ret;
	}

	public KorisnikDTO getRet() {
		return ret;
	}

	public void setRet(KorisnikDTO ret) {
		this.ret = ret;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getJe() {
		return je;
	}

	public void setJe(Long je) {
		this.je = je;
	}

	public Long getOd() {
		return od;
	}

	public void setOd(Long od) {
		this.od = od;
	}
	
}
