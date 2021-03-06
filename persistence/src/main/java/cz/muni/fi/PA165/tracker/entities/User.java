package cz.muni.fi.PA165.tracker.entities;

import cz.muni.fi.PA165.tracker.enums.Gender;
import cz.muni.fi.PA165.tracker.enums.UserType;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a user in our system
 * @author pmikova
 */

@Entity
@Table(name = "users")
public class User {
    @Enumerated
    @NotNull
    @Column(nullable = false)
    private UserType userType;

    @NotNull
    @Column (length = 30, nullable = false)
    private String name;

    @NotNull
    @Column (length = 30, nullable = false)
    private String surname;

    @NotNull
    @Min(1)
    @Column(nullable = false)
    private long weight;

    @Enumerated
    @NotNull
    @Column(nullable = false)
    private Gender gender;

    @NotNull
    @Column(nullable = false)
    private LocalDate birthdate;

    @NotNull
    @Column(length = 25, unique = true, nullable = false)
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotNull
    @Column(nullable = false)
    private String passwordHash;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<ActivityRecord> activityRecords = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<BurnedCalories> burnedCalories = new ArrayList<>();

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null);
    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
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

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<ActivityRecord> getActivityRecords() {
        return Collections.unmodifiableList(activityRecords);
    }

    public void addActivityRecord(ActivityRecord activityRecord) {
        activityRecords.add(activityRecord);
    }

    public void removeActivityRecord(ActivityRecord activityRecord) {
        activityRecords.remove(activityRecord);
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
