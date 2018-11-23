/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import java.util.List;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author Wezzy
 */
public interface IRatingRepository
{

    /**
     * Persists the given rating.
     *
     * @param rating the rating to persist.
     */
    void createRating(Rating rating) throws IOException;

    /**
     * Removes the given rating. Copy of updateRating, da den eneste forskel er
     * hvad vi skriver til filen i sidste ende
     *
     * @param rating
     */
    void deleteRating(Rating rating) throws IOException;

    /**
     * Gets all ratings from all users.
     *
     * @return List of all ratings.
     */
    List<Rating> getAllRatings() throws IOException;

    /**
     * Get all ratings from a specific user.
     *
     * @param user The user
     * @return The list of ratings.
     */
    List<Rating> getRatings(User user) throws IOException;

   

    /**
     * Updates the rating to reflect the given object. Assumes that the source
     * file is in order by movie ID, then User ID..
     *
     * @param rating The updated rating to persist.
     * @throws java.io.IOException
     */
    void updateRating(Rating rating) throws IOException;
    
}
