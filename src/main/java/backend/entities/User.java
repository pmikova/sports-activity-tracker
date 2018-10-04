package backend.entities;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Date;

public class User {

    private String name;
    private String surname;
    private int age;
    private int weight;
    private Gender gender;
    private Date birthdate;



    public User(String name, String surname, Date birthdate, int weight, Gender gender){
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.gender = gender;
        this.birthdate = birthdate;

    }



    public enum Gender{ MALE, FEMALE};

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        throw new NotImplementedException();
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
}
