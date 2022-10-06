package com.ahb.ahbets.service;

import com.ahb.ahbets.domain.Department;
import com.ahb.ahbets.repository.DepartmentRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Department}.
 */
@Service
@Transactional
public class DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Save a department.
     *
     * @param department the entity to save.
     * @return the persisted entity.
     */
    public Department save(Department department) {
        log.debug("Request to save Department : {}", department);
        return departmentRepository.save(department);
    }

    /**
     * Update a department.
     *
     * @param department the entity to save.
     * @return the persisted entity.
     */
    public Department update(Department department) {
        log.debug("Request to save Department : {}", department);
        return departmentRepository.save(department);
    }

    /**
     * Partially update a department.
     *
     * @param department the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Department> partialUpdate(Department department) {
        log.debug("Request to partially update Department : {}", department);

        return departmentRepository
            .findById(department.getDeprtId())
            .map(existingDepartment -> {
                if (department.getDeprtId() != null) {
                    existingDepartment.setDeprtId(department.getDeprtId());
                }
                if (department.getDepName() != null) {
                    existingDepartment.setDepName(department.getDepName());
                }
                if (department.getAvailable() != null) {
                    existingDepartment.setAvailable(department.getAvailable());
                }
                if (department.getRelesed() != null) {
                    existingDepartment.setRelesed(department.getRelesed());
                }
                if (department.getAssigned() != null) {
                    existingDepartment.setAssigned(department.getAssigned());
                }
//                if (department.getHcode() != null) {
//                    existingDepartment.setHcode(department.getHcode());
//                }

                return existingDepartment;
            })
            .map(departmentRepository::save);
    }

    /**
     * Get all the departments.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        log.debug("Request to get all Departments");
        return departmentRepository.findAll();
    }

    /**
     * Get one department by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Department> findOne(Long id) {
        log.debug("Request to get Department : {}", id);
        return departmentRepository.findById(id);
    }

    /**
     * Delete the department by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Department : {}", id);
        departmentRepository.deleteById(id);
    }
}
