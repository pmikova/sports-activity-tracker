package backend.entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a user in our system
 * @author pmikova
 */

public class User {

    private String name;
    private String surname;
    private int weight;
    private Gender gender;
    private Date birthdate;
    private UUID id;


    /**
     * Constructor to create object user.
     * @param name name of the user
     * @param surname surname of the user
     * @param birthdate date of birth
     * @param weight weight
     * @param gender gender
     */
    public User(String name, String surname, Date birthdate, int weight, Gender gender){
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.gender = gender;
        this.birthdate = birthdate;
        this.id = UUID.randomUUID();

    }

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

    // id will not have setter
    public UUID getId(){return id;}

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
