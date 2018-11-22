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
public class MovieDAODB implements IMovieRepository {

    
    UandP up = new UandP();
    @Override
    public Movie createMovie(int releaseYear, String title) throws IOException {
        
        
        return null;
    }

    @Override
    public void deleteMovie(Movie movie) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllMovies() throws IOException {

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("MRSDATABASE");
        ds.setUser(up.getUser());
        ds.setPassword(up.getPw());

        List<Movie> movies = new ArrayList();
        try (Connection con = ds.getConnection()) {
            Statement statement = con.createStatement();
            String sql = "SELECT * FROM MOVIE;";
            statement.execute(sql);
            ResultSet r = statement.getResultSet();
            while(r.next())
            {
                Movie m = new Movie(r.getInt("id"),r.getInt("year"),r.getString("title"));
                movies.add(m);
            }
                   
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public Movie getMovie(int id) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateMovie(Movie movie) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
