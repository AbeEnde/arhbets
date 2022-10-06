package com.ahb.ahbets.web.rest;


import com.ahb.ahbets.domain.Request;
import com.ahb.ahbets.domain.Result;
import com.ahb.ahbets.repository.ResultRepo;
import com.ahb.ahbets.service.ResultService;
import com.ahb.ahbets.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResultResorce {

    private final Logger log = LoggerFactory.getLogger(ResultResorce.class);

    private static final String ENTITY_NAME = "result";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResultRepo ResultRepo;
    private ResultService resltServ;

    public ResultResorce(ResultRepo ResultRepo ,ResultService resltServ) {
        this.ResultRepo = ResultRepo;
        this.resltServ = resltServ;
    }


    @PostMapping("/results")
    public ResponseEntity<Result> createRequest(@RequestBody Result result) throws URISyntaxException {
        log.debug("REST request to save Request : {}", result);

        if(result.getId()!=null){
          return partialUpdateRequest(result.getId(), result) ;
        }else {
            Result resultF = ResultRepo.save(result);

            return ResponseEntity
                .created(new URI("/api/results/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
        }

    }

    @PutMapping ("/results/id")
    public ResponseEntity<Result> UpdateRequest(@RequestBody Result result) throws URISyntaxException {


        log.debug("REST request to save Request : {}", result);

        Result resultF = resltServ.save(result);

        return ResponseEntity
            .created(new URI("/api/results/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PatchMapping(value = "/result/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Result> partialUpdateRequest(
        @PathVariable(value = "id", required = false)
        final Long id,
        @RequestBody Result result
    ) throws URISyntaxException {
        log.debug("REST request to partial update Request partially : {}, {}", id, result);
        if (result.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, result.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ResultRepo.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Optional<Result> res = ResultRepo
            .findById(result.getId())
            .map(existingRequest -> {
                if (result.getCode() != null) {
                    existingRequest.setCode(result.getCode());
                }

                if (result.getUserName() != null) {
                    existingRequest.setUserName(result.getUserName());
                }

                if (result.getExpValue() != null) {
                    existingRequest.setExpValue(result.getExpValue());
                }

                if (result.getPlaceV() != null) {
                    existingRequest.setPlaceV(result.getPlaceV());
                }

                if (result.getGenderV() != null) {
                    existingRequest.setGenderV(result.getGenderV());
                }

                if (result.getSpauseV() != null) {
                    existingRequest.setSpauseV(result.getSpauseV());
                }

                if (result.getMedicalV() != null) {
                    existingRequest.setMedicalV(result.getMedicalV());
                }

                if (result.getTotalV() != 0) {
                    existingRequest.setTotalV(result.getTotalV());
                }

                if (result.getResultV() != null) {
                    existingRequest.setResultV(result.getResultV());
                }


                return existingRequest;
            })
            .map(ResultRepo::save);

        return ResponseUtil.wrapOrNotFound(
            res,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString())
        );
    }

        @GetMapping("/getRes")
    public List<Result> getResults(){
        return ResultRepo.findAll();
    }
}
