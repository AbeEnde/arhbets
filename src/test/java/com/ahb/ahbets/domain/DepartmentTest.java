package com.ahb.ahbets.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ahb.ahbets.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepartmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Department.class);
        Department department1 = new Department();
        department1.setDeprtId(1L);
        Department department2 = new Department();
        department2.setDeprtId(department1.getDeprtId());
        assertThat(department1).isEqualTo(department2);
        department2.setDeprtId(2L);
        assertThat(department1).isNotEqualTo(department2);
        department1.setDeprtId(null);
        assertThat(department1).isNotEqualTo(department2);
    }
}
