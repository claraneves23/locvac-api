package com.api.locvac.mapper.patch;

import com.api.locvac.dto.VacinaPatchDTO;
import com.api.locvac.mapper.config.PatchMapperConfig;
import com.api.locvac.model.core.Vacina;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = PatchMapperConfig.class)

public interface VacinaPatchMapper {
    void patch(VacinaPatchDTO dto,
               @MappingTarget Vacina entity);
}
