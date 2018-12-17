package cz.muni.fi.PA165.tracker.mvc.configuration;

import cz.muni.fi.PA165.tracker.facade.UserFacade;
import cz.muni.fi.PA165.tracker.dto.UserAuthenticationDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of our custom AuthenticationProvider
 * @author pmikova 433345
 */
@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserFacade userFacade;

    /**
     * Implementation of authenticate that checks user credentials.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDTO user = null;

        try {
            user = userFacade.getByEmail(email);
        } catch (Exception e) {
            return null;
        }
        UserAuthenticationDTO authData = new UserAuthenticationDTO(user.getId(), password);

        if (userFacade.logIn(authData)) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            System.out.println(user.getId().toString());
            if (userFacade.isAdministrator(user.getId())) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
        }
        return null;
    }

    /**
     * Supports method determines whether our authentication provider can authenticate user with given token.
     */
    @Override
    public boolean supports(Class<?> authClass) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authClass));
    }
}

