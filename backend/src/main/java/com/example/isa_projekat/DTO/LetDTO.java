package com.example.isa_projekat.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.isa_projekat.model.Let;

public class LetDTO {
	private Long id;
	private Long od;
	private Long doo;
	private AerodromDTO odDest;
	private AerodromDTO doDest;
	private List<Long> presedanja = new ArrayList<Long>();
	private int brojPresedanja;
	private int tipLeta;
	private Date polece;
	private Date slece;
	private int vreme;
	private int duzina;
	private int cEconomy;
	private int cBusiness;
	private int cFirst;
	private int redova;
	private int brojOsoba;
	private int klasa;
	private List<RezervacijaDTO> rows = new ArrayList<RezervacijaDTO>();
	private Long idAvio;
	private String nazivAvio;
	private int prtljag;
	
	
	public LetDTO () {}

	public LetDTO(Let l) {
		super();
		this.id = l.getId();
		this.od = l.getOdDest().getId();
		this.doo = l.getDoDest().getId();
		this.brojPresedanja = l.getBrojPresedanja();
		this.tipLeta = l.getTipLeta();
		this.polece = new Date(l.getPoleceDate().getTime()+l.getPoleceTime().getTime());
		this.slece = new Date(l.getSleceDate().getTime()+l.getSleceTime().getTime());
		this.vreme = l.getVreme();
		this.duzina = l.getDuzina();
		this.cEconomy = l.getcEconomy();
		this.cBusiness = l.getcBusiness();
		this.cFirst = l.getcFirst();
		this.redova = l.getBrojRedova();
		this.idAvio = l.getAvio().getId();
		this.prtljag = l.getPrtljag();
		this.odDest = new AerodromDTO(l.getOdDest());
		this.doDest = new AerodromDTO(l.getDoDest());
		this.nazivAvio = l.getAvio().getIme();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public AerodromDTO getOdDest() {
		return odDest;
	}

	public void setOdDest(AerodromDTO odDest) {
		this.odDest = odDest;
	}

	public AerodromDTO getDoDest() {
		return doDest;
	}

	public void setDoDest(AerodromDTO doDest) {
		this.doDest = doDest;
	}

	public String getNazivAvio() {
		return nazivAvio;
	}

	public void setNazivAvio(String nazivAvio) {
		this.nazivAvio = nazivAvio;
	}

	public int getPrtljag() {
		return prtljag;
	}

	public void setPrtljag(int prtljag) {
		this.prtljag = prtljag;
	}

	public int getBrojOsoba() {
		return brojOsoba;
	}

	public void setBrojOsoba(int brojOsoba) {
		this.brojOsoba = brojOsoba;
	}

	public int getKlasa() {
		return klasa;
	}

	public void setKlasa(int klasa) {
		this.klasa = klasa;
	}

	public Long getIdAvio() {
		return idAvio;
	}

	public void setIdAvio(Long idAvio) {
		this.idAvio = idAvio;
	}

	public Date getPolece() {
		return polece;
	}

	public void setPolece(Date polece) {
		this.polece = polece;
	}

	public Date getSlece() {
		return slece;
	}

	public void setSlece(Date slece) {
		this.slece = slece;
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

	public Long getOd() {
		return od;
	}

	public void setOd(Long od) {
		this.od = od;
	}

	public Long getDoo() {
		return doo;
	}

	public void setDoo(Long doo) {
		this.doo = doo;
	}

	public List<Long> getPresedanja() {
		return presedanja;
	}

	public void setPresedanja(List<Long> presedanja) {
		this.presedanja = presedanja;
	}

	public int getBrojPresedanja() {
		return brojPresedanja;
	}

	public void setBrojPresedanja(int brojPresedanja) {
		this.brojPresedanja = brojPresedanja;
	}

	public int getTipLeta() {
		return tipLeta;
	}

	public void setTipLeta(int tipLeta) {
		this.tipLeta = tipLeta;
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

	public int getRedova() {
		return redova;
	}

	public void setRedova(int redova) {
		this.redova = redova;
	}

	public List<RezervacijaDTO> getRows() {
		return rows;
	}

	public void setRows(List<RezervacijaDTO> rows) {
		this.rows = rows;
	}
	
}
