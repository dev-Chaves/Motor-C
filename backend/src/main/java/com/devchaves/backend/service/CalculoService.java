package com.devchaves.backend.service;

import com.devchaves.backend.dto.CalculoRequest;
import com.devchaves.backend.util.BigDecimalUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class CalculoService {

    public BigDecimal calculoDasModeloPreReforma(CalculoRequest dto){

        BigDecimal rpa = BigDecimalUtil.toMonetario(dto.rpa());
        BigDecimal rbt12 = BigDecimalUtil.toMonetario(dto.rbt12());


        BigDecimal aliquotaAfetiva = new BigDecimal(BigInteger.ONE);

        return null;
    }

}
