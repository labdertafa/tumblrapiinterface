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
public class TumblrResponse {
    private long id;
    private String id_string;
    private String state;
    private String display_text;
}