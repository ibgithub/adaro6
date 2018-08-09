package com.ib.adaro.service.mapper;

import com.ib.adaro.domain.*;
import com.ib.adaro.service.dto.StepsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Steps and its DTO StepsDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface StepsMapper extends EntityMapper<StepsDTO, Steps> {

    @Mapping(source = "stepsEmployee.id", target = "stepsEmployeeId")
    StepsDTO toDto(Steps steps);

    @Mapping(source = "stepsEmployeeId", target = "stepsEmployee")
    Steps toEntity(StepsDTO stepsDTO);

    default Steps fromId(Long id) {
        if (id == null) {
            return null;
        }
        Steps steps = new Steps();
        steps.setId(id);
        return steps;
    }
}
