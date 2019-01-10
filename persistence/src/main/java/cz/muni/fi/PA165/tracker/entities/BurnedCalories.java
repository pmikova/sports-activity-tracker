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

    public Long getActivityRecordId() {
        return activityRecordId;
    }

    public void setActivityRecordId(Long activityRecordId) {
        this.activityRecordId = activityRecordId;
    }

    @NotNull
    private Long activityRecordId;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BurnedCalories)) return false;
        BurnedCalories that = (BurnedCalories) o;
        return getBurnedCalories() == that.getBurnedCalories() &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getActivityRecordId(), that.getActivityRecordId()) &&
                getActualWeight() == that.getActualWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, activityRecordId, burnedCalories, actualWeight);
    }
}
