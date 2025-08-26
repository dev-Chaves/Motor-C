package com.devchaves.backend;

import com.devchaves.backend.dto.CalculoRequest;
import com.devchaves.backend.entity.Anexo;
import com.devchaves.backend.entity.FaixaAnexo;
import com.devchaves.backend.exception.RegraDeCalculoNaoEncontradaException;
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

    @InjectMocks
    private CalculoService calculoService;

    @Test
    void deveCalcularODASCorretamenteParaCenarioValido(){

        CalculoRequest dto = new CalculoRequest("27252423000155"
                , "50000.00"
                , "350000.00"
                , 3L);

        FaixaAnexo faixaAnexoMock = new FaixaAnexo(
                Anexo.ANEXO_3,
                new BigDecimal("180000.01"),
                new BigDecimal("360000.00"),
                new BigDecimal("0.1120"), // Alíquota Nominal de 11,2%
                new BigDecimal("9360.00"),  // Parcela a Deduzir
                LocalDate.of(2025, 1, 1)
        );

        when(faixaRepository.faixaIdealDeCalculo(dto.anexo())).thenReturn(Optional.of(faixaAnexoMock));

        when(empresaRepository.findByCNPJ(dto.cnpj())).thenReturn(Optional.empty());

        BigDecimal DAS = calculoService.calculoDasModeloPreReforma(dto);

        // Assert (Verificar)
        // Cálculo manual para o valor esperado:
        // Alíquota Efetiva = ((350000 * 0.1120) - 9360) / 350000 = (39200 - 9360) / 350000 = 29840 / 350000 = 0.085257...
        // DAS = 50000 * 0.085257... = 4262.85
        BigDecimal valorEsperado = new BigDecimal("4262.86");

        System.out.println(DAS);

        assertNotNull(DAS);
        assertEquals(0, valorEsperado.compareTo(DAS));

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
