package cz.muni.fi.PA165.tracker.dto;

import java.util.Map;
import java.util.Objects;

/**
 * User stat DTO class.
 * @author pmikova 433345
 */
public class UserStatDTO {

    private UserDTO userDTO;

    private int calories;

    private int caloriesWeek;

    private int caloriesMonth;

    private int activities;

    private int activitiesWeek;

    private int activitiesMonth;

    private Map<SportActivityDTO, Integer> activitiesSumUp;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getCaloriesWeek() {
        return caloriesWeek;
    }

    public void setCaloriesWeek(int caloriesWeek) {
        this.caloriesWeek = caloriesWeek;
    }

    public int getCaloriesMonth() {
        return caloriesMonth;
    }

    public void setCaloriesMonth(int caloriesMonth) {
        this.caloriesMonth = caloriesMonth;
    }

    public int getActivities() {
        return activities;
    }

    public void setActivities(int activities) {
        this.activities = activities;
    }

    public int getActivitiesWeek() {
        return activitiesWeek;
    }

    public void setActivitiesWeek(int activitiesWeek) {
        this.activitiesWeek = activitiesWeek;
    }

    public int getActivitiesMonth() {
        return activitiesMonth;
    }

    public void setActivitiesMonth(int activitiesMonth) {
        this.activitiesMonth = activitiesMonth;
    }

    public Map<SportActivityDTO, Integer> getActivitiesSumUp() {
        return activitiesSumUp;
    }

    public void setActivitiesSumUp(Map<SportActivityDTO, Integer> activitiesSumUp) {
        this.activitiesSumUp = activitiesSumUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatDTO that = (UserStatDTO) o;
        return getCalories() == that.getCalories() &&
                getCaloriesWeek() == that.getCaloriesWeek() &&
                getCaloriesMonth() == that.getCaloriesMonth() &&
                getActivities() == that.getActivities() &&
                getActivitiesWeek() == that.getActivitiesWeek() &&
                getActivitiesMonth() == that.getActivitiesMonth() &&
                getUserDTO() == that.getUserDTO() &&
                getActivitiesSumUp()== that.getActivitiesSumUp();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserDTO(), getCalories(), getCaloriesWeek(),
                getCaloriesMonth(), getActivities(), getActivitiesWeek(), getActivitiesMonth(), getActivitiesSumUp());
    }
}
