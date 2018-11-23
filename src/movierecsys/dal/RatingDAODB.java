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
    
    UandP up = new UandP();
    
    
    @Override
    public void createRating(Rating rating) throws IOException
    {
        int unUsedId = 0;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        try (Connection con = ds.getConnection())
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
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            String sql = "DELETE FROM Rating WHERE movieId =" + rating.getMovie() + "AND " + "userId =" + rating.getUser() + ";";
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Rating> getAllRatings() throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        List<Rating> ratings = new ArrayList();
        try (Connection con = ds.getConnection())
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
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());
        
        
        List<Rating> Ratings = new ArrayList();
        try (Connection con = ds.getConnection())
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
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
           
            String sql = "UPDATE FROM Rating SET rating = "+ rating.getRating() + "WHERE movieId =" + rating.getMovie() + "AND " + "userId =" + rating.getUser() + ";";
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }
    
    
}
