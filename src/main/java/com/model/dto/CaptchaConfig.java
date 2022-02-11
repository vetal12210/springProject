package com.model.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CaptchaConfig {
    @Value("6LeFlHYdAAAAAMaDKJvvyhRWAduY6EQt4mp1Mhkg")
    private String site;
    @Value("6LeFlHYdAAAAABbGNB05HcbUatz3ycLgN5AC9Bhm")
    private String secret;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
