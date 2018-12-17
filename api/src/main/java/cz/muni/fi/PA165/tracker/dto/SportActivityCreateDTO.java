package cz.muni.fi.PA165.tracker.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * DTO for Sport Activity Create
 * @author Dominik-Bujna
 */

public class SportActivityCreateDTO {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "The name can only contain letters")
    private String activityName;

    @NotNull
    @Min(value = 1, message = "Must be bigger than 0")
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
