package com.api.locvac.mapper.patch;

import com.api.locvac.dto.TipoVacinaPatchDTO;
import com.api.locvac.mapper.config.PatchMapperConfig;
import com.api.locvac.model.core.TipoVacina;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = PatchMapperConfig.class)
public interface TipoVacinaPatchMapper {
    void patch(TipoVacinaPatchDTO dto,
               @MappingTarget TipoVacina entity);
}

