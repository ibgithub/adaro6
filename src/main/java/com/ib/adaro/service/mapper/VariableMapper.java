package com.ib.adaro.service.mapper;

import com.ib.adaro.domain.*;
import com.ib.adaro.service.dto.VariableDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Variable and its DTO VariableDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VariableMapper extends EntityMapper<VariableDTO, Variable> {



    default Variable fromId(Long id) {
        if (id == null) {
            return null;
        }
        Variable variable = new Variable();
        variable.setId(id);
        return variable;
    }
}
