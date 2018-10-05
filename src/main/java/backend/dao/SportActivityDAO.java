package backend.dao;
import backend.ActivityId;
import backend.entities.SportActivity;

/**
 * Interface for Sport Activity Data Access Object.
 * @author pmikova
 */
public interface SportActivityDAO {

    /**
     * This method should contain prepared statement for REPLACE
     * VmDecompilerStatus
     * @param activity VmDecompilerStatus to replace
     */
    void addOrReplaceSportActivity(SportActivity activity);

    /**
     * This method should contain prepared statement for QUERY
     * VmDecompilerStatus
     * @param activityId serves as unique to find VmDecompilerStatus in storage
     * @return
     */
    SportActivity getSportsActivity(ActivityId activityId);
}
