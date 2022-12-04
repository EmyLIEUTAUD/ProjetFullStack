package org.polytech.covid.Controller;

import org.polytech.covid.Config.JwtTokenUtil;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Service.JwtUserDetailsService;
import org.polytech.covid.Service.PersonneService;
import org.polytech.covid.Message.MessageResponse;
import org.polytech.covid.ServiceImpl.PersonneServiceImpl;
import org.polytech.covid.model.UserDTO;
import org.polytech.covid.security.JwtRequet;
import org.polytech.covid.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

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

        //authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(token));

      
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @RequestMapping(value = "/nouveau", method = RequestMethod.POST)
    public ResponseEntity<?> createPersonne(@RequestBody UserDTO user)  throws Exception{
        if (personneRepository.existsByMail(user.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        return ResponseEntity.ok(userDetailsService.save(user));
    }

}
