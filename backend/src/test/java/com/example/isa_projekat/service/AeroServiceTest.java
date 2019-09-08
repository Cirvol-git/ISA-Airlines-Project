package com.example.isa_projekat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
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

import com.example.isa_projekat.DTO.AerodromDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.repository.AeroRepository;
import com.example.isa_projekat.repository.AvioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AeroServiceTest {
	
	@Mock
	private AeroRepository aeroRepositoryMock;
	
	@Mock
	private AvioRepository avioRepositoryMock;
	
	@Mock
	private Aerodrom aero;
	
	@InjectMocks
	private AeroService aeroService;
	
	@Test
	public void testFindAll() {
		
		when(aeroRepositoryMock.findAll()).thenReturn(Arrays.asList(new Aerodrom()));
		List<AerodromDTO> aeros = aeroService.findAll();
		
		assertThat(aeros).hasSize(1);
		
		verify(aeroRepositoryMock, times(1)).findAll();
		verifyNoMoreInteractions(aeroRepositoryMock);
	}
	
	@Test
	public void testFindOne() {
		
		Aerodrom a = new Aerodrom();
		a.setId(5l);
		
		when(aeroRepositoryMock.findById(2l)).thenReturn(Optional.of(a));
		
		AerodromDTO found = aeroService.findOne(2l);
	
		assertEquals(found.getId(),a.getId());
		verify(aeroRepositoryMock, times(1)).findById(2l);
        verifyNoMoreInteractions(aeroRepositoryMock);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testUpdate() {
		
		Aerodrom a = new Aerodrom();
		a.setId(3l);
		a.setGrad("Novi Sad");
		a.setAdresa("Svetozara Miletica 50");
		a.setIme("NS Airlines");
		
		Aerodrom b = new Aerodrom();
		b.setId(3l);
		b.setGrad("Beograd");
		b.setAdresa("Beograd Adresa");
		b.setIme("Nikola tesla");
		
		
		when(aeroRepositoryMock.findById(a.getId())).thenReturn(Optional.of(b));
		when(aeroRepositoryMock.save(b)).thenReturn(a);
		
		AerodromDTO saved = aeroService.update(new AerodromDTO(a));
		
		assertEquals(saved.getId(), a.getId());
		assertEquals(saved.getIme(), a.getIme());
		assertEquals(saved.getAdresa(), a.getAdresa());
		assertEquals(saved.getGrad(), a.getGrad());
		
		verify(aeroRepositoryMock, times(1)).findById(3l);
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testCreate() {
		
		Aviokompanija avio = new Aviokompanija();
		avio.setId(1l);
		
		Aerodrom aerodrom = new Aerodrom();
		
		//aerodrom.setId(1l);
		aerodrom.setGrad("Novi Sad");
		aerodrom.setAdresa("Svetozara Miletica 50");
		aerodrom.setIme("NS Airlines");
		aerodrom.getKompanije().add(avio);
		
		//avio.getDestinacije().add(aerodrom);
		
		Aerodrom aerodrom1 = new Aerodrom();
		
		aerodrom1.setId(2l);
		aerodrom1.setGrad("Novi Sad");
		aerodrom1.setAdresa("Svetozara Miletica 50");
		aerodrom1.setIme("NS Airlines");
		aerodrom1.getKompanije().add(avio);
		
		AerodromDTO dto = new AerodromDTO(aerodrom);
		/*
		dto.setGrad("Novi Sad");
		dto.setAdresa("Svetozara Miletica 50");
		dto.setIme("NS Airlines");
		*/
		dto.setIdAvio(1l);
		
		
		when(avioRepositoryMock.findById(1l)).thenReturn(Optional.of(avio));
		when(aeroRepositoryMock.save(aerodrom)).thenReturn(aerodrom1);
	
		aeroService.create(dto);
		
		verify(avioRepositoryMock, times(1)).findById(1l);
	}
}
