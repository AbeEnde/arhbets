package com.ahb.ahbets.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ahb.ahbets.IntegrationTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RequestFormResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RequestFormResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final String DEFAULT_C_WZONE = "AAAAAAAAAA";
    private static final String UPDATED_C_WZONE = "BBBBBBBBBB";

    private static final String DEFAULT_C_WWOREDA = "AAAAAAAAAA";
    private static final String UPDATED_C_WWOREDA = "BBBBBBBBBB";

    private static final String DEFAULT_C_WHEALTHFACILITY = "AAAAAAAAAA";
    private static final String UPDATED_C_WHEALTHFACILITY = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTCHOICE = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTCHOICE = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDCHOICE = "AAAAAAAAAA";
    private static final String UPDATED_SECONDCHOICE = "BBBBBBBBBB";

    private static final String DEFAULT_THIRDCHOICE = "AAAAAAAAAA";
    private static final String UPDATED_THIRDCHOICE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SELFEXPIRENCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SELFEXPIRENCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SAPOSEEXPERIENCE = "AAAAAAAAAA";
    private static final String UPDATED_SAPOSEEXPERIENCE = "BBBBBBBBBB";

    private static final String DEFAULT_NO = "AAAAAAAAAA";
    private static final String UPDATED_NO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/request-forms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestFormRepository requestFormRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestFormMockMvc;

    private RequestForm requestForm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestForm createEntity(EntityManager em) {
        RequestForm requestForm = new RequestForm()
            .name(DEFAULT_NAME)
            .sex(DEFAULT_SEX)
            .profession(DEFAULT_PROFESSION)
            .c_wzone(DEFAULT_C_WZONE)
            .c_wworeda(DEFAULT_C_WWOREDA)
            .c_whealthfacility(DEFAULT_C_WHEALTHFACILITY)
            .firstchoice(DEFAULT_FIRSTCHOICE)
            .secondchoice(DEFAULT_SECONDCHOICE)
            .thirdchoice(DEFAULT_THIRDCHOICE)
            .selfexpirence(DEFAULT_SELFEXPIRENCE)
            .saposeexperience(DEFAULT_SAPOSEEXPERIENCE)
            .no(DEFAULT_NO);
        return requestForm;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestForm createUpdatedEntity(EntityManager em) {
        RequestForm requestForm = new RequestForm()
            .name(UPDATED_NAME)
            .sex(UPDATED_SEX)
            .profession(UPDATED_PROFESSION)
            .c_wzone(UPDATED_C_WZONE)
            .c_wworeda(UPDATED_C_WWOREDA)
            .c_whealthfacility(UPDATED_C_WHEALTHFACILITY)
            .firstchoice(UPDATED_FIRSTCHOICE)
            .secondchoice(UPDATED_SECONDCHOICE)
            .thirdchoice(UPDATED_THIRDCHOICE)
            .selfexpirence(UPDATED_SELFEXPIRENCE)
            .saposeexperience(UPDATED_SAPOSEEXPERIENCE)
            .no(UPDATED_NO);
        return requestForm;
    }

    @BeforeEach
    public void initTest() {
        requestForm = createEntity(em);
    }

    @Test
    @Transactional
    void createRequestForm() throws Exception {
        int databaseSizeBeforeCreate = requestFormRepository.findAll().size();
        // Create the RequestForm
        restRequestFormMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isCreated());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeCreate + 1);
        RequestForm testRequestForm = requestFormList.get(requestFormList.size() - 1);
        assertThat(testRequestForm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRequestForm.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testRequestForm.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testRequestForm.getc_wzone()).isEqualTo(DEFAULT_C_WZONE);
        assertThat(testRequestForm.getc_wworeda()).isEqualTo(DEFAULT_C_WWOREDA);
        assertThat(testRequestForm.getc_whealthfacility()).isEqualTo(DEFAULT_C_WHEALTHFACILITY);
        assertThat(testRequestForm.getFirstchoice()).isEqualTo(DEFAULT_FIRSTCHOICE);
        assertThat(testRequestForm.getSecondchoice()).isEqualTo(DEFAULT_SECONDCHOICE);
        assertThat(testRequestForm.getThirdchoice()).isEqualTo(DEFAULT_THIRDCHOICE);
        assertThat(testRequestForm.getSelfexpirence()).isEqualTo(DEFAULT_SELFEXPIRENCE);
        assertThat(testRequestForm.getSaposeexperience()).isEqualTo(DEFAULT_SAPOSEEXPERIENCE);
        assertThat(testRequestForm.getNo()).isEqualTo(DEFAULT_NO);
    }

    @Test
    @Transactional
    void createRequestFormWithExistingId() throws Exception {
        // Create the RequestForm with an existing ID
        requestForm.setId(1L);

        int databaseSizeBeforeCreate = requestFormRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestFormMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestFormRepository.findAll().size();
        // set the field null
        requestForm.setName(null);

        // Create the RequestForm, which fails.

        restRequestFormMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isBadRequest());

        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllRequestForms() throws Exception {
        // Initialize the database
        requestFormRepository.saveAndFlush(requestForm);

        // Get all the requestFormList
        restRequestFormMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(requestForm.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)))
            .andExpect(jsonPath("$.[*].c_wzone").value(hasItem(DEFAULT_C_WZONE)))
            .andExpect(jsonPath("$.[*].c_wworeda").value(hasItem(DEFAULT_C_WWOREDA)))
            .andExpect(jsonPath("$.[*].c_whealthfacility").value(hasItem(DEFAULT_C_WHEALTHFACILITY)))
            .andExpect(jsonPath("$.[*].firstchoice").value(hasItem(DEFAULT_FIRSTCHOICE)))
            .andExpect(jsonPath("$.[*].secondchoice").value(hasItem(DEFAULT_SECONDCHOICE)))
            .andExpect(jsonPath("$.[*].thirdchoice").value(hasItem(DEFAULT_THIRDCHOICE)))
            .andExpect(jsonPath("$.[*].selfexpirence").value(hasItem(DEFAULT_SELFEXPIRENCE.toString())))
            .andExpect(jsonPath("$.[*].saposeexperience").value(hasItem(DEFAULT_SAPOSEEXPERIENCE)))
            .andExpect(jsonPath("$.[*].no").value(hasItem(DEFAULT_NO)));
    }

    @Test
    @Transactional
    void getRequestForm() throws Exception {
        // Initialize the database
        requestFormRepository.saveAndFlush(requestForm);

        // Get the requestForm
        restRequestFormMockMvc
            .perform(get(ENTITY_API_URL_ID, requestForm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(requestForm.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION))
            .andExpect(jsonPath("$.c_wzone").value(DEFAULT_C_WZONE))
            .andExpect(jsonPath("$.c_wworeda").value(DEFAULT_C_WWOREDA))
            .andExpect(jsonPath("$.c_whealthfacility").value(DEFAULT_C_WHEALTHFACILITY))
            .andExpect(jsonPath("$.firstchoice").value(DEFAULT_FIRSTCHOICE))
            .andExpect(jsonPath("$.secondchoice").value(DEFAULT_SECONDCHOICE))
            .andExpect(jsonPath("$.thirdchoice").value(DEFAULT_THIRDCHOICE))
            .andExpect(jsonPath("$.selfexpirence").value(DEFAULT_SELFEXPIRENCE.toString()))
            .andExpect(jsonPath("$.saposeexperience").value(DEFAULT_SAPOSEEXPERIENCE))
            .andExpect(jsonPath("$.no").value(DEFAULT_NO));
    }

    @Test
    @Transactional
    void getNonExistingRequestForm() throws Exception {
        // Get the requestForm
        restRequestFormMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRequestForm() throws Exception {
        // Initialize the database
        requestFormRepository.saveAndFlush(requestForm);

        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();

        // Update the requestForm
        RequestForm updatedRequestForm = requestFormRepository.findById(requestForm.getId()).get();
        // Disconnect from session so that the updates on updatedRequestForm are not directly saved in db
        em.detach(updatedRequestForm);
        updatedRequestForm
            .name(UPDATED_NAME)
            .sex(UPDATED_SEX)
            .profession(UPDATED_PROFESSION)
            .c_wzone(UPDATED_C_WZONE)
            .c_wworeda(UPDATED_C_WWOREDA)
            .c_whealthfacility(UPDATED_C_WHEALTHFACILITY)
            .firstchoice(UPDATED_FIRSTCHOICE)
            .secondchoice(UPDATED_SECONDCHOICE)
            .thirdchoice(UPDATED_THIRDCHOICE)
            .selfexpirence(UPDATED_SELFEXPIRENCE)
            .saposeexperience(UPDATED_SAPOSEEXPERIENCE)
            .no(UPDATED_NO);

        restRequestFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRequestForm.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRequestForm))
            )
            .andExpect(status().isOk());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
        RequestForm testRequestForm = requestFormList.get(requestFormList.size() - 1);
        assertThat(testRequestForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRequestForm.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testRequestForm.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testRequestForm.getc_wzone()).isEqualTo(UPDATED_C_WZONE);
        assertThat(testRequestForm.getc_wworeda()).isEqualTo(UPDATED_C_WWOREDA);
        assertThat(testRequestForm.getc_whealthfacility()).isEqualTo(UPDATED_C_WHEALTHFACILITY);
        assertThat(testRequestForm.getFirstchoice()).isEqualTo(UPDATED_FIRSTCHOICE);
        assertThat(testRequestForm.getSecondchoice()).isEqualTo(UPDATED_SECONDCHOICE);
        assertThat(testRequestForm.getThirdchoice()).isEqualTo(UPDATED_THIRDCHOICE);
        assertThat(testRequestForm.getSelfexpirence()).isEqualTo(UPDATED_SELFEXPIRENCE);
        assertThat(testRequestForm.getSaposeexperience()).isEqualTo(UPDATED_SAPOSEEXPERIENCE);
        assertThat(testRequestForm.getNo()).isEqualTo(UPDATED_NO);
    }

    @Test
    @Transactional
    void putNonExistingRequestForm() throws Exception {
        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();
        requestForm.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, requestForm.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRequestForm() throws Exception {
        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();
        requestForm.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRequestForm() throws Exception {
        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();
        requestForm.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestFormMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRequestFormWithPatch() throws Exception {
        // Initialize the database
        requestFormRepository.saveAndFlush(requestForm);

        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();

        // Update the requestForm using partial update
        RequestForm partialUpdatedRequestForm = new RequestForm();
        partialUpdatedRequestForm.setId(requestForm.getId());

        partialUpdatedRequestForm
            .name(UPDATED_NAME)
            .c_wzone(UPDATED_C_WZONE)
            .c_wworeda(UPDATED_C_WWOREDA)
            .c_whealthfacility(UPDATED_C_WHEALTHFACILITY)
            .firstchoice(UPDATED_FIRSTCHOICE)
            .secondchoice(UPDATED_SECONDCHOICE)
            .selfexpirence(UPDATED_SELFEXPIRENCE)
            .saposeexperience(UPDATED_SAPOSEEXPERIENCE);

        restRequestFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequestForm.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestForm))
            )
            .andExpect(status().isOk());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
        RequestForm testRequestForm = requestFormList.get(requestFormList.size() - 1);
        assertThat(testRequestForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRequestForm.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testRequestForm.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testRequestForm.getc_wzone()).isEqualTo(UPDATED_C_WZONE);
        assertThat(testRequestForm.getc_wworeda()).isEqualTo(UPDATED_C_WWOREDA);
        assertThat(testRequestForm.getc_whealthfacility()).isEqualTo(UPDATED_C_WHEALTHFACILITY);
        assertThat(testRequestForm.getFirstchoice()).isEqualTo(UPDATED_FIRSTCHOICE);
        assertThat(testRequestForm.getSecondchoice()).isEqualTo(UPDATED_SECONDCHOICE);
        assertThat(testRequestForm.getThirdchoice()).isEqualTo(DEFAULT_THIRDCHOICE);
        assertThat(testRequestForm.getSelfexpirence()).isEqualTo(UPDATED_SELFEXPIRENCE);
        assertThat(testRequestForm.getSaposeexperience()).isEqualTo(UPDATED_SAPOSEEXPERIENCE);
        assertThat(testRequestForm.getNo()).isEqualTo(DEFAULT_NO);
    }

    @Test
    @Transactional
    void fullUpdateRequestFormWithPatch() throws Exception {
        // Initialize the database
        requestFormRepository.saveAndFlush(requestForm);

        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();

        // Update the requestForm using partial update
        RequestForm partialUpdatedRequestForm = new RequestForm();
        partialUpdatedRequestForm.setId(requestForm.getId());

        partialUpdatedRequestForm
            .name(UPDATED_NAME)
            .sex(UPDATED_SEX)
            .profession(UPDATED_PROFESSION)
            .c_wzone(UPDATED_C_WZONE)
            .c_wworeda(UPDATED_C_WWOREDA)
            .c_whealthfacility(UPDATED_C_WHEALTHFACILITY)
            .firstchoice(UPDATED_FIRSTCHOICE)
            .secondchoice(UPDATED_SECONDCHOICE)
            .thirdchoice(UPDATED_THIRDCHOICE)
            .selfexpirence(UPDATED_SELFEXPIRENCE)
            .saposeexperience(UPDATED_SAPOSEEXPERIENCE)
            .no(UPDATED_NO);

        restRequestFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequestForm.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestForm))
            )
            .andExpect(status().isOk());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
        RequestForm testRequestForm = requestFormList.get(requestFormList.size() - 1);
        assertThat(testRequestForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRequestForm.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testRequestForm.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testRequestForm.getc_wzone()).isEqualTo(UPDATED_C_WZONE);
        assertThat(testRequestForm.getc_wworeda()).isEqualTo(UPDATED_C_WWOREDA);
        assertThat(testRequestForm.getc_whealthfacility()).isEqualTo(UPDATED_C_WHEALTHFACILITY);
        assertThat(testRequestForm.getFirstchoice()).isEqualTo(UPDATED_FIRSTCHOICE);
        assertThat(testRequestForm.getSecondchoice()).isEqualTo(UPDATED_SECONDCHOICE);
        assertThat(testRequestForm.getThirdchoice()).isEqualTo(UPDATED_THIRDCHOICE);
        assertThat(testRequestForm.getSelfexpirence()).isEqualTo(UPDATED_SELFEXPIRENCE);
        assertThat(testRequestForm.getSaposeexperience()).isEqualTo(UPDATED_SAPOSEEXPERIENCE);
        assertThat(testRequestForm.getNo()).isEqualTo(UPDATED_NO);
    }

    @Test
    @Transactional
    void patchNonExistingRequestForm() throws Exception {
        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();
        requestForm.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, requestForm.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRequestForm() throws Exception {
        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();
        requestForm.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isBadRequest());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRequestForm() throws Exception {
        int databaseSizeBeforeUpdate = requestFormRepository.findAll().size();
        requestForm.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestFormMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(requestForm))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RequestForm in the database
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRequestForm() throws Exception {
        // Initialize the database
        requestFormRepository.saveAndFlush(requestForm);

        int databaseSizeBeforeDelete = requestFormRepository.findAll().size();

        // Delete the requestForm
        restRequestFormMockMvc
            .perform(delete(ENTITY_API_URL_ID, requestForm.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RequestForm> requestFormList = requestFormRepository.findAll();
        assertThat(requestFormList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
