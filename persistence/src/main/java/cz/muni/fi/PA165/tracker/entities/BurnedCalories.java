package cz.muni.fi.PA165.tracker.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * This class represent amount of burned calories for some recorded activity.
 * @author HonzaOstrava
 */
@Entity
@Table(name = "BurnedCalories")
public class BurnedCalories {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private User user;

    @NotNull
    private ActivityRecord activityRecord;

    @NotNull
    private int burnedCalories;

    @NotNull
    private double actualWeight;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ActivityRecord getActivityRecord() {
        return activityRecord;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setActivityRecord(ActivityRecord activityRecord) {
        this.activityRecord = activityRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BurnedCalories that = (BurnedCalories) o;
        return burnedCalories == that.burnedCalories &&
                Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(activityRecord, that.activityRecord) &&
                Objects.equals(actualWeight, that.actualWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, activityRecord, burnedCalories, actualWeight);
    }
}
