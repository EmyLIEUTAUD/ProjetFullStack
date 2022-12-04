package org.polytech.covid.security;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String type = "Bearer";
    /*private List<String> roles;
    private Integer identifiant;*/

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
        //this.roles = roles;
    }

    public String getToken() {
        return this.jwttoken;
    }
}