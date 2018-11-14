/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class RatingDAO
{

    private static final String RATING_SOURCE = "data/user_ratings_small";

    private static final int RECORD_SIZE = Integer.BYTES * 3;

    /**
     * Persists the given rating.
     *
     * @param rating the rating to persist.
     */
    public void createRating(Rating rating) throws IOException
    {
        //Blev nødt til at skrive den korte, men tunge måde at gøre denne på.
        //Der er ikke en måde at lave nyt data i midten af den hele, uden at skulle shifte det hele
        //Og så bliver det bedre at bruge getAllRatings sort end at lave en ny
        //List<Rating> ratings = getAllRatings();
        int id = rating.getMovie();
        int user = rating.getUser();
        int rt = rating.getRating();
        boolean hasCreatedRating = false;
        boolean hasDuplicate = false;
        MovieDAO movie = new MovieDAO();
        try (RandomAccessFile raf = new RandomAccessFile(RATING_SOURCE, "rw"))
        {
            List<Rating> ratings = getAllRatings();

            int size = ratings.size();
            for (int i = 0; i < ratings.size(); i++)
            {

                if (id == ratings.get(i).getMovie() && user == ratings.get(i).getUser())
                {
                    hasDuplicate = true;
                }
                if (hasDuplicate == false)
                {

                    if (id == ratings.get(i).getMovie() && user < ratings.get(i).getUser() && hasCreatedRating == false)
                    {
                        ratings.add(i, rating);
                        hasCreatedRating = true;

                    } else if (i != 0 && ratings.get(i - 1).getMovie() < ratings.get(i).getMovie() && hasCreatedRating == false && ratings.get(i - 1).getMovie() == id)
                    {
                        ratings.add(i, rating);
                        hasCreatedRating = true;

                    }
                    if (i == (size - 1) && hasCreatedRating == false)
                    {
                        ratings.add(i, rating);
                        hasCreatedRating = true;

                    }

                }
                raf.writeInt(ratings.get(i).getMovie());
                raf.writeInt(ratings.get(i).getUser());
                raf.writeInt(ratings.get(i).getRating());
            }
//            int i = 0;
//            int rafNextId = -1;
//            int rafNextUser = -1;
//            int rafNextRating = -1;
//            while (i < raf.length())
//            {
//                int rafId = raf.readInt();
//                int rafUser = raf.readInt();
//                int rafRating = raf.readInt();
//                if (hasCreatedRating == false)
//                {
//
//                    if (id == rafId && user > rafUser)
//                    {
//                        raf.seek(raf.getFilePointer() - RECORD_SIZE);
//                        raf.writeInt(id);
//                        raf.writeInt(user);
//                        raf.writeInt(rt);
//                        rafNextId = rafId;
//                        rafNextUser = rafUser;
//                        rafNextRating = rafRating;
//                        hasCreatedRating = true;
//                        System.out.println("in here1");
//                    } else
//                    {
//                        raf.seek(raf.getFilePointer() - RECORD_SIZE);
//                        raf.writeInt(rafId);
//                        raf.writeInt(rafUser);
//                        raf.writeInt(rafRating);
//                        System.out.println("in here2");
//                    }
//                } else
//                {
//                    System.out.println("in here3");
//                    raf.seek(raf.getFilePointer() - RECORD_SIZE);
//                    raf.writeInt(rafNextId);
//                    raf.writeInt(rafNextUser);
//                    raf.writeInt(rafNextRating);
//                    rafId = rafNextId;
//                    rafUser = rafNextUser;
//                    rafRating = rafNextRating;
//
//                }
//
////            }
//            }
        }
    }

    /**
     * Updates the rating to reflect the given object. Assumes that the source
     * file is in order by movie ID, then User ID..
     *
     * @param rating The updated rating to persist.
     * @throws java.io.IOException
     */
    public void updateRating(Rating rating) throws IOException
    {
        try (RandomAccessFile raf = new RandomAccessFile(RATING_SOURCE, "rw"))
        {
            long totalRatings = raf.length();
            long low = 0;
            long high = ((totalRatings - 1) / RECORD_SIZE) * RECORD_SIZE;
            while (high >= low) //Binary search of movie ID
            {
                long pos = (((high + low) / 2) / RECORD_SIZE) * RECORD_SIZE;
                raf.seek(pos);
                int movId = raf.readInt();
                int userId = raf.readInt();

                if (rating.getMovie() < movId) //We did not find the movie.
                {
                    high = pos - RECORD_SIZE; //We half our problem size to the upper half.
                } else if (rating.getMovie() > movId) //We did not find the movie.
                {
                    low = pos + RECORD_SIZE; //We half our problem size (Just the lower half)
                } else //We found a movie match, not to search for the user:
                {
                    if (rating.getUser() < userId) //Again we half our problem size
                    {
                        high = pos - RECORD_SIZE;
                    } else if (rating.getUser() > userId) //Another half sized problem
                    {
                        low = pos + RECORD_SIZE;
                    } else //Last option, we found the right row:
                    {
                        raf.write(rating.getRating()); //Remember the to reads at line 60,61. They positioned the filepointer just at the ratings part of the current record.
                        return; //We return from the method. We are done here. The try with resources will close the connection to the file.
                    }
                }
            }
        }
        throw new IllegalArgumentException("Rating not found in file, can't update!"); //If we reach this point we have been searching for a non-present rating.
    }

    /**
     * Removes the given rating. Copy of updateRating, da den eneste forskel er
     * hvad vi skriver til filen i sidste ende
     *
     * @param rating
     */
    public void deleteRating(Rating rating) throws IOException
    {
        
        try (RandomAccessFile raf = new RandomAccessFile(RATING_SOURCE, "rw"))
        {

            List<Rating> ratings = getAllRatings();
            raf.setLength(0);
            for(Rating r : ratings)
            {
                if(!(r.getMovie() == rating.getMovie() && r.getUser() == rating.getUser()))
                {
  
                raf.writeInt(r.getMovie());
                raf.writeInt(r.getUser());
                raf.writeInt(r.getRating());

                    
                
                }

            }

        }
    }

    /**
     * Gets all ratings from all users.
     *
     * @return List of all ratings.
     */
    public List<Rating> getAllRatings() throws IOException
    {
        List<Rating> allRatings = new ArrayList<>();
        byte[] all = Files.readAllBytes(new File(RATING_SOURCE).toPath()); //I get all records as binary data!
        for (int i = 0; i < all.length; i += RECORD_SIZE)
        {
            int movieId = ByteBuffer.wrap(all, i, Integer.BYTES).order(ByteOrder.BIG_ENDIAN).getInt();
            int userId = ByteBuffer.wrap(all, i + Integer.BYTES, Integer.BYTES).order(ByteOrder.BIG_ENDIAN).getInt();
            int rating = ByteBuffer.wrap(all, i + Integer.BYTES * 2, Integer.BYTES).order(ByteOrder.BIG_ENDIAN).getInt();
            Rating r = new Rating(movieId, userId, rating);
            allRatings.add(r);
        }
        Collections.sort(allRatings, (Rating o1, Rating o2)
                ->
        {
            int movieCompare = Integer.compare(o1.getMovie(), o2.getMovie());
            return movieCompare == 0 ? Integer.compare(o1.getUser(), o2.getUser()) : movieCompare;
        });
        return allRatings;
    }

    /**
     * Get all ratings from a specific user.
     *
     * @param user The user
     * @return The list of ratings.
     */
    public List<Rating> getRatings(User user) throws IOException
    {
        List<Rating> allRatings = new ArrayList();
        try (RandomAccessFile raf = new RandomAccessFile(RATING_SOURCE, "rw"))
        {

            int i = 0;
            while (i < raf.length())
            {

                try
                {
                    int movId = raf.readInt();
                    int userId = raf.readInt();
                    int rate = raf.readInt();
                    //System.out.println("movid : " + movId + " userId" + userId + " rate: " + rate);
                    if (user.getId() == userId)
                    {
                        Rating rating = new Rating(movId, userId, rate);
                        allRatings.add(rating);
                    }

                } catch (Exception ex)
                {
                    //Do nothing. Optimally we would log the error.
                }

                i += RECORD_SIZE;
            }

        }
        return allRatings;

    }

    private Rating getRatingFromLine(String line) throws NumberFormatException
    {
        String[] cols = line.split(",");
        int movId = Integer.parseInt(cols[0]);
        int userId = Integer.parseInt(cols[1]);
        int rating = Integer.parseInt(cols[2]);
        return new Rating(movId, userId, rating);
    }

    public void makeSmallFile() throws IOException
    {
        List<Rating> allRatings = getAllRatings();
        //List<Rating> smallRating = new ArrayList();
        try (RandomAccessFile raf = new RandomAccessFile("data/user_ratings_small", "rw"))
        {
            for (int i = 0; i < 10000; i++)
            {
                raf.writeInt(allRatings.get(i).getMovie());
                raf.writeInt(allRatings.get(i).getUser());
                raf.writeInt(allRatings.get(i).getRating());
            }
        }
    }

}
