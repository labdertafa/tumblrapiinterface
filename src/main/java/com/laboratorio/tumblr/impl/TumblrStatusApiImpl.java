package com.laboratorio.tumblr.impl;

import com.google.gson.JsonSyntaxException;
import com.laboratorio.clientapilibrary.exceptions.ApiClientException;
import com.laboratorio.clientapilibrary.model.ApiMethodType;
import com.laboratorio.clientapilibrary.model.ApiRequest;
import com.laboratorio.clientapilibrary.model.ApiResponse;
import com.laboratorio.clientapilibrary.utils.ElementoPost;
import com.laboratorio.clientapilibrary.utils.PostUtils;
import com.laboratorio.clientapilibrary.utils.TipoElementoPost;
import com.laboratorio.tumblr.TumblrStatusApi;
import com.laboratorio.tumblr.exception.TumblrApiException;
import com.laboratorio.tumblr.model.TumblrStatusResponse;

import java.util.List;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 01/05/2025
 */
public class TumblrStatusApiImpl extends TumblrBaseApi implements TumblrStatusApi {
    private final String blog;

    public TumblrStatusApiImpl(String accessToken, String blog) {
        super(accessToken);
        this.blog = blog;
    }

    @Override
    public TumblrStatusResponse postStatus(String text) {
        return postStatus(text, null);
    }

    @Override
    public TumblrStatusResponse postStatus(String text, String imagePath) {
        String endpoint = this.config.getProperty("post_endpoint");
        int okStatus = Integer.parseInt(this.config.getProperty("post_valor_ok"));
        String complementoEndpoint = this.config.getProperty("post_complemento_endpoint");
        String postType;
        if (imagePath == null) {
            postType = this.config.getProperty("post_text_type");
        } else {
            postType = this.config.getProperty("post_image_type");
        }

        try {
            String url = this.urlBase + "/" + endpoint + "/" + this.blog + "/" + complementoEndpoint;
            ApiRequest request = new ApiRequest(url, okStatus, ApiMethodType.POST);
            request.addTextFormData("type", postType);
            request.addApiHeader("Authorization", "Bearer " + this.accessToken);

            List<ElementoPost> elementos = PostUtils.extraerElementosPost(text);
            String textoPost = text;
            String etiquetas = "";
            for (ElementoPost elemento : elementos) {
                if (elemento.getType() == TipoElementoPost.Tag) {
                    if (!etiquetas.isEmpty()) {
                        etiquetas += ",";
                    }
                    etiquetas += elemento.getContenido();
                    textoPost = textoPost.replace(elemento.getContenido(), "");
                }
            }

            if (imagePath == null) {
                request.addTextFormData("body", textoPost);
            } else {
                request.addTextFormData("caption", textoPost);
                request.addFileFormData("data", imagePath);
            }
            if (!etiquetas.isEmpty()) {
                request.addTextFormData("tags", etiquetas);
            }

            ApiResponse response = this.client.executeApiRequest(request);
            log.info("Post response: {}", response.getResponseStr());

            return this.gson.fromJson(response.getResponseStr(), TumblrStatusResponse.class);
        } catch (JsonSyntaxException e) {
            this.logException(e);
            throw e;
        } catch (ApiClientException e) {
            throw e;
        } catch (Exception e) {
            throw new TumblrApiException(TumblrSessionApiImpl.class.getName(), "No se pudo postear una imagen en Tumblr", e);
        }
    }

    @Override
    public boolean deleteStatus(String statusId) {
        String endpoint = this.config.getProperty("delete_post_endpoint");
        int okStatus = Integer.parseInt(this.config.getProperty("delete_post_valor_ok"));
        String complementoEndpoint = this.config.getProperty("delete_post_complemento_endpoint");

        try {
            String url = this.urlBase + "/" + endpoint + "/" + this.blog + "/" + complementoEndpoint;
            ApiRequest request = new ApiRequest(url, okStatus, ApiMethodType.POST);
            request.addTextFormData("id", statusId);
            request.addApiHeader("Authorization", "Bearer " + this.accessToken);

            ApiResponse response = this.client.executeApiRequest(request);
            log.info("Delete Post response: {}", response.getResponseStr());

            return true;
        } catch (JsonSyntaxException e) {
            this.logException(e);
            throw e;
        } catch (ApiClientException e) {
            throw e;
        } catch (Exception e) {
            throw new TumblrApiException(TumblrSessionApiImpl.class.getName(), "Error eliminando un estado en Tumblr", e);
        }
    }
}