package com.example.SpringExampleJava.security;

import com.example.SpringExampleJava.services.UsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersService usersService;
    private final AuthenticationEntryPoint unauthorizedHandler;
    private final WebSecurityAuthSuccessHandler successHandler;

    public WebSecurityConfig(UsersService usersService, AuthenticationEntryPoint unauthorizedHandler, WebSecurityAuthSuccessHandler successHandler) {
        this.usersService = usersService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        "/hello"
                )
                .permitAll()
                .antMatchers(
                        HttpMethod.GET, "/user"
                ).authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").usernameParameter("email")
                .successHandler(successHandler)
                .failureHandler(new WebSecurityAuthFailureHandler())
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(new LogoutSuccessHandler()).invalidateHttpSession(true).deleteCookies("JSESSIONID");

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usersService);
        provider.setPasswordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.contentEquals(rawPassword);
            }
        });
        //provider.setPasswordEncoder(getEncoder());
        return provider;
    }

    /*@Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public AccessDecisionManager getAccessDecisionManager() {
        ArrayList<AccessDecisionVoter<?>> voters = new ArrayList<>();
        voters.add(new WebExpressionVoter());
        voters.add(new RoleVoter());
        voters.add(new AuthenticatedVoter());
        return new UnanimousBased(voters);
    }
}
