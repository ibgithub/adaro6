package com.ib.adaro.service;

import com.ib.adaro.service.dto.StepsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Steps.
 */
public interface StepsService {

    /**
     * Save a steps.
     *
     * @param stepsDTO the entity to save
     * @return the persisted entity
     */
    StepsDTO save(StepsDTO stepsDTO);

    /**
     * Get all the steps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StepsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" steps.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StepsDTO> findOne(Long id);

    /**
     * Delete the "id" steps.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
