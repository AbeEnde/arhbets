package com.ahb.ahbets.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ahb.ahbets.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HospitalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hospital.class);
        Hospital hospital1 = new Hospital();
        hospital1.setHospId(1L);
        Hospital hospital2 = new Hospital();
        hospital2.setHospId(hospital1.getHospId());
        assertThat(hospital1).isEqualTo(hospital2);
        hospital2.setHospId(2L);
        assertThat(hospital1).isNotEqualTo(hospital2);
        hospital1.setHospId(null);
        assertThat(hospital1).isNotEqualTo(hospital2);
    }
}
