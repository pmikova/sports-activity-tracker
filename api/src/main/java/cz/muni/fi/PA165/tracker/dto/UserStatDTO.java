package cz.muni.fi.PA165.tracker.dto;

import java.util.Map;

public class UserStatDTO {

    private UserDTO userDTO;

    private float calories;

    private float caloriesWeek;

    private float caloriesMonth;

    private int activities;

    private int activitiesWeek;

    private int activitiesMonth;

    private Map<SportActivityDTO, Integer> activitiesSumUp;

    private Map<SportActivityDTO, Float> caloriesSumUp;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getCaloriesWeek() {
        return caloriesWeek;
    }

    public void setCaloriesWeek(float caloriesWeek) {
        this.caloriesWeek = caloriesWeek;
    }

    public float getCaloriesMonth() {
        return caloriesMonth;
    }

    public void setCaloriesMonth(float caloriesMonth) {
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

    public Map<SportActivityDTO, Float> getCaloriesSumUp() {
        return caloriesSumUp;
    }

    public void setCaloriesSumUp(Map<SportActivityDTO, Float> caloriesSumUp) {
        this.caloriesSumUp = caloriesSumUp;
    }

    //TODO implement hash/equals
}
