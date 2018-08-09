package com.ib.adaro.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Employee.
 */
@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "heart_rate")
    private Double heartRate;

    @Column(name = "steps_day")
    private Integer stepsDay;

    @Column(name = "ideal_heart_rate")
    private Double idealHeartRate;

    @Column(name = "ideal_steps_day")
    private Integer idealStepsDay;

    @OneToMany(mappedBy = "hREmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HeartRate> employeeHRS = new HashSet<>();

    @OneToMany(mappedBy = "stepsEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Steps> employeeSteps = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Employee code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Employee birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public Employee age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public Employee sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public Employee height(Integer height) {
        this.height = height;
        return this;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Employee weight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public Employee heartRate(Double heartRate) {
        this.heartRate = heartRate;
        return this;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getStepsDay() {
        return stepsDay;
    }

    public Employee stepsDay(Integer stepsDay) {
        this.stepsDay = stepsDay;
        return this;
    }

    public void setStepsDay(Integer stepsDay) {
        this.stepsDay = stepsDay;
    }

    public Double getIdealHeartRate() {
        return idealHeartRate;
    }

    public Employee idealHeartRate(Double idealHeartRate) {
        this.idealHeartRate = idealHeartRate;
        return this;
    }

    public void setIdealHeartRate(Double idealHeartRate) {
        this.idealHeartRate = idealHeartRate;
    }

    public Integer getIdealStepsDay() {
        return idealStepsDay;
    }

    public Employee idealStepsDay(Integer idealStepsDay) {
        this.idealStepsDay = idealStepsDay;
        return this;
    }

    public void setIdealStepsDay(Integer idealStepsDay) {
        this.idealStepsDay = idealStepsDay;
    }

    public Set<HeartRate> getEmployeeHRS() {
        return employeeHRS;
    }

    public Employee employeeHRS(Set<HeartRate> heartRates) {
        this.employeeHRS = heartRates;
        return this;
    }

    public Employee addEmployeeHR(HeartRate heartRate) {
        this.employeeHRS.add(heartRate);
        heartRate.setHREmployee(this);
        return this;
    }

    public Employee removeEmployeeHR(HeartRate heartRate) {
        this.employeeHRS.remove(heartRate);
        heartRate.setHREmployee(null);
        return this;
    }

    public void setEmployeeHRS(Set<HeartRate> heartRates) {
        this.employeeHRS = heartRates;
    }

    public Set<Steps> getEmployeeSteps() {
        return employeeSteps;
    }

    public Employee employeeSteps(Set<Steps> steps) {
        this.employeeSteps = steps;
        return this;
    }

    public Employee addEmployeeSteps(Steps steps) {
        this.employeeSteps.add(steps);
        steps.setStepsEmployee(this);
        return this;
    }

    public Employee removeEmployeeSteps(Steps steps) {
        this.employeeSteps.remove(steps);
        steps.setStepsEmployee(null);
        return this;
    }

    public void setEmployeeSteps(Set<Steps> steps) {
        this.employeeSteps = steps;
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
        Employee employee = (Employee) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", age=" + getAge() +
            ", sex='" + getSex() + "'" +
            ", height=" + getHeight() +
            ", weight=" + getWeight() +
            ", heartRate=" + getHeartRate() +
            ", stepsDay=" + getStepsDay() +
            ", idealHeartRate=" + getIdealHeartRate() +
            ", idealStepsDay=" + getIdealStepsDay() +
            "}";
    }
}
