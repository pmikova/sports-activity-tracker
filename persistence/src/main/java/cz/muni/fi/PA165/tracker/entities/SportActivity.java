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
@Table(name = "Users")
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
    private boolean hasDistance;

    @NotNull
    public boolean hasDistance() {
        return hasDistance;
    }

    @NotNull
    public String getActivityName() {
        return activityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SportActivity toCompare = (SportActivity) o;
        return this.hasDistance == toCompare.hasDistance() &&
               this.activityName == toCompare.getActivityName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.activityName, this.hasDistance);
    }



}
