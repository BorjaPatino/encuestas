package com.iesjuanbosco.encuestas.repository;

import com.iesjuanbosco.encuestas.entity.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {
}

