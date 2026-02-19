package com.api.locvac.repository;

import com.api.locvac.model.core.Campanha;
import com.api.locvac.model.core.TipoVacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    Optional<Campanha> findCampanhaByNmCampanhaContainingIgnoreCase(String nmCampanha);
    boolean existsByNmCampanhaAndTipoVacinaAndDtComecoCampanhaAndDtFimCampanhaAndCdCampanhaNot
            (String nmCampanha,
             TipoVacina tipoVacina,
             LocalDate dtComecoCampanha,
             LocalDate dtFimCampanha,
             Long cdCampanha);

}
