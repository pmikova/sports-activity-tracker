package cz.muni.fi.PA165.tracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * This class represents a user in our system
 * @author pmikova
 */

@Entity
@Table(name = "Users")
public class User {

    @NotNull
    @Column (length = 30, nullable = false)
    private String name;
    @NotNull
    @Column (length = 30, nullable = false)
    private String surname;
    @NotNull
    @Column (length = 5, nullable = false)
    private int weight;
    @NotNull
    private Gender gender;
    @NotNull
    private Calendar birthdate;
    @NotNull
    @Column(length = 25, unique = true, nullable = false)
    private String email;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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
                Objects.equals(email, user.email) &&
                Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
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

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Calendar getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Calendar birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    //TODO should contain also encrypted password, username will be email

    //todo can enum be inside or should i put it out?
    /**
     * Enum gender specifies user gender.
     */
    public enum Gender{ MALE, FEMALE};
}
