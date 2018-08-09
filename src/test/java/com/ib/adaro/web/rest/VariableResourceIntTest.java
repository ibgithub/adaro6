package com.ib.adaro.web.rest;

import com.ib.adaro.Adaro6App;

import com.ib.adaro.domain.Variable;
import com.ib.adaro.repository.VariableRepository;
import com.ib.adaro.service.VariableService;
import com.ib.adaro.service.dto.VariableDTO;
import com.ib.adaro.service.mapper.VariableMapper;
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
import java.util.List;


import static com.ib.adaro.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VariableResource REST controller.
 *
 * @see VariableResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Adaro6App.class)
public class VariableResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private VariableRepository variableRepository;


    @Autowired
    private VariableMapper variableMapper;
    

    @Autowired
    private VariableService variableService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVariableMockMvc;

    private Variable variable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VariableResource variableResource = new VariableResource(variableService);
        this.restVariableMockMvc = MockMvcBuilders.standaloneSetup(variableResource)
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
    public static Variable createEntity(EntityManager em) {
        Variable variable = new Variable()
            .name(DEFAULT_NAME)
            .value(DEFAULT_VALUE)
            .desc(DEFAULT_DESC);
        return variable;
    }

    @Before
    public void initTest() {
        variable = createEntity(em);
    }

    @Test
    @Transactional
    public void createVariable() throws Exception {
        int databaseSizeBeforeCreate = variableRepository.findAll().size();

        // Create the Variable
        VariableDTO variableDTO = variableMapper.toDto(variable);
        restVariableMockMvc.perform(post("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isCreated());

        // Validate the Variable in the database
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeCreate + 1);
        Variable testVariable = variableList.get(variableList.size() - 1);
        assertThat(testVariable.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVariable.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testVariable.getDesc()).isEqualTo(DEFAULT_DESC);
    }

    @Test
    @Transactional
    public void createVariableWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = variableRepository.findAll().size();

        // Create the Variable with an existing ID
        variable.setId(1L);
        VariableDTO variableDTO = variableMapper.toDto(variable);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVariableMockMvc.perform(post("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Variable in the database
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVariables() throws Exception {
        // Initialize the database
        variableRepository.saveAndFlush(variable);

        // Get all the variableList
        restVariableMockMvc.perform(get("/api/variables?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(variable.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())));
    }
    

    @Test
    @Transactional
    public void getVariable() throws Exception {
        // Initialize the database
        variableRepository.saveAndFlush(variable);

        // Get the variable
        restVariableMockMvc.perform(get("/api/variables/{id}", variable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(variable.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingVariable() throws Exception {
        // Get the variable
        restVariableMockMvc.perform(get("/api/variables/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVariable() throws Exception {
        // Initialize the database
        variableRepository.saveAndFlush(variable);

        int databaseSizeBeforeUpdate = variableRepository.findAll().size();

        // Update the variable
        Variable updatedVariable = variableRepository.findById(variable.getId()).get();
        // Disconnect from session so that the updates on updatedVariable are not directly saved in db
        em.detach(updatedVariable);
        updatedVariable
            .name(UPDATED_NAME)
            .value(UPDATED_VALUE)
            .desc(UPDATED_DESC);
        VariableDTO variableDTO = variableMapper.toDto(updatedVariable);

        restVariableMockMvc.perform(put("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isOk());

        // Validate the Variable in the database
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeUpdate);
        Variable testVariable = variableList.get(variableList.size() - 1);
        assertThat(testVariable.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVariable.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testVariable.getDesc()).isEqualTo(UPDATED_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingVariable() throws Exception {
        int databaseSizeBeforeUpdate = variableRepository.findAll().size();

        // Create the Variable
        VariableDTO variableDTO = variableMapper.toDto(variable);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVariableMockMvc.perform(put("/api/variables")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(variableDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Variable in the database
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVariable() throws Exception {
        // Initialize the database
        variableRepository.saveAndFlush(variable);

        int databaseSizeBeforeDelete = variableRepository.findAll().size();

        // Get the variable
        restVariableMockMvc.perform(delete("/api/variables/{id}", variable.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Variable> variableList = variableRepository.findAll();
        assertThat(variableList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Variable.class);
        Variable variable1 = new Variable();
        variable1.setId(1L);
        Variable variable2 = new Variable();
        variable2.setId(variable1.getId());
        assertThat(variable1).isEqualTo(variable2);
        variable2.setId(2L);
        assertThat(variable1).isNotEqualTo(variable2);
        variable1.setId(null);
        assertThat(variable1).isNotEqualTo(variable2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VariableDTO.class);
        VariableDTO variableDTO1 = new VariableDTO();
        variableDTO1.setId(1L);
        VariableDTO variableDTO2 = new VariableDTO();
        assertThat(variableDTO1).isNotEqualTo(variableDTO2);
        variableDTO2.setId(variableDTO1.getId());
        assertThat(variableDTO1).isEqualTo(variableDTO2);
        variableDTO2.setId(2L);
        assertThat(variableDTO1).isNotEqualTo(variableDTO2);
        variableDTO1.setId(null);
        assertThat(variableDTO1).isNotEqualTo(variableDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(variableMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(variableMapper.fromId(null)).isNull();
    }
}
