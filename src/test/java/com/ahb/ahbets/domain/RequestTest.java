package com.ahb.ahbets.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ahb.ahbets.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Request.class);
        Request request1 = new Request();
        request1.setReqId(1L);
        Request request2 = new Request();
        request2.setReqId(request1.getReqId());
        assertThat(request1).isEqualTo(request2);
        request2.setReqId(2L);
        assertThat(request1).isNotEqualTo(request2);
        request1.setReqId(null);
        assertThat(request1).isNotEqualTo(request2);
    }
}
