package com.ahb.ahbets.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ahb.ahbets.IntegrationTest;
import com.ahb.ahbets.domain.Request;
import com.ahb.ahbets.repository.RequestRepository;
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
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link RequestResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RequestResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SEX = "AAAAAAAAAA";
    private static final String UPDATED_SEX = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final String DEFAULT_CWZONE = "AAAAAAAAAA";
    private static final String UPDATED_CWZONE = "BBBBBBBBBB";

    private static final String DEFAULT_CWWOREDA = "AAAAAAAAAA";
    private static final String UPDATED_CWWOREDA = "BBBBBBBBBB";

    private static final String DEFAULT_CWFACILITY = "AAAAAAAAAA";
    private static final String UPDATED_CWFACILITY = "BBBBBBBBBB";

    private static final String DEFAULT_FIRSTCHOICE = "AAAAAAAAAA";
    private static final String UPDATED_FIRSTCHOICE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_1 = "AAAAAAAAAA";
    private static final String UPDATED_CODE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDCHOICE = "AAAAAAAAAA";
    private static final String UPDATED_SECONDCHOICE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_2 = "AAAAAAAAAA";
    private static final String UPDATED_CODE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_THIRDCHOICE = "AAAAAAAAAA";
    private static final String UPDATED_THIRDCHOICE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_3 = "AAAAAAAAAA";
    private static final String UPDATED_CODE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_EXPRYEAR = "AAAAAAAAAA";
    private static final String UPDATED_EXPRYEAR = "BBBBBBBBBB";

    private static final String DEFAULT_EXPRMONTH = "AAAAAAAAAA";
    private static final String UPDATED_EXPRMONTH = "BBBBBBBBBB";

    private static final String DEFAULT_EXPRDAY = "AAAAAAAAAA";
    private static final String UPDATED_EXPRDAY = "BBBBBBBBBB";

    private static final String DEFAULT_SPEXPRYEAR = "AAAAAAAAAA";
    private static final String UPDATED_SPEXPRYEAR = "BBBBBBBBBB";

    private static final String DEFAULT_SPEXPRMONTH = "AAAAAAAAAA";
    private static final String UPDATED_SPEXPRMONTH = "BBBBBBBBBB";

    private static final String DEFAULT_SPEXPRDAY = "AAAAAAAAAA";
    private static final String UPDATED_SPEXPRDAY = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FILE_CONTENT_TYPE = "image/png";

    private static final String ENTITY_API_URL = "/api/requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRequestMockMvc;

    private Request request;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createEntity(EntityManager em) {
        Request request = new Request()
            .name(DEFAULT_NAME)
            .sex(DEFAULT_SEX)
            .profession(DEFAULT_PROFESSION)
            .cwzone(DEFAULT_CWZONE)
            .cwworeda(DEFAULT_CWWOREDA)
            .cwfacility(DEFAULT_CWFACILITY)
            .firstchoice(DEFAULT_FIRSTCHOICE)
            .code1(DEFAULT_CODE_1)
            .secondchoice(DEFAULT_SECONDCHOICE)
            .code2(DEFAULT_CODE_2)
            .thirdchoice(DEFAULT_THIRDCHOICE)
            .code3(DEFAULT_CODE_3)
            .expryear(DEFAULT_EXPRYEAR)
            .exprmonth(DEFAULT_EXPRMONTH)
            .exprday(DEFAULT_EXPRDAY)
            .spexpryear(DEFAULT_SPEXPRYEAR)
            .spexprmonth(DEFAULT_SPEXPRMONTH)
            .spexprday(DEFAULT_SPEXPRDAY)
            .file(DEFAULT_FILE)
            .fileContentType(DEFAULT_FILE_CONTENT_TYPE);
        return request;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Request createUpdatedEntity(EntityManager em) {
        Request request = new Request()
            .name(UPDATED_NAME)
            .sex(UPDATED_SEX)
            .profession(UPDATED_PROFESSION)
            .cwzone(UPDATED_CWZONE)
            .cwworeda(UPDATED_CWWOREDA)
            .cwfacility(UPDATED_CWFACILITY)
            .firstchoice(UPDATED_FIRSTCHOICE)
            .code1(UPDATED_CODE_1)
            .secondchoice(UPDATED_SECONDCHOICE)
            .code2(UPDATED_CODE_2)
            .thirdchoice(UPDATED_THIRDCHOICE)
            .code3(UPDATED_CODE_3)
            .expryear(UPDATED_EXPRYEAR)
            .exprmonth(UPDATED_EXPRMONTH)
            .exprday(UPDATED_EXPRDAY)
            .spexpryear(UPDATED_SPEXPRYEAR)
            .spexprmonth(UPDATED_SPEXPRMONTH)
            .spexprday(UPDATED_SPEXPRDAY)
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE);
        return request;
    }

    @BeforeEach
    public void initTest() {
        request = createEntity(em);
    }

    @Test
    @Transactional
    void createRequest() throws Exception {
        int databaseSizeBeforeCreate = requestRepository.findAll().size();
        // Create the Request
        restRequestMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(request))
            )
            .andExpect(status().isCreated());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate + 1);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRequest.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testRequest.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testRequest.getCwzone()).isEqualTo(DEFAULT_CWZONE);
        assertThat(testRequest.getCwworeda()).isEqualTo(DEFAULT_CWWOREDA);
        assertThat(testRequest.getCwfacility()).isEqualTo(DEFAULT_CWFACILITY);
        assertThat(testRequest.getFirstchoice()).isEqualTo(DEFAULT_FIRSTCHOICE);
        assertThat(testRequest.getCode1()).isEqualTo(DEFAULT_CODE_1);
        assertThat(testRequest.getSecondchoice()).isEqualTo(DEFAULT_SECONDCHOICE);
        assertThat(testRequest.getCode2()).isEqualTo(DEFAULT_CODE_2);
        assertThat(testRequest.getThirdchoice()).isEqualTo(DEFAULT_THIRDCHOICE);
        assertThat(testRequest.getCode3()).isEqualTo(DEFAULT_CODE_3);
        assertThat(testRequest.getExpryear()).isEqualTo(DEFAULT_EXPRYEAR);
        assertThat(testRequest.getExprmonth()).isEqualTo(DEFAULT_EXPRMONTH);
        assertThat(testRequest.getExprday()).isEqualTo(DEFAULT_EXPRDAY);
        assertThat(testRequest.getSpexpryear()).isEqualTo(DEFAULT_SPEXPRYEAR);
        assertThat(testRequest.getSpexprmonth()).isEqualTo(DEFAULT_SPEXPRMONTH);
        assertThat(testRequest.getSpexprday()).isEqualTo(DEFAULT_SPEXPRDAY);
        assertThat(testRequest.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testRequest.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void createRequestWithExistingId() throws Exception {
        // Create the Request with an existing ID
        request.setReqId(1L);

        int databaseSizeBeforeCreate = requestRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRequestMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(request))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRequests() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get all the requestList
        restRequestMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(request.getReqId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)))
            .andExpect(jsonPath("$.[*].cwzone").value(hasItem(DEFAULT_CWZONE)))
            .andExpect(jsonPath("$.[*].cwworeda").value(hasItem(DEFAULT_CWWOREDA)))
            .andExpect(jsonPath("$.[*].cwfacility").value(hasItem(DEFAULT_CWFACILITY)))
            .andExpect(jsonPath("$.[*].firstchoice").value(hasItem(DEFAULT_FIRSTCHOICE)))
            .andExpect(jsonPath("$.[*].code1").value(hasItem(DEFAULT_CODE_1)))
            .andExpect(jsonPath("$.[*].secondchoice").value(hasItem(DEFAULT_SECONDCHOICE)))
            .andExpect(jsonPath("$.[*].code2").value(hasItem(DEFAULT_CODE_2)))
            .andExpect(jsonPath("$.[*].thirdchoice").value(hasItem(DEFAULT_THIRDCHOICE)))
            .andExpect(jsonPath("$.[*].code3").value(hasItem(DEFAULT_CODE_3)))
            .andExpect(jsonPath("$.[*].expryear").value(hasItem(DEFAULT_EXPRYEAR)))
            .andExpect(jsonPath("$.[*].exprmonth").value(hasItem(DEFAULT_EXPRMONTH)))
            .andExpect(jsonPath("$.[*].exprday").value(hasItem(DEFAULT_EXPRDAY)))
            .andExpect(jsonPath("$.[*].spexpryear").value(hasItem(DEFAULT_SPEXPRYEAR)))
            .andExpect(jsonPath("$.[*].spexprmonth").value(hasItem(DEFAULT_SPEXPRMONTH)))
            .andExpect(jsonPath("$.[*].spexprday").value(hasItem(DEFAULT_SPEXPRDAY)))
            .andExpect(jsonPath("$.[*].fileContentType").value(hasItem(DEFAULT_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].file").value(hasItem(Base64Utils.encodeToString(DEFAULT_FILE))));
    }

    @Test
    @Transactional
    void getRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        // Get the request
        restRequestMockMvc
            .perform(get(ENTITY_API_URL_ID, request.getReqId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(request.getReqId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION))
            .andExpect(jsonPath("$.cwzone").value(DEFAULT_CWZONE))
            .andExpect(jsonPath("$.cwworeda").value(DEFAULT_CWWOREDA))
            .andExpect(jsonPath("$.cwfacility").value(DEFAULT_CWFACILITY))
            .andExpect(jsonPath("$.firstchoice").value(DEFAULT_FIRSTCHOICE))
            .andExpect(jsonPath("$.code1").value(DEFAULT_CODE_1))
            .andExpect(jsonPath("$.secondchoice").value(DEFAULT_SECONDCHOICE))
            .andExpect(jsonPath("$.code2").value(DEFAULT_CODE_2))
            .andExpect(jsonPath("$.thirdchoice").value(DEFAULT_THIRDCHOICE))
            .andExpect(jsonPath("$.code3").value(DEFAULT_CODE_3))
            .andExpect(jsonPath("$.expryear").value(DEFAULT_EXPRYEAR))
            .andExpect(jsonPath("$.exprmonth").value(DEFAULT_EXPRMONTH))
            .andExpect(jsonPath("$.exprday").value(DEFAULT_EXPRDAY))
            .andExpect(jsonPath("$.spexpryear").value(DEFAULT_SPEXPRYEAR))
            .andExpect(jsonPath("$.spexprmonth").value(DEFAULT_SPEXPRMONTH))
            .andExpect(jsonPath("$.spexprday").value(DEFAULT_SPEXPRDAY))
            .andExpect(jsonPath("$.fileContentType").value(DEFAULT_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.file").value(Base64Utils.encodeToString(DEFAULT_FILE)));
    }

    @Test
    @Transactional
    void getNonExistingRequest() throws Exception {
        // Get the request
        restRequestMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request
        Request updatedRequest = requestRepository.findById(request.getReqId()).get();
        // Disconnect from session so that the updates on updatedRequest are not directly saved in db
        em.detach(updatedRequest);
        updatedRequest
            .name(UPDATED_NAME)
            .sex(UPDATED_SEX)
            .profession(UPDATED_PROFESSION)
            .cwzone(UPDATED_CWZONE)
            .cwworeda(UPDATED_CWWOREDA)
            .cwfacility(UPDATED_CWFACILITY)
            .firstchoice(UPDATED_FIRSTCHOICE)
            .code1(UPDATED_CODE_1)
            .secondchoice(UPDATED_SECONDCHOICE)
            .code2(UPDATED_CODE_2)
            .thirdchoice(UPDATED_THIRDCHOICE)
            .code3(UPDATED_CODE_3)
            .expryear(UPDATED_EXPRYEAR)
            .exprmonth(UPDATED_EXPRMONTH)
            .exprday(UPDATED_EXPRDAY)
            .spexpryear(UPDATED_SPEXPRYEAR)
            .spexprmonth(UPDATED_SPEXPRMONTH)
            .spexprday(UPDATED_SPEXPRDAY)
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE);

        restRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRequest.getReqId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRequest))
            )
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRequest.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testRequest.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testRequest.getCwzone()).isEqualTo(UPDATED_CWZONE);
        assertThat(testRequest.getCwworeda()).isEqualTo(UPDATED_CWWOREDA);
        assertThat(testRequest.getCwfacility()).isEqualTo(UPDATED_CWFACILITY);
        assertThat(testRequest.getFirstchoice()).isEqualTo(UPDATED_FIRSTCHOICE);
        assertThat(testRequest.getCode1()).isEqualTo(UPDATED_CODE_1);
        assertThat(testRequest.getSecondchoice()).isEqualTo(UPDATED_SECONDCHOICE);
        assertThat(testRequest.getCode2()).isEqualTo(UPDATED_CODE_2);
        assertThat(testRequest.getThirdchoice()).isEqualTo(UPDATED_THIRDCHOICE);
        assertThat(testRequest.getCode3()).isEqualTo(UPDATED_CODE_3);
        assertThat(testRequest.getExpryear()).isEqualTo(UPDATED_EXPRYEAR);
        assertThat(testRequest.getExprmonth()).isEqualTo(UPDATED_EXPRMONTH);
        assertThat(testRequest.getExprday()).isEqualTo(UPDATED_EXPRDAY);
        assertThat(testRequest.getSpexpryear()).isEqualTo(UPDATED_SPEXPRYEAR);
        assertThat(testRequest.getSpexprmonth()).isEqualTo(UPDATED_SPEXPRMONTH);
        assertThat(testRequest.getSpexprday()).isEqualTo(UPDATED_SPEXPRDAY);
        assertThat(testRequest.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testRequest.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void putNonExistingRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setReqId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, request.getReqId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(request))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setReqId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(request))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setReqId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(request))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRequestWithPatch() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request using partial update
        Request partialUpdatedRequest = new Request();
        partialUpdatedRequest.setReqId(request.getReqId());

        partialUpdatedRequest
            .name(UPDATED_NAME)
            .sex(UPDATED_SEX)
            .cwzone(UPDATED_CWZONE)
            .cwworeda(UPDATED_CWWOREDA)
            .cwfacility(UPDATED_CWFACILITY)
            .code1(UPDATED_CODE_1)
            .secondchoice(UPDATED_SECONDCHOICE)
            .code2(UPDATED_CODE_2)
            .code3(UPDATED_CODE_3)
            .expryear(UPDATED_EXPRYEAR)
            .exprmonth(UPDATED_EXPRMONTH)
            .exprday(UPDATED_EXPRDAY)
            .spexpryear(UPDATED_SPEXPRYEAR);

        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequest.getReqId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequest))
            )
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRequest.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testRequest.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testRequest.getCwzone()).isEqualTo(UPDATED_CWZONE);
        assertThat(testRequest.getCwworeda()).isEqualTo(UPDATED_CWWOREDA);
        assertThat(testRequest.getCwfacility()).isEqualTo(UPDATED_CWFACILITY);
        assertThat(testRequest.getFirstchoice()).isEqualTo(DEFAULT_FIRSTCHOICE);
        assertThat(testRequest.getCode1()).isEqualTo(UPDATED_CODE_1);
        assertThat(testRequest.getSecondchoice()).isEqualTo(UPDATED_SECONDCHOICE);
        assertThat(testRequest.getCode2()).isEqualTo(UPDATED_CODE_2);
        assertThat(testRequest.getThirdchoice()).isEqualTo(DEFAULT_THIRDCHOICE);
        assertThat(testRequest.getCode3()).isEqualTo(UPDATED_CODE_3);
        assertThat(testRequest.getExpryear()).isEqualTo(UPDATED_EXPRYEAR);
        assertThat(testRequest.getExprmonth()).isEqualTo(UPDATED_EXPRMONTH);
        assertThat(testRequest.getExprday()).isEqualTo(UPDATED_EXPRDAY);
        assertThat(testRequest.getSpexpryear()).isEqualTo(UPDATED_SPEXPRYEAR);
        assertThat(testRequest.getSpexprmonth()).isEqualTo(DEFAULT_SPEXPRMONTH);
        assertThat(testRequest.getSpexprday()).isEqualTo(DEFAULT_SPEXPRDAY);
        assertThat(testRequest.getFile()).isEqualTo(DEFAULT_FILE);
        assertThat(testRequest.getFileContentType()).isEqualTo(DEFAULT_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateRequestWithPatch() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeUpdate = requestRepository.findAll().size();

        // Update the request using partial update
        Request partialUpdatedRequest = new Request();
        partialUpdatedRequest.setReqId(request.getReqId());

        partialUpdatedRequest
            .name(UPDATED_NAME)
            .sex(UPDATED_SEX)
            .profession(UPDATED_PROFESSION)
            .cwzone(UPDATED_CWZONE)
            .cwworeda(UPDATED_CWWOREDA)
            .cwfacility(UPDATED_CWFACILITY)
            .firstchoice(UPDATED_FIRSTCHOICE)
            .code1(UPDATED_CODE_1)
            .secondchoice(UPDATED_SECONDCHOICE)
            .code2(UPDATED_CODE_2)
            .thirdchoice(UPDATED_THIRDCHOICE)
            .code3(UPDATED_CODE_3)
            .expryear(UPDATED_EXPRYEAR)
            .exprmonth(UPDATED_EXPRMONTH)
            .exprday(UPDATED_EXPRDAY)
            .spexpryear(UPDATED_SPEXPRYEAR)
            .spexprmonth(UPDATED_SPEXPRMONTH)
            .spexprday(UPDATED_SPEXPRDAY)
            .file(UPDATED_FILE)
            .fileContentType(UPDATED_FILE_CONTENT_TYPE);

        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRequest.getReqId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRequest))
            )
            .andExpect(status().isOk());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
        Request testRequest = requestList.get(requestList.size() - 1);
        assertThat(testRequest.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRequest.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testRequest.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testRequest.getCwzone()).isEqualTo(UPDATED_CWZONE);
        assertThat(testRequest.getCwworeda()).isEqualTo(UPDATED_CWWOREDA);
        assertThat(testRequest.getCwfacility()).isEqualTo(UPDATED_CWFACILITY);
        assertThat(testRequest.getFirstchoice()).isEqualTo(UPDATED_FIRSTCHOICE);
        assertThat(testRequest.getCode1()).isEqualTo(UPDATED_CODE_1);
        assertThat(testRequest.getSecondchoice()).isEqualTo(UPDATED_SECONDCHOICE);
        assertThat(testRequest.getCode2()).isEqualTo(UPDATED_CODE_2);
        assertThat(testRequest.getThirdchoice()).isEqualTo(UPDATED_THIRDCHOICE);
        assertThat(testRequest.getCode3()).isEqualTo(UPDATED_CODE_3);
        assertThat(testRequest.getExpryear()).isEqualTo(UPDATED_EXPRYEAR);
        assertThat(testRequest.getExprmonth()).isEqualTo(UPDATED_EXPRMONTH);
        assertThat(testRequest.getExprday()).isEqualTo(UPDATED_EXPRDAY);
        assertThat(testRequest.getSpexpryear()).isEqualTo(UPDATED_SPEXPRYEAR);
        assertThat(testRequest.getSpexprmonth()).isEqualTo(UPDATED_SPEXPRMONTH);
        assertThat(testRequest.getSpexprday()).isEqualTo(UPDATED_SPEXPRDAY);
        assertThat(testRequest.getFile()).isEqualTo(UPDATED_FILE);
        assertThat(testRequest.getFileContentType()).isEqualTo(UPDATED_FILE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    void patchNonExistingRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setReqId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, request.getReqId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(request))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setReqId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(request))
            )
            .andExpect(status().isBadRequest());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRequest() throws Exception {
        int databaseSizeBeforeUpdate = requestRepository.findAll().size();
        request.setReqId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRequestMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(request))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Request in the database
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRequest() throws Exception {
        // Initialize the database
        requestRepository.saveAndFlush(request);

        int databaseSizeBeforeDelete = requestRepository.findAll().size();

        // Delete the request
        restRequestMockMvc
            .perform(delete(ENTITY_API_URL_ID, request.getReqId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Request> requestList = requestRepository.findAll();
        assertThat(requestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
