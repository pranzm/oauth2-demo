package com.pranjal.msdemo.oauth2demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class WebController {

    @GetMapping("/")
    public String publicPage(){
        return "<h1>This is Public</h1>";
    }

    @GetMapping("/private")
    public String privatePage(Authentication auth){
        return "<h1>This is Private ~[ " + getName(auth)+ "]~ </h1>";
    }

    /*
    Displaying the user name
     */
    private String getName(Authentication auth){
        return Optional.of(auth.getPrincipal())
                .filter(OidcUser.class::isInstance)
                .map(OidcUser.class::cast)
                .map(OidcUser::getEmail)
                .orElseGet(auth::getName);

    }
}
