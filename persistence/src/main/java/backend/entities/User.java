package backend.entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a user in our system
 * @author pmikova
 */

@Entity
@Table(name = "Users")
public class User {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private int weight;
    @NotNull
    private Gender gender;
    @NotNull
    private Date birthdate;
    @NotNull

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    /**
     * Enum gender specifies user gender.
     */
    public enum Gender{ MALE, FEMALE};


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return weight == user.weight &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                gender == user.gender &&
                Objects.equals(birthdate, user.birthdate) &&
                Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, weight, gender, birthdate, id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getWeight() {
        return weight;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    //TODO age must be generated from date of birth, will not have setter
    public int getAge() {
        throw new NotImplementedException();
    }

    //TODO should contain also encrypted password.


}
