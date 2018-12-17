package cz.muni.fi.PA165.tracker.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
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
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    private ActivityRecord activityRecord;

    @NotNull
    @Min(0)
    private int burnedCalories;

    @NotNull
    @Min(1)
    private double actualWeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setActivityRecord(ActivityRecord activityRecord) {
        this.activityRecord = activityRecord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BurnedCalories)) return false;
        BurnedCalories that = (BurnedCalories) o;
        return getBurnedCalories() == that.getBurnedCalories() &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getActivityRecord(), that.getActivityRecord()) &&
                getActualWeight() == that.getActualWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, activityRecord, burnedCalories, actualWeight);
    }
}
