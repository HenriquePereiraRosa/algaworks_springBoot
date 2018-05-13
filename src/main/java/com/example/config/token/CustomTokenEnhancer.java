/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.config.token;

import com.example.security.SystemUser;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 *
 * @author user
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oaat, OAuth2Authentication oaa) {
        SystemUser systemUser = (SystemUser) oaa.getPrincipal();
        
        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("nome", systemUser.getUsuario().getNome());
        
        ((DefaultOAuth2AccessToken)oaat).setAdditionalInformation(addInfo);
        return oaat;
    }
    
    
    
}
