
package com.example.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This is a configuration class to enable profiles in the code.
 * This class crates parameters to enable or disable the HTTP Security,
 * for example.
 *
 * @author user
 */
@ConfigurationProperties("app")
public class ApiProperty {
    
    private String allowedOrigin = 
            "http://localhost:8000"+"http://localhost:4200"+"http://localhost:8080";
    
    private final Security security = new Security();

    public Security getSecurity() {
        return security;
    }

    public String getAllowedOrigin() {
        return allowedOrigin;
    }

    public void setAllowedOrigin(String allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
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
