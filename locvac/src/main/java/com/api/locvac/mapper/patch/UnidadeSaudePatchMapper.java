package com.api.locvac.mapper.patch;

import com.api.locvac.dto.UnidadeSaudePatchDTO;
import com.api.locvac.mapper.config.PatchMapperConfig;
import com.api.locvac.model.core.UnidadeSaude;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = PatchMapperConfig.class)
public interface UnidadeSaudePatchMapper {

    void patch(UnidadeSaudePatchDTO dto,
               @MappingTarget UnidadeSaude entity);
}

