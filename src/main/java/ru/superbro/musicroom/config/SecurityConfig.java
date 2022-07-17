package ru.superbro.musicroom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.superbro.musicroom.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable(); // TODO enable csrf
        http.authorizeRequests()
            .antMatchers("/", "login", "/logout")
            .permitAll();
        http.authorizeRequests()
            .antMatchers("/file/*", "/public")
            .permitAll();
        http.authorizeRequests()
            .antMatchers("/playlists", "/playlist/*")
            .hasRole("USER");
        http.authorizeRequests()
            .and()
            .exceptionHandling()
            .accessDeniedPage("/error");
        http.authorizeRequests()
            .and()
            .formLogin()
            .loginProcessingUrl("/login-check")
            .loginPage("/login")
            .defaultSuccessUrl("/home")
            .failureUrl("/error")
            .usernameParameter("username")
            .passwordParameter("password");
        http.authorizeRequests()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
           .antMatchers("/scripts/**", "/styles/**", "/images/**");
    }

}
