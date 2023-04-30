package com.waqas.akram.proposalgeniusapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.waqas.akram.proposalgeniusapi.constant.AppConstants.GENERATE_PROPOSAL_API_ENDPOINT;

/**
 * This class configures CORS (Cross-Origin Resource Sharing) for the web application.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds CORS mappings to the provided registry.
     *
     * @param registry The registry to add the CORS mappings to.
     **/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(GENERATE_PROPOSAL_API_ENDPOINT)
                .allowedOrigins("")
                .allowedMethods("GET", "POST", "OPTIONS");
    }
}