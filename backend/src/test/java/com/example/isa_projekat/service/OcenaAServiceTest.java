package com.example.isa_projekat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.isa_projekat.DTO.OcenaDTO;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.OcenaAvio;
import com.example.isa_projekat.model.Rezervacija;
import com.example.isa_projekat.repository.AvioRepository;
import com.example.isa_projekat.repository.KorisnikRepository;
import com.example.isa_projekat.repository.OcenaARepository;
import com.example.isa_projekat.repository.RezervacijaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OcenaAServiceTest {

	@Mock
	private OcenaARepository ocenaARepositoryMock;
	
	@Mock
	private KorisnikRepository korisnikRepositoryMock;
	
	@Mock
	private AvioRepository avioRepositoryMock;
	
	@Mock
	private RezervacijaRepository rezervacijaRepositoryMock;
	
	@Mock
	private OcenaAvio ocena;
	
	@Mock
	private Rezervacija rez;
	
	@InjectMocks
	private OcenaAService ocenaAService;
	
	@Test
	public void testGetFromKorisnika() {
		
		Aviokompanija avio = new Aviokompanija();
		avio.setId(1l);
		
		Korisnik k = new Korisnik();
		k.setId(2l);
		
		OcenaAvio o = new OcenaAvio();
		o.setAvio(avio);
		o.setOdKorisnika(k);
		
		List<OcenaAvio> ocene = new ArrayList<>();
		ocene.add(o);
		
		when(korisnikRepositoryMock.findById(2l)).thenReturn(Optional.of(k));
		when(ocenaARepositoryMock.findByOdKorisnika(k)).thenReturn(ocene);
		
		List<OcenaDTO> ret = ocenaAService.getFromKorisnik(2l);
		
		assertThat(ret).hasSize(1);
		
		verify(korisnikRepositoryMock, times(1)).findById(2l);
		verify(ocenaARepositoryMock, times(1)).findByOdKorisnika(k);
		
		verifyNoMoreInteractions(korisnikRepositoryMock);
		verifyNoMoreInteractions(ocenaARepositoryMock);
		
	}
	/*
	@Test
	@Transactional
	@Rollback(true)
	public void testOceni() {
		
		Aviokompanija avio = new Aviokompanija();
		avio.setId(1l);
		
		Korisnik k = new Korisnik();
		k.setId(2l);
		
		OcenaDTO dto = new OcenaDTO();
		dto.setId(1l);
		dto.setIdAorL(1l);
		dto.setIdKorisnika(2l);
		
		OcenaAvio o = new OcenaAvio();
		o.setId(1l);
		o.setAvio(avio);
		o.setOdKorisnika(k);
		
		
		when(avioRepositoryMock.findById(1l)).thenReturn(Optional.of(avio));
		when(korisnikRepositoryMock.findById(2l)).thenReturn(Optional.of(k));
		
		when(ocenaARepositoryMock.findByAvioAndOdKorisnika(avio,k)).thenReturn(o);
		when(ocenaARepositoryMock.save(o)).thenReturn(o);
		
		when(ocena.getId()).thenReturn(1l);
		
		assertNotNull(ocenaAService.oceni(dto));
		
		verify(avioRepositoryMock, times(1)).findById(1l);
		verify(korisnikRepositoryMock, times(1)).findById(2l);
		verify(ocenaARepositoryMock, times(1)).findByAvioAndOdKorisnika(avio,k);
		verify(ocenaARepositoryMock, times(1)).save(o);
		
		verifyNoMoreInteractions(avioRepositoryMock);
		verifyNoMoreInteractions(korisnikRepositoryMock);
		verifyNoMoreInteractions(ocenaARepositoryMock);
	}
	*/
	@Test
	public void testCantVote() {
		
		Aviokompanija avio = new Aviokompanija();
		avio.setId(1l);
		
		Korisnik k = new Korisnik();
		k.setId(2l);
		
		List<Rezervacija> rezervacije = new ArrayList<>();
		rezervacije.add(rez);
		
		Let let = new Let();
		let.setAvio(avio);
		let.setSleceDate(new Date(0L));
		let.setSleceTime(new Date(0L));
		
		OcenaDTO dto = new OcenaDTO();
		dto.setIdAorL(1l);
		dto.setIdKorisnika(2l);
		
		when(avioRepositoryMock.findById(1l)).thenReturn(Optional.of(avio));
		when(korisnikRepositoryMock.findById(2l)).thenReturn(Optional.of(k));
	
		when(rezervacijaRepositoryMock.findByPripadaKorisniku(k)).thenReturn(rezervacije);
	
		when(rez.getPripadaLetu()).thenReturn(let);
		
		assertTrue(ocenaAService.cantVote(dto));
		
		verify(avioRepositoryMock, times(1)).findById(1l);
		verify(korisnikRepositoryMock, times(1)).findById(2l);
		verify(rezervacijaRepositoryMock, times(1)).findByPripadaKorisniku(k);
		
		verifyNoMoreInteractions(avioRepositoryMock);
		verifyNoMoreInteractions(korisnikRepositoryMock);
		verifyNoMoreInteractions(rezervacijaRepositoryMock);

	}
	
	@Test
	public void testProsek() {
		
		Aviokompanija avio = new Aviokompanija();
		avio.setId(1l);
		
		Korisnik k = new Korisnik();
		k.setId(2l);
		
		OcenaAvio o = new OcenaAvio();
		o.setAvio(avio);
		o.setOdKorisnika(k);
		
		List<OcenaAvio> ocene = new ArrayList<>();
		ocene.add(o);
		
		when(avioRepositoryMock.findById(1l)).thenReturn(Optional.of(avio));
		when(ocenaARepositoryMock.findByAvio(avio)).thenReturn(ocene);
		
		List<OcenaDTO> ret = ocenaAService.prosek(1l);
		
		assertThat(ret).hasSize(1);
		
		verify(avioRepositoryMock, times(1)).findById(1l);
		verify(ocenaARepositoryMock, times(1)).findByAvio(avio);
		
		verifyNoMoreInteractions(avioRepositoryMock);
		verifyNoMoreInteractions(ocenaARepositoryMock);
	}
}
