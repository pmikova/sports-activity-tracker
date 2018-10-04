package backend.entities;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Class activity record represents finished and recorded activity.
 * @author pmikova
 */
public class ActivityRecord {

    //private Activity activityType
    private Duration duration; //is computed
    private int distance; //
    private double averageSpeed;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    /**
     * Activity record constructor.
     * @param distance distance of given activity in kilometers
     * @param startTime start time of the activity
     * @param endTime end time of the activity
     */
    public ActivityRecord(int distance, LocalDateTime startTime, LocalDateTime endTime){
        this.distance = distance;
        this.startTime = startTime;
        this.endTime = endTime;
        setDuration(startTime, endTime);
        setAverageSpeed(distance, this.duration);

    }

    /**
     * This method counts the duration from two parameters
     * @param startTime start time of the activity
     * @param endTime finish time of the activity
     */
    private void setDuration(LocalDateTime startTime, LocalDateTime endTime){
        if (startTime == null || endTime == null){
            duration = null;
            //TODO decide if throw and exception or accept null as arg
        }
        else{
        this.duration = Duration.between(startTime, endTime);}
    }

    /**
     * Counts the distance in kilometers per hour
     * @param distance in kilometers
     */
    private void setAverageSpeed(int distance, Duration duration){
        long seconds = duration.getSeconds();
        long meters = distance * 1000;
        int mph = (int) Long.divideUnsigned(meters, seconds);
        double kph = mph * 3.6;
        this.averageSpeed = kph;


    }

}
