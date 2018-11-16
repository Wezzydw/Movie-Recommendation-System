/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.bll.util;

import java.io.IOException;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.dal.MovieDAO;

/**
 *
 * @author pgn
 */
public class MovieSearcher
{
    public List<Movie> search(List<Movie> searchBase, String query) throws IOException
    {
        MovieDAO md = new MovieDAO();
        List<Movie> movies = md.getAllMovies();
        return null;
    }
    
}
