package com.example.isa_projekat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
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

import com.example.isa_projekat.DTO.RezervacijaDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.Rezervacija;
import com.example.isa_projekat.repository.KorisnikRepository;
import com.example.isa_projekat.repository.LetRepository;
import com.example.isa_projekat.repository.RezervacijaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RezervacijaServiceTest {

	@Mock
	private RezervacijaRepository rezervacijaRepositoryMock;
	
	@Mock
	private KorisnikRepository korisnikRepositoryMock;
	
	@Mock
	private LetRepository letRepositoryMock;
	
	@Mock
	private Rezervacija rez;
	
	@Mock
	private Let let;
	
	@InjectMocks
	private RezervacijaService rezervacijaService;
	
	@Test
	public void testFindByKorisnik() {
		
		
		List<Rezervacija> rezs = new ArrayList<>();
		rezs.add(new Rezervacija());
		
		Korisnik k = new Korisnik();
		k.setId(1l);
		
		
		when(korisnikRepositoryMock.findById(1l)).thenReturn(Optional.of(k));
		when(rezervacijaRepositoryMock.findByPripadaKorisniku(k)).thenReturn(rezs);
		
		List<RezervacijaDTO> ret = rezervacijaService.findByKorisnik(1l);
		
		assertThat(ret).hasSize(1);
		
		verify(korisnikRepositoryMock, times(1)).findById(1l);
		verify(rezervacijaRepositoryMock, times(1)).findByPripadaKorisniku(k);
		
		verifyNoMoreInteractions(korisnikRepositoryMock);
		
	}
	
	@Test
	public void testFindPripadaKorisnikAndPotvrdjeno() {
		
		List<Rezervacija> rezs = new ArrayList<>();
		rezs.add(rez);
		
		Korisnik k = new Korisnik();
		k.setId(1l);
		
		
		when(korisnikRepositoryMock.findById(1l)).thenReturn(Optional.of(k));
		when(rezervacijaRepositoryMock.findByPripadaKorisnikuAndPotvrdjeno(k, true)).thenReturn(rezs);
		when(rez.getPripadaLetu()).thenReturn(let);
		when(let.getOdDest()).thenReturn(new Aerodrom());
		when(let.getDoDest()).thenReturn(new Aerodrom());
		when(let.getPoleceDate()).thenReturn(new Date());
		when(let.getSleceDate()).thenReturn(new Date());
		when(let.getPoleceTime()).thenReturn(new Date());
		when(let.getSleceTime()).thenReturn(new Date());
		when(let.getAvio()).thenReturn(new Aviokompanija());
		
		List<RezervacijaDTO> ret = rezervacijaService.findByKorisnikAndPotvrdjeno(1l, true);
		
		assertThat(ret).hasSize(1);
		
		verify(korisnikRepositoryMock, times(1)).findById(1l);
		verify(rezervacijaRepositoryMock, times(1)).findByPripadaKorisnikuAndPotvrdjeno(k, true);
		
		verifyNoMoreInteractions(korisnikRepositoryMock);
		
	}
	
	@Test
	public void testFindAllOfLet() {
		
		Let l = new Let();
		l.getRezervacije().add(new Rezervacija());
	
		when(letRepositoryMock.findById(1l)).thenReturn(Optional.of(l));
		//when(let.getRezervacije()).thenReturn(Stream.of(new Rezervacija()).collect(Collectors.toSet()));
		List<RezervacijaDTO> rezs = rezervacijaService.findAllRezervacije(1l);
		
		assertThat(rezs).hasSize(1);
		
		verify(letRepositoryMock, times(1)).findById(1l);
		verifyNoMoreInteractions(letRepositoryMock);
	}
	
	@Test
	public void testDecline() {
		
		Rezervacija r = new Rezervacija();
		r.setId(1l);
		
		when(rezervacijaRepositoryMock.findById(1l)).thenReturn(Optional.of(r));
		when(rezervacijaRepositoryMock.save(r)).thenReturn(rez);
		
		assertNotNull(rezervacijaService.decline(new RezervacijaDTO(r)));
		
		verify(rezervacijaRepositoryMock, times(1)).findById(1l);
		verify(rezervacijaRepositoryMock, times(1)).save(r);
		verifyNoMoreInteractions(rezervacijaRepositoryMock);

	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCreateFastReservation() {
		
		Korisnik k = new Korisnik();
		k.setId(1l);
		k.setIme("ime");
		k.setPrezime("prezime");
		
		Rezervacija r = new Rezervacija();
		r.setId(1l);
		
		RezervacijaDTO dto = new RezervacijaDTO();
		dto.setId(1l);
		dto.setId_korisnik(2l);
		dto.setVersion(55l);
		dto.setRezervisano(new Date());
		dto.setPotvrdjeno(true);
		dto.setPasos("1111");
		
		when(rezervacijaRepositoryMock.findById(1l)).thenReturn(Optional.of(r));
		when(korisnikRepositoryMock.findById(2l)).thenReturn(Optional.of(new Korisnik()));
		when(rezervacijaRepositoryMock.save(r)).thenReturn(new Rezervacija());
		
		assertNotNull(rezervacijaService.createBrzaRez(dto));
		
		verify(rezervacijaRepositoryMock, times(1)).findById(1l);
		verify(korisnikRepositoryMock, times(1)).findById(2l);
		verify(rezervacijaRepositoryMock, times(1)).save(r);
		
		verifyNoMoreInteractions(korisnikRepositoryMock);
		verifyNoMoreInteractions(rezervacijaRepositoryMock);
	}
	
}
