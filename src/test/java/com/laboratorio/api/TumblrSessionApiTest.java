package com.laboratorio.api;

import com.laboratorio.clientapilibrary.exceptions.ApiClientException;
import com.laboratorio.clientapilibrary.utils.ReaderConfig;
import com.laboratorio.tumblr.TumblrSessionApi;
import com.laboratorio.tumblr.impl.TumblrSessionApiImpl;
import com.laboratorio.tumblr.model.TumblrSessionResponse;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 01/05/2025
 */
public class TumblrSessionApiTest {
    private TumblrSessionApi sessionApi;

    @BeforeEach
    public void initTest() {
        this.sessionApi = new TumblrSessionApiImpl("");
    }

    @Test
    public void refreshTokenTest() {
        ReaderConfig config = new ReaderConfig("config/tumblr_api.properties");
        String refreshTokenTest = config.getProperty("test_refresh_token");

        TumblrSessionResponse response = this.sessionApi.refreshSession(refreshTokenTest);

        assertTrue(response.getExpires_in() > 0);
        assertNotNull(response.getAccess_token());
        assertNotNull(response.getRefresh_token());
    }

    @Test
    public void refreshTokenTest_KO() {
        String refreshTokenTest = "test_refresh_token";

        assertThrows(ApiClientException.class, () -> this.sessionApi.refreshSession(refreshTokenTest));
    }
}