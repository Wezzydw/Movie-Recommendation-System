/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package movierecsys.bll;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import movierecsys.be.Movie;
import movierecsys.be.Rating;
import movierecsys.be.User;
import movierecsys.dal.IMovieRepository;
import movierecsys.dal.MovieDAODB;

/**
 *
 * @author pgn
 */
public class MRSManager implements MRSOwsLogicFacade {
    
    IMovieRepository mDAODB = new MovieDAODB();
    @Override
    public List<Rating> getRecommendedMovies(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getAllTimeTopRatedMovies()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> getMovieReccomendations(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Movie> searchMovies(String query)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Movie createMovie(int year, String title)
    {
        try
        {
            return mDAODB.createMovie(year, title);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void updateMovie(Movie movie)
    {
        try
        {
            mDAODB.updateMovie(movie);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteMovie(Movie movie)
    {
        try
        {
            mDAODB.deleteMovie(movie);
        } catch (IOException ex)
        {
            Logger.getLogger(MRSManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rateMovie(Movie movie, User user, int rating)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User createNewUser(String name)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUserById(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllUsers()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
