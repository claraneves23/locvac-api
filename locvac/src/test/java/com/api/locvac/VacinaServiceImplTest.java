package com.api.locvac;

import com.api.locvac.dto.VacinaRequestDTO;
import com.api.locvac.mapper.VacinaMapper;
import com.api.locvac.model.core.Fabricante;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.repository.FabricanteRepository;
import com.api.locvac.repository.VacinaRepository;
import com.api.locvac.service.impl.VacinaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VacinaServiceImplTest {

    @Mock
    private VacinaRepository vacinaRepository;

    @Mock
    private FabricanteRepository fabricanteRepository;

    @Mock
    private VacinaMapper vacinaMapper;

    @InjectMocks
    private VacinaServiceImpl vacinaServiceImpl;

    @Test
    public void deveCadastrarVacina(){

        VacinaRequestDTO vacinaRequestDTO = new VacinaRequestDTO(
                "BCG",
                1L,
                LocalDate.of(2024, 1, 10),
                LocalDate.of(2026, 1, 10),
                "Vacina Teste"
        );

        Fabricante fabricante = new Fabricante();
        Vacina vacina = mock(Vacina.class);

        when(fabricanteRepository.findById(1L)).thenReturn(Optional.of(fabricante));
        when(vacinaRepository.existsByNmVacinaIgnoreCaseAndFabricanteAndDtFabricacao(anyString(),
                any(), any())).thenReturn(false);

        when(vacinaMapper.toEntity(vacinaRequestDTO, fabricante))
                .thenReturn(vacina);

        vacinaServiceImpl.cadastrarVacina(vacinaRequestDTO);

        verify(vacinaRepository).save(vacina);

    }

    @Test
    public void naoDeveCadastrarVacinaSeNaoExistirFabricanteCadastrado(){

        VacinaRequestDTO vacinaRequestDTO = new VacinaRequestDTO(
                "BCG",
                1L,
                LocalDate.of(2024, 1, 10),
                LocalDate.of(2026, 1, 10),
                "Vacina Teste"
        );

        when(fabricanteRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> vacinaServiceImpl.cadastrarVacina(vacinaRequestDTO)
        );

        assertEquals(
                "Fabricante não encontrado",
                exception.getMessage()
        );

        verify(vacinaRepository, never()).save(any());

    }

    @Test
    public void naoDeveCadastrarVacinaSeAVacinaForDuplicada(){

        VacinaRequestDTO vacinaRequestDTO = new VacinaRequestDTO(
                "BCG",
                1L,
                LocalDate.of(2024, 1, 10),
                LocalDate.of(2026, 1, 10),
                "Vacina Teste"
        );

        Fabricante fabricante = new Fabricante();

        when(fabricanteRepository.findById(1L)).thenReturn(Optional.of(fabricante));
        when(vacinaRepository.existsByNmVacinaIgnoreCaseAndFabricanteAndDtFabricacao(anyString(),
                any(), any())).thenReturn(true);

        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> vacinaServiceImpl.cadastrarVacina(vacinaRequestDTO)
        );

        assertEquals(
                "Vacina já cadastrada",
                exception.getMessage()
        );

        verify(vacinaRepository, never()).save(any());

    }

    @Test
    public void deveListarVacinas(){
        List<Vacina> vacinas = List.of(new Vacina(), new Vacina());

        when(vacinaRepository.findAll()).thenReturn(vacinas);

        List<Vacina> resultado = vacinaServiceImpl.listarVacinas();

        assertEquals(2, resultado.size());
    }


}
