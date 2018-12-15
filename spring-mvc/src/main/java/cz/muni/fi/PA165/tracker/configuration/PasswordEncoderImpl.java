package cz.muni.fi.PA165.tracker.configuration;

import cz.muni.fi.PA165.tracker.dto.UserAuthenticationDTO;
import cz.muni.fi.PA165.tracker.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {

    @Autowired
    private UserFacade userFacade;

    @Override
    public String encode(CharSequence login) {
        return login.toString();
    }

    @Override
    public boolean matches(CharSequence password, String login) {
        UserAuthenticationDTO userAuthenticateDTO = new UserAuthenticationDTO();
        userAuthenticateDTO.setId(userFacade.getByEmail(login).getId());
        userAuthenticateDTO.setPasswordHash(password.toString());
        try {
            return userFacade.logIn(userAuthenticateDTO);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}