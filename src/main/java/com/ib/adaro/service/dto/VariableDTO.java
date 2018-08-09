package com.ib.adaro.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Variable entity.
 */
public class VariableDTO implements Serializable {

    private Long id;

    private String name;

    private String value;

    private String desc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VariableDTO variableDTO = (VariableDTO) o;
        if (variableDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), variableDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VariableDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
