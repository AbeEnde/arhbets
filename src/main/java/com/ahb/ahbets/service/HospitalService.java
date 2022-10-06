package com.ahb.ahbets.service;

import com.ahb.ahbets.domain.Hospital;
import com.ahb.ahbets.repository.HospitalRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Hospital}.
 */
@Service
@Transactional
public class HospitalService {

    private final Logger log = LoggerFactory.getLogger(HospitalService.class);

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    /**
     * Save a hospital.
     *
     * @param hospital the entity to save.
     * @return the persisted entity.
     */
    public Hospital save(Hospital hospital) {
        log.debug("Request to save Hospital : {}", hospital);
        return hospitalRepository.save(hospital);
    }

    /**
     * Update a hospital.
     *
     * @param hospital the entity to save.
     * @return the persisted entity.
     */
    public Hospital update(Hospital hospital) {
        log.debug("Request to save Hospital : {}", hospital);
        return hospitalRepository.save(hospital);
    }

    /**
     * Partially update a hospital.
     *
     * @param hospital the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Hospital> partialUpdate(Hospital hospital) {
        log.debug("Request to partially update Hospital : {}", hospital);

        return hospitalRepository
            .findById(hospital.getCode())
            .map(existingHospital -> {
                if (hospital.getCode() != null) {
                    existingHospital.setCode(hospital.getCode());
                }
                if (hospital.getHospitalName() != null) {
                    existingHospital.setHospitalName(hospital.getHospitalName());
                }
                if (hospital.getCode() != null) {
                    existingHospital.setCode(hospital.getCode());
                }

                return existingHospital;
            })
            .map(hospitalRepository::save);
    }

    /**
     * Get all the hospitals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Hospital> findAll() {
        log.debug("Request to get all Hospitals");
        return hospitalRepository.findAll();
    }

    /**
     * Get one hospital by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Hospital> findOne(Long id) {
        log.debug("Request to get Hospital : {}", id);
        return hospitalRepository.findById(id);
    }

    /**
     * Delete the hospital by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Hospital : {}", id);
        hospitalRepository.deleteById(id);
    }
}
