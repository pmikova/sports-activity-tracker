package cz.muni.fi.PA165.tracker.mvc.configuration;


import cz.muni.fi.PA165.tracker.facade.UserFacade;
import cz.muni.fi.PA165.tracker.dto.UserAuthenticationDTO;
import cz.muni.fi.PA165.tracker.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuthProvider implements AuthenticationProvider {

    @Inject
    private UserFacade userFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDTO user = null;

        try {
            user = userFacade.getByEmail(email);
        } catch (Exception e) {
            return null; //we can not do anything when user doesn't exist
        }

        UserAuthenticationDTO authData = new UserAuthenticationDTO(user.getId(), password);

        if (userFacade.logIn(authData)) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            if (userFacade.isAdministrator(user)) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return authClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

