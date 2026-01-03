package com.api.locvac.repository;

import com.api.locvac.model.core.Fabricante;
import com.api.locvac.model.core.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    boolean existsByNmVacinaIgnoreCaseAndFabricanteAndDtFabricacao(String nome, Fabricante fabricante, LocalDate dataFabricacao);
}
