package com.api.locvac.repository;

import com.api.locvac.model.core.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

    Optional<Campanha> findCampanhaByNmCampanhaContainingIgnoreCase(String nmCampanha);
}
