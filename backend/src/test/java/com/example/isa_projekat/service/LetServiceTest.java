package com.example.isa_projekat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.isa_projekat.DTO.LetDTO;
import com.example.isa_projekat.model.Aerodrom;
import com.example.isa_projekat.model.Aviokompanija;
import com.example.isa_projekat.model.Let;
import com.example.isa_projekat.model.Rezervacija;
import com.example.isa_projekat.repository.AeroRepository;
import com.example.isa_projekat.repository.AvioRepository;
import com.example.isa_projekat.repository.LetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LetServiceTest {

	@Mock
	private LetRepository letRepositoryMock;
	
	@Mock
	private AeroRepository aeroRepositoryMock;
	
	@Mock
	private AvioRepository avioRepositoryMock;
	
	@Mock
	private Let let;
	
	@InjectMocks
	private LetService letService;
	
	@Test
	public void testFinaAll() {
		
		when(letRepositoryMock.findAll()).thenReturn(Arrays.asList(let));
		when(let.getOdDest()).thenReturn(new Aerodrom());
		when(let.getDoDest()).thenReturn(new Aerodrom());
		when(let.getPoleceDate()).thenReturn(new Date());
		when(let.getSleceDate()).thenReturn(new Date());
		when(let.getPoleceTime()).thenReturn(new Date());
		when(let.getSleceTime()).thenReturn(new Date());
		when(let.getAvio()).thenReturn(new Aviokompanija());
		
		List<LetDTO> aeros = letService.findAll();
		
		assertThat(aeros).hasSize(1);
		
		verify(letRepositoryMock, times(1)).findAll();
		verifyNoMoreInteractions(letRepositoryMock);
	}
	
	@Test
	public void testFindOne() {
		
		Let l = new Let();
		l.setId(1l);
		l.setRezervacije(Stream.of(new Rezervacija()).collect(Collectors.toSet()));
		
		when(letRepositoryMock.findById(1l)).thenReturn(Optional.of(let));
		when(let.getOdDest()).thenReturn(new Aerodrom());
		when(let.getDoDest()).thenReturn(new Aerodrom());
		when(let.getPoleceDate()).thenReturn(new Date());
		when(let.getSleceDate()).thenReturn(new Date());
		when(let.getPoleceTime()).thenReturn(new Date());
		when(let.getSleceTime()).thenReturn(new Date());
		when(let.getAvio()).thenReturn(new Aviokompanija());
		
		assertNotNull(letService.findOne(1l));
		
		verify(letRepositoryMock, times(1)).findById(1l);
		
		verifyNoMoreInteractions(letRepositoryMock);
		
	}
	
	@Test
	public void testFindByAvio() {
		
		Aviokompanija a = new Aviokompanija();
		a.setId(1l);
		a.getLetovi().add(let);
		
		when(avioRepositoryMock.findById(1l)).thenReturn(Optional.of(a));
		when(let.getOdDest()).thenReturn(new Aerodrom());
		when(let.getDoDest()).thenReturn(new Aerodrom());
		when(let.getPoleceDate()).thenReturn(new Date());
		when(let.getSleceDate()).thenReturn(new Date());
		when(let.getPoleceTime()).thenReturn(new Date());
		when(let.getSleceTime()).thenReturn(new Date());
		when(let.getAvio()).thenReturn(new Aviokompanija());
		
		List<LetDTO> ret = letService.findByAvio(1l);
		
		assertThat(ret).hasSize(1);
		
		verify(avioRepositoryMock, times(1)).findById(1l);
		verifyNoMoreInteractions(avioRepositoryMock);
	}
	
	@Test
	public void testPretraga() {
		
		LetDTO dto = new LetDTO();
		dto.setOd(1l);
		dto.setDoo(2l);
		dto.setTipLeta(0);
		dto.setKlasa(0);
		dto.setPrtljag(0);
		
		Aerodrom a1 = new Aerodrom();
		Aerodrom a2 = new Aerodrom();
		
		when(aeroRepositoryMock.findById(1l)).thenReturn(Optional.of(a1));
		when(aeroRepositoryMock.findById(2l)).thenReturn(Optional.of(a2));
		
		
		when(letRepositoryMock.findAllByPoleceDateAndSleceDateAndOdDestAndDoDest(null, null, a1, a2)).thenReturn(Arrays.asList(let));
		when(let.getOdDest()).thenReturn(new Aerodrom());
		when(let.getDoDest()).thenReturn(new Aerodrom());
		when(let.getPoleceDate()).thenReturn(new Date());
		when(let.getSleceDate()).thenReturn(new Date());
		when(let.getPoleceTime()).thenReturn(new Date());
		when(let.getSleceTime()).thenReturn(new Date());
		when(let.getAvio()).thenReturn(new Aviokompanija());
		
		List<LetDTO> ret = letService.pretraga(dto);
		
		assertThat(ret).hasSize(1);
		
		verify(aeroRepositoryMock, times(1)).findById(1l);
		verify(aeroRepositoryMock, times(1)).findById(2l);
		
		verify(letRepositoryMock, times(1)).findAllByPoleceDateAndSleceDateAndOdDestAndDoDest(null, null, a1, a2);
	
		verifyNoMoreInteractions(aeroRepositoryMock);
		verifyNoMoreInteractions(letRepositoryMock);
	}

}