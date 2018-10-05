package backend.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Class activity record represents finished and recorded activity.
 * @author pmikova
 */
public class ActivityRecord {

    private SportActivity activityType;
    private Duration duration; //is computed
    private int distance; //
    private double averageSpeed;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID id;


    /**
     * Activity record constructor.
     * @param distance distance of given activity in kilometers
     * @param startTime start time of the activity
     * @param endTime end time of the activity
     */
    public ActivityRecord(SportActivity activityType, int distance, LocalDateTime startTime, LocalDateTime endTime){
        this.distance = distance;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        setDuration(startTime, endTime);
        setAverageSpeed(distance, this.duration);
        this.id = UUID.randomUUID();

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

// generated code

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityRecord that = (ActivityRecord) o;
        return distance == that.distance &&
                Double.compare(that.averageSpeed, averageSpeed) == 0 &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, distance, averageSpeed, startTime, endTime, id);
    }

    public Duration getDuration() {
        return duration;
    }

    public int getDistance() {
        return distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    // setters are and WILL NOT be present, as the ActivityRecord should not be editable
}
