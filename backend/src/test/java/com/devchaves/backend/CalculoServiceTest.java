package com.devchaves.backend;

import com.devchaves.backend.dto.CalculoRequest;
import com.devchaves.backend.dto.CalculoResponse;
import com.devchaves.backend.entity.Anexo;
import com.devchaves.backend.entity.FaixaAnexo;
import com.devchaves.backend.exception.RegraDeCalculoNaoEncontradaException;
import com.devchaves.backend.repository.CalculoRepository;
import com.devchaves.backend.repository.EmpresaRepository;
import com.devchaves.backend.repository.FaixaRepository;
import com.devchaves.backend.service.CalculoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalculoServiceTest {

    @Mock
    private FaixaRepository faixaRepository;

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private CalculoRepository calculoRepository;

    @InjectMocks
    private CalculoService calculoService;

    @Test
    void deveCalcularODASCorretamenteParaCenarioValido(){

        CalculoRequest dto = new CalculoRequest("27252423000155"
                , "20000.00"
                , "480000.00"
                , 1L);

        FaixaAnexo faixaAnexoMock = new FaixaAnexo(
                Anexo.ANEXO_1,
                new BigDecimal("360000.01"),
                new BigDecimal("720000.00"),
                new BigDecimal("0.0950"),
                new BigDecimal("13860.00"),
                LocalDate.of(2025, 1, 1)
        );

        when(faixaRepository.faixaIdealDeCalculo(dto.anexo())).thenReturn(Optional.of(faixaAnexoMock));

        CalculoResponse DAS = calculoService.calculoDasModeloPreReforma(dto);

        BigDecimal valorEsperado = new BigDecimal("1322.50");

        System.out.println(DAS);

        assertNotNull(DAS);
        assertEquals(0, valorEsperado.compareTo(DAS.response()));

    }

    @Test
    void deveRetornarIllegalArgumentException(){

        CalculoRequest dto = new CalculoRequest("27252423000155"
                , "-800000000.00"
                , "350000.00"
                , 3L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            calculoService.calculoDasModeloPreReforma(dto);
        });

        String mensagemEsperada = "Faturamento não pode ser menor que 0 nem a Receita Bruta do mês";

        assertEquals(mensagemEsperada, exception.getMessage());

    }

    @Test
    void anexoNaoEncontradoDeveRetornarRegraDeCalculoNaoEncontrada(){

        CalculoRequest dto = new CalculoRequest("27252423000155"
                , "50000.00"
                , "350000.00"
                , 3L);

        RegraDeCalculoNaoEncontradaException exception = assertThrows(RegraDeCalculoNaoEncontradaException.class,
                () -> {
            calculoService.calculoDasModeloPreReforma(dto);
                });

        String mensagemEsperada = "Nenhuma Regra de Cálculo foi encontrada";

        assertEquals(mensagemEsperada, exception.getMessage());

    }
}
