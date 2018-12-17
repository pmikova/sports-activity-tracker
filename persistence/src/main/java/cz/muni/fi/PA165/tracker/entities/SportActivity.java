package cz.muni.fi.PA165.tracker.entities;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents a sports activity.
 * @author Dominik-Bujna
 */

@Entity
@Table(name = "SportActivity")
public class SportActivity {


    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 30, nullable = false)
    private String activityName;

    @NotNull
    @Min(1)
    private double burnedCaloriesPerHour;

    @NotNull
    @Min(0)
    private double weightCoefficient;

    @NotNull
    @OneToMany(mappedBy = "sportActivity")
    private List<ActivityRecord> activityRecordList = new ArrayList<>();

    public String getActivityName() {
        return activityName;
    }


    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id=id;
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
        if (!(o instanceof SportActivity)) return false;
        SportActivity that = (SportActivity) o;
        return Objects.equals(getActivityName(), that.getActivityName()) && getBurnedCaloriesPerHour() == that.getBurnedCaloriesPerHour();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getActivityName(), getBurnedCaloriesPerHour());
    }
}
