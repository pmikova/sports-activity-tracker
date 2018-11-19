package cz.muni.fi.PA165.tracker.dto;

import java.util.Objects;

/**
 * DTO for User.
 * @author pmikova 433345
 */
public class UserDTO extends UserCreateDTO{

    private Long id;

    public UserDTO(){

    }

    public UserDTO(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //TODO fix the methods according to the business "logic"
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getId(), userDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
