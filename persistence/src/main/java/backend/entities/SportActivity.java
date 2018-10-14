package backend.entities;


import java.util.Vector;

/**
 * This class represents a sports activity.
 * @author Dominik-Bujna
 */
public class SportActivity {


    /*

    each activity has its name
    some of them should contain distance information, which can be specified
    some of the activities have different intensity levels or style of performing, which is expressed by the categories


     */
    private String activityName;
    private boolean hasDistance;
    private boolean hasCategories;
    private Vector<String> categories;

    public SportActivity(String activityName, boolean hasDistance, boolean hasCategories, Vector<String> categories) {
        this.activityName = activityName;
        this.hasDistance = hasDistance;
        this.hasCategories = hasCategories;
        this.categories = categories;
    }

    public SportActivity(String activityName, boolean hasDistance) {
        this.activityName = activityName;
        this.hasDistance = hasDistance;
        this.hasCategories = false;
    }


    public boolean hasDistance() {
        return hasDistance;
    }

    public boolean hasCategories() {
        return hasCategories;
    }

    public Vector<String> getCategories() {
        return categories;
    }

    public String getActivityName() {
        return activityName;
    }






}
