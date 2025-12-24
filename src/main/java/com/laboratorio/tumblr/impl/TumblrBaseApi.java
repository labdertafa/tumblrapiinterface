package com.laboratorio.tumblr.impl;

import com.google.gson.Gson;
import com.laboratorio.clientapilibrary.ApiClient;
import com.laboratorio.clientapilibrary.utils.ReaderConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * author Rafael
 * version 1.1
 * created 01/05/2025
 * updated 24/12/2025
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
        this.gson = new Gson();
        String proxyHost = this.config.getProperty("tumblr_proxy_host");
        String proxyPortStr = this.config.getProperty("tumblr_proxy_port");
        String certificatePath = this.config.getProperty("tumblr_proxy_certificate");
        if (proxyHost != null && !proxyHost.isBlank() && proxyPortStr != null && !proxyPortStr.isBlank()
                && certificatePath != null && !certificatePath.isBlank()) {
            int proxyPort = Integer.parseInt(proxyPortStr);
            this.client = new ApiClient(proxyHost, proxyPort, certificatePath);
        } else {
            this.client = new ApiClient();
        }
    }
}