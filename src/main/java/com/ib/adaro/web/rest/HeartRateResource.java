package com.ib.adaro.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ib.adaro.service.HeartRateService;
import com.ib.adaro.web.rest.errors.BadRequestAlertException;
import com.ib.adaro.web.rest.util.HeaderUtil;
import com.ib.adaro.web.rest.util.PaginationUtil;
import com.ib.adaro.service.dto.HeartRateDTO;
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
 * REST controller for managing HeartRate.
 */
@RestController
@RequestMapping("/api")
public class HeartRateResource {

    private final Logger log = LoggerFactory.getLogger(HeartRateResource.class);

    private static final String ENTITY_NAME = "heartRate";

    private final HeartRateService heartRateService;

    public HeartRateResource(HeartRateService heartRateService) {
        this.heartRateService = heartRateService;
    }

    /**
     * POST  /heart-rates : Create a new heartRate.
     *
     * @param heartRateDTO the heartRateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new heartRateDTO, or with status 400 (Bad Request) if the heartRate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/heart-rates")
    @Timed
    public ResponseEntity<HeartRateDTO> createHeartRate(@RequestBody HeartRateDTO heartRateDTO) throws URISyntaxException {
        log.debug("REST request to save HeartRate : {}", heartRateDTO);
        if (heartRateDTO.getId() != null) {
            throw new BadRequestAlertException("A new heartRate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HeartRateDTO result = heartRateService.save(heartRateDTO);
        return ResponseEntity.created(new URI("/api/heart-rates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /heart-rates : Updates an existing heartRate.
     *
     * @param heartRateDTO the heartRateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated heartRateDTO,
     * or with status 400 (Bad Request) if the heartRateDTO is not valid,
     * or with status 500 (Internal Server Error) if the heartRateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/heart-rates")
    @Timed
    public ResponseEntity<HeartRateDTO> updateHeartRate(@RequestBody HeartRateDTO heartRateDTO) throws URISyntaxException {
        log.debug("REST request to update HeartRate : {}", heartRateDTO);
        if (heartRateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HeartRateDTO result = heartRateService.save(heartRateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, heartRateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /heart-rates : get all the heartRates.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of heartRates in body
     */
    @GetMapping("/heart-rates")
    @Timed
    public ResponseEntity<List<HeartRateDTO>> getAllHeartRates(Pageable pageable) {
        log.debug("REST request to get a page of HeartRates");
        Page<HeartRateDTO> page = heartRateService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/heart-rates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /heart-rates/:id : get the "id" heartRate.
     *
     * @param id the id of the heartRateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the heartRateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/heart-rates/{id}")
    @Timed
    public ResponseEntity<HeartRateDTO> getHeartRate(@PathVariable Long id) {
        log.debug("REST request to get HeartRate : {}", id);
        Optional<HeartRateDTO> heartRateDTO = heartRateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(heartRateDTO);
    }

    /**
     * DELETE  /heart-rates/:id : delete the "id" heartRate.
     *
     * @param id the id of the heartRateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/heart-rates/{id}")
    @Timed
    public ResponseEntity<Void> deleteHeartRate(@PathVariable Long id) {
        log.debug("REST request to delete HeartRate : {}", id);
        heartRateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
