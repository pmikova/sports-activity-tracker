package backend.dao;

import backend.entities.BurnedCalories;

public interface BurnedCaloriesDAO {


    /**
     * This method should contain prepared statement for REPLACE
     * VmDecompilerStatus
     * @param burnedCalories VmDecompilerStatus to replace
     */
    void addOrReplaceBurningCalories(BurnedCalories burnedCalories);

    /**
     * This method should contain prepared statement for QUERY
     * VmDecompilerStatus
     * @param burnedCalories serves as unique to find VmDecompilerStatus in storage
     * @return
     */
    BurnedCalories getBurnedCalories(BurnedCalories burnedCalories);
}


