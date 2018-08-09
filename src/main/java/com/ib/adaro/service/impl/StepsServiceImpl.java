package com.ib.adaro.service.impl;

import com.ib.adaro.service.StepsService;
import com.ib.adaro.domain.Steps;
import com.ib.adaro.repository.StepsRepository;
import com.ib.adaro.service.dto.StepsDTO;
import com.ib.adaro.service.mapper.StepsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Steps.
 */
@Service
@Transactional
public class StepsServiceImpl implements StepsService {

    private final Logger log = LoggerFactory.getLogger(StepsServiceImpl.class);

    private final StepsRepository stepsRepository;

    private final StepsMapper stepsMapper;

    public StepsServiceImpl(StepsRepository stepsRepository, StepsMapper stepsMapper) {
        this.stepsRepository = stepsRepository;
        this.stepsMapper = stepsMapper;
    }

    /**
     * Save a steps.
     *
     * @param stepsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StepsDTO save(StepsDTO stepsDTO) {
        log.debug("Request to save Steps : {}", stepsDTO);
        Steps steps = stepsMapper.toEntity(stepsDTO);
        steps = stepsRepository.save(steps);
        return stepsMapper.toDto(steps);
    }

    /**
     * Get all the steps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StepsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Steps");
        return stepsRepository.findAll(pageable)
            .map(stepsMapper::toDto);
    }


    /**
     * Get one steps by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StepsDTO> findOne(Long id) {
        log.debug("Request to get Steps : {}", id);
        return stepsRepository.findById(id)
            .map(stepsMapper::toDto);
    }

    /**
     * Delete the steps by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Steps : {}", id);
        stepsRepository.deleteById(id);
    }
}
