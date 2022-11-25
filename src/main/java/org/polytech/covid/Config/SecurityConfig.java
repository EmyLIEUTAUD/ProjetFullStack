package org.polytech.covid.Config;

import org.polytech.covid.security.JwtAuthenticationEntryPoint;
import org.polytech.covid.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (authz) -> authz
                                .antMatchers(HttpMethod.POST, "/login/authenticate", "/public/**").permitAll()
                                .antMatchers(HttpMethod.GET, "/public/**").permitAll()
                                .antMatchers(HttpMethod.PUT, "/public/**").permitAll()
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
                                .antMatchers(HttpMethod.GET, "/personnes/**").hasAuthority("MEDECIN"))
                .httpBasic(withDefaults())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement();
        http.addFilterBefore(
                jwtRequestFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        http.cors();
    return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }

}
