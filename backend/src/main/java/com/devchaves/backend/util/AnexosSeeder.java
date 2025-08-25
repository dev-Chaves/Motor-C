package com.devchaves.backend.util;

import com.devchaves.backend.entity.Anexo;
import com.devchaves.backend.entity.FaixaAnexo;
import com.devchaves.backend.repository.FaixaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AnexosSeeder implements ApplicationRunner {

    private final FaixaRepository faixaRepository;
    private static final Logger log = LoggerFactory.getLogger(AnexosSeeder.class);

    public AnexosSeeder(FaixaRepository faixaRepository) {
        this.faixaRepository = faixaRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (faixaRepository.count() == 0) {
            log.info("Banco de dados vazio, adicionando Faixas de Anexos...");
            inserirFaixaDeAnexos();
            log.info("Povoamento do banco de dados concluído com sucesso.");
        } else {
            log.info("Banco de dados já contém dados. Povoamento não necessário.");
        }
    }

    private void inserirFaixaDeAnexos() {
        // Lista única para armazenar todas as faixas
        List<FaixaAnexo> todasAsFaixas = new ArrayList<>();
        LocalDate dataVigencia = LocalDate.of(2025, 1, 1);

        // --- Anexo I ---
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_1, new BigDecimal("0.00"), new BigDecimal("180000.00"), new BigDecimal("0.0400"), new BigDecimal("0.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_1, new BigDecimal("180000.01"), new BigDecimal("360000.00"), new BigDecimal("0.0730"), new BigDecimal("5940.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_1, new BigDecimal("360000.01"), new BigDecimal("720000.00"), new BigDecimal("0.0950"), new BigDecimal("13860.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_1, new BigDecimal("720000.01"), new BigDecimal("1800000.00"), new BigDecimal("0.1070"), new BigDecimal("22500.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_1, new BigDecimal("1800000.01"), new BigDecimal("3600000.00"), new BigDecimal("0.1430"), new BigDecimal("87300.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_1, new BigDecimal("3600000.01"), new BigDecimal("4800000.00"), new BigDecimal("0.1900"), new BigDecimal("378000.00"), dataVigencia));

        // --- Anexo II ---
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_2, new BigDecimal("0.00"), new BigDecimal("180000.00"), new BigDecimal("0.0450"), new BigDecimal("0.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_2, new BigDecimal("180000.01"), new BigDecimal("360000.00"), new BigDecimal("0.0780"), new BigDecimal("5940.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_2, new BigDecimal("360000.01"), new BigDecimal("720000.00"), new BigDecimal("0.1000"), new BigDecimal("13860.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_2, new BigDecimal("720000.01"), new BigDecimal("1800000.00"), new BigDecimal("0.1120"), new BigDecimal("22500.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_2, new BigDecimal("1800000.01"), new BigDecimal("3600000.00"), new BigDecimal("0.1470"), new BigDecimal("85500.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_2, new BigDecimal("3600000.01"), new BigDecimal("4800000.00"), new BigDecimal("0.3000"), new BigDecimal("720000.00"), dataVigencia));

        // --- Anexo III ---
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_3, new BigDecimal("0.00"), new BigDecimal("180000.00"), new BigDecimal("0.0600"), new BigDecimal("0.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_3, new BigDecimal("180000.01"), new BigDecimal("360000.00"), new BigDecimal("0.1120"), new BigDecimal("9360.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_3, new BigDecimal("360000.01"), new BigDecimal("720000.00"), new BigDecimal("0.1350"), new BigDecimal("17640.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_3, new BigDecimal("720000.01"), new BigDecimal("1800000.00"), new BigDecimal("0.1600"), new BigDecimal("35640.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_3, new BigDecimal("1800000.01"), new BigDecimal("3600000.00"), new BigDecimal("0.2100"), new BigDecimal("125640.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_3, new BigDecimal("3600000.01"), new BigDecimal("4800000.00"), new BigDecimal("0.3300"), new BigDecimal("648000.00"), dataVigencia));

        // --- Anexo IV ---
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_4, new BigDecimal("0.00"), new BigDecimal("180000.00"), new BigDecimal("0.0450"), new BigDecimal("0.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_4, new BigDecimal("180000.01"), new BigDecimal("360000.00"), new BigDecimal("0.0900"), new BigDecimal("8100.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_4, new BigDecimal("360000.01"), new BigDecimal("720000.00"), new BigDecimal("0.1020"), new BigDecimal("12420.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_4, new BigDecimal("720000.01"), new BigDecimal("1800000.00"), new BigDecimal("0.1400"), new BigDecimal("39780.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_4, new BigDecimal("1800000.01"), new BigDecimal("3600000.00"), new BigDecimal("0.2200"), new BigDecimal("183780.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_4, new BigDecimal("3600000.01"), new BigDecimal("4800000.00"), new BigDecimal("0.3300"), new BigDecimal("828000.00"), dataVigencia));

        // --- Anexo V ---
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_5, new BigDecimal("0.00"), new BigDecimal("180000.00"), new BigDecimal("0.1550"), new BigDecimal("0.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_5, new BigDecimal("180000.01"), new BigDecimal("360000.00"), new BigDecimal("0.1800"), new BigDecimal("4500.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_5, new BigDecimal("360000.01"), new BigDecimal("720000.00"), new BigDecimal("0.1950"), new BigDecimal("9900.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_5, new BigDecimal("720000.01"), new BigDecimal("1800000.00"), new BigDecimal("0.2050"), new BigDecimal("17100.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_5, new BigDecimal("1800000.01"), new BigDecimal("3600000.00"), new BigDecimal("0.2300"), new BigDecimal("62100.00"), dataVigencia));
        todasAsFaixas.add(new FaixaAnexo(Anexo.ANEXO_5, new BigDecimal("3600000.01"), new BigDecimal("4800000.00"), new BigDecimal("0.3050"), new BigDecimal("540000.00"), dataVigencia));

        // Salva tudo de uma vez em uma única transação
        faixaRepository.saveAll(todasAsFaixas);
    }
}