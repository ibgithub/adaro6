package com.ib.adaro.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the HeartRate entity.
 */
public class HeartRateDTO implements Serializable {

    private Long id;

    private ZonedDateTime dateTime;

    private Double heartRate;

    private Long hREmployeeId;

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

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Long getHREmployeeId() {
        return hREmployeeId;
    }

    public void setHREmployeeId(Long employeeId) {
        this.hREmployeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HeartRateDTO heartRateDTO = (HeartRateDTO) o;
        if (heartRateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), heartRateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HeartRateDTO{" +
            "id=" + getId() +
            ", dateTime='" + getDateTime() + "'" +
            ", heartRate=" + getHeartRate() +
            ", hREmployee=" + getHREmployeeId() +
            "}";
    }
}
