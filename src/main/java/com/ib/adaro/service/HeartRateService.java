package com.ib.adaro.service;

import com.ib.adaro.service.dto.HeartRateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing HeartRate.
 */
public interface HeartRateService {

    /**
     * Save a heartRate.
     *
     * @param heartRateDTO the entity to save
     * @return the persisted entity
     */
    HeartRateDTO save(HeartRateDTO heartRateDTO);

    /**
     * Get all the heartRates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HeartRateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" heartRate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HeartRateDTO> findOne(Long id);

    /**
     * Delete the "id" heartRate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
