package cz.muni.fi.PA165.tracker.configuration;

import cz.muni.fi.PA165.tracker.dto.UserDTO;
import cz.muni.fi.PA165.tracker.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        List<UserDTO> users = userFacade.getAll();
        for (UserDTO user : users) {
            auth.inMemoryAuthentication()
                    .passwordEncoder(passwordEncoder)
                    .withUser(user.getEmail())
                    .password(user.getEmail())
                    .roles(userFacade.isAdministrator(user) ? "ADMIN" : "USER");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()


                .antMatchers(WebUris.URL_LOGIN + "*").permitAll()
                .antMatchers(WebUris.NOT_FOUND).permitAll()
                .anyRequest().authenticated()

                // Login
                .and()
                .formLogin()
                .loginPage(WebUris.URL_LOGIN)
                .loginProcessingUrl(WebUris.URL_LOGIN)
                .failureUrl(WebUris.URL_LOGIN + "?error=true")
                .usernameParameter("user_login").passwordParameter("user_password")
                .defaultSuccessUrl(WebUris.URL_HOME, true)
                .permitAll()

                // Logout
                .and()
                .logout()
                .logoutUrl(WebUris.URL_LOGOUT)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderImpl();
    }
}
