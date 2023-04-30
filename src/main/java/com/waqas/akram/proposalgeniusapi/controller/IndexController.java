package com.waqas.akram.proposalgeniusapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.waqas.akram.proposalgeniusapi.constant.AppConstants.INDEX_HTML_FILE_NAME;

@RestController
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String index() throws IOException {
        log.info("GET call landed for the index.html file.");
        Resource resource = new ClassPathResource(INDEX_HTML_FILE_NAME);
        return IOUtils.toString(resource.getInputStream(), Charset.defaultCharset());
    }
}