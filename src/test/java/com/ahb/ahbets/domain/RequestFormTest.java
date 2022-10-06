package com.ahb.ahbets.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ahb.ahbets.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestFormTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestForm.class);
        RequestForm requestForm1 = new RequestForm();
        requestForm1.setId(1L);
        RequestForm requestForm2 = new RequestForm();
        requestForm2.setId(requestForm1.getId());
        assertThat(requestForm1).isEqualTo(requestForm2);
        requestForm2.setId(2L);
        assertThat(requestForm1).isNotEqualTo(requestForm2);
        requestForm1.setId(null);
        assertThat(requestForm1).isNotEqualTo(requestForm2);
    }
}
