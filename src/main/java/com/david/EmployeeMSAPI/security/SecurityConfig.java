package com.david.EmployeeMSAPI.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(@Qualifier("appServiceImpl") UserDetailsService userDetailsService,
                          BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CustomAuthFilter customAuthFilter = new CustomAuthFilter(authenticationManagerBean());
        customAuthFilter.setFilterProcessesUrl("/api/login");

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/api/login", "/api/user/register","/api/user/token/refreshToken/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**", "/swagger-resources","/swagger-resources/**", "/configuration/ui", "/configuration/security", "/v2/api-docs", "/v3/api-docs").permitAll()
                .and()
                .authorizeRequests().antMatchers(GET, /*"/api/user/**",*/ "/api/user/getUserById/**").hasAuthority("ROLE_USER")
                .and()
                .authorizeRequests().antMatchers(POST,  "/api/user/saveRole/**", "/api/user/addRoleToUser" ,"/api/user/saveUser", "/api/user/saveSalary", "/api/user/saveJobDept", "/api/user/deleteUser/**" ).hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().antMatchers(GET,  "/getWalletById/**", "/api/user/getUserById/**").hasAuthority("ROLE_ADMIN")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .addFilter(customAuthFilter);

        http
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
