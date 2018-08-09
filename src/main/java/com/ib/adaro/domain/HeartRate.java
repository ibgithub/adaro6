package com.ib.adaro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A HeartRate.
 */
@Entity
@Table(name = "heart_rate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HeartRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_time")
    private ZonedDateTime dateTime;

    @Column(name = "heart_rate")
    private Double heartRate;

    @ManyToOne
    @JsonIgnoreProperties("employeeHRS")
    private Employee hREmployee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public HeartRate dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public HeartRate heartRate(Double heartRate) {
        this.heartRate = heartRate;
        return this;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Employee getHREmployee() {
        return hREmployee;
    }

    public HeartRate hREmployee(Employee employee) {
        this.hREmployee = employee;
        return this;
    }

    public void setHREmployee(Employee employee) {
        this.hREmployee = employee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HeartRate heartRate = (HeartRate) o;
        if (heartRate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), heartRate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HeartRate{" +
            "id=" + getId() +
            ", dateTime='" + getDateTime() + "'" +
            ", heartRate=" + getHeartRate() +
            "}";
    }
}
