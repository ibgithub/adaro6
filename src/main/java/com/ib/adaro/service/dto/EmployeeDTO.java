package com.ib.adaro.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Employee entity.
 */
public class EmployeeDTO implements Serializable {

    private Long id;

    private String code;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private LocalDate birthDate;

    private Integer age;

    private String sex;

    private Integer height;

    private Integer weight;

    private Double heartRate;

    private Integer stepsDay;

    private Double idealHeartRate;

    private Integer idealStepsDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getStepsDay() {
        return stepsDay;
    }

    public void setStepsDay(Integer stepsDay) {
        this.stepsDay = stepsDay;
    }

    public Double getIdealHeartRate() {
        return idealHeartRate;
    }

    public void setIdealHeartRate(Double idealHeartRate) {
        this.idealHeartRate = idealHeartRate;
    }

    public Integer getIdealStepsDay() {
        return idealStepsDay;
    }

    public void setIdealStepsDay(Integer idealStepsDay) {
        this.idealStepsDay = idealStepsDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EmployeeDTO employeeDTO = (EmployeeDTO) o;
        if (employeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
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
