package cz.muni.fi.PA165.tracker.mvc.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.inject.Inject;

/**
 * Security configuration for the Sports activity tracker
 * @author pmikova 43345
 */
@Configuration
@EnableWebSecurity
public class TrackerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(TrackerSecurityConfiguration.class);

    @Inject
    private AuthProvider authenticationProvider;

    /**
     * Inject our custom authentication provider.
     */
    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        log.debug("Registering authentication provider");
        auth.authenticationProvider(authenticationProvider);
    }


    /**
     * Configure URLs.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/activities/create/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/activities/update/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/activities/remove/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/activities/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/records/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/settings/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/users/remove/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/users/makeAdministrator/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/users/makeRegularUser/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/users/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/statistics").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error=invalidLoginAttempt")
                .usernameParameter("user").passwordParameter("pass")
                .and()
                .logout().logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .csrf().disable();

    }
}
