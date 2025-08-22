package com.devchaves.backend.util;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class BigDecimalUtil {

    /**
     * Escala padrão para valores monetários (Reais, Dólares, etc.).
     * Ex: 1234.56
     */
    public static final int SCALE_MONETARIO = 2;

    /**
     * Escala para alíquotas e percentuais, que exigem maior precisão.
     * Ex: 0.0730 (representando 7,30%)
     */
    public static final int SCALE_PERCENTUAL = 4;

    /**
     * Modo de arredondamento padrão para a maioria das operações financeiras.
     * HALF_UP: Arredonda para o "vizinho mais próximo". Se equidistante, arredonda para cima.
     * É o modo de arredondamento mais comum e intuitivo.
     */
    public static final RoundingMode ROUNDING_MODE_PADRAO = RoundingMode.HALF_UP;


    public static BigDecimal toMonetario(String valor){

        if(valor == null || valor.isBlank()){
            return BigDecimal.ZERO.setScale(SCALE_MONETARIO, ROUNDING_MODE_PADRAO);
        }

        return new BigDecimal(valor).setScale(SCALE_MONETARIO, ROUNDING_MODE_PADRAO);
    }

    public static BigDecimal toMonetario(BigDecimal valor){

        if(valor == null){
            return BigDecimal.ZERO.setScale(SCALE_MONETARIO, ROUNDING_MODE_PADRAO);
        }

        return valor.setScale(SCALE_MONETARIO, ROUNDING_MODE_PADRAO);

    }

    // Operações

    public static BigDecimal multiplicar(BigDecimal a, BigDecimal b){
        return a.multiply(b).setScale(SCALE_MONETARIO, ROUNDING_MODE_PADRAO);
    }

    public static BigDecimal dividir(BigDecimal dividendo, BigDecimal divisor){

        if(divisor.compareTo(BigDecimal.ZERO) == 0){
            throw new IllegalArgumentException("Divisor não pode ser zero");
        }

        return dividendo.divide(divisor,SCALE_MONETARIO, ROUNDING_MODE_PADRAO);
    }

    public static BigDecimal adicao(BigDecimal a, BigDecimal b){
        return a.add(b).setScale(SCALE_MONETARIO, ROUNDING_MODE_PADRAO);
    }

    public static BigDecimal subtracao(BigDecimal a, BigDecimal b){
        return a.subtract(b).setScale(SCALE_MONETARIO, ROUNDING_MODE_PADRAO);
    }


}
