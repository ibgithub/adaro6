package com.ib.adaro.web.rest;

import com.ib.adaro.Adaro6App;

import com.ib.adaro.domain.HeartRate;
import com.ib.adaro.repository.HeartRateRepository;
import com.ib.adaro.service.HeartRateService;
import com.ib.adaro.service.dto.HeartRateDTO;
import com.ib.adaro.service.mapper.HeartRateMapper;
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
 * Test class for the HeartRateResource REST controller.
 *
 * @see HeartRateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Adaro6App.class)
public class HeartRateResourceIntTest {

    private static final ZonedDateTime DEFAULT_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Double DEFAULT_HEART_RATE = 1D;
    private static final Double UPDATED_HEART_RATE = 2D;

    @Autowired
    private HeartRateRepository heartRateRepository;


    @Autowired
    private HeartRateMapper heartRateMapper;
    

    @Autowired
    private HeartRateService heartRateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHeartRateMockMvc;

    private HeartRate heartRate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HeartRateResource heartRateResource = new HeartRateResource(heartRateService);
        this.restHeartRateMockMvc = MockMvcBuilders.standaloneSetup(heartRateResource)
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
    public static HeartRate createEntity(EntityManager em) {
        HeartRate heartRate = new HeartRate()
            .dateTime(DEFAULT_DATE_TIME)
            .heartRate(DEFAULT_HEART_RATE);
        return heartRate;
    }

    @Before
    public void initTest() {
        heartRate = createEntity(em);
    }

    @Test
    @Transactional
    public void createHeartRate() throws Exception {
        int databaseSizeBeforeCreate = heartRateRepository.findAll().size();

        // Create the HeartRate
        HeartRateDTO heartRateDTO = heartRateMapper.toDto(heartRate);
        restHeartRateMockMvc.perform(post("/api/heart-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(heartRateDTO)))
            .andExpect(status().isCreated());

        // Validate the HeartRate in the database
        List<HeartRate> heartRateList = heartRateRepository.findAll();
        assertThat(heartRateList).hasSize(databaseSizeBeforeCreate + 1);
        HeartRate testHeartRate = heartRateList.get(heartRateList.size() - 1);
        assertThat(testHeartRate.getDateTime()).isEqualTo(DEFAULT_DATE_TIME);
        assertThat(testHeartRate.getHeartRate()).isEqualTo(DEFAULT_HEART_RATE);
    }

    @Test
    @Transactional
    public void createHeartRateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = heartRateRepository.findAll().size();

        // Create the HeartRate with an existing ID
        heartRate.setId(1L);
        HeartRateDTO heartRateDTO = heartRateMapper.toDto(heartRate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHeartRateMockMvc.perform(post("/api/heart-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(heartRateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HeartRate in the database
        List<HeartRate> heartRateList = heartRateRepository.findAll();
        assertThat(heartRateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHeartRates() throws Exception {
        // Initialize the database
        heartRateRepository.saveAndFlush(heartRate);

        // Get all the heartRateList
        restHeartRateMockMvc.perform(get("/api/heart-rates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(heartRate.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateTime").value(hasItem(sameInstant(DEFAULT_DATE_TIME))))
            .andExpect(jsonPath("$.[*].heartRate").value(hasItem(DEFAULT_HEART_RATE.doubleValue())));
    }
    

    @Test
    @Transactional
    public void getHeartRate() throws Exception {
        // Initialize the database
        heartRateRepository.saveAndFlush(heartRate);

        // Get the heartRate
        restHeartRateMockMvc.perform(get("/api/heart-rates/{id}", heartRate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(heartRate.getId().intValue()))
            .andExpect(jsonPath("$.dateTime").value(sameInstant(DEFAULT_DATE_TIME)))
            .andExpect(jsonPath("$.heartRate").value(DEFAULT_HEART_RATE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingHeartRate() throws Exception {
        // Get the heartRate
        restHeartRateMockMvc.perform(get("/api/heart-rates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHeartRate() throws Exception {
        // Initialize the database
        heartRateRepository.saveAndFlush(heartRate);

        int databaseSizeBeforeUpdate = heartRateRepository.findAll().size();

        // Update the heartRate
        HeartRate updatedHeartRate = heartRateRepository.findById(heartRate.getId()).get();
        // Disconnect from session so that the updates on updatedHeartRate are not directly saved in db
        em.detach(updatedHeartRate);
        updatedHeartRate
            .dateTime(UPDATED_DATE_TIME)
            .heartRate(UPDATED_HEART_RATE);
        HeartRateDTO heartRateDTO = heartRateMapper.toDto(updatedHeartRate);

        restHeartRateMockMvc.perform(put("/api/heart-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(heartRateDTO)))
            .andExpect(status().isOk());

        // Validate the HeartRate in the database
        List<HeartRate> heartRateList = heartRateRepository.findAll();
        assertThat(heartRateList).hasSize(databaseSizeBeforeUpdate);
        HeartRate testHeartRate = heartRateList.get(heartRateList.size() - 1);
        assertThat(testHeartRate.getDateTime()).isEqualTo(UPDATED_DATE_TIME);
        assertThat(testHeartRate.getHeartRate()).isEqualTo(UPDATED_HEART_RATE);
    }

    @Test
    @Transactional
    public void updateNonExistingHeartRate() throws Exception {
        int databaseSizeBeforeUpdate = heartRateRepository.findAll().size();

        // Create the HeartRate
        HeartRateDTO heartRateDTO = heartRateMapper.toDto(heartRate);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHeartRateMockMvc.perform(put("/api/heart-rates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(heartRateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HeartRate in the database
        List<HeartRate> heartRateList = heartRateRepository.findAll();
        assertThat(heartRateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHeartRate() throws Exception {
        // Initialize the database
        heartRateRepository.saveAndFlush(heartRate);

        int databaseSizeBeforeDelete = heartRateRepository.findAll().size();

        // Get the heartRate
        restHeartRateMockMvc.perform(delete("/api/heart-rates/{id}", heartRate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HeartRate> heartRateList = heartRateRepository.findAll();
        assertThat(heartRateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HeartRate.class);
        HeartRate heartRate1 = new HeartRate();
        heartRate1.setId(1L);
        HeartRate heartRate2 = new HeartRate();
        heartRate2.setId(heartRate1.getId());
        assertThat(heartRate1).isEqualTo(heartRate2);
        heartRate2.setId(2L);
        assertThat(heartRate1).isNotEqualTo(heartRate2);
        heartRate1.setId(null);
        assertThat(heartRate1).isNotEqualTo(heartRate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HeartRateDTO.class);
        HeartRateDTO heartRateDTO1 = new HeartRateDTO();
        heartRateDTO1.setId(1L);
        HeartRateDTO heartRateDTO2 = new HeartRateDTO();
        assertThat(heartRateDTO1).isNotEqualTo(heartRateDTO2);
        heartRateDTO2.setId(heartRateDTO1.getId());
        assertThat(heartRateDTO1).isEqualTo(heartRateDTO2);
        heartRateDTO2.setId(2L);
        assertThat(heartRateDTO1).isNotEqualTo(heartRateDTO2);
        heartRateDTO1.setId(null);
        assertThat(heartRateDTO1).isNotEqualTo(heartRateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(heartRateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(heartRateMapper.fromId(null)).isNull();
    }
}
