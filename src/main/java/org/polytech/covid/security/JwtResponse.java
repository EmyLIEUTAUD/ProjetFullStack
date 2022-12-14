package org.polytech.covid.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String type = "Bearer";
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;



    public JwtResponse(String jwttoken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.authorities = authorities;
    }

    public String getToken() {
        return this.jwttoken;
    }
}