package fr.ulco.feedbacks.security;

import fr.ulco.feedbacks.filter.CustomAuthenticationFilter;
import fr.ulco.feedbacks.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    /*
        https://jwt.io/

        Ex avec un access token : access_token
        eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjbGVtZW50Iiwicm9sZXMiOlsiVVNFUiJdLCJpc3MiOiJodHRwOi8vMTkyLjE2OC45OS4xMDA6ODA4MC9sb2dpbiIsImV4cCI6MTY1MjU2NjQ1N30.TFOClAKDGUKv_01UqulH16jd7szz8og4Rwe7-sUoT_w

         {
          "sub": "clement",
          "roles": [
            "USER"
          ],
          "iss": "http://192.168.99.100:8080/login",
          "exp": 1652566457
        }
    */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        // ORDER for RIGHTS is important :
        http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll();
        http.authorizeRequests().antMatchers("/auth/login/**", "/auth/user/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/auth/user/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/form/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/auth/users/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        // Check Bearer before each request
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    // get l'authentication manager de la class extends : WebSecurityConfigurerAdapter
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}