package com.ib.adaro.service.impl;

import com.ib.adaro.service.VariableService;
import com.ib.adaro.domain.Variable;
import com.ib.adaro.repository.VariableRepository;
import com.ib.adaro.service.dto.VariableDTO;
import com.ib.adaro.service.mapper.VariableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Variable.
 */
@Service
@Transactional
public class VariableServiceImpl implements VariableService {

    private final Logger log = LoggerFactory.getLogger(VariableServiceImpl.class);

    private final VariableRepository variableRepository;

    private final VariableMapper variableMapper;

    public VariableServiceImpl(VariableRepository variableRepository, VariableMapper variableMapper) {
        this.variableRepository = variableRepository;
        this.variableMapper = variableMapper;
    }

    /**
     * Save a variable.
     *
     * @param variableDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VariableDTO save(VariableDTO variableDTO) {
        log.debug("Request to save Variable : {}", variableDTO);
        Variable variable = variableMapper.toEntity(variableDTO);
        variable = variableRepository.save(variable);
        return variableMapper.toDto(variable);
    }

    /**
     * Get all the variables.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VariableDTO> findAll() {
        log.debug("Request to get all Variables");
        return variableRepository.findAll().stream()
            .map(variableMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one variable by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VariableDTO> findOne(Long id) {
        log.debug("Request to get Variable : {}", id);
        return variableRepository.findById(id)
            .map(variableMapper::toDto);
    }

    /**
     * Delete the variable by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Variable : {}", id);
        variableRepository.deleteById(id);
    }
}
