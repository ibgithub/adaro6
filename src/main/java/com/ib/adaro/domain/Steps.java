package com.ib.adaro.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Steps.
 */
@Entity
@Table(name = "steps")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Steps implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_time")
    private ZonedDateTime dateTime;

    @Column(name = "steps_count")
    private Long stepsCount;

    @ManyToOne
    @JsonIgnoreProperties("employeeSteps")
    private Employee stepsEmployee;

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

    public Steps dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Long getStepsCount() {
        return stepsCount;
    }

    public Steps stepsCount(Long stepsCount) {
        this.stepsCount = stepsCount;
        return this;
    }

    public void setStepsCount(Long stepsCount) {
        this.stepsCount = stepsCount;
    }

    public Employee getStepsEmployee() {
        return stepsEmployee;
    }

    public Steps stepsEmployee(Employee employee) {
        this.stepsEmployee = employee;
        return this;
    }

    public void setStepsEmployee(Employee employee) {
        this.stepsEmployee = employee;
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
        Steps steps = (Steps) o;
        if (steps.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), steps.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Steps{" +
            "id=" + getId() +
            ", dateTime='" + getDateTime() + "'" +
            ", stepsCount=" + getStepsCount() +
            "}";
    }
}
