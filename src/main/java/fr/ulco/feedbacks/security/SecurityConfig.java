package fr.ulco.feedbacks.security;

import fr.ulco.feedbacks.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.config.http.SessionCreationPolicy.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

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
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
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