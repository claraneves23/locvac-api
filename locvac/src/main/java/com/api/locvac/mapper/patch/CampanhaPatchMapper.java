package com.api.locvac.mapper.patch;

import com.api.locvac.dto.CampanhaPatchDTO;
import com.api.locvac.mapper.config.PatchMapperConfig;
import com.api.locvac.model.core.Campanha;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = PatchMapperConfig.class)
public interface CampanhaPatchMapper {
    void patch(CampanhaPatchDTO dto,
               @MappingTarget Campanha entity);
}
