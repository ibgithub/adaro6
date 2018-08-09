package com.ib.adaro.service.mapper;

import com.ib.adaro.domain.*;
import com.ib.adaro.service.dto.HeartRateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity HeartRate and its DTO HeartRateDTO.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface HeartRateMapper extends EntityMapper<HeartRateDTO, HeartRate> {

    @Mapping(source = "HREmployee.id", target = "HREmployeeId")
    HeartRateDTO toDto(HeartRate heartRate);

    @Mapping(source = "HREmployeeId", target = "HREmployee")
    HeartRate toEntity(HeartRateDTO heartRateDTO);

    default HeartRate fromId(Long id) {
        if (id == null) {
            return null;
        }
        HeartRate heartRate = new HeartRate();
        heartRate.setId(id);
        return heartRate;
    }
}
