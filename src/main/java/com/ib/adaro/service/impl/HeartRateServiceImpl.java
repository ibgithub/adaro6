package com.ib.adaro.service.impl;

import com.ib.adaro.service.HeartRateService;
import com.ib.adaro.domain.HeartRate;
import com.ib.adaro.repository.HeartRateRepository;
import com.ib.adaro.service.dto.HeartRateDTO;
import com.ib.adaro.service.mapper.HeartRateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing HeartRate.
 */
@Service
@Transactional
public class HeartRateServiceImpl implements HeartRateService {

    private final Logger log = LoggerFactory.getLogger(HeartRateServiceImpl.class);

    private final HeartRateRepository heartRateRepository;

    private final HeartRateMapper heartRateMapper;

    public HeartRateServiceImpl(HeartRateRepository heartRateRepository, HeartRateMapper heartRateMapper) {
        this.heartRateRepository = heartRateRepository;
        this.heartRateMapper = heartRateMapper;
    }

    /**
     * Save a heartRate.
     *
     * @param heartRateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HeartRateDTO save(HeartRateDTO heartRateDTO) {
        log.debug("Request to save HeartRate : {}", heartRateDTO);
        HeartRate heartRate = heartRateMapper.toEntity(heartRateDTO);
        heartRate = heartRateRepository.save(heartRate);
        return heartRateMapper.toDto(heartRate);
    }

    /**
     * Get all the heartRates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HeartRateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HeartRates");
        return heartRateRepository.findAll(pageable)
            .map(heartRateMapper::toDto);
    }


    /**
     * Get one heartRate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HeartRateDTO> findOne(Long id) {
        log.debug("Request to get HeartRate : {}", id);
        return heartRateRepository.findById(id)
            .map(heartRateMapper::toDto);
    }

    /**
     * Delete the heartRate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HeartRate : {}", id);
        heartRateRepository.deleteById(id);
    }
}
