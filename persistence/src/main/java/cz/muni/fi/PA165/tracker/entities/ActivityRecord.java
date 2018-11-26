package cz.muni.fi.PA165.tracker.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Class activity record represents finished and recorded activity.
 * @author pmikova 433345
 */
@Entity
@Table(name = "activityrecord")
public class ActivityRecord {

    @NotNull
    @Column(nullable = false)
    private Duration duration;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private SportActivity sportActivity;

    @Min(0)
    private int distance;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private double averageSpeed;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime startTime;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SportActivity getSportActivity() {
        return sportActivity;
    }

    public void setSportActivity(SportActivity sportActivity) {
        this.sportActivity = sportActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityRecord)) return false;
        ActivityRecord that = (ActivityRecord) o;
        return  Double.compare(that.getAverageSpeed(), getAverageSpeed()) == 0 &&
                Objects.equals(getDuration(), that.getDuration()) &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageSpeed, duration, startTime, endTime);
    }
}
