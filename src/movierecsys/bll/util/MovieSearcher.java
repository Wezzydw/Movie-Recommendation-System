/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.bll.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import movierecsys.be.Movie;
import movierecsys.dal.MovieDAO;

/**
 *
 * @author pgn
 */
public class MovieSearcher
{

    public List<Movie> search(List<Movie> searchBase, String query)
    {
        
        List<Movie> empty = new ArrayList();
        
        if (!query.isEmpty())
        {
            for (Movie m : searchBase)
            {
                if (m.getTitle().toLowerCase().contains(query.toLowerCase()))
                    empty.add(m);
            }
            return empty;
        }
        return empty;
    }

}
