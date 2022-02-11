package com.service;


import com.model.dto.GoogleResponse;
import com.model.dto.CaptchaConfig;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CaptchaService {
    private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");
    private final CaptchaConfig captchaConfig;
    private final HttpServletRequest request;

    public CaptchaService(CaptchaConfig captchaConfig, HttpServletRequest request) {
        this.captchaConfig = captchaConfig;
        this.request = request;
    }

    public void processResponse(String response) {
        if (!responseCheck(response)) {
            throw new RuntimeException("Response contains invalid characters");
        }
        RestOperations restTemplate = createRestTemplate();

        URI verifyUri = URI.create(String.format(
                "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                captchaConfig.getSecret(), response, request.getRemoteAddr()));
        GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);

        if (!googleResponse.isSuccess()) {
            throw new RuntimeException("reCaptcha was not successfully validated");
        }
    }

    public CaptchaConfig getCaptchaConfig() {
        return captchaConfig;
    }

    private boolean responseCheck(String response) {
        return StringUtils.hasLength(response) && RESPONSE_PATTERN.matcher(response).matches();
    }

    private RestOperations createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }
}
