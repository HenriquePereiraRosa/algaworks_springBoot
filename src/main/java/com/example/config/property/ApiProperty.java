
package com.example.config.property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    
    private List<String> allowedOrigins;
    
    private final Security security = new Security();

    public ApiProperty() {
        this.allowedOrigins = new ArrayList<>(Arrays.asList(
                "http://localhost:8000", "http://localhost:4200", 
                "https://angular-intro.herokuapp.com",
                "https://springBootIntro.herokuapp.com"));
    }

    public Security getSecurity() {
        return security;
    }

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigin(String allowedOrigin, int index) {
        this.allowedOrigins.set(index, allowedOrigin);
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
