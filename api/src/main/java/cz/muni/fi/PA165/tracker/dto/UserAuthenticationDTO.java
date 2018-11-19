package cz.muni.fi.PA165.tracker.dto;

import javax.validation.constraints.NotNull;

/**
 * DTO for user Authentication
 * @author pmikova 433345
 */
public class UserAuthenticationDTO {

    @NotNull
    private Long id;

    @NotNull
    private String passwordHash;

    public UserAuthenticationDTO(){

    }

    public UserAuthenticationDTO(Long id, String pw){
        this.id = id;
        this.passwordHash = pw;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
