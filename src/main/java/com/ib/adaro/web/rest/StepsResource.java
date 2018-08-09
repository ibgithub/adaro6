package com.ib.adaro.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ib.adaro.service.StepsService;
import com.ib.adaro.web.rest.errors.BadRequestAlertException;
import com.ib.adaro.web.rest.util.HeaderUtil;
import com.ib.adaro.web.rest.util.PaginationUtil;
import com.ib.adaro.service.dto.StepsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Steps.
 */
@RestController
@RequestMapping("/api")
public class StepsResource {

    private final Logger log = LoggerFactory.getLogger(StepsResource.class);

    private static final String ENTITY_NAME = "steps";

    private final StepsService stepsService;

    public StepsResource(StepsService stepsService) {
        this.stepsService = stepsService;
    }

    /**
     * POST  /steps : Create a new steps.
     *
     * @param stepsDTO the stepsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stepsDTO, or with status 400 (Bad Request) if the steps has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/steps")
    @Timed
    public ResponseEntity<StepsDTO> createSteps(@RequestBody StepsDTO stepsDTO) throws URISyntaxException {
        log.debug("REST request to save Steps : {}", stepsDTO);
        if (stepsDTO.getId() != null) {
            throw new BadRequestAlertException("A new steps cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StepsDTO result = stepsService.save(stepsDTO);
        return ResponseEntity.created(new URI("/api/steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /steps : Updates an existing steps.
     *
     * @param stepsDTO the stepsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stepsDTO,
     * or with status 400 (Bad Request) if the stepsDTO is not valid,
     * or with status 500 (Internal Server Error) if the stepsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/steps")
    @Timed
    public ResponseEntity<StepsDTO> updateSteps(@RequestBody StepsDTO stepsDTO) throws URISyntaxException {
        log.debug("REST request to update Steps : {}", stepsDTO);
        if (stepsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StepsDTO result = stepsService.save(stepsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stepsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /steps : get all the steps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of steps in body
     */
    @GetMapping("/steps")
    @Timed
    public ResponseEntity<List<StepsDTO>> getAllSteps(Pageable pageable) {
        log.debug("REST request to get a page of Steps");
        Page<StepsDTO> page = stepsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/steps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /steps/:id : get the "id" steps.
     *
     * @param id the id of the stepsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stepsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/steps/{id}")
    @Timed
    public ResponseEntity<StepsDTO> getSteps(@PathVariable Long id) {
        log.debug("REST request to get Steps : {}", id);
        Optional<StepsDTO> stepsDTO = stepsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stepsDTO);
    }

    /**
     * DELETE  /steps/:id : delete the "id" steps.
     *
     * @param id the id of the stepsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteSteps(@PathVariable Long id) {
        log.debug("REST request to delete Steps : {}", id);
        stepsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
