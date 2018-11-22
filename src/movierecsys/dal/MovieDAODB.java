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

/**
 *
 * @author Wezzy Laptop
 */
public class MovieDAODB implements IMovieRepository
{

    UandP up = new UandP();

    @Override
    public Movie createMovie(int releaseYear, String title) throws IOException
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
            for (Movie m : getAllMovies())
            {
                if (m.getId() != i)
                {
                    unUsedId = i;
                }
                i++;
            }
            String sql = "INSERT INTO Movie (id, name, title) VALUES("
                    + unUsedId + ","
                    + releaseYear + ",'"
                    + title.replace("'", "") + "');";
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        Movie movie = new Movie(unUsedId, releaseYear, title);
        return movie;
    }

    @Override
    public void deleteMovie(Movie movie) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            String sql = "DELETE FROM Movie WHERE id = " + movie.getId() + ";";
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Movie> getAllMovies() throws IOException
    {

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        List<Movie> movies = new ArrayList();
        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM MOVIE;";
            statement.execute(sql);
            ResultSet r = statement.getResultSet();
            while (r.next())
            {
                Movie m = new Movie(r.getInt("id"), r.getInt("year"), r.getString("title"));
                movies.add(m);
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public Movie getMovie(int id) throws IOException
    {
        Movie m = null;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
           
            String sql = "SELECT FROM Movie WHERE id = " + id + ";";
            statement.execute(sql);
            ResultSet r = statement.getResultSet();
            m = new Movie(r.getInt("id"), r.getInt("year"), r.getString("title"));

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return m;
    }

    @Override
    public void updateMovie(Movie movie) throws IOException
    {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
           
            String sql = "UPDATE Movie SET year = "
                    + movie.getYear() + ", title = '"
                    + movie.getTitle().replace("'", "") + "' WHERE id = " + movie.getId() + ";";
            statement.execute(sql);

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }

    }

}
