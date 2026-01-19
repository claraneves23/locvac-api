package com.api.locvac.repository;

import com.api.locvac.model.core.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
}
