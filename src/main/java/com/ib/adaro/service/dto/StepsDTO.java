package com.ib.adaro.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Steps entity.
 */
public class StepsDTO implements Serializable {

    private Long id;

    private ZonedDateTime dateTime;

    private Long heartRate;

    private Long stepsEmployeeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Long heartRate) {
        this.heartRate = heartRate;
    }

    public Long getStepsEmployeeId() {
        return stepsEmployeeId;
    }

    public void setStepsEmployeeId(Long employeeId) {
        this.stepsEmployeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StepsDTO stepsDTO = (StepsDTO) o;
        if (stepsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stepsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StepsDTO{" +
            "id=" + getId() +
            ", dateTime='" + getDateTime() + "'" +
            ", heartRate=" + getHeartRate() +
            ", stepsEmployee=" + getStepsEmployeeId() +
            "}";
    }
}
