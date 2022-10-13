package org.polytech.covid.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (authz) -> authz
                                .antMatchers(HttpMethod.POST, "/login/nouveau").permitAll()
                                .antMatchers(HttpMethod.GET, "/public", "/admin").authenticated()
                                .antMatchers(HttpMethod.POST, "/public", "/admin").authenticated()
                                .antMatchers(HttpMethod.PUT, "/public", "/admin").authenticated()
                                .antMatchers(HttpMethod.DELETE, "/public", "/admin").authenticated()
                                .antMatchers(HttpMethod.GET, "/public/**").hasAuthority("PUBLIC")
                                .antMatchers(HttpMethod.POST, "/public/**").hasAuthority("PUBLIC")
                                .antMatchers(HttpMethod.PUT, "/public/**").hasAuthority("PUBLIC")
                                .antMatchers(HttpMethod.DELETE, "/public/**").hasAuthority("PUBLIC")
                                .antMatchers(HttpMethod.GET, "/admin/centres/**").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.POST, "/admin/centres/**").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.PUT, "/admin/centres/**").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/admin/centres/**").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.GET, "/admin/administrateurs/**").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.POST, "/admin/administrateurs/**").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.PUT, "/admin/administrateurs/**").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/admin/administrateurs/**").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.POST, "/admin/csv/upload").hasAuthority("SUPER_ADMIN")
                                .antMatchers(HttpMethod.GET, "/admin/medecins/**").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.POST, "/admin/medecins/**").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.PUT, "/admin/medecins/**").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.DELETE, "/admin/medecins/**").hasAuthority("ADMIN")
                                .antMatchers(HttpMethod.GET, "/admin/medecin/planning/**").hasAuthority("MEDECIN"))
                .httpBasic(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable();// On rend les session
                                  // stateless
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
