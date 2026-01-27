package com.api.locvac.repository;

import com.api.locvac.model.core.TipoVacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoVacinaRepository extends JpaRepository<TipoVacina, Long> {

    Optional<TipoVacina> findByNmVacinaContainingIgnoreCase(String nmVacina);
}
