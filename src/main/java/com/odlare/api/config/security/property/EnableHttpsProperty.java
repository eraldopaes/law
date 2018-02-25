package com.odlare.api.config.security.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("law")
public class EnableHttpsProperty {

    private final Security security = new Security();

    public Security getSecurity() {
        return security;
    }

    public static class Security {

        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }
}
