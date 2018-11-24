package cz.muni.fi.PA165.tracker.service;

import cz.muni.fi.PA165.tracker.entities.SportActivity;
import cz.muni.fi.PA165.tracker.entities.User;

import java.time.LocalDate;
import java.util.Map;

/**
 * Class with non-basic services over user entity.
 * @author pmikova 433345
 */
public interface UserStatService {

        /**
         * Get all burned calories for given User.
         * @param user given user to count the calories
         * @return number of burned calories
         */
        float getAllCalories(User user);

        /**
         * Get all burned calories in given period of time.
         * @param user given user to count the calories for
         * @param start start time and day
         * @param end end time and day
         * @return number of burned calories
         */
        float getAllCalories(User user, LocalDate start, LocalDate end);

        /**
         * Get number of activities performed by user.
         * @param user given user to count the activites
         * @return number of activities performed by user
         */
        int getNumberOfActivities(User user);

        /**
         * Get number of activities performed in given period of time.
         * @param user given user to count the activities
         * @param start start time and day
         * @param end end time and day
         * @return number of activities performed by user
         */
        int getNumberOfActivities(User user, LocalDate start, LocalDate end);

        /**
         * Count all activities for user, by type.
         * @param user user to count the activities for
         * @return map of activities and number of each activity performed
         */
        Map<SportActivity, Integer> countActivitiesForUser(User user);

        /**
         * Count calories burned by each activity by user.
         * @param user user to count the calories for
         * @return map of activities and number with burned calories
         */
        Map<SportActivity, Float> countCaloriesForEachSportForUser(User user);
    }
