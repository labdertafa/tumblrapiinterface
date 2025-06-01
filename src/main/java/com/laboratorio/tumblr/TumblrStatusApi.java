package com.laboratorio.tumblr;

import com.laboratorio.tumblr.model.TumblrStatusResponse;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 01/05/2025
 */
public interface TumblrStatusApi {
    TumblrStatusResponse postStatus(String text);
    TumblrStatusResponse postStatus(String text, String imagePath);
    boolean deleteStatus(String statusId);
}