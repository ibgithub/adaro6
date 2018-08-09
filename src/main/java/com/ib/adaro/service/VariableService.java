package com.ib.adaro.service;

import com.ib.adaro.service.dto.VariableDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Variable.
 */
public interface VariableService {

    /**
     * Save a variable.
     *
     * @param variableDTO the entity to save
     * @return the persisted entity
     */
    VariableDTO save(VariableDTO variableDTO);

    /**
     * Get all the variables.
     *
     * @return the list of entities
     */
    List<VariableDTO> findAll();


    /**
     * Get the "id" variable.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VariableDTO> findOne(Long id);

    /**
     * Delete the "id" variable.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
