package org.polytech.covid.Controller;

import org.polytech.covid.Config.JwtTokenUtil;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Service.JwtUserDetailsService;
import org.polytech.covid.Message.MessageResponse;
import org.polytech.covid.model.UserDTO;
import org.polytech.covid.security.JwtRequet;
import org.polytech.covid.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    /***
     * Endpoint public pour se créer un compte professionnel et se loguer (destinée
     * aux professionnels)
     ***/

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequet authenticationRequest) throws Exception {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));

    }

    @RequestMapping(value = "/nouveau", method = RequestMethod.POST)
    public ResponseEntity<?> createPersonne(@RequestBody UserDTO user) throws Exception {
        if (personneRepository.existsByMail(user.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        return ResponseEntity.ok(userDetailsService.save(user));
    }

}
