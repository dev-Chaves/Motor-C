package com.devchaves.backend.repository;

import com.devchaves.backend.entity.FaixaAnexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaixaRepository extends JpaRepository<FaixaAnexo, Long> {
}
