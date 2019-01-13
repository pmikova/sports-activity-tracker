package cz.muni.fi.PA165.tracker.dto;

import cz.muni.fi.PA165.tracker.entities.SportActivity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO for creating Activity Record.
 * @author Dominik-Bujna
 */
public class ActivityRecordCreateDTO {

    public UserDTO getUser() {
        return user;
    }

    public void setUser(Long userId) {
        this.user = user;
    }

    public SportActivityDTO getSportActivity() {
        return sportActivityDTO;
    }

    public void setSportActivityId(SportActivity sportActivity) {
        this.sportActivityDTO = sportActivityDTO;
    }

    @NotNull
    private UserDTO user;

    @NotNull
    private SportActivityDTO sportActivityDTO;

    private Duration duration;

    private int distance;

    private double averageSpeed;


    @DateTimeFormat(pattern = "dd.MM.YYYY HH:mm")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "dd.MM.YYYY HH:mm")
    private LocalDateTime endTime;



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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityRecordCreateDTO)) return false;
        ActivityRecordCreateDTO that = (ActivityRecordCreateDTO) o;
        return getDistance() == that.getDistance() &&
                Double.compare(that.getAverageSpeed(), getAverageSpeed()) == 0 &&
                getUser().equals(that.getUser()) &&
                getSportActivity().equals(that.getSportActivity()) &&
                getDuration().equals(that.getDuration()) &&
                getStartTime().equals(that.getStartTime()) &&
                getEndTime().equals(that.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getSportActivity(), getDuration(), getDistance(), getAverageSpeed(), getStartTime(), getEndTime());
    }
}
