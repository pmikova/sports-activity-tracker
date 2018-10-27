package cz.muni.fi.PA165.tracker.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * This class represents a sports activity.
 * @author Dominik-Bujna
 */

@Entity
@Table(name = "SportActivity")
public class SportActivity {


    /*

    each activity has its name
    some of them should contain distance information, which can be specified
    some of the activities have different intensity levels or style of performing, which is expressed by the categories

     */
    @NotNull
    @Column(length = 30, nullable = false)
    private String activityName;

    @NotNull
    public String getActivityName() {
        return activityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SportActivity toCompare = (SportActivity) o;
        return this.activityName == toCompare.getActivityName();
    }

    //TODO: possible changes
    @Override
    public int hashCode() {
        return Objects.hash(this.activityName);
    }



}
