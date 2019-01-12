package cz.muni.fi.PA165.tracker.dto;

import java.util.Objects;

/**
 * DTO for Burned Calories.
 * @author HonzaOstrava
 */
public class BurnedCaloriesDTO {

    private Long id;

    private UserDTO user;

    private Long activityRecordId;

    private int burnedCalories;

    private double actualWeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getActivityRecordId() {
        return activityRecordId;
    }

    public void setActivityRecordId(Long activityRecordId) {
        this.activityRecordId = activityRecordId;
    }

    public int getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(int burnedCalories) {
        this.burnedCalories = burnedCalories;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BurnedCaloriesDTO)) return false;
        BurnedCaloriesCreateDTO that = (BurnedCaloriesCreateDTO) o;
        return getBurnedCalories() == that.getBurnedCalories() &&
                Double.compare(that.getActualWeight(), getActualWeight()) == 0 &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getActivityRecordId(), that.getActivityRecordId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getActivityRecordId(), getBurnedCalories(), getActualWeight());
    }
}
