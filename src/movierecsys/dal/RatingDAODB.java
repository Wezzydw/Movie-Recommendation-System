/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;

/**
 *
 * @author Wezzy
 */
public class RatingDAODB implements IRatingRepository
{

    DbConnectionProvider dcp = new DbConnectionProvider();
    
    @Override
    public void createRating(Rating rating) throws IOException
    {
        int unUsedId = 0;

        try (Connection con = dcp.getConnection())
        {
            Statement statement = con.createStatement();
            int i = 0;
            for (Rating r : getAllRatings())
            {
                if (r.getRating()!= i)
                {
                    unUsedId = i;
                }
                i++;
            }
            String sql = "INSERT INTO Rating (movieId, userId, rating) VALUES("
                    + unUsedId + ","
                    + rating.getUser() + ",'"
                    + rating.getMovie() + ");";
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void deleteRating(Rating rating) throws IOException
    {

        try (Connection con = dcp.getConnection())
        {
            Statement statement = con.createStatement();
            String sql = "DELETE FROM Rating WHERE movieId =" 
                    + rating.getMovie() + "AND " 
                    + "userId =" + rating.getUser() + ";";
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Rating> getAllRatings() throws IOException
    {

        List<Rating> ratings = new ArrayList();
        try (Connection con = dcp.getConnection())
        {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM Rating;";
            statement.execute(sql);
            ResultSet r = statement.getResultSet();
            while (r.next())
            {
                Rating rt = new Rating(r.getInt("movieId"), r.getInt("userId"), r.getInt("rating"));
                ratings.add(rt);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ratings;
    }

    @Override
    public List<Rating> getRatings(User user) throws IOException
    {

        
        
        List<Rating> Ratings = new ArrayList();
        try (Connection con = dcp.getConnection())
        {
            Statement statement = con.createStatement();
           
            String sql = "SELECT FROM Rating WHERE userId = " + user + ";";
            statement.execute(sql);
            ResultSet r = statement.getResultSet();
            
            while (r.next())
            {
                Rating rt = new Rating(r.getInt("movieId"), r.getInt("userId"), r.getInt("rating"));
                Ratings.add(rt);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return Ratings;
    }


    @Override
    public void updateRating(Rating rating) throws IOException
    {


        try (Connection con = dcp.getConnection())
        {
            Statement statement = con.createStatement();
           
            String sql = "UPDATE FROM Rating SET rating = "+ rating.getRating() 
                    + "WHERE movieId =" + rating.getMovie() 
                    + "AND " + "userId =" + rating.getUser() + ";";
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
    
    
}
