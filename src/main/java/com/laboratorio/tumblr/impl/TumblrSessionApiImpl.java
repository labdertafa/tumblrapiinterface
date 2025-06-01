package com.laboratorio.tumblr.impl;

import com.google.gson.JsonSyntaxException;
import com.laboratorio.clientapilibrary.exceptions.ApiClientException;
import com.laboratorio.clientapilibrary.model.ApiMethodType;
import com.laboratorio.clientapilibrary.model.ApiRequest;
import com.laboratorio.clientapilibrary.model.ApiResponse;
import com.laboratorio.tumblr.TumblrSessionApi;
import com.laboratorio.tumblr.exception.TumblrApiException;
import com.laboratorio.tumblr.model.TumblrSessionResponse;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 01/05/2025
 */
public class TumblrSessionApiImpl extends TumblrBaseApi implements TumblrSessionApi {
    public TumblrSessionApiImpl(String accessToken) {
        super(accessToken);
    }

    @Override
    public TumblrSessionResponse refreshSession(String refreshToken) {
        String endpoint = this.config.getProperty("refreshSession_endpoint");
        int okStatus = Integer.parseInt(this.config.getProperty("refreshSession_ok_status"));
        String appClientId = this.config.getProperty("app_client_id");
        String appClientSecret = this.config.getProperty("app_client_secret");
        String grantType = this.config.getProperty("refreshSession_grant_type");

        try {
            String url = this.urlBase + "/" + endpoint;
            ApiRequest request = new ApiRequest(url, okStatus, ApiMethodType.POST);
            request.addTextFormData("grant_type", grantType);
            request.addTextFormData("client_id", appClientId);
            request.addTextFormData("client_secret", appClientSecret);
            request.addTextFormData("refresh_token", refreshToken);

            ApiResponse response = this.client.executeApiRequest(request);

            log.info("Refresh Session response: {}", response.getResponseStr());

            return this.gson.fromJson(response.getResponseStr(), TumblrSessionResponse.class);
        } catch (JsonSyntaxException e) {
            this.logException(e);
            throw e;
        } catch (ApiClientException e) {
            throw e;
        } catch (Exception e) {
            throw new TumblrApiException(TumblrSessionApiImpl.class.getName(), "No se pudo refrescar la sesión de Tumblr", e);
        }
    }
}