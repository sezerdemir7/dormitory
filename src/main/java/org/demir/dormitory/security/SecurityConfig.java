package org.demir.dormitory.security;

import org.demir.dormitory.service.StaffService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final StaffService staffService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, StaffService staffService, PasswordEncoder passwordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.staffService = staffService;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests

                        .requestMatchers(
                                "/auth/welcome/**","/auth/register/**", "/auth/verify", "/auth/login",
                                "/auth/generateToken/**",
                                "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**",
                                "/ws/**","/topic/playground-status","/app/**","/topic",
                                "/v1/reservation/save",
                                "/v1/playground/all",
                                "/v1/cafeteria/all",
                                "/v1/hall/all",
                                "/v1/access-log/**",
                                "/v1/leave/save","/v1/leave/update","/v1/leave/delete",
                                "/v1/leave/oneLeave","v1/leave/approved","v1/leave/unapproved",
                                "v1/lesson/all","/v1/lesson/oneLesson","/v1/lesson/lesson",

                                "/v1/room/all",
                                "/v1/cafeteria/all",
                                "/v1/teacher/all",
                                "/v1/leave",
                                "/v1/leave/save",
                                "/v1/leave/delete",
                                "/v1/leave/oneLeave",
                               " /v1/leave/update"


                        ).permitAll()


                        .requestMatchers(
                                "/v1/teacher/save","v1/teacher/delete","v1/teacher/oneTeacher","v1/teacher/delete",
                                "v1/teacher/image","v1/teacher/save/contact-info","v1/teacher/save/image",
                                "/v1/staff/**",
                                "/v1/manager/save",
                                "/v1/manager/save/contact-info",
                                "/v1/manager/save/image"
                        ).hasRole("MANAGER")


                        .requestMatchers(
                                "/v1/reservation/delete","/v1/reservation/update","/v1/reservation/approved",
                                "/v1/reservation/unapproved","/v1/reservation/oneReservation","/v1/reservation/all",
                                "/v1/hall/**",
                                "/v1/room/**",
                                "/v1/student/**",
                                "/v1/garden/**",
                                "/v1/image/**",
                                "/v1/leave/approve/",
                                "/v1/playground/save","/v1/playground/update","/v1/playground/delete",
                                "/v1/cafeteria/save","/v1/cafeteria/update","/v1/cafeteria/delete",
                                "v1/cafeteria/oneCafeteria",
                                "/v1/lesson/update","/v1/lesson/delete","/v1/lesson/save",
                                "/v1/log/**",
                                "/v1/staff/**",
                                "/v1/student/**",
                                "/api/export"
                        ).hasRole("EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(staffService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

}
