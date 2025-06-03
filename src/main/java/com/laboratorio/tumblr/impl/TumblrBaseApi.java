package com.laboratorio.tumblr.impl;

import com.google.gson.Gson;
import com.laboratorio.clientapilibrary.ApiClient;
import com.laboratorio.clientapilibrary.utils.ReaderConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 02/05/2025
 */
public class TumblrBaseApi {
    protected static final Logger log = LogManager.getLogger(TumblrBaseApi.class);
    protected final String accessToken;
    protected final String urlBase;
    protected final ReaderConfig config;
    protected final ApiClient client;
    protected final Gson gson;

    public TumblrBaseApi(String accessToken) {
        this.accessToken = accessToken;
        this.config = new ReaderConfig("config/tumblr_api.properties");
        this.urlBase = this.config.getProperty("url_base_tumblr");
        this.client = new ApiClient();
        this.gson = new Gson();
    }
}