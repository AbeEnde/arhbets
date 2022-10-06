package com.ahb.ahbets.service;


import com.ahb.ahbets.domain.Result;
import com.ahb.ahbets.repository.ResultRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ResultService {

    private final Logger log = LoggerFactory.getLogger(ResultService.class);

 private final ResultRepo resultRepo;

    public ResultService(ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }

    public Result save( Result result){

        return resultRepo.save(result);

    }

    public Optional<Result> partialUpdate(Result result) {
        log.debug("Request to partially update Hospital : {}", result);

        return resultRepo
            .findById(result.getId())
            .map(existingHospital -> {
                if (result.getId() != null) {
                    existingHospital.setId(result.getId());
                }
                if (result.getExpValue() != null) {
                    existingHospital.setExpValue(result.getExpValue());
                }

                if (result.getPlaceV() != null) {
                    existingHospital.setPlaceV(result.getPlaceV());
                }
                if (result.getGenderV() != null) {
                    existingHospital.setGenderV(result.getGenderV());
                }
                if (result.getSpauseV() != null) {
                    existingHospital.setSpauseV(result.getSpauseV());
                }
                if (result.getMedicalV() != null) {
                    existingHospital.setMedicalV(result.getMedicalV());
                }
//                if (result.getTotalV() != null) {
//                    existingHospital.setTotalV(result.getTotalV());
//                }
                if (result.getResultV() != null) {
                    existingHospital.setResultV(result.getResultV());
                }
//                if (result.getReqst() != null) {
//                    existingHospital.setReqst(result.getReqst());
//                }

                return existingHospital;
            })
            .map(resultRepo::save);
    }




}
