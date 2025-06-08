package com.laboratorio.tumblr;

import com.laboratorio.tumblr.model.TumblrSessionResponse;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 08/05/2025
 */
public interface TumblrSessionApi {
    TumblrSessionResponse refreshSession(String appClientId, String appClientSecret, String refreshToken);
}