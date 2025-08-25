package com.devchaves.backend.service;

import com.devchaves.backend.dto.CalculoRequest;
import com.devchaves.backend.entity.Anexo;
import com.devchaves.backend.entity.Calculo;
import com.devchaves.backend.entity.Empresa;
import com.devchaves.backend.entity.FaixaAnexo;
import com.devchaves.backend.repository.EmpresaRepository;
import com.devchaves.backend.repository.FaixaRepository;
import com.devchaves.backend.util.BigDecimalUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CalculoService {

    private final FaixaRepository faixaRepository;

    private final EmpresaRepository empresaRepository;

    public CalculoService(FaixaRepository faixaRepository, EmpresaRepository empresaRepository) {
        this.faixaRepository = faixaRepository;
        this.empresaRepository = empresaRepository;
    }

    public BigDecimal calculoDasModeloPreReforma(CalculoRequest dto){

        BigDecimal rpa = BigDecimalUtil.toMonetario(dto.rpa());

        BigDecimal rbt12 = BigDecimalUtil.toMonetario(dto.rbt12());

        FaixaAnexo faixaAnexo = faixaRepository.faixaIdealDeCalculo(dto.anexo()).orElseThrow(()-> new UsernameNotFoundException("Anexo não encontrado"));


        Empresa empresa = new Empresa("null", Anexo.ANEXO_1);

        if(empresaRepository.findByCNPJ(dto.cnpj()).isEmpty()){
            empresa.setCnpj(dto.cnpj());
            empresa.setAnexoPadrao(Anexo.fromValor(Math.toIntExact(dto.anexo())));
        }

        BigDecimal aliquotaAfetiva = new BigDecimal(BigInteger.ONE);

        aliquotaAfetiva = (BigDecimalUtil
                .subtracao(BigDecimalUtil
                        .multiplicar(rbt12, faixaAnexo.getAliquotaNominal()), faixaAnexo.getDescontoDoValorRecolhido()));

        BigDecimal DAS = BigDecimalUtil.multiplicar(rpa, aliquotaAfetiva);

        Calculo calculoFeito = new Calculo(
                empresa,
                faixaAnexo,
                LocalDateTime.now(),
                LocalDate.now(),
                rpa,
                rbt12,
                aliquotaAfetiva,
                DAS
        );

        return DAS;
    }

}
