package com.ib.adaro.web.rest;

import com.ib.adaro.Adaro6App;

import com.ib.adaro.domain.Steps;
import com.ib.adaro.repository.StepsRepository;
import com.ib.adaro.service.StepsService;
import com.ib.adaro.service.dto.StepsDTO;
import com.ib.adaro.service.mapper.StepsMapper;
import com.ib.adaro.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.ib.adaro.web.rest.TestUtil.sameInstant;
import static com.ib.adaro.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the StepsResource REST controller.
 *
 * @see StepsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Adaro6App.class)
public class StepsResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_STEPS_COUNT = 1L;
    private static final Long UPDATED_STEPS_COUNT = 2L;

    @Autowired
    private StepsRepository stepsRepository;


    @Autowired
    private StepsMapper stepsMapper;
    

    @Autowired
    private StepsService stepsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStepsMockMvc;

    private Steps steps;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StepsResource stepsResource = new StepsResource(stepsService);
        this.restStepsMockMvc = MockMvcBuilders.standaloneSetup(stepsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Steps createEntity(EntityManager em) {
        Steps steps = new Steps()
            .dateTime(DEFAULT_DATE_TIME)
            .stepsCount(DEFAULT_STEPS_COUNT);
        return steps;
    }

    @Before
    public void initTest() {
        steps = createEntity(em);
    }

    @Test
    @Transactional
    public void createSteps() throws Exception {
        int databaseSizeBeforeCreate = stepsRepository.findAll().size();

        // Create the Steps
        StepsDTO stepsDTO = stepsMapper.toDto(steps);
        restStepsMockMvc.perform(post("/api/steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stepsDTO)))
            .andExpect(status().isCreated());

        // Validate the Steps in the database
        List<Steps> stepsList = stepsRepository.findAll();
        assertThat(stepsList).hasSize(databaseSizeBeforeCreate + 1);
        Steps testSteps = stepsList.get(stepsList.size() - 1);
        assertThat(testSteps.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testSteps.getStepsCount()).isEqualTo(DEFAULT_STEPS_COUNT);
    }

    @Test
    @Transactional
    public void createStepsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stepsRepository.findAll().size();

        // Create the Steps with an existing ID
        steps.setId(1L);
        StepsDTO stepsDTO = stepsMapper.toDto(steps);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStepsMockMvc.perform(post("/api/steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stepsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Steps in the database
        List<Steps> stepsList = stepsRepository.findAll();
        assertThat(stepsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSteps() throws Exception {
        // Initialize the database
        stepsRepository.saveAndFlush(steps);

        // Get all the stepsList
        restStepsMockMvc.perform(get("/api/steps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(steps.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(sameInstant(DEFAULT_DATE_TIME))))
            .andExpect(jsonPath("$.[*].stepsCount").value(hasItem(DEFAULT_STEPS_COUNT.intValue())));
    }
    

    @Test
    @Transactional
    public void getSteps() throws Exception {
        // Initialize the database
        stepsRepository.saveAndFlush(steps);

        // Get the steps
        restStepsMockMvc.perform(get("/api/steps/{id}", steps.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(steps.getId().intValue()))
            .andExpect(jsonPath("$.dateTime").value(sameInstant(DEFAULT_DATE_TIME)))
            .andExpect(jsonPath("$.stepsCount").value(DEFAULT_STEPS_COUNT.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingSteps() throws Exception {
        // Get the steps
        restStepsMockMvc.perform(get("/api/steps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSteps() throws Exception {
        // Initialize the database
        stepsRepository.saveAndFlush(steps);

        int databaseSizeBeforeUpdate = stepsRepository.findAll().size();

        // Update the steps
        Steps updatedSteps = stepsRepository.findById(steps.getId()).get();
        // Disconnect from session so that the updates on updatedSteps are not directly saved in db
        em.detach(updatedSteps);
        updatedSteps
            .dateTime(UPDATED_DATE_TIME)
            .stepsCount(UPDATED_STEPS_COUNT);
        StepsDTO stepsDTO = stepsMapper.toDto(updatedSteps);

        restStepsMockMvc.perform(put("/api/steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stepsDTO)))
            .andExpect(status().isOk());

        // Validate the Steps in the database
        List<Steps> stepsList = stepsRepository.findAll();
        assertThat(stepsList).hasSize(databaseSizeBeforeUpdate);
        Steps testSteps = stepsList.get(stepsList.size() - 1);
        assertThat(testSteps.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testSteps.getStepsCount()).isEqualTo(UPDATED_STEPS_COUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingSteps() throws Exception {
        int databaseSizeBeforeUpdate = stepsRepository.findAll().size();

        // Create the Steps
        StepsDTO stepsDTO = stepsMapper.toDto(steps);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStepsMockMvc.perform(put("/api/steps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stepsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Steps in the database
        List<Steps> stepsList = stepsRepository.findAll();
        assertThat(stepsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSteps() throws Exception {
        // Initialize the database
        stepsRepository.saveAndFlush(steps);

        int databaseSizeBeforeDelete = stepsRepository.findAll().size();

        // Get the steps
        restStepsMockMvc.perform(delete("/api/steps/{id}", steps.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Steps> stepsList = stepsRepository.findAll();
        assertThat(stepsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Steps.class);
        Steps steps1 = new Steps();
        steps1.setId(1L);
        Steps steps2 = new Steps();
        steps2.setId(steps1.getId());
        assertThat(steps1).isEqualTo(steps2);
        steps2.setId(2L);
        assertThat(steps1).isNotEqualTo(steps2);
        steps1.setId(null);
        assertThat(steps1).isNotEqualTo(steps2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StepsDTO.class);
        StepsDTO stepsDTO1 = new StepsDTO();
        stepsDTO1.setId(1L);
        StepsDTO stepsDTO2 = new StepsDTO();
        assertThat(stepsDTO1).isNotEqualTo(stepsDTO2);
        stepsDTO2.setId(stepsDTO1.getId());
        assertThat(stepsDTO1).isEqualTo(stepsDTO2);
        stepsDTO2.setId(2L);
        assertThat(stepsDTO1).isNotEqualTo(stepsDTO2);
        stepsDTO1.setId(null);
        assertThat(stepsDTO1).isNotEqualTo(stepsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(stepsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(stepsMapper.fromId(null)).isNull();
    }
}
