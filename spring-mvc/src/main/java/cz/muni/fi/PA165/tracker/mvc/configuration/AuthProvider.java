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

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
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
        System.out.println("USER FOUND");
        UserAuthenticationDTO authData = new UserAuthenticationDTO(user.getId(), password);

        if (userFacade.logIn(authData)) {
            System.out.println("EXISTS!");
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            if (userFacade.isAdministrator(user)) {
                System.out.println("IS ADMIN");
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                System.out.println("IS USER");
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authClass));
    }
}

