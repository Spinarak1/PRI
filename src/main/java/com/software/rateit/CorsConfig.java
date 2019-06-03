package com.software.rateit;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

class CorsConfig extends UrlBasedCorsConfigurationSource {

    CorsConfig() {
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues(); // <- frequantly used values

        this.registerCorsConfiguration("/**", config);
    }
}