package com.laboratorio.tumblr.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * author Rafael
 * version 1.0
 * created 01/05/2025
 * updated 01/05/2025
 */

@Getter @Setter @NoArgsConstructor
public class TumblrSessionResponse {
    private String access_token;
    private int expires_in;
    private String token_type;
    private String scope;
    private String refresh_token;
}