package com.api.locvac.repository;

import com.api.locvac.model.core.Fabricante;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.core.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {

    boolean existsByTipoVacinaAndFabricanteAndDtFabricacaoAndIdNot(
            TipoVacina tipoVacina,
            Fabricante fabricante,
            LocalDate dtFabricacao,
            Long id
    );



}
