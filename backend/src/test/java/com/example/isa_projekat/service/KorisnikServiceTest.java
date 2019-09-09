package com.example.isa_projekat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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

import com.example.isa_projekat.DTO.KorisnikDTO;
import com.example.isa_projekat.model.Korisnik;
import com.example.isa_projekat.repository.KorisnikRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KorisnikServiceTest {

	@Mock
	private KorisnikRepository korisnikRepositoryMock;
	
	@Mock
	private Korisnik korisnik;
	
	@InjectMocks
	private KorisnikService korisnikService;
	
	@Test
	public void testFindAll() {
		
		when(korisnikRepositoryMock.findAll()).thenReturn(Arrays.asList(new Korisnik()));
		List<KorisnikDTO> aeros = korisnikService.findAll();
		
		assertThat(aeros).hasSize(1);
		
		verify(korisnikRepositoryMock, times(1)).findAll();
		verifyNoMoreInteractions(korisnikRepositoryMock);
	}
	
	@Test
	public void testFindOne() {
		
		Korisnik a = new Korisnik();
		a.setId(5l);
		
		when(korisnikRepositoryMock.findById(2l)).thenReturn(Optional.of(a));
		
		KorisnikDTO found = korisnikService.findOne(2l);
	
		assertEquals(found.getId(),a.getId());
		verify(korisnikRepositoryMock, times(1)).findById(2l);
        verifyNoMoreInteractions(korisnikRepositoryMock);
	}
	
	@Test
	public void testPretraga() {
		
		KorisnikDTO dto = new KorisnikDTO();
		dto.setIme("ime");
		dto.setPrezime("prezime");
		
		when(korisnikRepositoryMock.findByImeIgnoreCaseContainingAndPrezimeIgnoreCaseContaining("ime", "prezime")).thenReturn(Arrays.asList(new Korisnik(), new Korisnik()));
		List<KorisnikDTO> search = korisnikService.pretraga(dto);
		
		assertThat(search).hasSize(2);
		
		verify(korisnikRepositoryMock, times(1)).findByImeIgnoreCaseContainingAndPrezimeIgnoreCaseContaining("ime", "prezime");
		verifyNoMoreInteractions(korisnikRepositoryMock);
	}
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		
		KorisnikDTO update = new KorisnikDTO();
		update.setId(1l);
		update.setEmail("email@hotmail.com");
		update.setPass("123");
		update.setIme("ime");
		update.setPrezime("prezime");
		update.setGrad("grad");
		update.setTelefon("066666");
		
		Korisnik k = new Korisnik();
		k.setEmail("mail@hotmail.com");
		
		when(korisnikRepositoryMock.findById(1l)).thenReturn(Optional.of(k));
		when(korisnikRepositoryMock.findByEmail("email@hotmail.com")).thenReturn(Optional.empty());
		when(korisnikRepositoryMock.save(k)).thenReturn(new Korisnik());
		
		assertNotNull(korisnikService.update(update));
		
		verify(korisnikRepositoryMock, times(1)).findById(1l);
		verify(korisnikRepositoryMock, times(1)).findByEmail("email@hotmail.com");
		verify(korisnikRepositoryMock, times(1)).save(k);
		
		verifyNoMoreInteractions(korisnikRepositoryMock);
	}
}
