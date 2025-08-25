package com.devchaves.backend.repository;

import com.devchaves.backend.entity.FaixaAnexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface FaixaRepository extends JpaRepository<FaixaAnexo, Long> {

    @Query(value = "SELECT f FROM FaixaAnexo WHERE f.anexo_id = :anexo_id AND" +
            ":rbt12 BETWEEN f.receitaBrutaMin AND f.receitaBruxaMax")
    Optional<FaixaAnexo> faixaIdealDeCalculo(@Param("anexo_id")Long id);

}
