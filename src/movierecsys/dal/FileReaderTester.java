/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author pgn
 */
public class FileReaderTester
{

    /**
     * Example method. This is the code I used to create the users.txt files.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {

//        MovieDAO movieDao = new MovieDAO();
//        movieDao.returnToBackupList();
//        Movie movie = movieDao.createMovie(2020, "Tonny og Spasserbussen 3, nu med folk"); //Only run this once, or you will get multiple entries!
//        System.out.println(movie);
//
//        movieDao.createMovie(2008, "Dannys nye bil");
//        movieDao.deleteMovie(movieDao.getMovie(17753));
//        Movie n = new Movie(17752, 2008, "Out of Order 2");
//        movieDao.updateMovie(n);
//
//        RatingDAO ratingDao = new RatingDAO();
        //ratingDao.makeSmallFile();
        //System.out.println("done");
//        for (Rating r : ratingDao.getAllRatings())
//        {
//            System.out.println("Movie: " + r.getMovie() + " User: " + r.getUser() + " Rating: " + r.getRating());
//        }
//        Rating newRating = new Rating(8, 2535052, 5);
//        Rating newRating6 = new Rating(28, 2535052, -3);
//        ratingDao.createRating(newRating);
//        ratingDao.createRating(newRating6);
//
//        Rating newRating1 = new Rating(16082, 6666666, 5);
//        Rating newRating2 = new Rating(17442, 6666666, 1);
//        Rating newRating3 = new Rating(17104, 6666666, 3);
//        Rating newRating4 = new Rating(17104, 6666666, 5);
//        Rating newRating5 = new Rating(8, 922922, 5);
//        Rating newRating7 = new Rating(8, 922922, -3);
//
//        ratingDao.createRating(newRating1);
//        ratingDao.createRating(newRating2);
//        ratingDao.createRating(newRating3);
//        ratingDao.createRating(newRating5);
//        //ratingDao.getAllRatings();
//        for (Rating r : ratingDao.getAllRatings())
//        {
//            System.out.println("Movie: " + r.getMovie() + " User: " + r.getUser() + " Rating: " + r.getRating());
//        }
//        ratingDao.updateRating(newRating7);
//        User test1 = new User(2535052, "Test 1");
//        User test2 = new User(6666666, "Test 2");
////
//        for (Rating r : ratingDao.getRatings(test1))
//        {
//            System.out.println("Movie: " + r.getMovie() + " User: " + r.getUser() + " Rating: " + r.getRating());
//        }
//        for (Rating r : ratingDao.getRatings(test2))
//        {
//            System.out.println("Movie: " + r.getMovie() + " User: " + r.getUser() + " Rating: " + r.getRating());
//        }
//        ratingDao.deleteRating(newRating1);
        
//
//        for (Rating r : ratingDao.getRatings(test2))
//        {
//            System.out.println("Movie: " + r.getMovie() + " User: " + r.getUser() + " Rating: " + r.getRating());
//        }
//                for (Rating r : ratingDao.getAllRatings())
//        {
//            System.out.println("Movie: " + r.getMovie() + " User: " + r.getUser() + " Rating: " + r.getRating());
//        }

        UserDAO userDAO = new UserDAO();
//for (User m : userDAO.getAllUsers())
//        {
//            System.out.println("User: " + u.getId() + u.getName());
//        }
        
//        userDAO.getUser(2905);
//        System.out.println("User: " +userDAO.getUser(2905).getName());

        
        User n = new User(7, " Georgi Facellie");
        userDAO.updateUser(n);
        
    }
}

      
        
     

  
