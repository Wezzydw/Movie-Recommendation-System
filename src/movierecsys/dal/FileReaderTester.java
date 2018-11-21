/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
       // mitigateMovies();
        //mitigateUsers();
        mitigateRatings();
//        MovieDbDao db = new MovieDbDao();
//        db.getAllMovies();
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

//        UserDAO userDAO = new UserDAO();
//        for (User m : userDAO.getAllUsers())
//        {
//            System.out.println("User: " + m.getId() + m.getName());
//        }
//        
////        userDAO.getUser(2905);
////        System.out.println("User: " +userDAO.getUser(2905).getName());
//
//        System.out.println("Antal users: " + userDAO.getAllUsers().size());
//        User n = new User(7, "Georgi Facellie");
//        userDAO.updateUser(n);
        }
    /**
     *
     * @throws IOException
     */
    public static void mitigateMovies() throws IOException
        {
        
        DbConnectionProvider ds = new DbConnectionProvider();
        
        MovieDAO mvDAO = new MovieDAO();
        List<Movie> movies = mvDAO.getAllMovies();
        
        try (Connection con = ds.getConnection())
        {
            
            Statement statement = con.createStatement();
            
            for (Movie movie : movies)
            {
                String sql = "INSERT INTO MOVIE (id,year,title) VALUES("
                        + movie.getId() + ","
                        + movie.getYear() + ",'"
                        + movie.getTitle().replace("'", "") + "');";
                
                System.out.println(sql);
                int i = statement.executeUpdate(sql);
                //INSERT INTO MOVIE (id,year,title) VALUES (1,2018,Venom);
                System.out.println("Affected row = " + i);            
            }
        } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
    
    
    
        }
    
    public static void mitigateUsers() throws IOException
    {
        DbConnectionProvider ds = new DbConnectionProvider();

        List<User> users = new UserDAO().getAllUsers();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            int counter = 0;
            for (User user : users)
            {
                String sql = "INSERT INTO [User] (id,name) VALUES("
                        + user.getId() + ",'"
                        + user.getName() + "');";
                statement.addBatch(sql);
                counter++;
                if (counter % 1000 == 0)
                {
                    statement.executeBatch();
                    System.out.println("Added 1000 users.");
                }
            }
            if (counter % 1000 != 0)
            {
                statement.executeBatch();
                System.out.println("Added final batch of users.");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static void mitigateRatings() throws IOException
    {
        List<Rating> allRatings = new RatingDAO().getAllRatings();
        DbConnectionProvider ds = new DbConnectionProvider();
        try (Connection con = ds.getConnection())
        {
            Statement st = con.createStatement();
            int counter = 0;
            for (Rating rating : allRatings)
            {
                String sql = "INSERT INTO Rating (movieId, userId, rating) VALUES ("
                        + rating.getMovie() + ","
                        + rating.getUser() + ","
                        + rating.getRating()
                        + ");";
                st.addBatch(sql);
                counter++;
                if (counter % 10000 == 0)
                {
                    st.executeBatch();
                    System.out.println("Added " + counter + " ratings.");
                }
            }
            if (counter % 10000 != 0)
            {
                st.executeBatch();
                System.out.println("Added final batch of ratings.");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}

      
        
     

  
