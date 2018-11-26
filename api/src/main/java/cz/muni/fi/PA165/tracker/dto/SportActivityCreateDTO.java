package cz.muni.fi.PA165.tracker.dto;

import java.util.Objects;

/**
 * DTO for Sport Activity Create
 * @author Dominik-Bujna
 */

public class SportActivityCreateDTO {

    private String activityName;

    private double burnedCaloriesPerHour;

    private double weightCoefficient;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }


    public double getBurnedCaloriesPerHour() {
        return burnedCaloriesPerHour;
    }

    public void setBurnedCaloriesPerHour(double burnedCaloriesPerHour) {
        this.burnedCaloriesPerHour = burnedCaloriesPerHour;
    }

    public double getWeightCoefficient() {
        return weightCoefficient;
    }

    public void setWeightCoefficient(double coefficient) {
        this.weightCoefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportActivityCreateDTO)) return false;
        SportActivityCreateDTO that = (SportActivityCreateDTO) o;
        return Double.compare(that.getBurnedCaloriesPerHour(), getBurnedCaloriesPerHour()) == 0 &&
                Double.compare(that.getWeightCoefficient(), getWeightCoefficient()) == 0 &&
                getActivityName().equals(that.getActivityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActivityName(), getBurnedCaloriesPerHour(), getWeightCoefficient());
    }
}
