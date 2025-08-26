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

        if(rpa.compareTo(BigDecimal.ZERO) < 0 || rbt12.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Faturamento não pode ser menor que 0 nem a Receita Bruta do mês");
        }

        FaixaAnexo faixaAnexo = faixaRepository.faixaIdealDeCalculo(dto.anexo()).orElseThrow(()-> new UsernameNotFoundException("Anexo não encontrado"));

        Empresa empresa = new Empresa("null", Anexo.ANEXO_1);

        if(empresaRepository.findByCNPJ(dto.cnpj()).isEmpty()){
            empresa.setCnpj(dto.cnpj());
            empresa.setAnexoPadrao(Anexo.fromValor(Math.toIntExact(dto.anexo())));
        }

        BigDecimal produtoEntreFaturamentoAnualEAliquotaNominal = BigDecimalUtil.multiplicar(rbt12, faixaAnexo.getAliquotaNominal());

        BigDecimal subtracaoEntreOProdutoEParcelaADeduzir = BigDecimalUtil.subtracao(produtoEntreFaturamentoAnualEAliquotaNominal, faixaAnexo.getDescontoDoValorRecolhido());

        if(subtracaoEntreOProdutoEParcelaADeduzir.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor não pode ser 0 ou menor que 0");
        }

        BigDecimal aliquotaAfetiva = (BigDecimalUtil.dividir(subtracaoEntreOProdutoEParcelaADeduzir, rbt12));

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

        return BigDecimalUtil.toMonetario(DAS);
    }

}
