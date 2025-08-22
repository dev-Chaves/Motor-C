package com.devchaves.backend.repository;

import com.devchaves.backend.entity.Calculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculoRepository extends JpaRepository<Calculo, Long> {
}
