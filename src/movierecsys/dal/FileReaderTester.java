/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import java.io.IOException;
import java.util.List;
import movierecsys.be.Movie;

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
        MovieDAO movieDao = new MovieDAO();
        Movie movie = movieDao.createMovie(2020, "Tonny og Spasserbussen"); //Only run this once, or you will get multiple entries!
        System.out.println(movie);
        movieDao.createMovie(2008, "Dannys nye bil");
 
    }
}
